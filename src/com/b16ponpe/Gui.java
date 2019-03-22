package com.b16ponpe;

import javax.swing.*;
import java.awt.*;


public class Gui{

    public Gui(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);
        frame.setFocusable(true);
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.add(game);

        //Set gamescreen center of the window
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
        game.start();
    }
}
