package com.b16ponpe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private Handler handler;
    private Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {

        //Go to menu
        if (e.getKeyCode() == KeyEvent.VK_M) {
            game.gameState = Game.STATE.Menu;

        }


        //Quit program
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }

        //Keys for player1
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            handler.decreasePlayer1(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            handler.increasePlayer1(true);
        }

        //Keys for player2
        if (e.getKeyCode() == KeyEvent.VK_W){
            handler.decreasePlayer2(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_E){
            handler.increasePlayer2(true);

        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {

        //Keys for player1
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            handler.increasePlayer1(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            handler.decreasePlayer1(false);
        }

        //Keys for player2
        if (e.getKeyCode() == KeyEvent.VK_W){
            handler.decreasePlayer2(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_E){
            handler.increasePlayer2(false);
        }
    }
}
