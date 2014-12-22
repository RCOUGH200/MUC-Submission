package uk.ac.gcu.mondayschild.mondayschild;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Administrator on 02/10/2014.
 */
public class mcAboutDialogue extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder mcAboutDialog = new AlertDialog.Builder(getActivity());
        mcAboutDialog.setMessage("This is an information app for Dota 2")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        mcAboutDialog.setTitle("About");
        mcAboutDialog.setIcon(R.drawable.ic_menu_action_about);
        return mcAboutDialog.create();
    }
}
