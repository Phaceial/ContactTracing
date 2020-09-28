package edu.temple.contacttracing;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements SettingsFragment.SettingsInterface {
    TracingFragment tracingFragment;
    private SharedPreferences preferences;
    private String preferencesFile = "edu.temple.android.contacttracing";
    int tracingDistance, sedentaryTime;
    DailyID dailyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(preferencesFile, MODE_PRIVATE);
        tracingFragment = TracingFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.home, tracingFragment).commit();

        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 11 );

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);

        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        getSupportFragmentManager().beginTransaction().replace(R.id.home, new SettingsFragment()).addToBackStack("settings").commit();
        return true;
    }


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putInt(Keys.TRACING_KEY, tracingDistance);
        preferencesEditor.putInt(Keys.SEDENTARY_KEY, sedentaryTime);
        preferencesEditor.apply();
    }

    @Override
    public void updateTracing(int tracingDistance) {
        this.tracingDistance = tracingDistance;
    }

    @Override
    public void updateSedentary(int sedentaryTime) {
        this.sedentaryTime = sedentaryTime;
    }
}