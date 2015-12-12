package anna.takeapill;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Tab_3 extends Fragment implements SensorEventListener {


    private Sensor mySensor_Pedometer;
    private SensorManager SM;

    int flag = 0;

    // Steps counted in current session
    private int mSteps = 0;
    // Value of the step counter sensor when the listener was registered.
    // (Total steps are calculated from this value.)
    private int mCounterSteps = 0;
    // Steps counted by the step counter previously. Used to keep counter consistent across rotation
    // changes
    private int mPreviousCounterSteps = 0;

    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    public Tab_3() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_tab_3, container, false);
        final View view = inflater.inflate(R.layout.fragment_tab_3, container, false);


        // Create our Sensor Manager
        SM = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        // Pedometer Sensor
        mySensor_Pedometer = SM.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        SM.registerListener(this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_NORMAL);
        mPreviousCounterSteps = mSteps;





        // analyzes the pedometer data
        final Button analyze_me = (Button) view.findViewById(R.id.analyze_me);
        analyze_me.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                ArrayList<Integer> array_list = new ArrayList<Integer>();

                SharedPreferences sharedPref_pedometer = getActivity().getSharedPreferences("Settings_Pedometer", 0);

                Integer eight_oclock = sharedPref_pedometer.getInt("eight_oclock", 0);
                Integer nine_oclock = sharedPref_pedometer.getInt("nine_oclock", 0);
                Integer ten_oclock = sharedPref_pedometer.getInt("ten_oclock", 0);
                Integer eleven_oclock = sharedPref_pedometer.getInt("eleven_oclock", 0);
                Integer twelve_oclock = sharedPref_pedometer.getInt("twelve_oclock", 0);
                Integer thirteen_oclock = sharedPref_pedometer.getInt("thirteen_oclock", 0);
                Integer fourteen_oclock = sharedPref_pedometer.getInt("fourteen_oclock", 0);
                Integer fifteen_oclock = sharedPref_pedometer.getInt("fifteen_oclock", 0);

                int[] numbers = {eight_oclock,nine_oclock,ten_oclock,eleven_oclock,
                        twelve_oclock,thirteen_oclock, fourteen_oclock, fifteen_oclock};

                int smallest = numbers[0];
                int index = 0;
                for (int i = 0; i < numbers.length; i++)
                    if (numbers[i] < smallest)
                    {
                        smallest = numbers[i];
                        index = i;
                    }
                TextView count = (TextView) getView().findViewById(R.id.your_analysis);
                if( smallest == eight_oclock) {
                    count.setText("You should take your pill at 8");
                }
                if( smallest == nine_oclock) {
                    count.setText("You should take your pill at 9");
                }
                if( smallest == ten_oclock) {
                    count.setText("You should take your pill at 10");
                }
                if( smallest == eleven_oclock) {
                    count.setText("You should take your pill at 11");
                }
                if( smallest == twelve_oclock) {
                    count.setText("You should take your pill at 12");
                }
                if( smallest == thirteen_oclock) {
                    count.setText("You should take your pill at 1");
                }
                if( smallest == fourteen_oclock) {
                    count.setText("You should take your pill at 2");
                }
                if( smallest == fifteen_oclock) {
                    count.setText("You should take your pill at 3");
                }
            }

        });











        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SM.registerListener(this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        SM.unregisterListener(this, mySensor_Pedometer);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        SharedPreferences sharedPref_pedometer = getActivity().getSharedPreferences("Settings_Pedometer", 0);
        final SharedPreferences.Editor editor = sharedPref_pedometer.edit();
        // initiate the sensor events
        Sensor sensor = event.sensor;

        // to differentiate it between it and the accelerometer
        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
                if (mCounterSteps < 1) {
                    // initial value
                    mCounterSteps = (int) event.values[0];
                }

                if (flag == 1) {
                    // initial value
                    mCounterSteps = (int) event.values[0];
                    flag = 0;
                }

                // Calculate steps taken based on first counter value received.
                mSteps = (int) event.values[0] - mCounterSteps;

                // Add the number of steps previously taken, otherwise the counter would start at 0.
                // This is needed to keep the counter consistent across rotation changes.
                mSteps = mSteps + mPreviousCounterSteps;


                Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
                Integer current_hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format

                // check if it is not the current hour
                Integer past_hour = sharedPref_pedometer.getInt("past hour", 0);

                if (current_hour == past_hour) {
                    // yay everything is the same, as you were
                    editor.putInt("past hour", current_hour);
                }
                else {
                    mSteps = 0;
                    mCounterSteps = 0;
                    mPreviousCounterSteps = 0;
                    TextView count = (TextView) getView().findViewById(R.id.your_analysis);
                    count.setText("You have taken " + String.valueOf(mSteps) + " steps!");
                }

                editor.putInt("past hour", current_hour);



                if (current_hour == 8) {
                    editor.putInt("eight_oclock", mSteps);
                    editor.putInt("past hour", 8);
                } else if (current_hour == 9) {
                    editor.putInt("nine_oclock", mSteps);
                    editor.putInt("past hour", 9);
                } else if (current_hour == 10) {
                    editor.putInt("ten_oclock", mSteps);
                    editor.putInt("past hour", 10);
                } else if (current_hour == 11) {
                    editor.putInt("eleven_oclock", mSteps);
                    editor.putInt("past hour", 11);
                } else if (current_hour == 12) {
                    editor.putInt("twelve_oclock", mSteps);
                    editor.putInt("past hour", 12);
                } else if (current_hour == 13) {
                    editor.putInt("thirteen_oclock", mSteps);
                    editor.putInt("past hour", 13);
                } else if (current_hour == 14) {
                    editor.putInt("fourteen_oclock", mSteps);
                    editor.putInt("past hour", 14);
                } else if (current_hour == 15) {
                    editor.putInt("fifteen_oclock", mSteps);
                    editor.putInt("past hour", 15);
                }


                TextView count = (TextView) getView().findViewById(R.id.your_analysis);

                //count.setText(String.valueOf(event.values[0]));
                count.setText("You have taken " + String.valueOf(mSteps) + " steps!");


                editor.putString("current_day", "Sunday");

                // make a graph
                // find the graph on the layout
                GraphView graph = (GraphView) getView().findViewById(R.id.graph);

                graph.setTitle("number of steps per hour");
                graph.getGridLabelRenderer().setVerticalAxisTitle("number of steps");
                graph.getGridLabelRenderer().setHorizontalAxisTitle("o'clock, military time");


                Integer eight_oclock = sharedPref_pedometer.getInt("eight_oclock", 0);
                Integer nine_oclock = sharedPref_pedometer.getInt("nine_oclock", 0);
                Integer ten_oclock = sharedPref_pedometer.getInt("ten_oclock", 0);
                Integer eleven_oclock = sharedPref_pedometer.getInt("eleven_oclock", 0);
                Integer twelve_oclock = sharedPref_pedometer.getInt("twelve_oclock", 0);
                Integer thirteen_oclock = sharedPref_pedometer.getInt("thirteen_oclock", 0);
                Integer fourteen_oclock = sharedPref_pedometer.getInt("fourteen_oclock", 0);
                Integer fifteen_oclock = sharedPref_pedometer.getInt("fifteen_oclock", 0);


                BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[]{
                        new DataPoint(8, eight_oclock),
                        new DataPoint(9, nine_oclock),
                        new DataPoint(10, ten_oclock),
                        new DataPoint(11, eleven_oclock),
                        new DataPoint(12, twelve_oclock),
                        new DataPoint(13, thirteen_oclock),
                        new DataPoint(14, fourteen_oclock),
                        new DataPoint(15, fifteen_oclock)
                });


                graph.addSeries(series);
                graph.removeAllSeries();

                series.resetData(new DataPoint[]{
                });

                graph.removeAllSeries();
                BarGraphSeries<DataPoint> series2 = new BarGraphSeries<DataPoint>(new DataPoint[]{
                        new DataPoint(8, eight_oclock),
                        new DataPoint(9, nine_oclock),
                        new DataPoint(10, ten_oclock),
                        new DataPoint(11, eleven_oclock),
                        new DataPoint(12, twelve_oclock),
                        new DataPoint(13, thirteen_oclock),
                        new DataPoint(14, fourteen_oclock),
                        new DataPoint(15, fifteen_oclock)
                });

                graph.addSeries(series2);
                editor.apply();

            }

        }

    }

    // just a default class

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use
    }



}


