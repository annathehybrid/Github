package anna.howtocelebratewintersolstice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Making the ListView
        // Step 1: initialize the list view
        ListView view_of_list = (ListView) findViewById(R.id.listview);

        // Step 2: initialize the lst adapter
        ArrayAdapter<String> adapter_of_titles = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);

        // Step 3: populate the list adapter
        for (Traditions_List enum_of_traditions : Traditions_List.values()) {
            adapter_of_titles.add(getString(enum_of_traditions.title));
        }

        // Step 4: set the adapter
        view_of_list.setAdapter(adapter_of_titles);

        // Setting up an On Click Item Lister
        // Step 1: implement the OnItemClickListener in the main class

        // Step 2: write the methods associated with the OnItemClickListener, automatically done

        // Step 3: put a listener on the list view itself
        view_of_list.setOnItemClickListener(this);

        // Step 4: In the methods section, tell the computer what list items to look out for
        // and what to do when those items are clicked



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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Step 4: In the methods section, tell the computer what list items to look out for
        // and what to do when those items are clicked

        // Load up the items from the traditions list
        // Make a switch statement telling the computer
        // what to do
        // when each item is clicked

        Traditions_List item_of_list = Traditions_List.values()[position];

        switch (item_of_list) {

            case Choice_1:
                Intent intent_contemplate = new Intent(MainActivity.this, Contemplate.class);
                startActivity(intent_contemplate);
                break;

            case Choice_2:
                Intent intent_love = new Intent(MainActivity.this, Love.class);
                startActivity(intent_love);
                break;

            case Choice_3:
                Intent intent_yule = new Intent(MainActivity.this, Yule.class);
                startActivity(intent_yule);
                break;

            case Choice_4:
                Intent intent_snacks = new Intent(MainActivity.this, Snacks.class);
                startActivity(intent_snacks);
                break;


        }




    }
}