package anna.howtocelebratechristmas;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Email me if you want me to feature your Christmas traditions", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        ImageView southern_house = (ImageView) findViewById(R.id.southern_christmas_image);
        southern_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pick a tradition and swipe right!", Toast.LENGTH_SHORT).show();
            }
        });



        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);

        for (Traditions demo : Traditions.values()) {
            adapter.add(getString(demo.titleResId));
        }

        listView.setAdapter(adapter);

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
        if (id == R.id.action_send_email) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"skateboardingalice@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "");
            i.putExtra(Intent.EXTRA_TEXT   , "");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
            return true;

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Traditions demo = Traditions.values()[position];
        switch (demo) {

            case Choice_1:
                Intent tree_intent = new Intent(MainActivity.this, Trees.class);
                startActivity(tree_intent);
                break;

            case Choice_2:
                Intent cookie_intent = new Intent(MainActivity.this, Cookies.class);
                startActivity(cookie_intent);
                break;

            case Choice_3:
                Intent wreath_intent = new Intent(MainActivity.this, Wreaths.class);
                startActivity(wreath_intent);
                break;

            case Choice_4:
                Intent beverage_intent = new Intent(MainActivity.this, Beverages.class);
                startActivity(beverage_intent);
                break;

            case Choice_5:
                Intent entree_intent = new Intent(MainActivity.this, Entrees.class);
                startActivity(entree_intent);
                break;

            case Choice_6:
                Intent cheer_intent = new Intent(MainActivity.this, Cheer.class);
                startActivity(cheer_intent);
                break;

            case Choice_7:
                Intent decoration_intent = new Intent(MainActivity.this, Decorations.class);
                startActivity(decoration_intent);
                break;
        }
    }

}
