package com.maksatabrayev.bilkeatslocal.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maksatabrayev.bilkeatslocal.databinding.MenuRecyclerRowBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {

    private ArrayList<Menu> menuArrayList;
    // Constructor
    public MenuAdapter(ArrayList<Menu> menuArrayList){
       this.menuArrayList = menuArrayList;
    }
    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MenuRecyclerRowBinding menuRecyclerRowBinding = MenuRecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),
                                                         parent, false);
        return new MenuHolder(menuRecyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {
        //DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


        Menu menu = menuArrayList.get(position);
        holder.menuRecyclerRowBinding.dateTextView.setText(menu.menuDateString);
        String [] lunchMeals = menu.getLunchMeals();
        holder.menuRecyclerRowBinding.lunchMealsTextView.setText(lunchMeals[0] + "\n" +
                lunchMeals[1] + "\n" +lunchMeals[2] + "\n"+lunchMeals[3]);

        holder.menuRecyclerRowBinding.lunchNutrientsTextView.setText("Calorie: " + menu.getLunchCalorie()+
                "\nCarbohydrate: " + menu.getLunchCarbohyrate()+"\nProtein: " + menu.getLunchProtein()+"\nFat: " + menu.getLunchFat());

        String [] dinnerMeals = menu.getDinnerMeals();
        holder.menuRecyclerRowBinding.dinnerMealsTextView.setText(dinnerMeals[0] + "\n" +
                dinnerMeals[1] + "\n" +dinnerMeals[2] + "\n"+dinnerMeals[3]);

        holder.menuRecyclerRowBinding.dinnerNutrientsTxtView.setText("Calorie: " + menu.getDinnerCalorie()+
                "\nCarbohydrate: " + menu.getDinnerCarbohydrate()+"\nProtein: " + menu.getDinnerProtein()+"\nFat: " + menu.getDinnerFat());


    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    public class MenuHolder extends RecyclerView.ViewHolder{
        MenuRecyclerRowBinding menuRecyclerRowBinding;
        public MenuHolder(MenuRecyclerRowBinding menuRecyclerRowBinding){
            super(menuRecyclerRowBinding.getRoot());
            this.menuRecyclerRowBinding = menuRecyclerRowBinding;

        }
    }
}
