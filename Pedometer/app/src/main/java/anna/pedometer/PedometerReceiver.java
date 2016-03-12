package anna.pedometer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class PedometerReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {




        Log.e("MyActivity", "In the receiver!");


        Intent serviceIntent = new Intent(context, PedometerService.class);

        context.startService(serviceIntent);

    }
}
