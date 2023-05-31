package com.maksatabrayev.bilkeatslocal.signup_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.maksatabrayev.bilkeatslocal.databinding.ActivitySignupBinding;
import com.maksatabrayev.bilkeatslocal.main.MainActivity;


public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
    }

    public void signUpButtonClicked(View view){
        String emailText = binding.emailText.getText().toString();
        String passwordText = binding.passwordText.getText().toString();
                                                    // String conPasText = binding.conpas.getText().toString();
        if(!(emailText.equals("")) ){               //To add : && passwordText.equals(conPasText)){
            auth.createUserWithEmailAndPassword(emailText,passwordText).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(SignupActivity.this, PersonalInfoActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignupActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(this, "Somethig went wrong, please check the password and email again.", Toast.LENGTH_SHORT).show();
        }
    }
    public void haveAccountClicked(View view){
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
    }
}