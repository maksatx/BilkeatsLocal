package com.maksatabrayev.bilkeatslocal.calorietracking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.maksatabrayev.bilkeatslocal.R;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityCalorieTrackingBinding;
import com.maksatabrayev.bilkeatslocal.lpd.LpdActivity;
import com.maksatabrayev.bilkeatslocal.main.MainActivity;
import com.maksatabrayev.bilkeatslocal.signup_login.PersonalInfoActivity;

import java.util.HashMap;
import java.util.Map;


public class CalorieTrackingActivity extends AppCompatActivity {
    public int takeCarbon;
    public int takeProtein;
    public int takeFat;
    public int sum;
    public int shouldSumCalorie;
    public int proteinCalorieRemain;
    public int fatCalorieRemain;
    public int carbonCalorieRemain;

    String gender;
    String activityString  ;
    int weigth;
    int height ;
    int age;

    FirebaseFirestore database;
    FirebaseAuth auth;

    ActivityCalorieTrackingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalorieTrackingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        navigationBarController();
        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        /*
        *  Map <String, Object> personalData = value.getDocuments().get(i).getData();
                   weigth = Integer.parseInt((String)personalData.get("weight"));
                   height = Integer.parseInt((String) personalData.get("height"));
                   gender = (String)personalData.get("gender");
                   activityString = (String) personalData.get("activityLevel");*/


        DocumentReference docRef = database.collection("PersonalInfo").document(auth.getCurrentUser().getEmail());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        Map <String, Object> personalData = document.getData();
                        weigth = Integer.parseInt((String)personalData.get("weight"));
                        height = Integer.parseInt((String) personalData.get("height"));
                        gender = (String)personalData.get("gender");
                        activityString = (String) personalData.get("activityLevel");
                        age    =  Integer.parseInt((String) personalData.get("age"));


                        double activity = 1.35;
                        if(activityString.equals("Light")) activity = 1.2;
                        else if (activityString.equals("Active")) activity = 1.5;

                        if(gender.equals("Female"))
                            shouldSumCalorie = (int)((655.1 + 9.56 * weigth + 1.85 * height - 4.67 * age) * activity);
                        else if (gender.equals("Male"))
                            shouldSumCalorie = (int)((66.5 + 13.75 * weigth + 5 * height - 6.77 * age) * activity);


                        proteinCalorieRemain = (int)(shouldSumCalorie * 30.0/100);
                        carbonCalorieRemain = (int)(shouldSumCalorie * 45.0/100);;
                        fatCalorieRemain = (int)(shouldSumCalorie * 25.0/100);
                        while(shouldSumCalorie != proteinCalorieRemain + carbonCalorieRemain + fatCalorieRemain){
                            carbonCalorieRemain += 1;
                        }

                        binding.shouldSome.setText("of " + shouldSumCalorie + " kcal");
                        binding.remainCarb.setText("" + carbonCalorieRemain);
                        binding.remainProt.setText("" + proteinCalorieRemain);
                        binding.remainFat.setText("" + fatCalorieRemain);
                        binding.sumCalorie.setText("" + 0);
                        binding.circularProgressIndicator.setProgress(0);
                    }
                }

            }
        });

    }
    public void outsideAdd(View view){
        takeProtein = Integer.parseInt(binding.outProt.getText().toString());
        proteinCalorieRemain -= takeProtein;
        binding.remainProt.setText("" + proteinCalorieRemain);
        takeCarbon = Integer.parseInt(binding.outCarb.getText().toString());
        carbonCalorieRemain -= takeCarbon;
        binding.remainCarb.setText("" + carbonCalorieRemain);
        takeFat = Integer.parseInt(binding.outFat.getText().toString());
        fatCalorieRemain -= takeFat;
        binding.remainFat.setText("" + fatCalorieRemain);
        sum = Integer.parseInt(binding.sumCalorie.getText().toString());
        sum = sum + takeFat + takeCarbon + takeProtein;
        binding.sumCalorie.setText("" + sum);
        binding.circularProgressIndicator.setProgress(100*sum/shouldSumCalorie);


    }

    private void navigationBarController(){
        binding.nav.findViewById(R.id.calorieTrack).setActivated(true);
        binding.nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    startActivity(new Intent(CalorieTrackingActivity.this, MainActivity.class));
                } else if (item.getItemId() == R.id.calorieTrack) {

                } else if (item.getItemId() == R.id.settings) {

                } else if (item.getItemId() == R.id.lpd) {
                    startActivity(new Intent(CalorieTrackingActivity.this, LpdActivity.class));
                } else if (item.getItemId() == R.id.piano) {

                }
                return true;
            }
        });
    }
}