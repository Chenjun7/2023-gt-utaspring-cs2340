package me.game.cs2340.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private final Player player;
    private final Joystick joystick;
    public Game(Context context) {
        super(context);

        //Gets the surface holder and adds callback to game
        SurfaceHolder surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);

        this.gameLoop = new GameLoop(this, surfaceHolder);
        this.player = new Player(this.getContext(), 1000, 500, 30);
        this.joystick = new Joystick(275, 700, 70, 40);

        this.setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.gameLoop.startLoop();
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
            case MotionEvent.ACTION_MOVE:
                if (this.joystick.isPressed((double) event.getY(), (double) event.getY())) {
                    this.joystick.setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_UP:
                this.joystick.setIsPressed(false);
                this.joystick.resetActuator();
                return true;
        }

        return super.onTouchEvent(event);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.drawUPS(canvas);
        this.drawFPS(canvas);
        this.player.draw(canvas);
        this.joystick.draw(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String updatesPerSecond = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(this.getContext(), R.color.white));
        paint.setTextSize(50);
        canvas.drawText("UPS: " + updatesPerSecond, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String framesPerSecond = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(this.getContext(), R.color.white));
        paint.setTextSize(50);
        canvas.drawText("FPS: " + framesPerSecond, 100, 200, paint);
    }

    public void update() {
        this.joystick.update();
        this.player.update(joystick);
    }
}
