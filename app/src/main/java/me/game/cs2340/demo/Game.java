package me.game.cs2340.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private final Player player;
    private List<Enemy> enemyList;
    private final Joystick joystick;
    public Game(Context context) {
        super(context);

        //Gets the surface holder and adds callback to game
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.gameLoop = new GameLoop(this, surfaceHolder);
        this.joystick = new Joystick(275, 700, 70, 40);
        this.player = new Player(getContext(), joystick, 1000, 500, 30);
        this.enemyList = new LinkedList<Enemy>();

        this.setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (joystick.isPressed((double) event.getX(), (double) event.getY())) {
                    joystick.setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick.getIsPressed()) {
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
                return true;
        }

        return super.onTouchEvent(event);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
        joystick.draw(canvas);
        player.draw(canvas);
        for (Enemy enemy : enemyList) {
            enemy.draw(canvas);
        }
    }

    public void drawUPS(Canvas canvas) {
        String updatesPerSecond = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        paint.setTextSize(50);
        canvas.drawText("UPS: " + updatesPerSecond, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String framesPerSecond = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        paint.setTextSize(50);
        canvas.drawText("FPS: " + framesPerSecond, 100, 200, paint);
    }

    public void update() {
        joystick.update();
        player.update();

        if(Enemy.spawnReady()) {
            enemyList.add(new Enemy(getContext(), player));
        }

        for (Enemy enemy : enemyList) {
            enemy.update();
        }

        Iterator<Enemy> enemyIterator = enemyList.iterator();
        while(enemyIterator.hasNext()) {
            if (CircleEntity.isColliding(enemyIterator.next(), player)) {
                enemyIterator.remove();
            }
        }
    }
}
