package com.huaqin.punan.shakedemo;

import java.util.Date;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ShakeActivity extends Activity implements SensorEventListener{

    private static final String TAG = "ShakeActivity";
    private float mTempX, mTempY, mTempZ, x, y, z;
    private long mtime = new Date().getTime();
    private int flagGravityOver = 0;
    private int flagGravityLow = 0;
    private float mtemrGravity = 0;
    private float MtemrGravity = 100000;
    float mX[] = {-5,5};
    long timeX[] = {0,0};
    SensorManager mSensorManager;
    TextView mtv;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake_activity);

        mtv = (TextView)findViewById(R.id.mytv);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    protected void onResume() {
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
        super.onResume();
    }

    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shake, menu);
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        x = values[0];
        y = values[1];
        z = values[2];

        float rGravity = x*x + y*y + z*z;

        if(x > mX[0]){
            mX[0] = x;
            timeX[0] = new Date().getTime();
        }

        if(x < mX[1]){
            mX[1] = x;
            timeX[1] = new Date().getTime();
        }

        if(new Date().getTime() -mtime >700){
            flagGravityOver = 0;
            flagGravityOver = 0;
            mtime = new Date().getTime();
        }

        if(flagGravityLow != 0 & (int)rGravity > 700){
            flagGravityOver++;
            mtime = new Date().getTime();
        }

        if((int)rGravity < 70){
            flagGravityLow++;
            mtime = new Date().getTime();
        }

        if(flagGravityLow > 0 & flagGravityOver > 0){
            i++;
            mtv.setText("Shake Times = " + i);

            flagGravityOver = 0;
            flagGravityOver = 0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
