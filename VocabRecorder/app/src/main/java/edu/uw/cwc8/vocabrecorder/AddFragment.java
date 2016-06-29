package edu.uw.cwc8.vocabrecorder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * The AddFragment creates an Alert Dialog which allows
 * the user to input information about their activity.
 */
public class AddFragment extends android.support.v4.app.DialogFragment {

    public static final String TAG = "SelfTracker.Recorder";

    private MainActivity activity;
    public static AddFragment newInstance() {
        return new AddFragment();
    }

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View rootView = inflater.inflate(R.layout.fragment_add, null);
        builder.setView(rootView);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        activity = (MainActivity)getActivity();
        final Bundle extras = getArguments();
        final EditText wordText = (EditText) rootView.findViewById(R.id.word);
        final EditText pos1Text = (EditText) rootView.findViewById(R.id.type1);
        final EditText def1Text = (EditText) rootView.findViewById(R.id.def1);
        final EditText syn1Text = (EditText) rootView.findViewById(R.id.syn1);
        final EditText pos2Text = (EditText) rootView.findViewById(R.id.type2);
        final EditText def2Text = (EditText) rootView.findViewById(R.id.def2);
        final EditText syn2Text = (EditText) rootView.findViewById(R.id.syn2);

        if (extras == null) {
            Log.v("***Add***", "Extras is empty");
            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.v(TAG, "Recorded: " + wordText.getText());

                    Long tsLong = System.currentTimeMillis();
                    Date date = new Date(tsLong);
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                    String tStamp = sdf.format(date);

                    Toast.makeText(getActivity(),
                            "You saved: " + wordText.getText(),
                            Toast.LENGTH_SHORT).show();

                    //add to database
                    WordDatabase.addToDatabase(getActivity(), wordText.getText().toString(), pos1Text.getText().toString(), def1Text.getText().toString(), syn1Text.getText().toString(),
                            pos2Text.getText().toString(), def2Text.getText().toString(), syn2Text.getText().toString(), tStamp);
                }
            });
        } else {
            Log.v("***Add***", "Extras is NOT empty");
            Log.v("***Add***", extras.getString("word"));
            wordText.setText(extras.getString("word"));
            pos1Text.setText(extras.getString("type1"));
            def1Text.setText(extras.getString("def1"));
            syn1Text.setText(extras.getString("syn1"));
            pos2Text.setText(extras.getString("type2"));
            def2Text.setText(extras.getString("def2"));
            syn2Text.setText(extras.getString("syn2"));
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id){
                    Long tsLong = System.currentTimeMillis();
                    Date date = new Date(tsLong);
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                    String tStamp = sdf.format(date);

                    WordDatabase.updateDatabase(getActivity(), wordText.getText().toString(), pos1Text.getText().toString(), def1Text.getText().toString(), syn1Text.getText().toString(),
                            pos2Text.getText().toString(), def2Text.getText().toString(), syn2Text.getText().toString(), tStamp, extras.getInt("id"));
                    Toast.makeText(getActivity(), "Updated the word: " + wordText.getText().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }

        // Create the AlertDialog object and return it
        return builder.create();
    }

}