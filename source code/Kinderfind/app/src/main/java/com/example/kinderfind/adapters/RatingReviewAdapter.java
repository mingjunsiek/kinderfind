package com.example.kinderfind.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kinderfind.R;
import com.example.kinderfind.models.RatingReview;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class RatingReviewAdapter extends RecyclerView.Adapter<RatingReviewAdapter.RatingReviewHolder> {
    private List<RatingReview> ratingReviewsList = new ArrayList<>();

    @NonNull
    @Override
    public RatingReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item, parent, false);
        return new RatingReviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingReviewHolder holder, int position) {
        RatingReview currentRatingReview = ratingReviewsList.get(position);
        holder.username.setText(currentRatingReview.getUsername());
        holder.review.setText(currentRatingReview.getUsername());
        holder.date.setText(currentRatingReview.getUsername());
        holder.ratingBar.setRating((float)(currentRatingReview.getTotal_rating()));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RatingReviewHolder extends RecyclerView.ViewHolder{
        private TextView username, review, date;
        private ImageView image;
        private MaterialRatingBar ratingBar;

        public RatingReviewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.review_info_name);
            review = itemView.findViewById(R.id.review_info_review);
            date = itemView.findViewById(R.id.review_date);
            image = itemView.findViewById(R.id.review_image);
            ratingBar = itemView.findViewById(R.id.review_info_stars);
        }
    }
}
