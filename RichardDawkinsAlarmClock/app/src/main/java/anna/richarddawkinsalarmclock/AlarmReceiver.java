package anna.richarddawkinsalarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;



public class AlarmReceiver extends BroadcastReceiver {

    //this will sound the alarm tone
    //this will sound the alarm once, if you wish to
    //raise alarm in loop continuously then use MediaPlayer and setLooping(true)
    Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
    Ringtone ringtone;

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
        //Acquire the lock
        wl.acquire();

        Toast.makeText(context, "I'm in the receiver", Toast.LENGTH_LONG).show();

        //Release the lock
        wl.release();

        Log.e("MyActivity", "In the receiver");


        String snooze_or_not;
        snooze_or_not = intent.getStringExtra("snooze");
        Log.e("MyActivity", "received " + snooze_or_not);


    }


    public void setOnetimeTimer(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);

        Toast.makeText(context, "one time alarm", Toast.LENGTH_SHORT).show();

        //mp.stop();

    }

    public void CancelAlarm(Context context) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        context.stopService(intent);

        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        MediaPlayer mp = MediaPlayer.create(context, notification);


        Toast.makeText(context, "cancel the alarm", Toast.LENGTH_SHORT).show();

        if (mp.isPlaying()) {
            Log.e("MyActivity", "I am telling you to stop");

            mp.stop();
        }

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.stop();

        //MediaPlayer mp = MediaPlayer.create(context, notification);
        mp.stop();
        mp.release();

    }


}


    //Ringtone ringtone = RingtoneManager.getRingtone(this(), alarmUri);

    //MediaPlayer mp = MediaPlayer.create(AlarmReceiver.this, notification);
    //final MediaPlayer mp = MediaPlayer.create(AlarmReceiver.this, alarmUri);
