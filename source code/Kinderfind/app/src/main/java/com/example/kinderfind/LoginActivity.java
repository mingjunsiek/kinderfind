package com.example.kinderfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.ActionBar;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText emailTb;
    private EditText passwordTb;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Hide action bar.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        loginBtn = (Button)findViewById(R.id.loginBtn);
        emailTb = (EditText)findViewById(R.id.emailText);
        passwordTb = (EditText)findViewById(R.id.passwordText);
        registerBtn = (Button)findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                verifyLogin(emailTb.getText().toString(), passwordTb.getText().toString());

            }
        });

        registerBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void verifyLogin(String email, String password){
        if(email.equals("sean") && password.equals("111")) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
        else{

            AlertDialog.Builder builder= new AlertDialog
                    .Builder(LoginActivity.this)
                    .setMessage("Incorrect email or password")
                    .setTitle("Login Error");

            builder.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            dialog.cancel();
                        }
                    });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();

            // Show the Alert Dialog box
            alertDialog.show();
        }
    }
}
