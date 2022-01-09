package com.example.budzik;

import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class WywolanyBudzik extends AppCompatActivity {

    Vibrator wibrator;
    MediaPlayer mp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wywolany_budzik_activity);

        wibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        wibrator.vibrate(new long[] {0, 600, 300}, 1);

        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        mp = MediaPlayer.create(getApplicationContext(), alarm);
        mp.setLooping(true);
        mp.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        wibrator.cancel();
        mp.stop();
    }

    public void wylaczBudzik(View v) {
        finish();
    }
}
