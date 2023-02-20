package me.game.cs2340.demo;

import android.content.Context;

import androidx.core.content.ContextCompat;

public class Enemy extends CircleEntity {
    private static final double SPEED_PIXELS_PER_SECOND = Player.getSpeedPixelsPerSecond();
    private static final double MAX_SPEED = Player.getSpeedPixelsPerSecond() / GameLoop.getMaxUPS();
    private Player player;

    public Enemy(Context context, Player player, double xPosition, double yPosition, double radius) {
        super(context, ContextCompat.getColor(context, R.color.enemy), xPosition, yPosition, radius);
        this.player = player;
    }
    @Override
    public void update() {
        double xDistanceToPlayer = player.getXPosition() - xPosition;
        double yDistanceToPlayer = player.getYPosition() - yPosition;

        double distanceToPlayer = GameEntity.getEntityDistance(this, player);

        double xDirection = xDistanceToPlayer / distanceToPlayer;
        double yDirection = yDistanceToPlayer / distanceToPlayer;

        if(distanceToPlayer > 0) {
            xVelocity = xDirection * MAX_SPEED;
            yVelocity = yDirection * MAX_SPEED;
        } else {
            xVelocity = 0;
            yVelocity = 0;
        }

        // =========================================================================================
        //   Update position of the enemy
        // =========================================================================================
        xPosition += xVelocity;
        yPosition += yVelocity;
    }
}
