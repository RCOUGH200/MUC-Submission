package uk.ac.gcu.mondayschild.mondayschild;

import java.io.Serializable;

/**
 * Created Ryan
 */
public class mcMapData implements Serializable{

// *********************************************
// Declare variables etc.
// *********************************************

    private int entryID;
    private String Tournament;
    private float Latitude;
    private float Longitude;

    private static final long serialVersionUID = 0L;

// *********************************************
// Declare getters and setters etc.
// *********************************************


    public int getEntryID() {
        return entryID;
    }

    public void setEntryID(int entryID) {
        this.entryID = entryID;
    }

    public String getTournament() {
        return Tournament;
    }

    public void setTournament(String tournament) {this.Tournament = tournament;}

    public float getLatitude()
    {
        return Latitude;
    }

    public void setLatitude(float Lat)
    {
        this.Latitude = Lat;
    }

    public float getLongitude()
    {
        return Longitude;
    }

    public void setLongitude(float fLongitude)
    {
        this.Longitude = fLongitude;
    }

    @Override
    public String toString() {
        String mapData;
        mapData = "d2TournamentInfo [entryID=" + entryID;
        mapData = ", Tournament=" + Tournament;
        mapData = ", Latitude=" + Latitude;
        mapData = ", Longitude=" + Longitude +"]";
        return mapData;
    }

}