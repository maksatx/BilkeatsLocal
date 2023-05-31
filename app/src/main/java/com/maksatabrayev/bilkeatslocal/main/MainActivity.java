package com.maksatabrayev.bilkeatslocal.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.maksatabrayev.bilkeatslocal.R;
import com.maksatabrayev.bilkeatslocal.calorietracking.CalorieTrackingActivity;

import com.maksatabrayev.bilkeatslocal.databinding.ActivityMainBinding;
import com.maksatabrayev.bilkeatslocal.lpd.LpdActivity;
import com.maksatabrayev.bilkeatslocal.pianopage.classical_music.ClassicalPianoPage;
import com.maksatabrayev.bilkeatslocal.settings.SettingsActivity;
import com.maksatabrayev.bilkeatslocal.signup_login.AllergenActivity;
import com.maksatabrayev.bilkeatslocal.signup_login.PersonalInfoActivity;
import com.maksatabrayev.bilkeatslocal.signup_login.SignupActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static int carbonCal = 0;
    public static int proteinCal = 0;
    public static int fatCal = 0;
    private ActivityMainBinding binding;
    private FirebaseFirestore firebaseFirestore;
    boolean isData2 = false;
    Date currentDate;
    Menu menu;
    DateFormat dateFormat;
    String [] lunchMeals;
    String [] dinnerMeals;
    double points;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        if(isLunch()) binding.textView6.setText("Lunch");
        else binding.textView6.setText("Dinner");


        navigationBarController();
        // Checking if the user is logged in
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() == null){
            startActivity(new Intent(this, SignupActivity.class));
            finish();
        }
        // Setting navigation bar menu
        //navigationBarController();
        // Getting the current day and constructing a menu object
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        currentDate = new Date();
        menu = new Menu(currentDate);
        lunchMeals = menu.getLunchMeals();
        dinnerMeals = menu.getDinnerMeals();
        // Setting the layout
        binding.currentDateTextView.setText(dateFormat.format(currentDate));
        if(isLunch()) {
            binding.MealTextView0.setText(lunchMeals[0]);
            binding.MealTextView1.setText(lunchMeals[1]);
            binding.MealTextView2.setText(lunchMeals[2]);
            binding.MealTextView3.setText(lunchMeals[3]);
        }else {

            binding.MealTextView0.setText(dinnerMeals[0]);
            binding.MealTextView1.setText(dinnerMeals[1]);
            binding.MealTextView2.setText(dinnerMeals[2]);
            binding.MealTextView3.setText(dinnerMeals[3]);
        }
        estimatedQueueTime();





        for (int i = 0; i < 4; i++) {
            final int a = i;
            DocumentReference docRef = firebaseFirestore.collection("MealInfo").document(lunchMeals[a]);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (!document.exists()) {
                            HashMap<String, Object> mealData = new HashMap<>();
                            mealData.put("name", lunchMeals[a]);
                            mealData.put("generalPoints", 2.5);
                            mealData.put("count", 0);

                            firebaseFirestore.collection("MealInfo").document(lunchMeals[a]).set(mealData);
                        }


                    }

                }

            });
        }



        for (int i = 0; i < 4; i++) {
            final int a = i;
            DocumentReference docRef = firebaseFirestore.collection("MealInfo").document(dinnerMeals[a]);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (!document.exists()) {
                            HashMap<String, Object> mealData = new HashMap<>();
                            mealData.put("name", dinnerMeals[a]);
                            mealData.put("generalPoints", 2.5);
                            mealData.put("count", 0);

                            firebaseFirestore.collection("MealInfo").document(dinnerMeals[a]).set(mealData);
                        }


                    }

                }

            });
        }

        if(isLunch()){

            if(isData(lunchMeals[0])){
                firebaseFirestore.collection("PersonalRatingInfo").document(auth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                              binding.ratingBar1.setRating((float)((double)mealData.get(lunchMeals[0])));
                            }
                        }
                    }
                });

            }
            else {
                firebaseFirestore.collection("MealInfo").document(lunchMeals[0]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                               binding.ratingBar1.setRating((float)((double)mealData.get("generalPoints")));
                            }
                        }
                    }
                });
            }


            if(isData(lunchMeals[1])){
                firebaseFirestore.collection("PersonalRatingInfo").document(auth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                               binding.ratingBar2.setRating((float)(double)mealData.get(lunchMeals[1]));
                            }
                        }
                    }
                });

            }
            else {
                firebaseFirestore.collection("MealInfo").document(lunchMeals[1]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                                binding.ratingBar2.setRating((float)((double)mealData.get("generalPoints")));
                            }
                        }
                    }
                });
            }
            if(isData(lunchMeals[2])){
                firebaseFirestore.collection("PersonalRatingInfo").document(auth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                                binding.ratingBar3.setRating((float)(double)mealData.get(lunchMeals[2]));
                            }
                        }
                    }
                });

            }
            else {
                firebaseFirestore.collection("MealInfo").document(lunchMeals[2]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                               binding.ratingBar3.setRating((float)((double)mealData.get("generalPoints")));
                            }
                        }
                    }
                });
            }
            if(isData(lunchMeals[3])){
                firebaseFirestore.collection("PersonalRatingInfo").document(auth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                               binding.ratingBar4.setRating((float)(double)mealData.get(lunchMeals[3]));
                            }
                        }
                    }
                });

            }
            else {
                firebaseFirestore.collection("MealInfo").document(lunchMeals[3]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                              binding.ratingBar4.setRating((float)((double)mealData.get("generalPoints")));
                            }
                        }
                    }
                });
            }
        }else{
            if(isData(dinnerMeals[0])){
                firebaseFirestore.collection("PersonalRatingInfo").document(auth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                              binding.ratingBar1.setRating((float)(double)mealData.get(dinnerMeals[0]));
                            }
                        }
                    }
                });

            }
            else {
                firebaseFirestore.collection("MealInfo").document(dinnerMeals[0]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                               binding.ratingBar1.setRating((float)((double)mealData.get("generalPoints")));
                            }
                        }
                    }
                });
            }


            if(isData(dinnerMeals[1])){
                firebaseFirestore.collection("PersonalRatingInfo").document(auth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                               binding.ratingBar2.setRating((float)(double)mealData.get(dinnerMeals[1]));
                            }
                        }
                    }
                });

            }
            else {
                firebaseFirestore.collection("MealInfo").document(dinnerMeals[1]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                                binding.ratingBar2.setRating((float)((double)mealData.get("generalPoints")));
                            }
                        }
                    }
                });
            }
            if(isData(dinnerMeals[2])){
                firebaseFirestore.collection("PersonalRatingInfo").document(auth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                                binding.ratingBar3.setRating((float)(double)mealData.get(dinnerMeals[2]));
                            }
                        }
                    }
                });

            }
            else {
                firebaseFirestore.collection("MealInfo").document(dinnerMeals[2]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                                binding.ratingBar3.setRating((float)((double)mealData.get("generalPoints")));
                            }
                        }
                    }
                });
            }
            if(isData(dinnerMeals[3])){
                firebaseFirestore.collection("PersonalRatingInfo").document(auth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                                binding.ratingBar4.setRating((float)(double)mealData.get(dinnerMeals[3]));
                            }
                        }
                    }
                });

            }
            else {
                firebaseFirestore.collection("MealInfo").document(dinnerMeals[3]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Map<String, Object> mealData = document.getData();
                                binding.ratingBar4.setRating((float)((double)mealData.get("generalPoints")));
                            }
                        }
                    }
                });
            }
        }
    }




    private void navigationBarController(){
        binding.nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home){

                } else if (item.getItemId() == R.id.calorieTrack) {
                    startActivity(new Intent(MainActivity.this, CalorieTrackingActivity.class));
                } else if (item.getItemId() == R.id.settings) {
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                } else if (item.getItemId() == R.id.lpd) {
                    startActivity(new Intent(MainActivity.this, LpdActivity.class));
                } else if (item.getItemId() == R.id.piano) {
                   startActivity(new Intent(MainActivity.this, ClassicalPianoPage.class));
                }
                return true;
            }
        });
    }
    public void update(View view){
        double rating1 = (double)binding.ratingBar1.getRating();
        double rating2 = (double)binding.ratingBar2.getRating();
        double rating3 = (double)binding.ratingBar3.getRating();
        double rating4 = (double)binding.ratingBar4.getRating();
       double[] ratings = new double[4];
        double[] generalRatings = new double[4];
        int[] count = new int[4];
        ratings[0]=rating1;
        ratings[1]=rating2;
        ratings[2]=rating3;
        ratings[3]=rating4;



        if(isLunch()){

            HashMap <String, Object> personalData = new HashMap<>();
            for (int i = 0; i < 4; i++) {
                final int a = i;
                personalData.put(lunchMeals[i], ratings[i]);

                firebaseFirestore.collection("PersonalRatingInfo").document(auth.getCurrentUser().getEmail()).set(personalData);
                firebaseFirestore.collection("MealInfo").document(lunchMeals[i]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Map<String, Object> mealData = document.getData();
                                generalRatings[a] = (double)mealData.get("generalPoints");
                                count[a] = (int)(long)mealData.get("count");
                            }
                        }
                    }
                });
                {
                    DocumentReference docRef = firebaseFirestore.collection("MealInfo").document(lunchMeals[a]);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();

                                HashMap<String, Object> mealData = new HashMap<>();
                                mealData.put("name", lunchMeals[a]);
                                mealData.put("generalPoints", (float)(generalRatings[a] * count[a] + ratings[a])/(count[a] + 1));
                                mealData.put("count", count[a] + 1);

                                firebaseFirestore.collection("MealInfo").document(lunchMeals[a]).set(mealData);



                            }

                        }

                    });
                }
            }

        }else{

            HashMap <String, Object> personalData = new HashMap<>();
            for (int i = 0; i < 4; i++) {
                final int a = i;
                personalData.put(dinnerMeals[i], ratings[i]);

                firebaseFirestore.collection("PersonalRatingInfo").document(auth.getCurrentUser().getEmail()).set(personalData);
                firebaseFirestore.collection("MealInfo").document(dinnerMeals[i]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Map<String, Object> mealData = document.getData();
                                generalRatings[a] = (double)mealData.get("generalPoints");
                                count[a] = (int)(long)mealData.get("count");
                            }
                        }
                    }
                });
                {
                    DocumentReference docRef = firebaseFirestore.collection("MealInfo").document(dinnerMeals[a]);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (!document.exists()) {
                                    HashMap<String, Object> mealData = new HashMap<>();
                                    mealData.put("name", dinnerMeals[a]);
                                    mealData.put("generalPoints", (float)(generalRatings[a] * count[a] + ratings[a])/(count[a] + 1));
                                    mealData.put("count", count[a] + 1);

                                    firebaseFirestore.collection("MealInfo").document(dinnerMeals[a]).set(mealData);
                                }


                            }

                        }

                    });
                }
            }
        }


    }
    public void estimatedQueueTime(){
        double generalRatings[] = new double[4];
        double average = 0;
        for (int i = 0; i < 4; i++) {
            final int a = i;
            firebaseFirestore.collection("MealInfo").document(dinnerMeals[i]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Map<String, Object> mealData = document.getData();
                            generalRatings[a] = (double)mealData.get("generalPoints");

                        }
                    }
                }
            });
            average += generalRatings[i];
        }
        double rate = average/2;

        int crowd = 0;
        double clock = whatTimeIsIt();

        if((clock > 12.20 && clock < 12.40) || (clock > 17.20 && clock < 17.40)){
            crowd += 5;
        }else if((clock > 11.00 && clock < 12.20) || (clock > 13.00 && clock < 14.00) || (clock > 17.00 && clock < 17.20) ||(clock > 19.00 && clock < 20.00)){
            crowd += 1;
        }else crowd += 3;

        if(rate < 3) crowd += 1;
        else if(rate < 5.5) crowd += 3;
        else if(rate < 8) crowd += 4;
        else crowd += 5;
        String str = "before the meal time";
        if((clock < 23.59 && clock > 00.00) || (clock < 20.00 && clock > 17.00)) {

            if (crowd <= 4) str = "5 Minutes";
            else if (crowd < 7) str = "10 Minutes";
            else str = "15 Minutes";
        }
        binding.button8.setText(str);

    }
    public boolean isLunch(){
        if(whatTimeIsIt() < 14.00) return true;
        else return false;
    }
    public boolean isData(String meal){
      if(auth.getCurrentUser().getEmail() != null){
          firebaseFirestore.collection("PersonalRatingInfo").document(auth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
              @Override
              public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                  if(task.isSuccessful()) {
                      DocumentSnapshot document = task.getResult();
                      if(document.exists()){
                          Map<String, Object> mealData = document.getData();
                          if(mealData.containsKey(meal)) isData2 = true;
                          else isData2 = false;
                      }
                  }
              }
          });
      }

        return isData2;
    }
    public double whatTimeIsIt(){

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH.mm");
        String stringTime = dateFormat.format(currentDate);
        double time = Double.valueOf(stringTime);
        return time;
    }

    public void WeeklyMenuButtonClicked(View view){
        startActivity(new Intent(MainActivity.this, WeeklyMenuActivity.class));
    }
    public void eaten(View view){
       if(isLunch()){
           carbonCal = menu.getLunchCarbohyrate();
           proteinCal =menu.getLunchProtein();
           fatCal = menu.getLunchFat();
       }
       else{
           carbonCal = menu.getDinnerCarbohydrate();
           proteinCal = menu.getDinnerProtein();
           fatCal = menu.getDinnerFat();
       }

       startActivity(new Intent(MainActivity.this, CalorieTrackingActivity.class));
    }

}