package com.anna.pillmuncher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    public final static String EXTRA_MESSAGE = "com.anna.pillmuncher.MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;

        // Inflate the menu items for use in the action bar
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_main, menu);
        //return super.onCreateOptionsMenu(menu);

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
         if (id == R.id.action_search)
         {
             Context context = getApplicationContext();
             CharSequence text = "You made a search!";
             int duration = Toast.LENGTH_SHORT;

             Toast toast = Toast.makeText(context, text, duration);
             toast.show();
            return true;
         }

         else if (id == R.id.action_new)
         {
             Intent intent = new Intent(this, Activity2.class);
             startActivity(intent);

             return true;
         }

         else if (id == R.id.action_settings)
         {
             //Intent intent = new Intent(this, SettingsActivity.class);
             //startActivity(intent);
             startActivity(new Intent(this, SettingsActivity.class));
             return true;
         }

         else if (id == R.id.action_map)
         {
             openPreferredLocationInMap();
             return true;
         }

        return super.onOptionsItemSelected(item);

    }




    // ** Called when the user clicks the Send button */
    //public void sendMessage(View view) {
    //    Intent intent = new Intent(this, DisplayMessageActivity.class);
    //    EditText editText = (EditText) findViewById(R.id.edit_message);
    //    String message = editText.getText().toString();
    //    intent.putExtra(EXTRA_MESSAGE, message);
    //    startActivity(intent);

    //}


    // ** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Box.class);
        Button button_pill = (Button)this.findViewById(R.id.button1);
        startActivity(intent);

    }


    /** Called when the user clicks the Send button */
    public void sendMessage2(View view) {
        //RelativeLayout container = (RelativeLayout)this.findViewById(R.id.container);
        //TestView t = (TextView)container.findViewById(R.id.txt);
        Intent intent = new Intent(this, Activity2.class);
        Button button_pill = (Button)this.findViewById(R.id.button2);
        startActivity(intent);

    }

    private void openPreferredLocationInMap() {
        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this);
        String location = sharedPrefs.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));

        // Using the URI scheme for showing a location found on a map.  This super-handy
        // intent can is detailed in the "Common Intents" page of Android's developer site:
        // http://developer.android.com/guide/components/intents-common.html#Maps
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Couldn't call " + location + ", no receiving apps installed!");
        }
    }





}
