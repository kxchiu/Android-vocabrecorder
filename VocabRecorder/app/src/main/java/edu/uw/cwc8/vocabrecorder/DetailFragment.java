package edu.uw.cwc8.vocabrecorder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        final Bundle bundle = getArguments();

        if(bundle != null) {
            TextView titleWord = (TextView) rootView.findViewById(R.id.txtWord);
            TextView type1View = (TextView) rootView.findViewById(R.id.txtType1);
            TextView def1View = (TextView) rootView.findViewById(R.id.txtDef1);
            TextView syn1View = (TextView) rootView.findViewById(R.id.txtSyn1);
            TextView type2View = (TextView) rootView.findViewById(R.id.txtType2);
            TextView def2View = (TextView) rootView.findViewById(R.id.txtDef2);
            TextView syn2View = (TextView) rootView.findViewById(R.id.txtSyn2);

            String word = bundle.getString("word");
            String t1 = bundle.getString("type1");
            String d1 = bundle.getString("def1");
            String s1 = bundle.getString("syn1");
            String t2 = bundle.getString("type2");
            String d2 = bundle.getString("def2");
            String s2 = bundle.getString("syn2");
            String tStamp = bundle.getString("timestamp");

            Word vocabWord = new Word(word, t1, d1, s1, t2, d2, s2, tStamp); //recreate vocab word

            //set text
            titleWord.setText(vocabWord.toString());
            type1View.setText(vocabWord.type1);
            def1View.setText(vocabWord.def1);
            syn1View.setText(vocabWord.syn1);
            type2View.setText(vocabWord.type2);
            def2View.setText(vocabWord.def2);
            syn2View.setText(vocabWord.syn2);
        }

        // search for the word in browser
        View btnG = rootView.findViewById(R.id.btnGoogle);
        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "***Google*** button clicked!");

                String searchWord = bundle.getString("word");
                Log.v(TAG, "*Searching*: " + searchWord);

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=" + searchWord));
                startActivity(intent);
            }
        });

        // TODO: complete update
        // update the word in database
        View btnU = rootView.findViewById(R.id.btnUpdate);

        // delete the word from database
        // thought process: onClick -> delete from database -> replace the right panel with an empty fragment or something
        View btnD = rootView.findViewById(R.id.btnDelete);
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "***Delete*** button clicked!");
                WordDatabase.deleteFromDatabase(getContext(), bundle.getString("word"));
                Toast t = Toast.makeText(getContext(),
                        "You have deleted the word '" + bundle.getString("word") + "'",
                        Toast.LENGTH_SHORT);
            }
        });

        return rootView;
    }
}
