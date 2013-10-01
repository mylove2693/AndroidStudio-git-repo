package com.huaqin.punan.disapp;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisableAppActivity extends Activity {

    private final static String TAG = "DisableAppMain";

    private PackageManager mPackageManager;
    private TextView mytv;
    private Button mybtn;

    private String mms = "com.android.mms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disable_app_activity);

        mytv = (TextView)findViewById(R.id.mytv);
        mybtn = (Button)findViewById(R.id.mybtn);
        mybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPackageManager.setApplicationEnabledSetting(mms,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER,
                        0);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.disable_app, menu);
        return true;
    }
    
}
