package anna.howtocelebratechristmas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

public class Decorations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<Information> items = Decorations_Data.load_decorations_items();

        Decorations_Pager_Adapter adapter = new Decorations_Pager_Adapter();
        adapter.addAll(items);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        RecyclerTabLayout recyclerTabLayout = (RecyclerTabLayout)
                findViewById(R.id.recycler_tab_layout);
        recyclerTabLayout.setUpWithAdapter(new Decorations_Adapter(viewPager));

    }

}
