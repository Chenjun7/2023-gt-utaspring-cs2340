package me.game.cs2340.demo;

import android.content.Context;

import androidx.core.content.ContextCompat;

public class Enemy extends CircleEntity {
    private static final double SPEED_PIXELS_PER_SECOND = Player.getSpeedPixelsPerSecond() * 0.5;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.getMaxUPS();
    private static final double SPAWNS_PER_MINUTE = 50;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.getMaxUPS() / SPAWNS_PER_SECOND;
    private static double updatesTillNextSpawn;
    private Player player;

    public Enemy(Context context, Player player) {
        super(context, ContextCompat.getColor(context, R.color.enemy),
                Math.random() * 1000, Math.random() * 1000, 30);
        this.player = player;
        updatesTillNextSpawn = UPDATES_PER_SPAWN;
    }

    public static boolean spawnReady() {
        if (updatesTillNextSpawn <= 0) {
            updatesTillNextSpawn += UPDATES_PER_SPAWN;
            return true;
        } else {
            updatesTillNextSpawn -= 1;
            return false;
        }
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

        xPosition += xVelocity;
        yPosition += yVelocity;
    }
}
