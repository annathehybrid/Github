package anna.pedometer;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

public class MyPreference extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new MyPreferenceFragment()).commit();
    }

    //@Override
    //public void onBuildHeaders(List<Header> target)
    //{
    //    loadHeadersFromResource(R.xml.headers_preference, target);
    //}

    //@Override
    //protected boolean isValidFragment(String fragmentName)
    //{
    //    return MyPreferenceFragment.class.getName().equals(fragmentName);
    //}
}
