package edu.uw.cwc8.vocabrecorder;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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

        Bundle bundle = getArguments();

        if(bundle != null) {
            TextView titleWord = (TextView) rootView.findViewById(R.id.txtWord);
            TextView pos1View = (TextView) rootView.findViewById(R.id.txtType1);
            TextView def1View = (TextView) rootView.findViewById(R.id.txtDef1);
            TextView syn1View = (TextView) rootView.findViewById(R.id.txtSyn1);
            TextView pos2View = (TextView) rootView.findViewById(R.id.txtType2);
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

            //set text for title
            titleWord.setText(vocabWord.toString());

            //TODO: set text for other TextViews
        }

        return rootView;
    }
}
