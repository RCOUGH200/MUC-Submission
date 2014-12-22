package uk.ac.gcu.mondayschild.mondayschild;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Ryan
 */
public class d2SaveOutput extends Activity implements View.OnClickListener {

    SharedPreferences mcSharedPrefs;
    Button btnBack;
    TextView d2Display;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d2_save);
       // btnBack = (Button) findViewById(R.id.btnBack);
       // btnBack.setOnClickListener(this);
        d2Display = (TextView)findViewById(R.id.save);
        mcSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        loadSavedPreferences();
        Log.e("n", "SDOutput msg");
    }

    private void loadSavedPreferences()
    {
        d2Display.setText(mcSharedPrefs.getString("d2_Location", "The International"));
    }

    public void onClick(View view)
    {
        setResult(Activity.RESULT_OK);
        finish();
    }
}