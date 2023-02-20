package me.game.cs2340.demo;

import android.graphics.Canvas;

public abstract class GameEntity {
    protected double xPosition;
    protected double yPosition;
    protected double xVelocity;
    protected double yVelocity;

    public GameEntity(double xPosition, double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public double getXPosition() {
        return xPosition;
    }
    public double getYPosition() {
        return yPosition;
    }

    public abstract void draw(Canvas canvas);
    public abstract void update();

    public static double getEntityDistance(GameEntity obj1, GameEntity obj2) {
        return Math.sqrt(
                Math.pow(obj2.getXPosition() - obj1.getXPosition(), 2) +
                        Math.pow(obj2.getYPosition() - obj1.getYPosition(), 2)
        );
    }
}
