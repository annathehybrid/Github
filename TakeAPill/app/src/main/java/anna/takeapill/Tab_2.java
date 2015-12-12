package anna.takeapill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Tab_2 extends Fragment{
    // TODO: Rename parameter arguments, choose names that match

    private static final int DATASET_COUNT = 8;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    public Tab_2() {
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
        //return inflater.inflate(R.layout.fragment_tab_2, container, false);
        View view = inflater.inflate(R.layout.fragment_tab_2, container, false);

        initializeData();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Set CustomAdapter as the adapter for RecyclerView.
        mAdapter = new CustomAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initializeData();
    }

    @Override
    public void onPause() {
        super.onPause();
        initializeData();
    }


    private void initializeData() {
        mDataset = new String[DATASET_COUNT];

        SharedPreferences sharedPref = getActivity().getSharedPreferences("Settings", 0);

        int december_7_flag = sharedPref.getInt("7_flag", 0);
        int december_7_hour = sharedPref.getInt("7_hour", 0);
        int december_7_minute = sharedPref.getInt("7_minute", 0);
        String combined_7 = "Dec 7: Pill taken at " + december_7_hour + ":" + december_7_minute;

        int december_8_flag = sharedPref.getInt("8_flag", 0);
        int december_8_hour = sharedPref.getInt("8_hour", 0);
        int december_8_minute = sharedPref.getInt("8_minute", 0);
        String combined_8 = "Dec 8: Pill taken at " + december_8_hour + ":" + december_8_minute;

        int december_9_flag = sharedPref.getInt("9_flag", 0);
        int december_9_hour = sharedPref.getInt("9_hour", 0);
        int december_9_minute = sharedPref.getInt("9_minute", 0);
        String combined_9 = "Dec 9: Pill taken at " + december_9_hour + ":" + december_9_minute;

        int december_10_flag = sharedPref.getInt("10_flag", 0);
        int december_10_hour = sharedPref.getInt("10_hour", 0);
        int december_10_minute = sharedPref.getInt("10_minute", 0);
        String combined_10 = "Dec 10: Pill taken at " + december_10_hour + ":" + december_10_minute;

        int december_11_flag = sharedPref.getInt("11_flag", 0);
        int december_11_hour = sharedPref.getInt("11_hour", 0);
        int december_11_minute = sharedPref.getInt("11_minute", 0);
        String combined_11 = "Dec 11: Pill taken at " + december_11_hour + ":" + december_11_minute;

        int december_12_flag = sharedPref.getInt("12_flag", 0);
        int december_12_hour = sharedPref.getInt("12_hour", 0);
        int december_12_minute = sharedPref.getInt("12_minute", 0);
        String combined_12 = "Dec 12: Pill taken at " + december_12_hour + ":" + december_12_minute;

        int december_13_flag = sharedPref.getInt("13_flag", 0);
        int december_13_hour = sharedPref.getInt("13_hour", 0);
        int december_13_minute = sharedPref.getInt("13_minute", 0);
        String combined_13 = "Dec 13: Pill taken at " + december_13_hour + ":" + december_13_minute;

        int december_14_flag = sharedPref.getInt("14_flag", 0);
        int december_14_hour = sharedPref.getInt("14_hour", 0);
        int december_14_minute = sharedPref.getInt("14_minute", 0);
        String combined_14 = "Dec 14: Pill taken at " + december_14_hour + ":" + december_14_minute;



        for (int i = 0; i < DATASET_COUNT; i++) {


            if (i == 0  && december_7_flag == 0) {
                mDataset[i] = "Dec 7: not taken";
            }
            if (i == 0 && december_7_flag == 1) {
                mDataset[i] = combined_7;
            }


            if (i == 1  && december_8_flag == 0) {
                mDataset[i] = "Dec 8: not taken";
            }
            if (i == 1 && december_8_flag == 1) {
                mDataset[i] = combined_8;
            }


            if (i == 2  && december_9_flag == 0) {
                mDataset[i] = "Dec 9: not taken";
            }
            if (i == 2 && december_9_flag == 1) {
                mDataset[i] = combined_9;
            }


            if (i == 3  && december_10_flag == 0) {
                mDataset[i] = "Dec 10: not taken";
            }
            if (i == 3 && december_10_flag == 1) {
                mDataset[i] = combined_10;
            }


            if (i == 4  && december_11_flag == 0) {
                mDataset[i] = "Dec 11: not taken";
            }
            if (i == 4 && december_11_flag == 1) {
                mDataset[i] = combined_11;
            }


            if (i == 5  && december_12_flag == 0) {
                mDataset[i] = "Dec 12: not taken";
            }
            if (i == 5 && december_12_flag == 1) {
                mDataset[i] = combined_12;
            }


            if (i == 6  && december_13_flag == 0) {
                mDataset[i] = "Dec 13: not taken";
            }
            if (i == 6 && december_13_flag == 1) {
                mDataset[i] = combined_13;
            }


            if (i == 7  && december_14_flag == 0) {
                mDataset[i] = "Dec 14: not taken";
            }
            if (i == 7 && december_14_flag == 1) {
                mDataset[i] = combined_14;
            }
        }
    }
}
