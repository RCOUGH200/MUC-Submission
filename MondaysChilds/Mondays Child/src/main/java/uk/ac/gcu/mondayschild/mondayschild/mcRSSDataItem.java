package uk.ac.gcu.mondayschild.mondayschild;

import java.io.Serializable;

/**
 * Created by owner on 23/10/2014.
 */
public class mcRSSDataItem implements Serializable {

    private String itemTitle;
    private String itemDesc;
    private String itemDate;

    public String getItemTitle()
    {
        return this.itemTitle;
    }

    public void setItemTitle(String sItemTitle)
    {
        this.itemTitle = sItemTitle;
    }

    public String getItemDesc()
    {
        return this.itemDesc;
    }

    public void setItemDesc(String sItemDesc)
    {
        this.itemDesc = sItemDesc;
    }

    public String getItemDate(){return this.itemDate; }

    public void setItemDate(String sItemDate){this.itemDate = sItemDate; }

    public mcRSSDataItem()
    {
        this.itemTitle = "";
        this.itemDesc = "";
        this.itemDate = "";
    }

    @Override
    public String toString(){
        String starSignRSSHoroscopeData;
        starSignRSSHoroscopeData = "mcRSSDataItem [itemTitle=" + itemTitle;
        starSignRSSHoroscopeData = ", itemDesc=" + itemDesc;
        return starSignRSSHoroscopeData;
    }
}
