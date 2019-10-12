package com.example.kinderfind.adapters;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.example.kinderfind.models.Kindergarten;

public class LocalStorage {

    private Context context;

    public LocalStorage(Context context){
        this.context = context;
    }

    public void storeIntoSharedPreferences(ArrayList<Kindergarten> arraylistKindergarten){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arraylistKindergarten);
        editor.putString("kindergarten arraylist", json);
        editor.apply();
    }

    public ArrayList<Kindergarten> getFromSharedPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        Gson gson = new Gson();
        String json = preferences.getString("kindergarten arraylist", null);
        Type type = new TypeToken<ArrayList<Kindergarten>>() {}.getType();
        return gson.fromJson(json, type);
    }

}
