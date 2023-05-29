package com.maksatabrayev.bilkeatslocal.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.maksatabrayev.bilkeatslocal.R;
import com.maksatabrayev.bilkeatslocal.calorietracking.CalorieTrackingActivity;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityMainBinding;
import com.maksatabrayev.bilkeatslocal.lpd.LpdActivity;
import com.maksatabrayev.bilkeatslocal.signup_login.AllergenActivity;
import com.maksatabrayev.bilkeatslocal.signup_login.PersonalInfoActivity;
import com.maksatabrayev.bilkeatslocal.signup_login.SignupActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
 private ActivityMainBinding binding;
 Date currentDate;
 Menu menu;
 DateFormat dateFormat;
 String [] lunchMeals;
 String [] dinnerMeals;
 private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // Checking if the user is logged in
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() == null){
           startActivity(new Intent(this, SignupActivity.class));
           finish();
        }
        // Setting navigation bar menu
        navigationBarController();
        // Getting the current day and constructing a menu object
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        currentDate = new Date();
        Menu menu = new Menu(currentDate);
        lunchMeals = menu.getLunchMeals();
        dinnerMeals = menu.getDinnerMeals();
        // Setting the layout
        binding.currentDateTextView2.setText(dateFormat.format(currentDate));

        binding.lunchMealTextView0.setText(lunchMeals[0]);
        binding.lunchMealTextView1.setText(lunchMeals[1]);
        binding.lunchMealTextView2.setText(lunchMeals[2]);
        binding.lunchMealTextView3.setText(lunchMeals[3]);

        binding.dinnerMealTexView0.setText(dinnerMeals[0]);
        binding.dinnerMealTexView1.setText(dinnerMeals[1]);
        binding.dinnerMealTexView2.setText(dinnerMeals[2]);
        binding.dinnerMealTexView3.setText(dinnerMeals[3]);

        binding.lunchCalorieTextView.setText("" + menu.getLunchCalorie());
        binding.lunchCarbohydrateTextView.setText(""+menu.getLunchCarbohyrate());
        binding.lunchPoteinTextView.setText("" + menu.getLunchProtein());
        binding.lunchFatTextView.setText("" + menu.getLunchFat());

        binding.dinnerCalorieTextView.setText("" + menu.getDinnerCalorie());
        binding.dinnerCarbohydrateTxtView.setText(""+menu.getDinnerCarbohydrate());
        binding.dinnerProteinTxtView.setText("" + menu.getDinnerProtein());
        binding.dinnerFatTxtView.setText("" + menu.getDinnerFat());



    }

    private void navigationBarController(){
        binding.nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home){

                } else if (item.getItemId() == R.id.calorieTrack) {
                    startActivity(new Intent(MainActivity.this, CalorieTrackingActivity.class));
                } else if (item.getItemId() == R.id.settings) {

                } else if (item.getItemId() == R.id.lpd) {
                    startActivity(new Intent(MainActivity.this, LpdActivity.class));
                } else if (item.getItemId() == R.id.piano) {

                }
                return true;
            }
        });
    }
}