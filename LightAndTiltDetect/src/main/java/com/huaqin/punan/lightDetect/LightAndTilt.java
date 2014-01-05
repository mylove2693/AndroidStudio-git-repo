package com.huaqin.punan.lightDetect;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class LightAndTilt extends Activity {

    public static LightAndTiltService mLtService;

    private Intent intent;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mLtService = ((LightAndTiltService.LtServiceBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_and_tilt);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new mPlaceholderFragment())
                    .commit();
        }

        intent = new Intent(this, LightAndTiltService.class);
        this.bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.light_and_tilt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unbindService(mServiceConnection);
    }

    public static class mPlaceholderFragment extends Fragment implements LightAndTiltService.OnMaxValueUpdate{
        private Switch sw_light;
        private Switch sw_tilt;

        private Button btn_clear;
        private Button btn_alert;
        private Button btn_pause;
        private Button btn_stop;

        private static TextView tv_min_light;
        private static TextView tv_max_light;
        private static TextView tv_min_tilt;
        private static TextView tv_max_tilt;

        private MediaPlayer mediaPlayer;

        public mPlaceholderFragment(){

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //LayoutInflater inflater = getActivity().getLayoutInflater();

            View rootview = inflater.inflate(R.layout.fragment_light_and_tilt,container,false);

            mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.alert);

            sw_light = (Switch)rootview.findViewById(R.id.mysw1);
            sw_tilt = (Switch)rootview.findViewById(R.id.mysw2);

            tv_min_light = (TextView)rootview.findViewById(R.id.tv_min_light);
            tv_max_light = (TextView)rootview.findViewById(R.id.tv_max_light);

            tv_min_tilt = (TextView)rootview.findViewById(R.id.tv_min_tilt);
            tv_max_tilt = (TextView)rootview.findViewById(R.id.tv_max_tilt);

            sw_light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        mLtService.startLightDetect();
                    }else{
                        mLtService.stopLightDetect();
                    }
                }
            });

            sw_tilt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        mLtService.startTiltDetect();
                    }else{
                        mLtService.stopTiltDetect();
                    }
                }
            });

            btn_clear = (Button)rootview.findViewById(R.id.btn_clear);
            btn_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv_min_light.setText("");
                    tv_max_light.setText("");
                    tv_min_tilt.setText("");
                    tv_max_tilt.setText("");
                }
            });

            btn_alert = (Button)rootview.findViewById(R.id.btn_alert);
            btn_alert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        //mediaPlayer.prepare();
                        mediaPlayer.setLooping(true);
                        mediaPlayer.start();
                    }catch (IllegalArgumentException e){
                        e.printStackTrace();
                    }catch (IllegalStateException e){
                        e.printStackTrace();
                    }//catch (IOException e){
                     //   e.printStackTrace();
                    //}
                }
            });

            btn_pause = (Button)rootview.findViewById(R.id.btn_pause);
            btn_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.pause();
                }
            });

            btn_stop = (Button)rootview.findViewById(R.id.btn_stop);
            btn_stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                }
            });

            return rootview;
        }

        @Override
        public void updateLight(float min, float max) {
            tv_min_light.setText("Min Light:" + min);
            tv_max_light.setText("Max Light:" + max);
        }

        @Override
        public void updateTilt(float min_x, float max_x, float min_y, float max_y) {
            tv_min_tilt.setText("Min X:"+min_x+", Min Y:"+min_y);
            tv_max_tilt.setText("Max X:"+max_x+", Max Y:"+max_y);
        }
    }
}
