package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ImageView alarm;
    private ImageView clock;
    private ImageView stopWatch;
    private ImageView options;
    FragmentManager fragmentManager;
    private String tag;
    private Drawable alarmFocused;
    private Context context;
    private boolean optionsClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm = findViewById(R.id.alarm);
        clock = findViewById(R.id.clock);
        stopWatch = findViewById(R.id.stopWatch);
        options = findViewById(R.id.options);
        context = getApplicationContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alarmFocused = context.getDrawable(R.drawable.alarm_clock_focused);
        }

        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new AlarmFragment(), AlarmFragment.TAG);
                if (v.isClickable())
                    alarm.setImageDrawable(alarmFocused);

            }
        });

        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new ClockFragment(), ClockFragment.TAG);
            }
        });
        stopWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new StopWatchFragment(), StopWatchFragment.TAG);
            }
        });
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: options clicked");
                Toast.makeText(getApplicationContext(), "Options clicked", Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), options);
                popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
                Log.d(TAG, "onClick: pop up completed");
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.settings:
                                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                                startActivity(intent);

                        }
                        return false;
                    }
                });
                
            }
        });
    }
    

    private void switchFragment(Fragment fragment, String tag) {
        Log.d(TAG, "switchFragment: " + tag + " ");
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, tag);
//        Log.d(TAG, "switchFragment: after adding to back stack" + fragmentManager.getBackStackEntryCount());
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {

/*
        if (fragmentManager.getBackStackEntryCount() != 0) {
            Log.d(TAG, "onBackPressed: if");
            fragmentManager.popBackStackImmediate();
        }*/
        super.onBackPressed();
/*
        Log.d(TAG, "onBackPressed: after " + fragmentManager.getBackStackEntryCount());
*/

    }

    /*@Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.alarm) {
            switchFragment(new AlarmFragment(), AlarmFragment.TAG);
            tag = AlarmFragment.TAG;
        } else if (id == R.id.clock) {
            switchFragment(new ClockFragment(), ClockFragment.TAG);
            tag = ClockFragment.TAG;
        } else if (id == R.id.stopWatch) {
            switchFragment(new StopWatchFragment(), StopWatchFragment.TAG);

            tag = StopWatchFragment.TAG;
        } else if (id == R.id.options) {
            Toast.makeText(getApplicationContext(), "Options clicked", Toast.LENGTH_SHORT).show();
        }
*/


}