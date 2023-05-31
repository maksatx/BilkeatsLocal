package com.maksatabrayev.bilkeatslocal.pianopage.classical_music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.maksatabrayev.bilkeatslocal.R;
import com.maksatabrayev.bilkeatslocal.calorietracking.CalorieTrackingActivity;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityClassicalPianoPageBinding;
import com.maksatabrayev.bilkeatslocal.lpd.LpdActivity;
import com.maksatabrayev.bilkeatslocal.main.MainActivity;
import com.maksatabrayev.bilkeatslocal.pianopage.pop_music.PopPianoPage;
import com.maksatabrayev.bilkeatslocal.pianopage.rock_music.RockPianoPage;
import com.maksatabrayev.bilkeatslocal.settings.SettingsActivity;

public class ClassicalPianoPage extends AppCompatActivity {
 ActivityClassicalPianoPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClassicalPianoPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigationBarController();
    }

    public void classicalMusic1(View view){
        Intent intent = new Intent(this, ClassicalMusic1.class);
        startActivity(intent);
    }

    public void classicalMusic2(View view){
        Intent intent = new Intent(this, ClassicalMusic2.class);
        startActivity(intent);
    }

    public void classicalMusic3(View view){
        Intent intent = new Intent(this, ClassicalMusic3.class);
        startActivity(intent);
    }

    public void classicalMusic4(View view){
        Intent intent = new Intent(this, ClassicalMusic4.class);
        startActivity(intent);
    }

    public void classicalMusic5(View view){
        Intent intent = new Intent(this, ClassicalMusic5.class);
        startActivity(intent);
    }

    public void goToRock(View view){
        Intent intent = new Intent(this, RockPianoPage.class);
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
                   startActivity(new Intent(ClassicalPianoPage.this, MainActivity.class));
                } else if (item.getItemId() == R.id.calorieTrack) {
                    startActivity(new Intent(ClassicalPianoPage.this, CalorieTrackingActivity.class));
                } else if (item.getItemId() == R.id.settings) {
                    startActivity(new Intent(ClassicalPianoPage.this, SettingsActivity.class));
                } else if (item.getItemId() == R.id.lpd) {
                    startActivity(new Intent(ClassicalPianoPage.this, LpdActivity.class));
                } else if (item.getItemId() == R.id.piano) {

                }
                return true;
            }
        });
    }
}