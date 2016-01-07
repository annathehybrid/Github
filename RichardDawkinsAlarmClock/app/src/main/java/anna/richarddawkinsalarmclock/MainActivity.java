package anna.richarddawkinsalarmclock;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
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
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

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

        alarm = new AlarmReceiver();
        alarmTextView = (TextView) findViewById(R.id.alarmText);

        Button start_alarm= (Button) findViewById(R.id.start_alarm);
        start_alarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startTimer(v);
                setAlarmText("Alarm set!");
                Toast.makeText(getApplicationContext(), "You set the alarm", Toast.LENGTH_SHORT).show();
            }

        });

        Button stop_alarm= (Button) findViewById(R.id.stop_alarm);
        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimer(v);
                setAlarmText("Alarm canceled");
                Toast.makeText(getApplicationContext(), "You canceled the alarm", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }


    public void startTimer(View view){
        Context context = this.getApplicationContext();

        //Create pending intent to start the alarm to start the alarm logger receiver
        Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);

        // Get the alarm manager service
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // set the alarm to the time that you picked
        final Calendar calendar = Calendar.getInstance();
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

        // You can put extra strings in the intent
        myIntent.putExtra("EXTRA", "goooooooooo");
        pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // start the alarm!
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pending_intent);

        Log.e("MyActivity", "Application started");
    }


    public void cancelTimer(View view){
        // can't get this to wooooorrkkkk :(
        Context context = this.getApplicationContext();

        //stopService(new Intent(MainActivity.this, RingtonePlayingService.class));
        Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);

        // Get the alarm manager service
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // start the alarm 1 second after pressing the button
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 1);

        // just cancel the sounds, omg, why isn't this working
        myIntent.putExtra("EXTRA", "cancel");

        PendingIntent pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pending_intent);

        // I'm trying!!!!!!!!!!!!!!!!!!
        stopService(new Intent(MainActivity.this, RingtonePlayingService.class));
        alarmManager.cancel(pending_intent);

        Log.e("MyActivity", "I'm trying to stop it");
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
