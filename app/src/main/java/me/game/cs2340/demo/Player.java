package me.game.cs2340.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.getMaxUPS();
    private double xPosition;
    private double yPosition;
    private double radius;
    private Paint paint;
    private double xVelocity;
    private double yVelocity;

    public Player(Context context, double xPosition, double yPosition, double radius) {
        this.setPosition(xPosition, yPosition);
        this.radius = radius;

        this.paint = new Paint();
        this.paint.setColor(ContextCompat.getColor(context, R.color.player));

    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) xPosition, (float) yPosition, (float) radius, paint);
    }

    public void update(Joystick joystick) {
        xVelocity = joystick.getActuatorX() * MAX_SPEED;
        yVelocity = joystick.getActuatorY() * MAX_SPEED;

        xPosition += xVelocity;
        yPosition += yVelocity;
    }

    public void setPosition(double xPosition, double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
