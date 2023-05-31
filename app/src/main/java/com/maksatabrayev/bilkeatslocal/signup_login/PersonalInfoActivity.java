package com.maksatabrayev.bilkeatslocal.signup_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.maksatabrayev.bilkeatslocal.R;
import com.maksatabrayev.bilkeatslocal.calorietracking.CalorieTrackingActivity;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityPersonalInfoBinding;
import com.maksatabrayev.bilkeatslocal.main.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonalInfoActivity extends AppCompatActivity {

    private ActivityPersonalInfoBinding binding;
    private FirebaseFirestore database;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        createGenderSpinnerItems();
        createActivityLevelSpinnerItems();
        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }
    public void savePersonalInfoButtonClicked(View view){
         String  weight = binding.weightTextNumber.getText().toString();
         String  height = binding.heightTextNumber.getText().toString();
         String gender = binding.genderSpinner.getSelectedItem().toString();
         String  activityLevel = binding.activityLevelSpinner.getSelectedItem().toString();
         String  age = binding.ageEditTextNumber.getText().toString();
        HashMap <String, Object> personalData = new HashMap<>();
        personalData.put("weight", weight);
        personalData.put("height", height);
        personalData.put("gender", gender);
        personalData.put("activityLevel", activityLevel);
        personalData.put("age", age);

        database.collection("PersonalInfo").document(auth.getCurrentUser().getEmail())
                .set(personalData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intentToAllergenActivity = new Intent(PersonalInfoActivity.this, AllergenActivity.class);
                        intentToAllergenActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentToAllergenActivity);
                    }
    }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PersonalInfoActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void createGenderSpinnerItems(){
        ArrayList<String> genderArrayList = new ArrayList<>();
        genderArrayList.add("Male");
        genderArrayList.add("Female");

        ArrayAdapter<String> genderArrayAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, genderArrayList );

        binding.genderSpinner.setAdapter(genderArrayAdapter);
    }

    private void createActivityLevelSpinnerItems(){
        ArrayList<String> activityLevelArrayList = new ArrayList<>();
        activityLevelArrayList.add("Light");
        activityLevelArrayList.add("Moderate");
        activityLevelArrayList.add("Active");

        ArrayAdapter<String> activityLevelArrayAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, activityLevelArrayList);

        binding.activityLevelSpinner.setAdapter(activityLevelArrayAdapter);
    }
}