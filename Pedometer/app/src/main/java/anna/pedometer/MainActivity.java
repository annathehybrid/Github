package anna.pedometer;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor mySensor_Pedometer;
    private SensorManager mSensorManager;
    //private Sensor mAccelerometer;
    TextView update_text;
    boolean on_or_off;
    String hour_graph;

    // Steps counted in current session
    public static int mSteps = 0;
    // Value of the step counter sensor when the listener was registered.
    // (Total steps are calculated from this value.)
    private int mCounterSteps = 0;
    // Steps counted by the step counter previously. Used to keep counter consistent across rotation
    // changes
    private int mPreviousCounterSteps = 0;

    static final String M_STEPS = "mSteps";
    static final String M_COUNTER_STEPS = "mCounterSteps";
    static final String M_PREVIOUS_COUNTER_STEPS = "mPreviousCounterSteps";


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //initialize our text update box
        update_text = (TextView) findViewById(R.id.update_text);

        // get the sensor service
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        // we want to make a pedometer sensor
        mySensor_Pedometer = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);


        // Restore preferences
        SharedPreferences shared_preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //mSteps = shared_preferences.getInt("number of steps", 27);

        //SharedPreferences shared_preferences = getSharedPreferences("Settings", 0);
        boolean on_or_off = shared_preferences.getBoolean("switch", false);


        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {

            // if the user has chosen pedometer on
            // register the listener
            if (on_or_off) {

                // Restore value of members from saved state
                mSteps = savedInstanceState.getInt(M_STEPS);
                mPreviousCounterSteps = mSteps;

                // check your preferences
                checkValues();

            }
            // else if the user has chosen pedometer off
            // unregister the listener
            else {
                //mSensorManager.unregisterListener(MainActivity.this, mySensor_Pedometer);
                Log.e("on create: ", "off" + String.valueOf(on_or_off));
            }


        } else {
            // Probably initialize members with default values for a new instance
            //mSteps = 0;
            Log.e("on instance saved: ", "called");

            //SharedPreferences shared_preferences = getSharedPreferences("Settings", 0);
            Log.e("on create: ", "why is it " + String.valueOf(mSteps));

            mPreviousCounterSteps = shared_preferences.getInt("number of steps", 27);
        }


        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        Integer current_hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format


        HashMap<String, Integer> meMap=new HashMap<String, Integer>();


        hour_graph = "Hour";
        if (current_hour < 10) {
            hour_graph = hour_graph + "0" + current_hour;
        }
        else {
            hour_graph = hour_graph + current_hour;
        }

        meMap.put(hour_graph, 111);

        for (Object loop_object : meMap.keySet()) {
            String key = (String) loop_object;
            Integer value = (Integer) meMap.get(key);

            //String key = entry.getKey();
            //Object value = entry.getValue();

            Toast.makeText(getBaseContext(), key + " and " + value,
                    Toast.LENGTH_SHORT).show();
        }







        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, 0);

        final NotificationManager mNM = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Notification mNotify  = new Notification.Builder(this)
                .setContentTitle("The pedometer is running" + "!")
                .setContentText("Click me!")

                .setSmallIcon(R.drawable.green_square)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();

        //mNM.notify(0, mNotify);




    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(M_STEPS, mSteps);
        savedInstanceState.putInt(M_COUNTER_STEPS, mCounterSteps);
        savedInstanceState.putInt(M_PREVIOUS_COUNTER_STEPS, mPreviousCounterSteps);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


    private void checkValues() {

        SharedPreferences shared_preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean pedometer_on_off = shared_preferences.getBoolean("switch", true);
        String strUserName = shared_preferences.getString("username", "NA");
        boolean bAppUpdates = shared_preferences.getBoolean("applicationUpdates", false);


        Log.e("on create: ", "check values: " + pedometer_on_off);

        if (pedometer_on_off) {
            mSensorManager.registerListener(MainActivity.this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_NORMAL);

        }
        else {
            mSensorManager.unregisterListener(MainActivity.this, mySensor_Pedometer);
        }

    }

    private void set_alarm_text(String output) {
        update_text.setText(output);
    }




    public void Today_Button(View view) {
        Intent intent = new Intent(this, Today.class);
        startActivity(intent);
    }

    public void History_Button(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
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
        if (id == R.id.preferences) {
            Intent intent = new Intent();
            intent.setClassName(this, "anna.pedometer.MyPreference");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onResume() {
        super.onResume();
        //mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        SharedPreferences shared_preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //SharedPreferences shared_preferences = getSharedPreferences("Settings", 0);
        boolean on_or_off = shared_preferences.getBoolean("switch", false);


        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences.Editor editor = shared_preferences.edit();
        editor.putInt("number of steps", mSteps);

        // Commit the edits!
        editor.apply();

        if (on_or_off) {
            mSensorManager.registerListener(MainActivity.this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.e("on resume: ", "on " + String.valueOf(on_or_off));
        }
        else {
            mSensorManager.unregisterListener(MainActivity.this, mySensor_Pedometer);
            Log.e("on resume: ", "off" + String.valueOf(on_or_off));
        }


    }

    protected void onPause() {
        super.onPause();

        // check if the user has checked whether they want the pedometer off or on
        SharedPreferences shared_preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //SharedPreferences shared_preferences = getSharedPreferences("Settings", 0);
        boolean on_or_off = shared_preferences.getBoolean("switch", false);

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences.Editor editor = shared_preferences.edit();
        editor.putInt("number of steps", mSteps);

        // Commit the edits!
        editor.apply();


        // if the user has chosen pedometer on
        // register the listener
        if (on_or_off) {
            mSensorManager.registerListener(MainActivity.this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.e("on pause: ", "on" + String.valueOf(on_or_off));
        }
        // else if the user has chosen pedometer off
        // unregister the listener
        else {
            mSensorManager.unregisterListener(MainActivity.this, mySensor_Pedometer);
            Log.e("on pause: ", "off" + String.valueOf(on_or_off));
        }
    }


    protected void onStop() {
        super.onStop();

        // check if the user has checked whether they want the pedometer off or on
        SharedPreferences shared_preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //SharedPreferences shared_preferences = getSharedPreferences("Settings", 0);
        boolean on_or_off = shared_preferences.getBoolean("switch", false);


        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences.Editor editor = shared_preferences.edit();
        editor.putInt("number of steps", mSteps);

        // Commit the edits!
        editor.apply();



        // if the user has chosen pedometer on
        // register the listener
        if (on_or_off) {
            mSensorManager.registerListener(MainActivity.this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.e("on stop: ", "on" + String.valueOf(on_or_off));
        }
        // else if the user has chosen pedometer off
        // unregister the listener
        else {
            mSensorManager.unregisterListener(MainActivity.this, mySensor_Pedometer);
            Log.e("on stop: ", "off" + String.valueOf(on_or_off));
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //Write your code here

        // check if the user has checked whether they want the pedometer off or on
        SharedPreferences shared_preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //SharedPreferences shared_preferences = getSharedPreferences("Settings", 0);
        boolean on_or_off = shared_preferences.getBoolean("switch", false);


        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences.Editor editor = shared_preferences.edit();
        editor.putInt("number of steps", mSteps);

        // Commit the edits!
        editor.apply();



        // if the user has chosen pedometer on
        // register the listener
        if (on_or_off) {
            mSensorManager.registerListener(MainActivity.this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.e("on destroy: ", "on" + String.valueOf(on_or_off));

        }
        // else if the user has chosen pedometer off
        // unregister the listener
        else {
            mSensorManager.unregisterListener(MainActivity.this, mySensor_Pedometer);
            Log.e("on destroy: ", "off" + String.valueOf(on_or_off));
        }



    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        // initiate the sensor events
        Sensor sensor = event.sensor;


        // check if the user has checked whether they want the pedometer off or on
        SharedPreferences shared_preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        // to differentiate it between it and the accelerometer
        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if (mCounterSteps < 1) {
                // initial value
                mCounterSteps = (int) event.values[0];
            }

            // Calculate steps taken based on first counter value received.
            mSteps = (int) event.values[0] - mCounterSteps;

            // Add the number of steps previously taken, otherwise the counter would start at 0.
            // This is needed to keep the counter consistent across rotation changes.
            mSteps = mSteps + mPreviousCounterSteps;

            // We need an Editor object to make preference changes.
            // All objects are from android.context.Context
            SharedPreferences.Editor editor = shared_preferences.edit();
            editor.putInt("number of steps", mSteps);

            Log.e("number of steps is ", String.valueOf(mSteps));

            // Commit the edits!
            editor.apply();

        }


        // method that changes the update text Textbox
        set_alarm_text("You walked: " + mSteps);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use
    }


}


