package uk.ac.gcu.mondayschild.mondayschild;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by owner on 06/11/2014.
 */
public class mcMapActivity extends FragmentActivity {

    mcMapData mapData;
    private Marker mapDataMarker;
    private GoogleMap mapTournament;
    private float markerColour = 210.0f;
    String tournName;
    private LatLng latlangCentre;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.mc_map_view);
        //Get the intent and set the extra values from the previous page
        Intent intent = getIntent();
        tournName = intent.getStringExtra("choice");
        mcMapDataDBMgr mapDB = new mcMapDataDBMgr(this, "tournamentLoc.s3db", null, 1);
        try {
            mapDB.dbCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapData = mapDB.getMapEKFameEntry(tournName);
        Log.e("name", tournName);
        latlangCentre = new LatLng(mapData.getLatitude(),mapData.getLongitude());
        SetUpMap();
        AddMarker();

    }

    public void SetUpMap()
    {
        mapTournament = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        if(mapTournament != null){
            mapTournament.moveCamera(CameraUpdateFactory.newLatLngZoom(latlangCentre, 12));
            mapTournament.setMyLocationEnabled(true);
            mapTournament.getUiSettings().setCompassEnabled(true);
            mapTournament.getUiSettings().setMyLocationButtonEnabled(true);
            mapTournament.getUiSettings().setRotateGesturesEnabled(true);
        }
    }

    public void AddMarker()
    {
        MarkerOptions marker;
        String mrkTitle;
        String mrkText;

        mrkTitle = "Tournament: " + mapData.getTournament();
        mrkText =  Float.toString(mapData.getLatitude()) + "-" + Float.toString(mapData.getLongitude());
        marker = SetMarker(mrkTitle,mrkText,new LatLng(mapData.getLatitude(), mapData.getLongitude()), markerColour, true);
        mapDataMarker = mapTournament.addMarker(marker);
    }

    public MarkerOptions SetMarker(String title, String snippet, LatLng position, float markerColour, boolean centreAnchor)
    {
        float anchorX;
        float anchorY;

        if(centreAnchor)
        {
            anchorX = 0.5f;
            anchorY = 0.5f;
        }
        else
        {
            anchorX = 0.5f;
            anchorY = 1f;
        }

        MarkerOptions marker = new MarkerOptions().title(title).snippet(snippet).icon(BitmapDescriptorFactory.defaultMarker(markerColour)).anchor(anchorX, anchorY).position(position);

        return marker;
    }
}
