package me.game.cs2340.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {
    private double xPosition;
    private double yPosition;
    private double radius;
    private Paint paint;

    public Player(Context context, double xPosition, double yPosition, double radius) {
        this.setPosition(xPosition, yPosition);
        this.radius = radius;

        this.paint = new Paint();
        this.paint.setColor(ContextCompat.getColor(context, R.color.player));
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) xPosition, (float) yPosition, (float) radius, paint);
    }

    public void update() {

    }

    public void setPosition(double xPosition, double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
