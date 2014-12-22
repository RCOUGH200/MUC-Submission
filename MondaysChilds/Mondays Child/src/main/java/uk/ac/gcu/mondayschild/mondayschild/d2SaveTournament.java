package uk.ac.gcu.mondayschild.mondayschild;

/**
 * Created by Ryan
 */

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class d2SaveTournament extends Activity {

    SharedPreferences mcSharedPrefs;
    private String d2Location;

    private void setLocation(String newLocation)
    {
        this.d2Location = newLocation;
    }

    public String getmcSDDayBorn()
    {
        return d2Location;
    }

    public d2SaveTournament(SharedPreferences mcSDPrefs)
    {
        setLocation("The International");
        try
        {
            this.mcSharedPrefs = mcSDPrefs;
        }
        catch (Exception e)
        {
            Log.e("n", "Pref Manager is NULL");
        }
        setDefaultPrefs();
    }

    //Save boolean preferences
    public void savePreferences(String key, boolean value)
    {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    //Save String preferences
    public void savePreferences(String key, String value)
    {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //Save Int  preferences
    public void savePreferences(String key, int value)
    {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    //Preferences created by the main activity
    public void setDefaultPrefs()
    {
        savePreferences("d2_Location", "N/A");
    }
}