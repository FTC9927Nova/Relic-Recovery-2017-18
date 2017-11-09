package org.firstinspires.ftc.teamcode.Util;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.media.MediaPlayer;

import org.firstinspires.ftc.teamcode.R;

/**
 * Created by Ethan Pereira on 11/9/2017.
 */

public class Sound extends Activity {
    final MediaPlayer mp = MediaPlayer.create(this, R.raw.nbig);


    public void startSound(){
        mp.stop();
        mp.start();
    }

    public void stopSound(){
        mp.stop();
    }
}
