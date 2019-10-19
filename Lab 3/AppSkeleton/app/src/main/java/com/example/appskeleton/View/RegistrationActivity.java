package com.example.appskeleton.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appskeleton.Controller.RegistrationController;
import com.example.appskeleton.R;

/**
 * This activity allows a user to register an account using valid information.
 */
public class RegistrationActivity extends AppCompatActivity {

    /**
     * This function will create the fields for email, password, confirm password, username and image.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    /**
     * This function checks if the field is valid. If it is not, an error will pop up.
     * Else, it will call the RegistrationController to register the user to the database
     */
    public void submitRegistration(){
        validateField();
    }

    /**
     * This function check if the fields are valid.
     * If it is not valid, an error will pop up.
     */
    public void validateField(){
        //show error if fields are not valid
        showError();
    }

    /**
     * This function will start the tutorial for the user to learn how to use the app
     */
    public void displayTutorial(){

    }

    /**
     * This function will display the relevant errors
     */
    public void showError(){

    }
}
