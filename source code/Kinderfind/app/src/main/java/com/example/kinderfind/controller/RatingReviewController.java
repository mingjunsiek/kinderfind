package com.example.kinderfind.controller;

import android.media.Rating;

import androidx.annotation.NonNull;

import com.example.kinderfind.models.RatingReview;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RatingReviewController {
    private List<RatingReview> RatingReviewService = new ArrayList<>();
    private RatingReview ratingReview;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceRatingReview;

    public interface DataStatus{
        void DataIsLoaded(RatingReview ratingReviews, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public RatingReviewController(String center_code){
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceRatingReview = mDatabase.getReference("rating_review").child(center_code);
    }

    public void readRatingReview(final String userid, final DataStatus dataStatus){
        mReferenceRatingReview.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> keys = new ArrayList<>();
                if(dataSnapshot.exists()) {
//                    RatingReviewService.clear();
//                    keys.add(dataSnapshot.getKey());
                    ratingReview = dataSnapshot.child(userid).getValue(RatingReview.class);
//                    RatingReviewService.add(ratingReview);
                }
                else
                    ratingReview = null;
//                List<String> keys = new ArrayList<>();
//                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
//                    keys.add(keyNode.getKey());
//                    System.out.println("readRatingReview "+keyNode.getValue());
//                    //RatingReview ratingReview = keyNode.getValue(RatingReview.class);
//                    RatingReview ratingReview = new RatingReview();
//                    //keyNode.getKey().
//                    //ratingReview.setManpower_rating(keyNode.child("manpower_rating").getValue(Double.class));
//                    System.out.println("Manpower: "+ratingReview.getManpower_rating());
//
//                    ratingReview.setTimestamp(keyNode.child("timestamp").getValue(Long.class));
//                    System.out.println("Manpower: "+ratingReview.getManpower_rating());
//                    RatingReviewService.add(ratingReview);
//                }
                System.out.println("RatingReview: "+ ratingReview);
                dataStatus.DataIsLoaded(ratingReview, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addRatingReview(RatingReview ratingReview, String user_id, final DataStatus dataStatus){
        //String key = mReferenceRatingReview.push().getKey();
        mReferenceRatingReview.child(user_id).setValue(ratingReview)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }

    public void updateRatingReview(RatingReview ratingReview, String user_id, final DataStatus dataStatus){
        mReferenceRatingReview.child(user_id).setValue(ratingReview)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }

}
