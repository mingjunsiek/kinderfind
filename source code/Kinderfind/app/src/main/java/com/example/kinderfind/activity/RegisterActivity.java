package com.example.kinderfind.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.kinderfind.R;
import com.example.kinderfind.utils.InternetReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class RegisterActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;
    private static final String TAG = "EmailPassword";
    private EditText inputEmail, inputPassword, inputRePassword, inputUsername;
    private Button btnRegister, btnChooseImage;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private ImageView imageView;
    private Uri imageUri;
    private StorageReference mStorageRef;
    private InternetReceiver internetReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_interface);

        // Register InternetReceiver
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        internetReceiver = new InternetReceiver(cm);

        if(!internetReceiver.checkForInternet())
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();

        auth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference("profile_pic");

        btnRegister = findViewById(R.id.regRegisterBtn);
        inputEmail = findViewById(R.id.regEmailText);
        inputPassword = findViewById(R.id.regPwdText);
        inputRePassword = findViewById(R.id.regRepwdText);
        inputUsername = findViewById(R.id.regUserNameTxt);
        progressBar = findViewById(R.id.regProgressBar);
        btnChooseImage = findViewById(R.id.register_image_upload);
        imageView = findViewById(R.id.register_image_view);

        Glide.with(RegisterActivity.this)
                .load(R.mipmap.ic_launcher)
                .apply(RequestOptions.circleCropTransform())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        btnChooseImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openFileChooser();
            }
        });

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
                                Toast.makeText(RegisterActivity.this, "Account has been successfully created", Toast.LENGTH_SHORT).show();
                                if (!task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d(TAG, "createUserWithEmail:success");
                                    final FirebaseUser user = auth.getCurrentUser();
                                    uploadFile(user);
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
                                                startActivity(new Intent(RegisterActivity.this, TutorialActivity.class));
                                                finish();
                                                }
                                            });
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

    private void openFileChooser(){

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageUri = null;
            if (data != null) {
                imageUri = data.getData();
                Log.i(TAG, "Uri: " + imageUri.toString());
                Glide.with(RegisterActivity.this)
                        .load(imageUri)
                        .apply(RequestOptions.circleCropTransform())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
            }
        }
    }
    private void uploadFile(FirebaseUser user){
        if(imageUri == null)
            imageUri = Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/"+R.drawable.ic_default_pic);

        StorageReference fileReference = mStorageRef.child(user.getUid());

        fileReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
//    private String getFileExtension(Uri uri){
//        ContentResolver cr = getContentResolver();
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(cr.getType(uri));
//    }


}
