package uk.ac.gcu.mondayschild.mondayschild;

import java.io.Serializable;

/**
 * Created by Ryan now redundant
 */
public class d2TournInfo implements Serializable{

    private int tournamentID;
    private String tournamentName;
    private String tournamentLogo;
    private String tournamentDate;
    private String tournamentFund;

    private static final long serialVersionUID = 0L;

    public int getTournamentID(){
        return tournamentID;
    }

    public void setTournamentID(int newTournamentID){
        this.tournamentID = newTournamentID;
    }

    public String getTournamentName(){
        return tournamentName;
    }

    public void setTournamentName(String newName){
        this.tournamentName = newName;
    }

    public String getTournamentLogo(){
        return tournamentLogo;
    }

    public void setTournamentLogo(String newLogo){
        this.tournamentLogo = newLogo;
    }

    public String getTournamentDate(){
        return tournamentDate;
    }

    public void setTournamentDate(String newDate){
        this.tournamentDate = newDate;
    }

    public String getTournamentFund(){
        return tournamentFund;
    }

    public void setTournamentFund(String newFund){this.tournamentFund = newFund;}
/*
    @Override
    public String toString(){
        String starSignData;
        starSignData = "mcStarSignsInfo [starSignID=" + starSignID;
        starSignData = ", starSign=" + starSign;
        starSignData = ", starSignImg=" + starSignImg;
        starSignData = ", starSignDates" + starSignDates;
        starSignData = ", starSignCharacteristics=" + starSignCharacteristics +"]";
        return starSignData;
    }*/
}