package com.maksatabrayev.bilkeatslocal.settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.maksatabrayev.bilkeatslocal.R;
import com.maksatabrayev.bilkeatslocal.calorietracking.CalorieTrackingActivity;
import com.maksatabrayev.bilkeatslocal.databinding.ActivitySettingsBinding;
import com.maksatabrayev.bilkeatslocal.lpd.LpdActivity;
import com.maksatabrayev.bilkeatslocal.main.MainActivity;
import com.maksatabrayev.bilkeatslocal.pianopage.pop_music.PopPianoPage;
import com.maksatabrayev.bilkeatslocal.signup_login.SignupActivity;

public class SettingsActivity extends AppCompatActivity {
ActivitySettingsBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navigationBarController();
        auth = FirebaseAuth.getInstance();
    }


    public void deleteAccountClicked(View view){
        new AlertDialog.Builder(this)
                .setTitle("Delete account")
                .setMessage("Are you sure you want to delete your account?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseUser user = auth.getCurrentUser();
                        // Prompt the user to re-provide their sign-in credentials
                        user.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            startActivity(new Intent(SettingsActivity.this, SignupActivity.class));
                                            Toast.makeText(SettingsActivity.this, "Deleted User Successfully,", Toast.LENGTH_LONG).show();
                                            finish();
                                        }
                                    }
                                });
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void signOutClicked(View view){
        auth.signOut();

        startActivity(new Intent(SettingsActivity.this, SignupActivity.class));
        finish();
    }

    public void changePasswordEmailClicked(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change password and email");

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        builder.setView(customLayout);

          builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  // send data from the AlertDialog to the Activity
                  EditText emailEditText = customLayout.findViewById(R.id.emailEditText);
                  EditText passwordEditText = customLayout.findViewById(R.id.passwordEditText);

                  auth.getCurrentUser().updateEmail(emailEditText.getText().toString());
                  auth.getCurrentUser().updatePassword(passwordEditText.getText().toString());
              }
          });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void contactClicked(View view){
        startActivity(new Intent(SettingsActivity.this, ContactActivity.class));
    }

    private void navigationBarController(){
        binding.nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home){
                    startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                } else if (item.getItemId() == R.id.calorieTrack) {
                    startActivity(new Intent(SettingsActivity.this, CalorieTrackingActivity.class));
                } else if (item.getItemId() == R.id.settings) {

                } else if (item.getItemId() == R.id.lpd) {
                    startActivity(new Intent(SettingsActivity.this, LpdActivity.class));
                } else if (item.getItemId() == R.id.piano) {
                    startActivity(new Intent(SettingsActivity.this, PopPianoPage.class));
                }
                return true;
            }
        });
    }
}