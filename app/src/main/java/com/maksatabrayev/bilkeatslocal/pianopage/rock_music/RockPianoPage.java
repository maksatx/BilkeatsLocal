package com.maksatabrayev.bilkeatslocal.pianopage.rock_music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.maksatabrayev.bilkeatslocal.R;
import com.maksatabrayev.bilkeatslocal.calorietracking.CalorieTrackingActivity;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityRockMusic1Binding;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityRockPianoPageBinding;
import com.maksatabrayev.bilkeatslocal.lpd.LpdActivity;
import com.maksatabrayev.bilkeatslocal.main.MainActivity;
import com.maksatabrayev.bilkeatslocal.pianopage.classical_music.ClassicalPianoPage;
import com.maksatabrayev.bilkeatslocal.pianopage.pop_music.PopPianoPage;
import com.maksatabrayev.bilkeatslocal.settings.SettingsActivity;


public class RockPianoPage extends AppCompatActivity {
 ActivityRockPianoPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRockPianoPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigationBarController();
    }

    public void rockMusic1(View view){
        Intent intent = new Intent(this, RockMusic1.class);
        startActivity(intent);
    }

    public void rockMusic2(View view){
        Intent intent = new Intent(this, RockMusic2.class);
        startActivity(intent);
    }

    public void rockMusic3(View view){
        Intent intent = new Intent(this, RockMusic3.class);
        startActivity(intent);
    }

    public void rockMusic4(View view){
        Intent intent = new Intent(this, RockMusic4.class);
        startActivity(intent);
    }

    public void rockMusic5(View view){
        Intent intent = new Intent(this, RockMusic5.class);
        startActivity(intent);
    }

    public void goToClassic(View view){
        Intent intent = new Intent(this, ClassicalPianoPage.class);
        startActivity(intent);
    }

    public void goToPop(View view){
        Intent intent = new Intent(this, PopPianoPage.class);
        startActivity(intent);
    }

    private void navigationBarController(){
        binding.nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home){
                    startActivity(new Intent(RockPianoPage.this, MainActivity.class));
                } else if (item.getItemId() == R.id.calorieTrack) {
                    startActivity(new Intent(RockPianoPage.this, CalorieTrackingActivity.class));
                } else if (item.getItemId() == R.id.settings) {
                    startActivity(new Intent(RockPianoPage.this, SettingsActivity.class));
                } else if (item.getItemId() == R.id.lpd) {
                    startActivity(new Intent(RockPianoPage.this, LpdActivity.class));
                } else if (item.getItemId() == R.id.piano) {

                }
                return true;
            }
        });
    }
}