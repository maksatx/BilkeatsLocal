package com.maksatabrayev.bilkeatslocal.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.maksatabrayev.bilkeatslocal.R;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityAllergenBinding;
import com.maksatabrayev.bilkeatslocal.main.MainActivity;

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
      startActivity(new Intent(AllergenActivity.this, MainActivity.class));
      finish();
    }
}