package me.game.cs2340.demo;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{
    private Game game;
    private SurfaceHolder surfaceHolder;

    private boolean isRunning;
    private double averageUPS;
    private double averageFPS;

    private static final double MAX_UPS = 30.0;
    private static final double UPS_PERIOD = 1000 / MAX_UPS;
    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;

        isRunning = false;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageUPS;
    }

    public void startLoop() {
        isRunning = true;
        super.start();
    }

    public static double getMaxUPS() {
        return MAX_UPS;
    }
    @Override
    public void run() {
        super.run();

        int updatesCounter = 0;
        int framesCounter = 0;

        long startTime, elapsedTime, sleepTime;

        Canvas canvas = null;

        startTime = System.currentTimeMillis();

        while(isRunning) {
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (this.surfaceHolder) {
                    this.game.update();
                    updatesCounter += 1;
                    this.game.draw(canvas);
                }
            } catch (IllegalArgumentException e) {
                System.err.println(e.getStackTrace());
            } finally {
                if (canvas != null) {
                    try {
                        this.surfaceHolder.unlockCanvasAndPost(canvas);
                        framesCounter += 1;
                    } catch (Exception e) {
                        System.err.println(e.getStackTrace());
                    }
                }
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updatesCounter * UPS_PERIOD - elapsedTime);
            if (sleepTime > 0) {
                try {
                    this.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.err.println(e.getStackTrace());
                }
            }

            while (sleepTime < 0 && updatesCounter < MAX_UPS - 1) {
                game.update();
                updatesCounter += 1;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updatesCounter * UPS_PERIOD - elapsedTime);
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageUPS = updatesCounter / (1000 * elapsedTime);
                averageFPS = framesCounter / (1000 * elapsedTime);
                updatesCounter = 0;
                framesCounter = 0;
                startTime = System.currentTimeMillis();
            }
        }

    }
}
