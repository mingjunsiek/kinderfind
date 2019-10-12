package com.example.kinderfind.activities;

import com.example.kinderfind.adapters.DbAdapter;
import com.example.kinderfind.adapters.FirebaseSuccessListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.example.kinderfind.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN";
    private Button loginBtn, registerBtn;
    private EditText emailTb, passwordTb;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Hide action bar.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        loginBtn = findViewById(R.id.loginBtn);
        emailTb = findViewById(R.id.loginEmailTxt);
        passwordTb = findViewById(R.id.loginPassTxt);
        registerBtn = findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.loginProgressBar);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTb.getText().toString();
                final String password = passwordTb.getText().toString();

                //hideSoftKeyboard(LoginActivity.this);

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 8 || password.length() > 16){
                    Toast.makeText(getApplicationContext(), "Please enter a password length of 8 to 16", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                progressBar.setVisibility(View.INVISIBLE);

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


    }

    public void onStart() {
        super.onStart();

        DbAdapter dbAdapter = new DbAdapter();
        dbAdapter.readDataFromKindergarten(getApplicationContext(), new FirebaseSuccessListener() {

            @Override
            public void onKindergartenDataCompleted(boolean isDataCompleted) {

                // Check if user is signed in (non-null) and update UI accordingly.
                FirebaseUser user = auth.getCurrentUser();

                if(isDataCompleted && user!=null){

                    progressBar.setVisibility(View.VISIBLE);
                    //you know the value is true, here you can update or request any change for example you can do this
                    Log.d(TAG, "onKindergartenDataCompleted: true");
                    Log.d(TAG, "onStart: signed in " +user.getDisplayName());
                    Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Log.d(TAG, "onKindergartenDataCompleted: dataisloaded but user is null");
                }
            }
        });

    }

    public static void hideSoftKeyboard(Activity activity) {

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);

    }


}
