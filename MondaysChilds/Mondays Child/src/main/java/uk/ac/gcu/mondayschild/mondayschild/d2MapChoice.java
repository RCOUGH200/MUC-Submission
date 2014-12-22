package uk.ac.gcu.mondayschild.mondayschild;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.concurrent.ExecutionException;

/**
 * Created by Ryan
 */
public class d2MapChoice  extends MainActivity implements View.OnClickListener{

    Spinner tChoice;
    Button searchBtn;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d2_map_choice);
        //Get the button
        searchBtn = (Button)findViewById(R.id.submitBtn2);
        searchBtn.setOnClickListener(this);
        //Find the spinner
        tChoice = (Spinner) findViewById(R.id.mapSpin);
        //Set the spinner values to that of the tournaments string array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tournaments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tChoice.setAdapter(adapter);
    }
    public void onClick(View view)
    {
        //Create intent for map screen
        Intent d2MapScreen = new Intent(getApplicationContext(), mcMapActivity.class);
        //Send spinner choice with intent
        d2MapScreen.putExtra("choice", tChoice.getSelectedItem().toString());
        //Start the map
        startActivity(d2MapScreen);
        //Save the location
        mcSDPrefs.savePreferences("d2_Location", tChoice.getSelectedItem().toString());
    }
}
