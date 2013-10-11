package com.huaqin.punan.fragment2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        fm.beginTransaction();
        Fragment fragOne = new MyFragment();
        Bundle arguments = new Bundle();
        arguments.putBoolean("shouldYouCreateAChildFragment", true);
        fragOne.setArguments(arguments);
        ft.add(R.id.main_frag_container, fragOne);
        ft.commit();
    }
    
}
