package uk.ac.gcu.mondayschild.mondayschild;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.List;

import java.util.concurrent.ExecutionException;
/**
 * Created by Ryan
 */
public class d2PatchOutput extends MainActivity implements View.OnClickListener{

    ArrayAdapter<String> adapter;
    private ListView listView1;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patch_output);
        //Parse RSS feed
        mcRSSDataItem userHoroscope = new mcRSSDataItem();
        String RSSFeedURL = "http://blog.dota2.com/feed/";
        mcAsyncRSSParser rssAsyncParse = new mcAsyncRSSParser(this, RSSFeedURL);
        try {
            userHoroscope = rssAsyncParse.execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Seperate feed into string arrays
        String[] descArray = userHoroscope.getItemDesc().split("~split~");
        String[] titleArray = userHoroscope.getItemTitle().split("~split~");
        String[] dateArray = userHoroscope.getItemDate().split("~split~");
        patch patch_data[] = new patch[descArray.length ];
        //Create patch objects with values
        for(int i = 0; i < descArray.length; i ++) {
            patch tempPatch = new patch(titleArray[i], descArray[i], dateArray[i]);
            patch_data[i] = tempPatch;
        }
        //Create patch adapter
        patchAdapter adapter = new patchAdapter(this,
                R.layout.patch_item, patch_data);

        //Fill listview with patches
        listView1 = (ListView)findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
    }
}
