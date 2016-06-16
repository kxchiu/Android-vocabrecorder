package edu.uw.cwc8.vocabrecorder;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class WordFragment extends Fragment {

    private static final String TAG = "****Word Fragment****";
    private SimpleCursorAdapter adapter;

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
            throw new ClassCastException();
            //(context.toString()) + "must implement OnWordSelectionListener");
        }
    }
}
