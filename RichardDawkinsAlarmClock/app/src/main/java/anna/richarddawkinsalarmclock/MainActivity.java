package anna.richarddawkinsalarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    private PendingIntent pending_intent;

    private TimePicker alarmTimePicker;
    private static MainActivity inst;
    private TextView alarmTextView;

    private AlarmReceiver alarm;

    public static MainActivity instance() {
        return inst;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //alarm = new AlarmReceiver();

        //alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);

        //final Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
        //calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());


        Button start_alarm= (Button) findViewById(R.id.start_alarm);
        start_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer(v);
            }
        });



        Button stop_alarm= (Button) findViewById(R.id.stop_alarm);
        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimer(v);
            }
        });


    }


    public void startTimer(View view){
        Context context = this.getApplicationContext();

        // Get the alarm manager service
        //alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);



        //Create pending intent to start the alarm to start the alarm logger receiver
        Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        //myIntent.setAction("CUSTOM_INTENT");
        //myIntent.putExtra("snooze", "nope" );
        //sendBroadcast(myIntent);

        // calendar
        //final Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.SECOND, 1);

        //pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
        //alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pending_intent);


        //Intent startIntent = new Intent(MainActivity.this, RingtonePlayingService.class);
        //myIntent.putExtra("ringtone-uri", "notification");
        //myIntent.putExtra("snooze", "nope");
        //context.startService(startIntent);

        startService(new Intent(MainActivity.this, RingtonePlayingService.class));

        Log.e("MyActivity", "Application started");

        //Log.e("MyActivity", calendar.toString());
        //setAlarmText("Alarm on, compound");
    }


    public void cancelTimer(View view){
        Context context = this.getApplicationContext();

        // Get the alarm manager service
        //alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //Create pending intent to start the alarm to start the alarm logger receiver
        //Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        //myIntent.setAction("CUSTOM_INTENT");
        //sendBroadcast(myIntent);

        stopService(new Intent(MainActivity.this, RingtonePlayingService.class));


        Log.e("MyActivity", "I'm trying to stop it");

        //pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("MyActivity", "on Destroy");
    }

}
