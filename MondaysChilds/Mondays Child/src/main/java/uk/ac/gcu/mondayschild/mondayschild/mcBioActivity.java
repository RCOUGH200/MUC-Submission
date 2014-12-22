package uk.ac.gcu.mondayschild.mondayschild;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by owner on 30/10/2014.
 */
public class mcBioActivity extends Activity
{
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_bio_draw_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(new mcBiorhythmsSurfaceView(this));
    }
}
