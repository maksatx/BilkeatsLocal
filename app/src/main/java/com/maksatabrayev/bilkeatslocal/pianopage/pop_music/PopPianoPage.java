package com.maksatabrayev.bilkeatslocal.pianopage.pop_music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.maksatabrayev.bilkeatslocal.R;
import com.maksatabrayev.bilkeatslocal.calorietracking.CalorieTrackingActivity;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityPopPianoPageBinding;
import com.maksatabrayev.bilkeatslocal.lpd.LpdActivity;
import com.maksatabrayev.bilkeatslocal.main.MainActivity;
import com.maksatabrayev.bilkeatslocal.pianopage.classical_music.ClassicalPianoPage;
import com.maksatabrayev.bilkeatslocal.pianopage.rock_music.RockPianoPage;
import com.maksatabrayev.bilkeatslocal.settings.SettingsActivity;

public class PopPianoPage extends AppCompatActivity {
ActivityPopPianoPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPopPianoPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       navigationBarController();
    }

    public void popMusic1(View view){
        Intent intent = new Intent(this, PopMusic1.class);
        startActivity(intent);
    }

    public void popMusic2(View view){
        Intent intent = new Intent(this, PopMusic2.class);
        startActivity(intent);
    }

    public void popMusic3(View view){
        Intent intent = new Intent(this, PopMusic3.class);
        startActivity(intent);
    }

    public void popMusic4(View view){
        Intent intent = new Intent(this, PopMusic4.class);
        startActivity(intent);
    }

    public void popMusic5(View view){
        Intent intent = new Intent(this, PopMusic5.class);
        startActivity(intent);
    }


    public void goToClassic(View view){
        Intent intent = new Intent(this, ClassicalPianoPage.class);
        startActivity(intent);
    }

    public void goToRock(View view){
        Intent intent = new Intent(this, RockPianoPage.class);
        startActivity(intent);
    }

    private void navigationBarController(){
        binding.nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home){
                   startActivity(new Intent(PopPianoPage.this, MainActivity.class));
                } else if (item.getItemId() == R.id.calorieTrack) {
                    startActivity(new Intent(PopPianoPage.this, CalorieTrackingActivity.class));
                } else if (item.getItemId() == R.id.settings) {
                    startActivity(new Intent(PopPianoPage.this, SettingsActivity.class));
                } else if (item.getItemId() == R.id.lpd) {
                    startActivity(new Intent(PopPianoPage.this, LpdActivity.class));
                } else if (item.getItemId() == R.id.piano) {

                }
                return true;
            }
        });
    }
}