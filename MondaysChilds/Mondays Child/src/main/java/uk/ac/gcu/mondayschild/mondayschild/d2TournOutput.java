package uk.ac.gcu.mondayschild.mondayschild;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ryan
 */
public class d2TournOutput extends MainActivity implements View.OnClickListener{

    ArrayAdapter<String> adapter;
    private ListView listView2;
    String startMonth = "January";
    String endMonth = "February";

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tourn_output);

        //Get the intent and set the extra values from the previous page
        Intent intent = getIntent();
        startMonth = intent.getStringExtra("startMonth");
        endMonth = intent.getStringExtra("endMonth");

        //Create the database
        d2TournamentInfoDBMgr d2TournMgr = new d2TournamentInfoDBMgr(this, "tournaments.s3db",null, 1);
        try{
            d2TournMgr.dbCreate();
        } catch (IOException e){
            e.printStackTrace();
        }
        //Get all tournament objects
        d2TournInfo[] allTournaments = d2TournMgr.getAll();

        //Start and end positions for filter
        int start = -1;
        int end = -1;
        //Start at the beginning to find the end month (Descending order)
        int index = 0;
        while (start == -1 && index < allTournaments.length) {
            if(allTournaments[index].getTournamentDate().equals(endMonth)){
                start = index;
            }
            //Go up one
            index++;
        }
        //Start at end to find the start month (Descending order)
        index = allTournaments.length-1;
        while (end == -1 && index > -1) {
            if(allTournaments[index].getTournamentDate().equals(startMonth)){
                end = index;
            }
            //Move down one
            index--;
        }
        //Catch any number errors and display all
        if(start == -1 || end == -1 || start>end) {
            start = 0;
            end = allTournaments.length-1;
            Toast.makeText(this, "Error, showing all.", Toast.LENGTH_SHORT).show();
        }

        int filteredLength = (end-start)+1;

        tournament tourn_data[] = new tournament[filteredLength];
        for(int i = start; i <= end; i ++) {
            //Convert the string name of the image to the int resource
            Resources res = getResources();
            int resID = res.getIdentifier(allTournaments[i].getTournamentLogo() , "drawable", "uk.ac.gcu.mondayschild.mondayschild");
            //Created a new tournament object
            tournament tempTourn = new tournament(allTournaments[i].getTournamentName(), resID,
                    allTournaments[i].getTournamentDate(), allTournaments[i].getTournamentFund());
            //add to array
            tourn_data[i-start] = tempTourn;
        }

        //Set the adapter
        tournAdapter adapter = new tournAdapter(this,
                R.layout.tournamenttem, tourn_data);

        //Find listview and populate with tournaments
        listView2 = (ListView)findViewById(R.id.listView2);
        listView2.setAdapter(adapter);
    }
}