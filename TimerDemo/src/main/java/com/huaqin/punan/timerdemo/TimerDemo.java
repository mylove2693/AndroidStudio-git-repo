package com.huaqin.punan.timerdemo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TimerDemo extends Activity {

    public static Context context;
    public static final String TIMING_ACTION = "timing_send_message";
    public static final String DEALY_ACTION = "delay_send_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_demo);

        context = this;

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timer_demo, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private Button btn1;
        private Button btn2;
        private Button btn3;

        private TextView mytv1;
        private TextView mytv2;

        public PlaceholderFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_timer_demo, container, false);
            btn1 = (Button)rootView.findViewById(R.id.btn1);
            btn2 = (Button)rootView.findViewById(R.id.btn2);
            btn3 = (Button)rootView.findViewById(R.id.btn3);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, "btn1 clicked", Toast.LENGTH_SHORT).show();
                    AlarmManagerUtil.sendUpdateBroadcast(context);
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context,"btn2 clicked",Toast.LENGTH_SHORT).show();
                    AlarmManagerUtil.sendUpdateBroadcastRepeat(context);
                }
            });

            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlarmManagerUtil.cancelUpdateBroadcast(context);
                }
            });


            mytv1 = (TextView) rootView.findViewById(R.id.mytv1);
            mytv2 = (TextView) rootView.findViewById(R.id.mytv2);

            mytv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            mytv1.setText("在" + selectedHour + ":" + selectedMinute + "打开FM");
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            });

            mytv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            mytv2.setText("在" + selectedHour + ":" + selectedMinute + "关闭FM");
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            });



            return rootView;
        }

        @Override
        public void onPause() {
            super.onPause();
        }
    }

    public static class AlarmManagerUtil{
        public static AlarmManager getAlarmManager(Context ctx){
            return (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        }

        /**
         * 指定时间后发送信息(有如闹钟的设置)
         * 注意: Receiver记得在manifest.xml中注册
         *
         * @param ctx context
         */
        public static void sendUpdateBroadcast(Context ctx){
            Log.i("score", "send to start update broadcase,delay time :" + 60000);

            AlarmManager am = getAlarmManager(ctx);
            // 60秒后将产生广播,触发UpdateReceiver的执行
            Intent intent = new Intent(ctx, UpdateReceiver.class);
            intent.setAction(DEALY_ACTION);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0);
            am.set(AlarmManager.RTC, System.currentTimeMillis()+60000, pendingIntent);
        }

        public static void sendUpdateBroadcastRepeat(Context ctx){
            Intent intent =new Intent(ctx, UpdateReceiver.class);
            intent.setAction(TIMING_ACTION);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0);

            //开始时间
            long firstime= SystemClock.elapsedRealtime();

            AlarmManager am = (AlarmManager) ctx.getSystemService(ALARM_SERVICE);
            //60秒一个周期，不停的发送广播
            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime, 60 * 1000, pendingIntent);
        }

        /**
         * 取消定时执行(有如闹钟的取消)
         *
         * @param ctx context
         */
        public static void cancelUpdateBroadcast(Context ctx){
            AlarmManager am = getAlarmManager(ctx);
            Intent intent = new Intent(ctx, UpdateReceiver.class);
            intent.setAction(TIMING_ACTION);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0);
            am.cancel(pendingIntent);
        }

    }

    // 更新数据库的广播接收器
    public static class UpdateReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            //Toast.makeText(context, "更新比分数据", Toast.LENGTH_LONG).show();

            // 设置全局定时器(闹钟) 60秒后再发广播通知本广播接收器触发执行.
            // 这种方式很像JavaScript中的 setTimeout(xxx,60000)
            //AlarmManagerUtil.sendUpdateBroadcast(context);

            String action = intent.getAction();
            if(TIMING_ACTION.equals(action)){
                Toast.makeText(context, "接收到定时广播", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
