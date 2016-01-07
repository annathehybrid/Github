package anna.richarddawkinsalarmclock;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.util.Random;

public class RingtonePlayingService extends Service {

    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.e("MyActivity", "In the service");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {

        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        player = new MediaPlayer();
        try {
            player.setDataSource(this, alert);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
            player.setAudioStreamType(AudioManager.STREAM_ALARM);
            player.setLooping(true);
            try {
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.start();
        }

        Log.e("MyActivity", "In the service");


        return START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {
        Log.e("JSLog", "on destroy called");
        super.onDestroy();

        player.stop();
    }


}