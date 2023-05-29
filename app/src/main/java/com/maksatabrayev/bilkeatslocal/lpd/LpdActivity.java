package com.maksatabrayev.bilkeatslocal.lpd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityLpdBinding;


import java.util.ArrayList;
import java.util.Map;

public class LpdActivity extends AppCompatActivity {

    private  ActivityLpdBinding binding;
    private ArrayList<Item> itemArrayList;

    private FirebaseFirestore firebaseFirestore;
    private ItemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLpdBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseFirestore = FirebaseFirestore.getInstance();

        getData();

        itemArrayList = new ArrayList<>();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemAdapter = new ItemAdapter(itemArrayList);
        binding.recyclerView.setAdapter(itemAdapter);
    }

    private void getData(){
        firebaseFirestore.collection("DeclaredItems").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(LpdActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                if(value != null){

                    for(DocumentSnapshot document : value.getDocuments()){
                        Map<String, Object> data = document.getData();

                        Item item = new Item((String)data.get("description"), "" + data.get("date"), (String) data.get("imageUrl"));
                        itemArrayList.add(item);
                    }
                    itemAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void declareLostItemButtonClicked(View view){
        startActivity(new Intent(this, DeclareActivity.class));
    }

}