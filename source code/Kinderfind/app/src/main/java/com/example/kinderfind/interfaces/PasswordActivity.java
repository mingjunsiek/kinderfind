package com.example.kinderfind.interfaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kinderfind.R;
import com.example.kinderfind.utils.InternetReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class PasswordActivity extends AppCompatActivity {

    private InternetReceiver internetReceiver;
    private IntentFilter intentFilter;
    private ProgressBar progressBar;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_interface);

        Button updateBtn = findViewById(R.id.cPwBtn);
        progressBar = findViewById(R.id.passwordProgressBar);
        final EditText currentPw = findViewById(R.id.cPwText);
        final EditText newPw = findViewById(R.id.cPwNewPwText);
        final EditText retypePw = findViewById(R.id.cRePwNewPwText);

        // Register InternetReceiver
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        internetReceiver = new InternetReceiver(cm);

        if(!internetReceiver.checkForInternet())
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();

        updateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                setProgressBar();
                String cPw = currentPw.getText().toString().trim();
                final String password = newPw.getText().toString().trim();
                String repassword = retypePw.getText().toString().trim();

                if (TextUtils.isEmpty(cPw)) {
                    Toast.makeText(getApplicationContext(), "Enter Current password!", Toast.LENGTH_SHORT).show();
                    hideProgressBar();
                    return;
                }

                if (TextUtils.isEmpty(repassword) || (TextUtils.isEmpty(password))) {
                    Toast.makeText(getApplicationContext(), "Re-enter new password!", Toast.LENGTH_SHORT).show();
                    hideProgressBar();
                    return;
                }

                if ((password.length() < 8 || password.length() > 16) && (repassword.length() < 8 || repassword.length() > 16)) {
                    Toast.makeText(getApplicationContext(), "Please enter a password length of 8 to 16", Toast.LENGTH_SHORT).show();
                    hideProgressBar();
                    return;
                }

                if (password.equals(repassword) == false) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    hideProgressBar();
                    return;
                }

                user = FirebaseAuth.getInstance().getCurrentUser();

                AuthCredential credential = EmailAuthProvider
                        .getCredential(user.getEmail(), cPw);

                // Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    user.updatePassword(password)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.d("update password", "User password updated.");

                                                        Toast.makeText(PasswordActivity.this,
                                                                "Password has been updated",
                                                                Toast.LENGTH_SHORT).show();
                                                        hideProgressBar();

                                                        startActivity(new Intent(PasswordActivity.this, UserProfileActivity.class));
                                                        finish();
                                                    }
                                                    else {
                                                        Toast.makeText(PasswordActivity.this,
                                                                "Password not updated please try again later",
                                                                Toast.LENGTH_SHORT).show();
                                                        hideProgressBar();
                                                    }
                                                }
                                            });
                                } else {
                                    Toast.makeText(PasswordActivity.this,
                                            "Wrong password, please retype password",
                                            Toast.LENGTH_SHORT).show();
                                    hideProgressBar();
                                }
                            }
                        });
            }
        });

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

    public void setProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void hideProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
