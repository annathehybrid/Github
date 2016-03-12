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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity  {

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
        // method that changes the update text Textbox
        set_alarm_text("You walked: " + mSteps);


        // method to make a graph
        make_graph();


        // start the service
        Intent pedometer_broadcast_intent = new Intent(this.getApplicationContext(), PedometerReceiver.class);
        sendBroadcast(pedometer_broadcast_intent);


        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {

            // if the user has chosen pedometer on
            // register the listener
            if (on_or_off) {

                // Restore value of members from saved state
                mSteps = savedInstanceState.getInt(M_STEPS);
                mPreviousCounterSteps = mSteps;

            }
            // else if the user has chosen pedometer off
            // unregister the listener
            else {
                //mSensorManager.unregisterListener(MainActivity.this, mySensor_Pedometer);
                Log.e("on create: ", "off" + String.valueOf(on_or_off));
            }


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

        //if (on_or_off) {
        //    mSensorManager.registerListener(MainActivity.this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_NORMAL);
        //    Log.e("on resume: ", "on " + String.valueOf(on_or_off));
        //}
        //else {
        //    mSensorManager.unregisterListener(MainActivity.this, mySensor_Pedometer);
        //    Log.e("on resume: ", "off" + String.valueOf(on_or_off));
        //}
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
        //if (on_or_off) {
        //    mSensorManager.registerListener(MainActivity.this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_NORMAL);
        //    Log.e("on pause: ", "on" + String.valueOf(on_or_off));
        //}
        // else if the user has chosen pedometer off
        // unregister the listener
        //else {
        //    mSensorManager.unregisterListener(MainActivity.this, mySensor_Pedometer);
        //    Log.e("on pause: ", "off" + String.valueOf(on_or_off));
        //}
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
        //if (on_or_off) {
        //    mSensorManager.registerListener(MainActivity.this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_NORMAL);
        //    Log.e("on stop: ", "on" + String.valueOf(on_or_off));
        //}
        // else if the user has chosen pedometer off
        // unregister the listener
        //else {
        //    mSensorManager.unregisterListener(MainActivity.this, mySensor_Pedometer);
        //    Log.e("on stop: ", "off" + String.valueOf(on_or_off));
        //}

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //Write your code here
    }


    private void make_graph() {
        // make a graph
        // find the graph on the layout
        GraphView graph = (GraphView) findViewById(R.id.graph);

        graph.setTitle("number of steps per hour");
        graph.getGridLabelRenderer().setVerticalAxisTitle("number of steps");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("o'clock, military time");

        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(1, 61),
                new DataPoint(2, 62),
                new DataPoint(3, 63),
                new DataPoint(4, 64),
                new DataPoint(5, 65),
                new DataPoint(6, 66),
                new DataPoint(7, 67),
                new DataPoint(8, 68),
                new DataPoint(9, 69),
                new DataPoint(10, 610),
                new DataPoint(11, 611),
                new DataPoint(12, 612),
                new DataPoint(13, 613),
                new DataPoint(14, 614),
                new DataPoint(15, 615),
                new DataPoint(16, 616),
                new DataPoint(17, 617),
                new DataPoint(18, 618),
                new DataPoint(19, 619),
                new DataPoint(20, 620),
                new DataPoint(21, 621),
                new DataPoint(22, mPreviousCounterSteps),
                new DataPoint(23, mCounterSteps),
                new DataPoint(24, mSteps)

        });


        graph.addSeries(series);
        //graph.removeAllSeries();

        //series.resetData(new DataPoint[]{
        //});

    }



}


