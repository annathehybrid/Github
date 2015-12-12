package anna.takeapill;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;


public class Tab_1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    int flag = 0;

    public Tab_1() {
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
        View view = inflater.inflate(R.layout.fragment_tab_1, container, false);
        super.onCreate(savedInstanceState);

        //views to drag
        final TextView fiber = (TextView) view.findViewById(R.id.fiber);

        //set touch listeners
        fiber.setOnTouchListener(new ChoiceTouchListener());

        // initialize the mouth
        final TextView mouth = (TextView) view.findViewById(R.id.mouth);

        //set drag listeners
        mouth.setOnDragListener(new ChoiceDragListener());

        //initalize the textview
        final TextView congratulations = (TextView) view.findViewById(R.id.congratulations);


        // basically resets everything
        // just here for debugging, really
        final Button reset_button = (Button) view.findViewById(R.id.reset);
        reset_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //views to drag
                fiber.setVisibility(TextView.VISIBLE);

                flag = 0;

                mouth.setText("Drag into me: Mouth");
                mouth.setVisibility(View.VISIBLE);
                mouth.setTypeface(Typeface.DEFAULT);



                SharedPreferences sharedPref = getActivity().getSharedPreferences("Settings", 0);

                Integer hour_of_day = sharedPref.getInt("Hour I took pill", 0);
                Integer minute_of_day = sharedPref.getInt("Minute I took pill", 0);
                Integer day_of_month = sharedPref.getInt("Day I took pill", 0);

                String confirmation_string = "You last took your fiber at December "
                        + day_of_month +
                        ", " + hour_of_day.toString() + ":"
                        + minute_of_day.toString();

                congratulations.setText(confirmation_string);

            }
        });

        return view;

    }




    // default touch listener for option 1
    final class ChoiceTouchListener implements View.OnTouchListener {

        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //setup drag

                ClipData data = ClipData.newPlainText("a fiber pill", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);

                view.setVisibility(View.VISIBLE);

                return true;
            }
            else {
                return false;
            }

        }
    }

    // default listener for the 3 choices
    private class ChoiceDragListener implements View.OnDragListener {




        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle drag events

            int action = event.getAction();

            Calendar c = Calendar.getInstance();
            int hour_of_day = c.get(Calendar.HOUR_OF_DAY);
            int minute_of_day = c.get(Calendar.MINUTE);
            int day_of_month = c.get(Calendar.DAY_OF_MONTH);


            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();
                    //stop displaying the view where it was before it was dragged
                    view.setVisibility(View.INVISIBLE);

                    //view dragged item is being dropped on
                    TextView dropTarget = (TextView) v;

                    //view being dragged and dropped
                    TextView dropped = (TextView) view;

                    //set the tag in the target view to the ID of the view being dropped
                    dropTarget.setTag(dropped.getId());

                    //set the tag in the target view to the ID of the view being dropped
                    dropTarget.setTag(dropped.getId());
                    dropTarget.setVisibility(View.INVISIBLE);

                    //update the text in the target view to reflect the data being dropped
                    String dropped_text = "You ate me";
                    Integer current_time = hour_of_day;
                    Integer minute = minute_of_day;

                    String combined = dropped_text + " at " + current_time + ":" + minute + "!";
                    dropTarget.setText(combined);

                    flag = 1;

                    //make it bold to highlight the fact that an item has been dropped
                    dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                    Log.e("done ", "to fiber");

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Settings", 0);
                    final SharedPreferences.Editor editor = sharedPref.edit();



                    editor.putInt("Hour I took pill", hour_of_day);
                    editor.putInt("Minute I took pill", minute);
                    editor.putInt("Day I took pill", day_of_month);

                    if (day_of_month == 1) {
                        editor.putInt("1_flag", 1);
                        editor.putInt("1_hour", hour_of_day);
                        editor.putInt("1_minute", minute);
                    }
                    if (day_of_month == 2) {
                        editor.putInt("2_flag", 1);
                        editor.putInt("2_hour", hour_of_day);
                        editor.putInt("2_minute", minute);
                    }
                    if (day_of_month == 3) {
                        editor.putInt("3_flag", 1);
                        editor.putInt("4_hour", hour_of_day);
                        editor.putInt("5_minute", minute);
                    }
                    if (day_of_month == 4) {
                        editor.putInt("4_flag", 1);
                        editor.putInt("4_hour", hour_of_day);
                        editor.putInt("4_minute", minute);
                    }
                    if (day_of_month == 5) {
                        editor.putInt("5_flag", 1);
                        editor.putInt("5_hour", hour_of_day);
                        editor.putInt("5_minute", minute);
                    }
                    if (day_of_month == 6) {
                        editor.putInt("6_flag", 1);
                        editor.putInt("6_hour", hour_of_day);
                        editor.putInt("6_minute", minute);
                    }

                    if (day_of_month == 7) {
                        editor.putInt("7_flag", 1);
                        editor.putInt("7_hour", hour_of_day);
                        editor.putInt("7_minute", minute);
                    }
                    if (day_of_month == 8) {
                        editor.putInt("8_flag", 1);
                        editor.putInt("8_hour", hour_of_day);
                        editor.putInt("8_minute", minute);
                    }
                    if (day_of_month == 9) {
                        editor.putInt("9_flag", 1);
                        editor.putInt("9_hour", hour_of_day);
                        editor.putInt("9_minute", minute);
                    }
                    if (day_of_month == 10) {
                        editor.putInt("10_flag", 1);
                        editor.putInt("10_hour", hour_of_day);
                        editor.putInt("10_minute", minute);
                    }
                    if (day_of_month == 11) {
                        editor.putInt("11_flag", 1);
                        editor.putInt("11_hour", hour_of_day);
                        editor.putInt("11_minute", minute);
                    }
                    if (day_of_month == 12) {
                        editor.putInt("12_flag", 1);
                        editor.putInt("12_hour", hour_of_day);
                        editor.putInt("12_minute", minute);
                    }
                    if (day_of_month == 13) {
                        editor.putInt("13_flag", 1);
                        editor.putInt("13_hour", hour_of_day);
                        editor.putInt("13_minute", minute);
                    }
                    if (day_of_month == 14) {
                        editor.putInt("14_flag", 1);
                        editor.putInt("14_hour", hour_of_day);
                        editor.putInt("14_minute", minute);
                    }

                    editor.apply();

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }

            return true;
        }
    }





}
