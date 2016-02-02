package annaxu.richarddawkinsalarmclock;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    AlarmManager alarmManager;
    private PendingIntent pending_intent;

    private TimePicker alarmTimePicker;
    private static MainActivity inst;
    private TextView alarmTextView;

    private AlarmReceiver alarm;
    private Context context;
    Spinner spinner;
    int richard_quote = 0;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.context = this;

        //alarm = new AlarmReceiver();
        alarmTextView = (TextView) findViewById(R.id.alarmText);

        final Intent myIntent = new Intent(this.context, AlarmReceiver.class);

        // Get the alarm manager service
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // set the alarm to the time that you picked
        final Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.SECOND, 3);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);

        //spinner creation
        Spinner spinner = (Spinner) findViewById(R.id.richard_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dawkins_sounds, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);



        Button start_alarm= (Button) findViewById(R.id.start_alarm);
        start_alarm.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)

            @Override
            public void onClick(View v) {

                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());

                final int hour = alarmTimePicker.getHour();
                final int minute = alarmTimePicker.getMinute();;

                String minute_string = String.valueOf(minute);
                String hour_string = String.valueOf(hour);

                if (minute < 10) {
                    minute_string = "0" + String.valueOf(minute);
                }

                if (hour > 12) {
                    hour_string = String.valueOf(hour - 12) ;
                }

                myIntent.putExtra("extra", "yes");
                myIntent.putExtra("quote id", String.valueOf(richard_quote));
                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

                setAlarmText("Alarm set to " + hour_string + ":" + minute_string);
            }

        });

        Button stop_alarm= (Button) findViewById(R.id.stop_alarm);
        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myIntent.putExtra("extra", "no");
                myIntent.putExtra("quote id", String.valueOf(richard_quote));
                sendBroadcast(myIntent);

                alarmManager.cancel(pending_intent);
                setAlarmText("Alarm canceled");

                //setAlarmText("ID is " + richard_quote);
            }
        });

    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Toast.makeText(parent.getContext(), "Spinner item 3!" + id, Toast.LENGTH_SHORT).show();
        richard_quote = (int) id;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback

    }
}