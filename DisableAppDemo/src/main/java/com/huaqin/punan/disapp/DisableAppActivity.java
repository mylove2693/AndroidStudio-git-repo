package com.huaqin.punan.disapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DisableAppActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disable_app_activity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.disable_app, menu);
        return true;
    }
    
}
