package com.maksatabrayev.bilkeatslocal.lpd;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.maksatabrayev.bilkeatslocal.databinding.LpdRecyclerRowBinding;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    private ArrayList<Item> itemArrayList;
    // Constructor
    public ItemAdapter(ArrayList<Item> itemArrayList){
        this.itemArrayList = itemArrayList;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LpdRecyclerRowBinding lpdRecyclerRowBinding = LpdRecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemHolder(lpdRecyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.lpdRecyclerRowBinding.recyclerViewUserDescriptionText.setText(itemArrayList.get(holder.getAdapterPosition()).description);
        holder.lpdRecyclerRowBinding.recyclerViewDateTextView.setText(itemArrayList.get(holder.getAdapterPosition()).date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ItemDetailsActivity.class);
                intent.putExtra("item", itemArrayList.get(holder.getAdapterPosition()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        LpdRecyclerRowBinding lpdRecyclerRowBinding;
        public ItemHolder(LpdRecyclerRowBinding lpdRecyclerRowBinding){
            super(lpdRecyclerRowBinding.getRoot());
            this.lpdRecyclerRowBinding = lpdRecyclerRowBinding;
        }
    }
}