package com.example.alarm;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Navigator {

    private static Navigator navigator;
    private static final String TAG = "Navigator";
    private FragmentManager fragmentManager ;

    private Navigator() {

    }

    public static Navigator getInstance() {
        if (navigator == null) {
            navigator = new Navigator();
        }

        return navigator;
    }


    private void switchFragment(Fragment fragment, String tag) {
        Log.d(TAG, "switchFragment: " + tag + " ");
       // fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        Log.d(TAG, "switchFragment: after adding to back stack" + fragmentManager.getBackStackEntryCount());
        fragmentTransaction.commit();


    }
}
