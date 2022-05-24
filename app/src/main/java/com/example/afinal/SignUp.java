package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener{


    private EditText editEmail, editPhone, editName, editPassword;
    private TextView AlrReg, btnSignUp;
    private FirebaseAuth mAuth;
    // ...
// Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Firebase Authentication
        mAuth = FirebaseAuth.getInstance();


        //Variables
        AlrReg = (TextView) findViewById(R.id.AlrReg);
        AlrReg.setOnClickListener(this);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

        editName = (EditText)findViewById(R.id.editName);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);

    }

    //Onclick Switch Case for Sign Up Button and Already Registered Text
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnSignUp:
                btnSignUp();
                break;
            case R.id.AlrReg:
                startActivity(new Intent(this, SignIn.class));
                break;
        }
    }

    //Get EditText Variables
    private void btnSignUp() {
        String phone = editPhone.getText().toString().trim();
        String name = editName.getText().toString().trim();
        String pass = editPassword.getText().toString().trim();
        String email = editEmail.getText().toString().trim();

        //Output Errors
        if(name.isEmpty()){
            editName.setError("Name is required!");
            editName.requestFocus();
            return;
        }

        if(phone.isEmpty()){
            editPhone.setError("Phone is required!");
            editPhone.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            editPassword.setError("Password is required!");
            editPassword.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editEmail.setError("Email is required!");
            editEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Correct email is required!");
            editEmail.requestFocus();
            return;
        }

        if(pass.length() < 4){
            editPassword.setError("Password must be 4 characters or over!");
            editPassword.requestFocus();
            return;
        }

        //Firebase Authentication function to create user
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name,phone,email);
                            //Add user in realtime database
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUp.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(SignUp.this, "Failed registration!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(SignUp.this, "Failed registration!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}