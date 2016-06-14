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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "****MainActivity****";
    public FrameLayout frameLeft;
    public FrameLayout frameRight;
    private int config;
    private boolean rightVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLeft = (FrameLayout)findViewById(R.id.containerLeft);
        frameRight = (FrameLayout)findViewById(R.id.containerRight);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(config == Configuration.ORIENTATION_LANDSCAPE){
            FrameLayout frameRight = (FrameLayout)findViewById(R.id.containerRight);
            rightVisible = frameRight.getVisibility() == View.VISIBLE;
            Log.v(TAG, "Landscape Mode Visibility: " + frameRight.getVisibility());
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
            case R.id.menu_summary:
                //showSummaryFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //TODO: create a listview word fragment for the vocabs
    public void showWordFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerLeft, new WordFragment())
                .commit();
    }

    //TODO: create a summary fragment
    private void showSummaryFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerRight, new SummaryFragment())
                .commit();
    };

    //TODO: create a add fragment
    public void onShowDialog(AddFragment fragment){
        Log.v(TAG, "Callback active");
        FragmentManager manager = getFragmentManager();
        fragment.show(manager, "dialog");
    }

    //TODO: create a detail fragment, set up a vocab class
    public void onWordSelected(Cursor word){
        DetailFragment detail = new DetailFragment();

        Bundle bundle = new Bundle();
        
    }
}
