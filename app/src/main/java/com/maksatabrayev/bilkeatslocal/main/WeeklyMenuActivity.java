package com.maksatabrayev.bilkeatslocal.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityWeeklyMenuBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WeeklyMenuActivity extends AppCompatActivity {

    private ActivityWeeklyMenuBinding binding;
    private ArrayList<Menu> menuArrayList;
    private MenuAdapter menuAdapter;
    private ArrayList<Date> weekDatesArrayList;
    private Date currentDate;
    private Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeeklyMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        currentDate = new Date();
        calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        weekDatesArrayList = getWeekDates();
        menuArrayList = new ArrayList<>();

        for(int i=0; i<7; i++){
            menuArrayList.add(new Menu(getWeekDates().get(i)));
        }

        menuAdapter = new MenuAdapter(menuArrayList);
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.menuRecyclerView.setAdapter(menuAdapter);

    }

    private ArrayList<Date> getWeekDates(){
        int currentDateId = calendar.get(Calendar.DAY_OF_WEEK);
         ArrayList<Date> weekDatesArrayList = new ArrayList<>();
        if (currentDateId == Calendar.MONDAY){
            weekDatesArrayList.add(currentDate);
            for(int i=0; i < 6; i++){
                calendar.add(Calendar.DATE, 1);
                weekDatesArrayList.add(calendar.getTime());
            }
        }
        else if (currentDateId == Calendar.TUESDAY) {
            calendar.add(Calendar.DATE, -1);
            weekDatesArrayList.add(calendar.getTime());
            for (int i = 0; i < 6; i++) {
                calendar.add(Calendar.DATE, 1);
                weekDatesArrayList.add(calendar.getTime());
            }
        }
        else if (currentDateId == Calendar.WEDNESDAY) {
            calendar.add(Calendar.DATE, -2);
            weekDatesArrayList.add(calendar.getTime());
            for (int i = 0; i < 6; i++) {
                calendar.add(Calendar.DATE, 1);
                weekDatesArrayList.add(calendar.getTime());
            }
        }
        else if (currentDateId == Calendar.THURSDAY) {
            calendar.add(Calendar.DATE, -3);
            weekDatesArrayList.add(calendar.getTime());
            for (int i = 0; i < 6; i++) {
                calendar.add(Calendar.DATE, 1);
                weekDatesArrayList.add(calendar.getTime());
            }
        }
        else if (currentDateId == Calendar.FRIDAY) {
            calendar.add(Calendar.DATE, -4);
            weekDatesArrayList.add(calendar.getTime());
            for (int i = 0; i < 6; i++) {
                calendar.add(Calendar.DATE, 1);
                weekDatesArrayList.add(calendar.getTime());
            }
        }
        else if (currentDateId == Calendar.SATURDAY) {
            calendar.add(Calendar.DATE, -5);
            weekDatesArrayList.add(calendar.getTime());
            for (int i = 0; i < 6; i++) {
                calendar.add(Calendar.DATE, 1);
                weekDatesArrayList.add(calendar.getTime());
            }
        }
        else if (currentDateId == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, -6);
            weekDatesArrayList.add(calendar.getTime());
            for (int i = 0; i < 6; i++) {
                calendar.add(Calendar.DATE, 1);
                weekDatesArrayList.add(calendar.getTime());
            }
        }
        return weekDatesArrayList;
    }
}