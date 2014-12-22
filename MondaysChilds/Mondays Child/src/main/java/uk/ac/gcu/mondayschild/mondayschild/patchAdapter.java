package uk.ac.gcu.mondayschild.mondayschild;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;


/**
 * Created by Ryan
 */

public class patchAdapter extends ArrayAdapter<patch>{

    Context context;
    int layoutResourceId;
    patch data[] = null;

    public patchAdapter(Context context, int layoutResourceId, patch[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        patchHolder holder = null;

        if(row == null)
        {
            //Inflate the listview
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new patchHolder();
            //Get the views
            holder.txtTitle = (TextView)row.findViewById(R.id.patchTitle);
            holder.txtDesc = (TextView)row.findViewById(R.id.patchDesc);
            holder.txtDate = (TextView)row.findViewById(R.id.patchDate);

            row.setTag(holder);
        }
        else
        {
            holder = (patchHolder)row.getTag();
        }
        //Sets the views to the object value
        patch aPatch = data[position];
        holder.txtTitle.setText(aPatch.title);
        holder.txtDesc.setText(aPatch.desc);
        holder.txtDate.setText(aPatch.date);

        return row;
    }
    //Holds the views
    static class patchHolder
    {
        TextView txtTitle;
        TextView txtDesc;
        TextView txtDate;
    }
}