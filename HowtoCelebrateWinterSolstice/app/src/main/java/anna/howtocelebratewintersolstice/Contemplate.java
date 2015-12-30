package anna.howtocelebratewintersolstice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;



public class Contemplate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contemplate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // Step 1: Create an information class that holds the variables and their
        // corresponding data types
        // Create a data class that populates those variables in the information class

        List<Information> list_of_items = Contemplate_Data.load_all_items();


        // Step 2: Create a pager adapter class
        // and then add a pager view into the activity UI
        // create a pager layout for the pager adapter to use
        // add all the items from the information class to the pager
        // adapter

        Contemplate_Pager_Adapter pager_adapter = new Contemplate_Pager_Adapter();
        pager_adapter.addAll(list_of_items);



        // Step 3: initialize the pager view from the XML
        // and set the adapter to the pager
        ViewPager viewpager = (ViewPager) findViewById(R.id.view_pager);
        viewpager.setAdapter(pager_adapter);


        //initialize the recycler tab layout
        RecyclerTabLayout recycler_tab_layout = (RecyclerTabLayout)
                findViewById(R.id.recycler_tab_layout);
        recycler_tab_layout.setUpWithAdapter(new Contemplate_Tab_Adapter(viewpager));


    }

}
