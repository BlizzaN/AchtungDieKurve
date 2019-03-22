package com.b16ponpe;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Handler {

    private Player player1 = null;
    private Player player2 = null;
    private Game game;
    private Random rand = new Random();

    private String drawOrWon = "";
    private boolean gameOver = false;

    private ArrayList<Player> players = new ArrayList<Player>();


    public Handler(Game game) {
        this.game = game;
        initilizePlayer();

    }


    //Create players
    public void initilizePlayer() {

        gameOver = false;

        player1 = new Player(rand.nextInt(400 - 100) + 100, rand.nextInt(400 - 100) + 100, ID.Player1, game);
        player2 = new Player(rand.nextInt(400 - 100) + 100, rand.nextInt(400 - 100) + 100, ID.Player2, game);

        players.clear();
        players.add(player1);
        players.add(player2);
    }

    //Check if players collide with each other, head against tail
    private void collide(GameObject obj1, GameObject obj2) {
        double dy = obj2.getY() - obj1.getY();
        double dx = obj2.getX() - obj1.getX();

        if (dx * dx + dy * dy <= 4 * 4) {
            game.gameState = Game.STATE.Collide;
            gameOver = true;
            printCorrectPlayer(obj1.getIdPlayer());

        }
    }

    //Check who won the game
    private void printCorrectPlayer(ID id){
        if (id.equals(ID.Player1)){
            drawOrWon = "Red won";

        }else{
            drawOrWon = "Green won";
        }
    }

    //Check if player heads collide with each other, head against head
    // This is to determine draw
    // Use this ONLY for heads
    private void collideHead(GameObject obj1, GameObject obj2) {
    double dy = obj2.getY() - obj1.getY();
    double dx = obj2.getX() - obj1.getX();

        if (dx * dx + dy * dy <= 4 * 4) {
            game.gameState = Game.STATE.Collide;


            gameOver = true;
            drawOrWon = "Draw";

        }
    }

    //Collision detection
    private void collideBetweenPlayers() {

        for (int head = 0; head < players.size(); head++) {        //head is coming from here
            for (int body = 0; body < players.size(); body++) {    // body is coming from here
                if (head != body) {                             // no check against own

                    for (int bodyPartIndex = 0; bodyPartIndex < players.get(body).getBodyParts().size(); bodyPartIndex++) {
                        if(bodyPartIndex == 0) {    // it is a head
                            //Send only players head to this method
                            collideHead(players.get(head).getBodyParts().get(0), players.get(body).getBodyParts().get(bodyPartIndex));
                        }
                        else{                       // not a head
                            //Send players body and head to check if players collide with each other
                            collide(players.get(body).getBodyParts().get(bodyPartIndex),players.get(head).getBodyParts().get(0));
                        }
                    }
                }
            }
        }

    }




    //Player 1, increase or decrease the curve
    public void increasePlayer1(boolean increase) {
        player1.setIncrease(increase);
    }

    public void decreasePlayer1(boolean decrease) {
        player1.setDecrease(decrease);
    }

    //Player 2, increase or decrease the curve
    public void increasePlayer2(boolean increase) {
        player2.setIncrease(increase);
    }

    public void decreasePlayer2(boolean decrease) {
        player2.setDecrease(decrease);
    }

    public void tick() {
        player1.tick();
        player2.tick();
        collideBetweenPlayers();
    }

    //Render player and render if gameOvers boolean value has changed
    public void render(Graphics2D g2) {
        player1.render(g2);
        player2.render(g2);

        if (gameOver) {
            g2.setFont(new Font("", Font.BOLD, 60));
            g2.setColor(Color.WHITE);
            g2.drawString(drawOrWon, 300, 200);
        }

    }
}
