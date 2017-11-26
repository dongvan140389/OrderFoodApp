package com.example.vonga.oderfoodapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vonga.oderfoodapp.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    Button btnLogin;
    TextInputEditText txtPhoneLogin, txtPassLogin;

    FirebaseDatabase db;
    DatabaseReference tb_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        addControls();

        //Init firebase
        db = FirebaseDatabase.getInstance();
        tb_user = db.getReference("User");

        addEvents();
    }

    private void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignInActivity.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                tb_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if phone not exist in database
                        if(dataSnapshot.child(txtPhoneLogin.getText().toString()).exists()){
                            mDialog.dismiss();
                            User user = dataSnapshot.child(txtPhoneLogin.getText().toString()).getValue(User.class);
                            if(user.getPass().equals(txtPassLogin.getText().toString())){
                                Toast.makeText(SignInActivity.this,"Sign in successful!",Toast.LENGTH_SHORT);
                            }else{
                                Toast.makeText(SignInActivity.this,"Sign in failed!",Toast.LENGTH_SHORT);
                            }
                        }else{
                            mDialog.dismiss();
                            Toast.makeText(SignInActivity.this,"Phone not exist!",Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void addControls() {
        txtPassLogin = findViewById(R.id.txtPassLogin);
        txtPhoneLogin = findViewById(R.id.txtPhoneLogin);
        btnLogin = findViewById(R.id.btnLogin);
    }
}
