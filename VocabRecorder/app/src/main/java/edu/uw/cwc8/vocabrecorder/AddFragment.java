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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * The AddFragment creates an Alert Dialog which allows
 * the user to input information about their activity.
 */
public class AddFragment extends DialogFragment {

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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setMessage("Record Your Milktea Drinking Activity!");

        final View rootView = inflater.inflate(R.layout.fragment_add, null);
        builder.setView(rootView);
        activity = (MainActivity)getActivity();

        Spinner spinner = (Spinner)rootView.findViewById(R.id.type1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText wordText = (EditText) rootView.findViewById(R.id.word);

                EditText timeText = (EditText) rootView.findViewById(R.id.drinkTime);
                EditText ratingText = (EditText) rootView.findViewById(R.id.drinkRating);

                Log.v(TAG, "Recorded: " + wordText.getText() + " at " + timeText.getText());

                int cupNum = Integer.parseInt(wordText.getText().toString());
                int ratingNum = Integer.parseInt(ratingText.getText().toString());
                Long tsLong = System.currentTimeMillis();
                Date date = new Date(tsLong);
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                String tStamp = sdf.format(date);

                //a Toast to tell the user where they bought the milktea from
                Toast.makeText(getActivity(),
                        "You drink: " + cupText.getText() + " at " + timeText.getText(),
                        Toast.LENGTH_SHORT).show();

                //add to database
                WordDatabase.addToDatabase(getActivity(), cupNum, timeText.getText().toString(), ratingNum, tStamp);
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }

}