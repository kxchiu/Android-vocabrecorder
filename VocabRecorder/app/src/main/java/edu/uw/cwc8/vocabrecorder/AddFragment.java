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
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setMessage("Record Your Milktea Drinking Activity!");

        final View rootView = inflater.inflate(R.layout.fragment_add, null);
        builder.setView(rootView);
        activity = (MainActivity)getActivity();

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText wordText = (EditText) rootView.findViewById(R.id.word);
                EditText pos1Text = (EditText) rootView.findViewById(R.id.type1);
                EditText def1Text = (EditText) rootView.findViewById(R.id.def1);
                EditText syn1Text = (EditText) rootView.findViewById(R.id.syn1);
                EditText pos2Text = (EditText) rootView.findViewById(R.id.type2);
                EditText def2Text = (EditText) rootView.findViewById(R.id.def2);
                EditText syn2Text = (EditText) rootView.findViewById(R.id.syn2);

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

        // Create the AlertDialog object and return it
        return builder.create();
    }

}