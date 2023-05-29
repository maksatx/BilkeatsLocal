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
        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
        holder.menuRecyclerRowBinding.dateTextView.setText(dateFormat.format(new Date()));

        String [] lunchMeals = menuArrayList.get()
        // holder.menuRecyclerRowBinding.dinnerMealsTextView.setText(menuArrayList.get(position).);
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
