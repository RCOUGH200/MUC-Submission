package uk.ac.gcu.mondayschild.mondayschild;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ryan
 */
public class tournAdapter extends ArrayAdapter<tournament> {

    Context context;
    int layoutResourceId;
    tournament data[] = null;

    public tournAdapter(Context context, int layoutResourceId, tournament[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        tournHolder holder = null;

        if(row == null)
        {
            //Inflate the listview
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new tournHolder();
            //Get views
            holder.txtTitle = (TextView)row.findViewById(R.id.tournTitle);
            holder.logo = (ImageView)row.findViewById(R.id.tournLogo);
            holder.txtDate = (TextView)row.findViewById(R.id.tournMonthVal);
            holder.txtFund = (TextView)row.findViewById(R.id.tournFundVal);

            row.setTag(holder);
        }
        else
        {
            holder = (tournHolder)row.getTag();
        }

        tournament aTournament = data[position];
        //Set views
        holder.txtTitle.setText(aTournament.title);
        holder.logo.setImageResource(aTournament.icon);
        holder.txtDate.setText(aTournament.date);
        holder.txtFund.setText(aTournament.fund);

        return row;
    }
    //Holds the views
    static class tournHolder
    {
        TextView txtTitle;
        ImageView logo;
        TextView txtDate;
        TextView txtFund;
    }
}