package edu.uw.cwc8.vocabrecorder;

import android.content.res.Configuration;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import edu.uw.cwc8.vocabrecorder.AddFragment;

public class MainActivity extends AppCompatActivity implements WordFragment.OnWordSelectionListener {

    private static final String TAG = "****MainActivity****";
    public FrameLayout frameLeft;
    public FrameLayout frameRight;
    public static final String MASTER_FRAG = "MasterFrag";
    public static final String SUMMARY_FRAG = "SummaryFrag";
    public static final String DETAIL_FRAG = "DetailFrag";
    private int config;
    private boolean rightVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        config = getResources().getConfiguration().orientation;

        frameLeft = (FrameLayout)findViewById(R.id.containerLeft);
        frameRight = (FrameLayout)findViewById(R.id.containerRight);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(config == Configuration.ORIENTATION_LANDSCAPE){
            FrameLayout frameRight = (FrameLayout)findViewById(R.id.containerRight);
            rightVisible = frameRight.getVisibility() == View.VISIBLE;
            Log.v(TAG, "Landscape Mode Visibility: " + frameRight.getVisibility());

            showWordFragment();
            showSummaryFragment();
        } else {
            Log.v(TAG,"Your device is not in landscape mode");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_search:
                showWordFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showWordFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerLeft, new WordFragment())
                .commit();
    }

    private void showDetailFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerRight, new DetailFragment())
                .addToBackStack(null)
                .commit();
    }


    private void showSummaryFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerRight, new SummaryFragment())
                .addToBackStack(null)
                .commit();
    };

    //TODO: create a add fragment
    public void onShowDialog(AddFragment fragment){
        Log.v(TAG, "Callback active");
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        fragment.show(manager, "dialog");
    }

    public void onWordSelected(Cursor word){
        DetailFragment detail = new DetailFragment();

        Bundle bundle = new Bundle();
        bundle.putString("word", word.getString(1));
        bundle.putString("type1", word.getString(2));
        bundle.putString("def1", word.getString(3));
        bundle.putString("syn1", word.getString(4));
        bundle.putString("type2", word.getString(5));
        bundle.putString("def2", word.getString(6));
        bundle.putString("syn2", word.getString(7));
        bundle.putString("timeStamp", word.getString(8));

        detail.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerLeft, new WordFragment())
                .replace(R.id.containerRight, detail)
                .addToBackStack(null)
                .commit();
    }
}
