package com.b16ponpe;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Player extends GameObject {

    private int speed = 2;
    private Game game;

    private double angle = 0;
    private int length = 0;
    private Random rand = new Random();
    private int gap = 0;
    private boolean gameOver = false;
    private String playerWon = "";

    private ArrayList<GameObject> bodyParts = new ArrayList<>();

    public Player(double x, double y, ID idPlayer, Game game) {
        super(x, y, idPlayer);
        this.game = game;

        bodyParts.add(new PlayerHead(x, y, idPlayer));
    }

    //Check if player collide with own body
    private void collide(GameObject obj1, GameObject obj2) {

        double dy = obj2.getY() - obj1.getY();
        double dx = obj2.getX() - obj1.getX();

        if (dx * dx + dy * dy <= 4*4) {

            printCorrectPlayer(getIdPlayer());
            gameOver = true;
            game.gameState = Game.STATE.Collide;

        }
    }

    private void printCorrectPlayer(ID id){
        if (id.equals(ID.Player1)){
            playerWon = "Green won";

        }else{
            playerWon = "Red won";
        }
    }

    public void tick() {

        //Check if player is outside screen
        if (bodyParts.get(0).getX() > game.WIDTH - 10 || bodyParts.get(0).getX() < 0){
            printCorrectPlayer(getIdPlayer());
            gameOver = true;

            game.gameState = Game.STATE.Collide;
        }else if(bodyParts.get(0).getY() > game.HEIGHT - 40 || bodyParts.get(0).getY() < 0){
            printCorrectPlayer(getIdPlayer());
            gameOver = true;

            game.gameState = Game.STATE.Collide;
        }

        length += speed;

        //Make gap
        if (rand.nextInt(120) == 0 ){
            gap = 10;
        }
        if (gap > 0) {
            gap--;
        }else {
            if (length % 3 == 0) {
                bodyParts.add(new Body(x, y, getIdPlayer()));
            }
        }


        //turn left
        if (decrease) {
            angle -= (2 * Math.PI / 360) * speed;
        }

        //turn right
        if (increase) {
            angle += (2 * Math.PI / 360) * speed;
        }

        x += Math.cos(angle);
        y += Math.sin(angle);

        bodyParts.get(0).setX(x);
        bodyParts.get(0).setY(y);
        bodyParts.get(0).tick();


        //Collide, send playersHead and body to collide function
        for (int i = 1; i < bodyParts.size() - 4; i++) {
            collide(bodyParts.get(0), bodyParts.get(i));
        }
    }

    //Made this method so handler can fetch the arraylist and collide between players
    public ArrayList<GameObject> getBodyParts() {
        return bodyParts;
    }

    //render bodyparts start whit the head
    public void render(Graphics2D g2) {

        for (int i = bodyParts.size() - 1; i >= 0; i--) {
            bodyParts.get(i).render(g2);
        }

        if (gameOver) {
            g2.setFont(new Font("", Font.BOLD, 60));
            g2.setColor(Color.WHITE);
            g2.drawString(playerWon, 300, 200);

        }
    }
}
