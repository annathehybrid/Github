package com.anna.pillmuncher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONObject;


public class Box extends AppCompatActivity {

    ImageButton imgButton;
    public final static String EXTRA_MESSAGE = "http://rxnav.nlm.nih.gov/REST/rxcui.json?idtype=NDC&id=11523-7020-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);

        imgButton =(ImageButton)findViewById(R.id.imageButton);

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                String text = "http://rxnav.nlm.nih.gov/REST/rxcui.json?idtype=NDC&id=11523-7020-1";
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
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

        return super.onOptionsItemSelected(item);

    }




}
