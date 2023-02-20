package me.game.cs2340.demo;

import android.content.Context;

import androidx.core.content.ContextCompat;

public class Spell extends CircleEntity {
    public static final double SPEED_PIXELS_PER_SECOND = 800.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.getMaxUPS();

    public Spell(Context context, Player player) {
        super(context, ContextCompat.getColor(context, R.color.spell),
                player.getXPosition(), player.getYPosition(), 25);
        xVelocity = player.getXDirection() * MAX_SPEED;
        yVelocity = player.getYDirection() * MAX_SPEED;
    }
    @Override
    public void update() {
        xPosition += xVelocity;
        yPosition += yVelocity;
    }
}
