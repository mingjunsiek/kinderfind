package com.example.kinderfind.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kinderfind.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private EditText inputEmail, inputPassword, inputRePassword, inputUsername;
    private Button btnRegister;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        btnRegister = findViewById(R.id.regRegisterBtn);
        inputEmail = findViewById(R.id.regEmailText);
        inputPassword = findViewById(R.id.regPwdText);
        inputRePassword = findViewById(R.id.regRepwdText);
        inputUsername = findViewById(R.id.regUserNameTxt);
        progressBar = findViewById(R.id.regProgressBar);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String repassword = inputRePassword.getText().toString().trim();
                final String username = inputUsername.getText().toString().trim();

                if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(), "Enter a valid email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(repassword)) {
                    Toast.makeText(getApplicationContext(), "Re-enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 8 || password.length() > 16) {
                    Toast.makeText(getApplicationContext(), "Please enter a password length of 8 to 16", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.equals(repassword) == false) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(), "Enter username!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d(TAG, "createUserWithEmail:success");
                                    final FirebaseUser user = auth.getCurrentUser();

                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(username)
                                            .build();

                                    user.updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    progressBar.setVisibility(View.GONE);
                                                    Log.d(TAG, "User profile updated.");
                                                    sendVerification(user);
                                                    startActivity(new Intent(RegisterActivity.this, MapsActivity.class));
                                                    finish();
                                                }
                                            });
                                }
                            }
                        });
            }
        });
    }

    private void sendVerification(final FirebaseUser user){
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(RegisterActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
