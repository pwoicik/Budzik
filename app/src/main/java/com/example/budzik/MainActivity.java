package com.example.budzik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ustawBudzik(View v) {
        TimePicker picker = (TimePicker) findViewById(R.id.timePicker);
        picker.setIs24HourView(true);
        ZonedDateTime czas = ZonedDateTime.of(
                LocalDate.now(),
                LocalTime.of(picker.getHour(), picker.getMinute()),
                ZoneId.systemDefault()
        );

        Intent i = new Intent(getApplicationContext(), BudzikBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(
                getApplicationContext(),
                0,
                i,
                PendingIntent.FLAG_IMMUTABLE
        );

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                czas.toEpochSecond() * 1000,
                pi
        );

        Toast.makeText(getApplicationContext(), "Budzik ustawiony!", Toast.LENGTH_SHORT).show();
    }
}
