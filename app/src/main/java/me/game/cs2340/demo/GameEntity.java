package me.game.cs2340.demo;

import android.graphics.Canvas;

public abstract class GameEntity {
    protected double xPosition;
    protected double yPosition;
    protected double xVelocity;
    protected double yVelocity;
    protected double xDirection;
    protected double yDirection;

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

    public double getXDirection() {
        return xDirection;
    }

    public double getYDirection() {
        return yDirection;
    }

    public abstract void draw(Canvas canvas);
    public abstract void update();

    public static double getEntityDistance(GameEntity obj1, GameEntity obj2) {
        return Utility.getEuclideanDistance(obj1.getXPosition(), obj1.getYPosition(), obj2.getXPosition(), obj2.getYPosition());
    }
}
