package anna.howtocelebratewintersolstice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

public class Snacks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        // Step 1: make an information class -done
        // Step 2: make a data class to populate the data types in the information class
        List<Information> list_of_items = Snacks_Data.load_all_items();


        // Step 2: Create a pager adapter class
        // and then add a pager view into the activity UI
        // create a pager layout for the pager adapter to use
        // add all the items from the information class to the pager
        // adapter

        Pager_Adapter pager_adapter = new Pager_Adapter();
        pager_adapter.addAll(list_of_items);



        // Step 3: initialize the pager view from the XML
        // and set the adapter to the pager
        ViewPager viewpager = (ViewPager) findViewById(R.id.view_pager);
        viewpager.setAdapter(pager_adapter);

        // initialize the tab layout
        RecyclerTabLayout recycler_tab_layout = (RecyclerTabLayout)
                findViewById(R.id.recycler_tab_layout);
        // set the tabs with the values from the tab adapter
        recycler_tab_layout.setUpWithAdapter(new Tab_Adapter(viewpager));

    }

}
