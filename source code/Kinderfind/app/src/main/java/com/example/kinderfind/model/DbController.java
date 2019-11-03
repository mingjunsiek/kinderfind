package com.example.kinderfind.model;

import android.content.Context;
import android.util.Log;

import com.example.kinderfind.utils.LocalStorage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import com.example.kinderfind.entities.Kindergarten;

public class DbController {

    private DatabaseReference dbRef;
    private FirebaseDatabase database;
    private static final String TAG = "Firebase";
    private static ArrayList<Kindergarten> kindergartenArrayList = new ArrayList<Kindergarten>();

    public interface FirebaseSuccessListener {
        //this is to listen for kindergarten data
        void onKindergartenDataCompleted(boolean isDataCompleted);
    }

    public DbController(){

        database = FirebaseDatabase.getInstance();

    }
    public ArrayList<Kindergarten> getKindergartenArrayList(){
        return kindergartenArrayList;
    }
    public void readDataFromKindergarten(final Context context, final FirebaseSuccessListener firebaseSL){

        dbRef = database.getReference("kindergarten");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                String cCode,cName, cAddress, cContact, cEmail, cWebsite, cLastUpdated,
                        cOrganisation, cPlaceId, cPostalcode, cIsSpark;
                double lat, longt, totalRating;

                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    cCode = (String) messageSnapshot.child("center_code").getValue();
                    cName = (String) messageSnapshot.child("centre_name").getValue();
                    cAddress = (String) messageSnapshot.child("centre_address").getValue();

                    cContact = ( messageSnapshot.child("centre_contact_no").getValue() instanceof String)?
                            (String)messageSnapshot.child("centre_contact_no").getValue():
                            messageSnapshot.child("centre_contact_no").getValue().toString();

                    cEmail = (String) messageSnapshot.child("centre_email_address").getValue();
                    cWebsite = (String) messageSnapshot.child("centre_website").getValue();
                    cLastUpdated = (String) messageSnapshot.child("last_updated").getValue();
                    lat = (double) messageSnapshot.child("latitude").getValue();
                    longt = (double) messageSnapshot.child("longtitude").getValue();
                    cOrganisation = (String) messageSnapshot.child("organisation_type").getValue();
                    cPlaceId= (String) messageSnapshot.child("placeId").getValue();
                    cPostalcode = messageSnapshot.child("postal_code").getValue().toString();
                    cIsSpark = (String) messageSnapshot.child("spark_certified").getValue();

                    kindergartenArrayList.add(new Kindergarten(cCode, cAddress, cContact, cEmail,
                            cName, cWebsite, cLastUpdated, lat, longt, cOrganisation, cPlaceId,
                            cPostalcode, cIsSpark));
                }

                LocalStorage localStorage = new LocalStorage(context);
                localStorage.storeIntoSharedPreferences(kindergartenArrayList);
                firebaseSL.onKindergartenDataCompleted(true);

                //List<Kindergarten> kindergartenArrayList = new ArrayList<>();
//                for (DataSnapshot unit : dataSnapshot.getChildren()){
//                    Kindergarten value = unit.getValue(Kindergarten.class);
                    //System.out.println(value);
                    //kindergartenArrayList.add(value);
//                }
//                long rows = dataSnapshot.getChildrenCount();
//                Log.d(TAG, "onDataChange: " + rows);
//                GenericTypeIndicator<ArrayList<Kindergarten>> t = new GenericTypeIndicator<ArrayList<Kindergarten>>() {};
//                messages = dataSnapshot.getValue(t);
////                System.out.println(messages);
//                Log.d(TAG, "onDataChange: "+ messages);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w( "Failed to read value.", error.toException());
            }
        });

    }

}
