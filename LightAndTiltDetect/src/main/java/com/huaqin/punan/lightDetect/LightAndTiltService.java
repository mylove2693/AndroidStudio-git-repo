package com.huaqin.punan.lightDetect;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by punan on 11/27/13.
 */
public class LightAndTiltService extends Service implements SensorEventListener {

    private LtServiceBinder mBinder = new LtServiceBinder();
    private SensorManager sensorManager;

    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LtServiceBinder extends Binder {
        LightAndTiltService getService() {
            return LightAndTiltService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("LightAndTiltService OnCreate");

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        switch (event.sensor.getType()) {
            case Sensor.TYPE_ORIENTATION:
                String orientation = "方向\n" + "旋转角度value[0]：" + event.values[0] + "\n"
                        + "X倾斜value[1]:" + event.values[1] + "\n" + "Y倾斜value[2]:" + event.values[2] + "\n";
                System.out.println(orientation);
                break;
            case Sensor.TYPE_LIGHT:
                System.out.println("亮度：" + event.values[0]);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void startLightDetect() {
        //  注册光线传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void stopLightDetect() {
        sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT));
    }

    public void startTiltDetect() {

        //  注册方向传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void stopTiltDetect() {
        sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION));
    }

}
