package com.maksatabrayev.bilkeatslocal.lpd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.maksatabrayev.bilkeatslocal.R;
import com.maksatabrayev.bilkeatslocal.calorietracking.CalorieTrackingActivity;
import com.maksatabrayev.bilkeatslocal.databinding.ActivityLpdBinding;
import com.maksatabrayev.bilkeatslocal.main.MainActivity;
import com.maksatabrayev.bilkeatslocal.pianopage.pop_music.PopPianoPage;
import com.maksatabrayev.bilkeatslocal.settings.SettingsActivity;


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

        navigationBarController();

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

    private void navigationBarController(){
        binding.nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home){
                    startActivity(new Intent(LpdActivity.this, MainActivity.class));
                } else if (item.getItemId() == R.id.calorieTrack) {
                    startActivity(new Intent(LpdActivity.this, CalorieTrackingActivity.class));
                } else if (item.getItemId() == R.id.settings) {
                    startActivity(new Intent(LpdActivity.this, SettingsActivity.class));

                } else if (item.getItemId() == R.id.lpd) {

                } else if (item.getItemId() == R.id.piano) {
                    startActivity(new Intent(LpdActivity.this, PopPianoPage.class));
                }
                return true;
            }
        });
    }

}