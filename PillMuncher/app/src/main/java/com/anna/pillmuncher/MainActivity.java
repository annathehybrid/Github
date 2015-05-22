package com.anna.pillmuncher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.anna.pillmuncher.MESSAGE";

    public final static String EXTRA_MESSAGE2 = "com.anna.pillmuncher.MESSAGE2";

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
         if (id == R.id.action_settings) {
           return true;
        }
        else if (id == R.id.action_search)
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
        return super.onOptionsItemSelected(item);

    }


    // ** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    /** Called when the user clicks the Send button */
    public void sendMessage2(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE2, message);
        startActivity(intent);

    }





}
