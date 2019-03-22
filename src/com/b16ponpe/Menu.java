package com.b16ponpe;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;

    Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }


    //Check where mouse is pressed
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();


        if (game.gameState == Game.STATE.Menu) {
            handler.initilizePlayer();

            if (mouseOver(mouseX, mouseY, 300, 200, 300, 80)) {
                //Play button
                game.gameState = Game.STATE.Game;

            } else if (mouseOver(mouseX, mouseY, 300, 300, 300, 80)) {
                //Settings button
                game.gameState = Game.STATE.Settings;

            } else if (mouseOver(mouseX, mouseY, 300, 400, 300, 80)) {
                //Quit
                System.exit(0);
            }
        }

        if (game.gameState == Game.STATE.Settings) {

            if (mouseOver(mouseX, mouseY, 270, 440, 300, 80)) {

                //Back button
                game.gameState = Game.STATE.Menu;
            }
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    //Check position of mousearrow
    private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        if (mouseX > x && mouseX < x + width) {
            if (mouseY > y && mouseY < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void tick() {

    }

    //Render buttons
    public void render(Graphics g) {
        Font font = new Font("arial", 1, 50);

        if (game.gameState == Game.STATE.Menu) {

            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Achtung Die Kurve", 220, 100);

            //Font for play
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Play", 400, 260);

            //Play button
            g.setColor(Color.WHITE);
            g.drawRect(300, 200, 300, 80);

            //Font for settings
            g.setColor(Color.WHITE);
            g.drawString("Settings", 350, 360);

            //Setting button
            g.setColor(Color.WHITE);
            g.drawRect(300, 300, 300, 80);

            //For for quiting
            g.setColor(Color.WHITE);
            g.drawString("Quit", 400, 460);

            //Quiting button
            g.setColor(Color.WHITE);
            g.drawRect(300, 400, 300, 80);

        } else if (game.gameState == Game.STATE.Settings) {

            Font fontText = new Font("arial", 1, 20);

            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Settings", 350, 100);

            g.setFont(fontText);
            g.drawString("Player one with red snake uses arrowkeys < >.", 240, 250);
            g.drawString("Player two with green snake uses arrowkeys w e.", 240, 280);
            g.drawString("Press m for menu.", 240, 310);
            g.drawString("Press esc for quit.", 240, 340);

            //Font for back button
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Back", 390, 500);

            //Back button
            g.setColor(Color.WHITE);
            g.drawRect(300, 440, 300, 80);

        }
    }
}
