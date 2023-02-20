package me.game.cs2340.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class CircleEntity extends GameEntity {
    protected double radius;
    protected Paint paint;

    public CircleEntity(Context context, int color, double xPosition, double yPosition, double radius) {
        super(xPosition, yPosition);
        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) xPosition, (float) yPosition, (float) radius, paint);
    }
}
