package uk.ac.gcu.mondayschild.mondayschild;

/**
 * Created by Ryan
 */

//Patch object
public class patch {
    public String title;
    public String desc;
    public String date;
    public patch(){
        super();
    }

    public patch(String title, String desc, String date) {
        super();
        this.title = title;
        this.desc = desc;
        this.date = date;
    }
}