package com.example.kinderfind.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.example.kinderfind.R;
import com.example.kinderfind.utils.InternetReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN";
    private Button loginBtn, registerBtn;
    private EditText emailTb, passwordTb;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private InternetReceiver internetReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_interface);

        auth = FirebaseAuth.getInstance();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Hide action bar.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Register InternetReceiver
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        internetReceiver = new InternetReceiver(cm);

        if(!internetReceiver.checkForInternet())
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();

        loginBtn = findViewById(R.id.loginBtn);
        emailTb = findViewById(R.id.loginEmailTxt);
        passwordTb = findViewById(R.id.loginPassTxt);
        registerBtn = findViewById(R.id.registerBtn);

        progressBar = findViewById(R.id.loginProgressBar);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTb.getText().toString();
                final String password = passwordTb.getText().toString();
                //hideSoftKeyboard(LoginActivity.this);

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.no_email), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.no_password), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 8 || password.length() > 16){
                    Toast.makeText(getApplicationContext(), getString(R.string.minimum_password), Toast.LENGTH_SHORT).show();
                    return;
                }

                setProgressBar();
                if(internetReceiver.checkForInternet()){
                    //authenticate user
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    hideProgressBar();
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    } else {
                                        hideProgressBar();
                                        Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });

                }
                else {
                    hideProgressBar();
                    Toast.makeText(LoginActivity.this, R.string.no_internet, Toast.LENGTH_LONG).show();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    public void setProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void hideProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(internetReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(internetReceiver);
    }

    public static void hideSoftKeyboard(Activity activity) {

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);

    }


}
