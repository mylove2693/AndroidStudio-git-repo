package com.huaqin.punan.fragment4;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FragmentDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main4);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fragment_demo, menu);
        return true;
    }
    
}
