package com.b16ponpe;


import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Body extends GameObject {

    public Body(double x, double y, ID idPlayer) {
        super(x, y, idPlayer);
    }

    public void tick() {}

    //Render both players body
    public void render(Graphics2D g2) {
        if (idPlayer == ID.Player1) {
            Ellipse2D circle = new Ellipse2D.Double(x, y, Game.PLAYERSIZE, Game.PLAYERSIZE);
            g2.setPaint(new Color(255, 4, 32));
            g2.fill(circle);
        }
        if (idPlayer == ID.Player2){
            Ellipse2D circle = new Ellipse2D.Double(x, y, Game.PLAYERSIZE, Game.PLAYERSIZE);
            g2.setPaint(new Color(16, 255, 0));
            g2.fill(circle);
        }
    }
}
