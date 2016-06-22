package edu.uw.cwc8.vocabrecorder;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class WordFragment extends Fragment {

    private static final String TAG = "****Word Fragment****";
    private SimpleCursorAdapter adapter;

    private MainActivity wActivity;
    private OnWordSelectionListener callback;

    public interface OnWordSelectionListener {
        void onWordSelected(Cursor word);
        void onShowDialog(AddFragment frag);
    }

    public WordFragment(){
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Log.v(TAG, "WordFragment attached");

        try {
            callback = (OnWordSelectionListener) context;
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnWordSelectionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_word, container, false);

        wActivity = (MainActivity)getActivity();
        /** List View **/
        final ArrayList<Word> list = new ArrayList<Word>();
        list.clear();
        String[] cols = new String[]{WordDatabase.WordEntry.COL_WORD};
        int[] ids = new int[]{R.id.txtItem};

        adapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.list_item,
                WordDatabase.queryDatabase(getActivity()),
                cols, ids,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        Log.v(TAG, WordDatabase.queryDatabase(getActivity()).toString());

        AdapterView listView = (AdapterView)rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        //respond to item clicking
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor word = (Cursor) parent.getItemAtPosition(position);
                Log.i(TAG, "selected: " + word);

                //swap the fragments to show the detail
                ((OnWordSelectionListener) getActivity()).onWordSelected(word);
            }
        });

        //when the "Add Event" button is pressed
        Button addButton = (Button) rootView.findViewById(R.id.btnAdd);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.v(TAG, "Add button pressed!");
                AddFragment addFrag = AddFragment.newInstance();

                ((OnWordSelectionListener) getActivity()).onShowDialog(addFrag);

            }
        });

        return rootView;
    }
}
