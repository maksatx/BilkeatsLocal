package com.maksatabrayev.bilkeatslocal.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.maksatabrayev.bilkeatslocal.R;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityAllergenBinding;

public class AllergenActivity extends AppCompatActivity {
    ActivityAllergenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllergenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }

    public void saveAllergensButtonClicked(View view){

    }
}