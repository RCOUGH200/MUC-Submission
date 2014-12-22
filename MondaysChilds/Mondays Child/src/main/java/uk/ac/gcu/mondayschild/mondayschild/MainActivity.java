package uk.ac.gcu.mondayschild.mondayschild;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.DatePicker;
import android.widget.ArrayAdapter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    //Values
    Spinner startDate;
    Spinner endDate;
    Button submitBtn;
    d2SaveTournament mcSDPrefs;
    SharedPreferences mySharedPrefs;
    FragmentManager fmAboutDialogue;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override

    public void onClick(View view)
    {
        //Create intent for the ouput screen
        Intent d2Tournament_Screen = new Intent(getApplicationContext(), d2TournOutput.class);
        //Send the new intent the values for the start and end month
        d2Tournament_Screen.putExtra("startMonth", startDate.getSelectedItem().toString());
        d2Tournament_Screen.putExtra("endMonth", endDate.getSelectedItem().toString());
        //Start the activity
        startActivity(d2Tournament_Screen);
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        //Find the button
        submitBtn = (Button)findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);
        //Set the defauly preferences
        mySharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mcSDPrefs = new d2SaveTournament(mySharedPrefs);
        mcSDPrefs.setDefaultPrefs();
        fmAboutDialogue = this.getFragmentManager();
        //Find the spinners
        startDate = (Spinner) findViewById(R.id.startDateSpin);
        endDate = (Spinner)findViewById(R.id.endDateSpin);
        //Set the spinner values to that of the months string array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startDate.setAdapter(adapter);
        endDate.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mc_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            //When an option is clicked
            case R.id.mMap:
                //Create intent and open
                Intent mcMap = new Intent(this, d2MapChoice.class);
                this.startActivity(mcMap);
                return true;
            case R.id.mBio:
                //Create intent and open
                Intent mcBioDraw = new Intent(this, mcBioActivity.class);
                this.startActivity(mcBioDraw);
                return true;
            case R.id.mPatch:
                //Create intent and open
                Intent d2Patch = new Intent(this, d2PatchOutput.class);
                this.startActivity(d2Patch);
                return true;
            case R.id.mTourn:
                //Create intent and open
                Intent main = new Intent(this, MainActivity.class);
                this.startActivity(main);
                return true;
            case R.id.mSave:
                //Create intent and open
                Intent save = new Intent(this, d2SaveOutput.class);
                this.startActivity(save);
                return true;
            case R.id.mQuit:
                //Create intent and open
                finish();
                return true;
            case R.id.mAbout:
                //Create intent and open
                DialogFragment mcAboutDlgs = new mcAboutDialogue();
                mcAboutDlgs.show(fmAboutDialogue,"mc_About_Dlg");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
