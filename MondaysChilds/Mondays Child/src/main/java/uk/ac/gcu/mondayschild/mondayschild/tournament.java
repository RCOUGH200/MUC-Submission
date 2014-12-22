package uk.ac.gcu.mondayschild.mondayschild;

/**
 * Created by Ryan
 */

//tournament object
public class tournament{
    public String title;
    public int icon;
    public String date;
    public String fund;
    public tournament(){
        super();
    }

    public tournament(String title, int icon, String date, String fund) {
        super();
        this.title = title;
        this.icon = icon;
        this.date = date;
        this.fund = fund;
    }
}