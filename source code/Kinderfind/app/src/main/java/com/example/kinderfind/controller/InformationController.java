package com.example.kinderfind.controller;

import androidx.annotation.NonNull;

import com.example.kinderfind.models.Kindergarten;
import com.example.kinderfind.models.KindergartenServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InformationController {

    private List<KindergartenServices> kindergartenServices = new ArrayList<>();
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceKindergarten;

    public interface DataStatus{
        void DataIsLoaded(List<KindergartenServices> kindergartens, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public InformationController(String center_code){
        //mReferenceKindergarten = FirebaseDatabase.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceKindergarten = mDatabase.getReference("kindergarten_services").child(center_code);
    }

    public void readKindergarten(final DataStatus dataStatus){
        mReferenceKindergarten.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kindergartenServices.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    KindergartenServices kindergarten = keyNode.getValue(KindergartenServices.class);
                    kindergartenServices.add(kindergarten);
                }
                dataStatus.DataIsLoaded(kindergartenServices, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
