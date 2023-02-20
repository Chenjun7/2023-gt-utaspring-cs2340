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

    public static boolean isColliding(CircleEntity obj1, CircleEntity obj2) {
        return getEntityDistance(obj1, obj2) < obj1.getRadius() + obj2.getRadius();
    }
    public void draw(Canvas canvas) {
        canvas.drawCircle((float) xPosition, (float) yPosition, (float) radius, paint);
    }

    public double getRadius() {
        return radius;
    }
}
