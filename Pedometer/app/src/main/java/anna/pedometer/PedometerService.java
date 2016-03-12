package anna.pedometer;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class PedometerService extends Service implements SensorEventListener {

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




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e("MyActivity", "In the service");


        // get the sensor service
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        if (mSensorManager.getSensorList(Sensor.TYPE_STEP_COUNTER).size() != 0) {
            // we want to make a pedometer sensor
            mySensor_Pedometer = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        };


        // Restore preferences
        SharedPreferences shared_preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean on_or_off = shared_preferences.getBoolean("switch", false);

        // if the user has chosen pedometer on
        // register the listener
        if (on_or_off) {

            mSteps = MainActivity.mSteps;
            mPreviousCounterSteps = MainActivity.mSteps;

            // check your preferences
            checkValues();

        }
        // else if the user has chosen pedometer off
        // unregister the listener
        else {
            //mSensorManager.unregisterListener(MainActivity.this, mySensor_Pedometer);
            Log.e("on create: ", "off" + String.valueOf(on_or_off));
        }








        return START_NOT_STICKY;
    }

    private void checkValues() {

        SharedPreferences shared_preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean pedometer_on_off = shared_preferences.getBoolean("switch", true);

        Log.e("on create: ", "check values: " + pedometer_on_off);

        if (pedometer_on_off) {
            mSensorManager.registerListener(PedometerService.this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_NORMAL);

        }
        else {
            mSensorManager.unregisterListener(PedometerService.this, mySensor_Pedometer);
        }

    }

    @Override
    public void onDestroy() {
        Log.e("JSLog", "on destroy called");
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
            mSensorManager.registerListener(PedometerService.this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.e("on destroy: ", "on" + String.valueOf(on_or_off));

        }
        // else if the user has chosen pedometer off
        // unregister the listener
        else {
            mSensorManager.unregisterListener(PedometerService.this, mySensor_Pedometer);
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

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use
    }
}
