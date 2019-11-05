package com.example.kinderfind.model;

import androidx.annotation.NonNull;

import com.example.kinderfind.entities.KindergartenServices;
import com.example.kinderfind.entities.RatingReview;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class informationModel {

    private List<KindergartenServices> kindergartenServices = new ArrayList<>();
    private List<RatingReview> ratingReviewsList = new ArrayList<>();
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceKindergarten;
    private DatabaseReference mReferenceRatingReview;

    public interface KindergartenDataStatus{
        void DataIsLoaded(List<KindergartenServices> kindergartens, List<String> keys);
//        void DataIsInserted();
//        void DataIsUpdated();
//        void DataIsDeleted();
    }

    public interface RatingReviewDataStatus{
        void DataIsLoaded(List<RatingReview> ratingReviews, List<String> keys);
//        void DataIsInserted();
//        void DataIsUpdated();
    }

    public informationModel(String center_code){
        //mReferenceKindergarten = FirebaseDatabase.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceKindergarten = mDatabase.getReference("kindergarten_services").child(center_code);
        mReferenceRatingReview = mDatabase.getReference("rating_review").child(center_code);
    }

    public void readKindergarten(final KindergartenDataStatus dataStatus){
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

    public void readRatingReview(final RatingReviewDataStatus ratingReviewDataStatus){
        mReferenceRatingReview.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ratingReviewsList.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    RatingReview ratingReview = keyNode.getValue(RatingReview.class);
                    ratingReviewsList.add(ratingReview);
                }
                Collections.sort(ratingReviewsList, new Comparator<RatingReview>() {
                    public int compare(RatingReview o1, RatingReview o2) {
                        if (new Date(o1.getTimestamp()) == null || new Date(o2.getTimestamp()) == null)
                            return 0;
                        return new Date(o1.getTimestamp()).compareTo(new Date(o2.getTimestamp()));
                    }
                });
                Collections.reverse(ratingReviewsList);
                ratingReviewDataStatus.DataIsLoaded(ratingReviewsList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
