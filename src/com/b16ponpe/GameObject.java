package com.b16ponpe;

import java.awt.*;


public abstract class GameObject {

    protected double x, y;
    protected boolean increase, decrease;
    protected ID idPlayer;

    public abstract void tick();
    public abstract void render(Graphics2D g2);


    public GameObject(double x, double y, ID idPlayer){
        this.x = x;
        this.y = y;
        this.idPlayer = idPlayer;

    }

    public ID getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(ID idPlayer) {
        this.idPlayer = idPlayer;
    }

    public boolean isIncrease() {
        return increase;
    }

    public void setIncrease(boolean incerease) {
        this.increase = incerease;
    }

    public boolean isDecrease() {
        return decrease;
    }

    public void setDecrease(boolean decrease) {
        this.decrease = decrease;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
