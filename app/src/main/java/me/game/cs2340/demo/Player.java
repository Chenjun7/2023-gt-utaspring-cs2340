package me.game.cs2340.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player extends CircleEntity {
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.getMaxUPS();
    private Joystick joystick;

    public Player(Context context, Joystick joystick, double xPosition, double yPosition, double radius) {
        super(context, ContextCompat.getColor(context, R.color.player), xPosition, yPosition, radius);
        this.joystick = joystick;
    }

    public void update() {
        xVelocity = joystick.getActuatorX() * MAX_SPEED;
        yVelocity = joystick.getActuatorY() * MAX_SPEED;

        xPosition += xVelocity;
        yPosition += yVelocity;

        if (xVelocity != 0 || yVelocity != 0) {
            double euclideanDistance = Utility.getEuclideanDistance(0, 0, xVelocity, yVelocity);
            xDirection = xVelocity / euclideanDistance;
            yDirection = yVelocity / euclideanDistance;
        }
    }

    public static double getSpeedPixelsPerSecond() {
        return SPEED_PIXELS_PER_SECOND;
    }
}
