package com.anna.pillmuncher;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {



    public MainActivityFragment() {
    }


    private ArrayAdapter<String> mForecastAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_main, container, false);
        //return inflater.inflate(R.layout.fragment_main, container, false);

        //make fake data
        ArrayList<String> weekForecast = new ArrayList<String>();

        weekForecast.add("Mon 6/23?- Sunny - 31/17");
        weekForecast.add("Tue 6/24 - Foggy - 21/8");
        weekForecast.add("Wed 6/25 - Cloudy - 22/17");
        weekForecast.add("Thurs 6/26 - Rainy - 18/11");
        weekForecast.add("Fri 6/27 - Foggy - 21/10");
        weekForecast.add("Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18");
        weekForecast.add( "Sun 6/29 - Sunny - 20/7");

        //initialize adapter
        mForecastAdapter = new ArrayAdapter<String>(
                getActivity(), //global information about the app inviroment, resources
                R.layout.list_item_forecast, //layout for each element
                R.id.list_item_forecast_textview, //id of the textview
                weekForecast); // forecast data


        ListView listView = (ListView) view.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);
        return view;
    }







}
