package com.huaqin.punan.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends Activity {

    private Button btn_send;
    private GridView mGridView;
    private BroadcastReceiver mMusicStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);

        btn_send = (Button)findViewById(R.id.send_broadcast);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("");
                sendBroadcast(intent);
            }
        });


        mMusicStateReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };

        IntentFilter mIntentFilter = new IntentFilter("");
        registerReceiver(mMusicStateReceiver,mIntentFilter);

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMusicStateReceiver);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
