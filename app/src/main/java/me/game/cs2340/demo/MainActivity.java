package me.game.cs2340.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * MainActivity is the entry point to the game.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Sets flags to full screen mode
        Window applicationWindow = this.getWindow();
        applicationWindow.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        //Set content view to game
        this.setContentView(new Game(this));
    }
}
