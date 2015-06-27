package com.anna.pillmuncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(Box.EXTRA_MESSAGE);

        Log.i("debug", "made it to new class");
        // Create the text view

        int padding = 50;
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setPadding(padding, padding, padding, padding);
        textView.setText(message);



        // Set the text view as the activity layout
        GridLayout.LayoutParams layout = new GridLayout.LayoutParams();
        addContentView(textView, layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_message, menu);
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
}
