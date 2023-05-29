package com.maksatabrayev.bilkeatslocal.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityWeeklyMenuBinding;

import java.util.ArrayList;

public class WeeklyMenuActivity extends AppCompatActivity {

    private ActivityWeeklyMenuBinding binding;
    private ArrayList<Menu> menuArrayList;
    private MenuAdapter menuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeeklyMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}