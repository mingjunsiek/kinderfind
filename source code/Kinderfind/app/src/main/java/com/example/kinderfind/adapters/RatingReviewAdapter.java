package com.example.kinderfind.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.kinderfind.R;
import com.example.kinderfind.activities.InformationActivity;
import com.example.kinderfind.activities.RegisterActivity;
import com.example.kinderfind.models.RatingReview;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class RatingReviewAdapter extends RecyclerView.Adapter<RatingReviewAdapter.RatingReviewHolder> {
    private List<RatingReview> ratingReviewsList = new ArrayList<>();
    private List<String> mkeys;
    Context context;

    public RatingReviewAdapter(List<RatingReview> ratingReviews, List<String> mkeys, Context context) {
        this.ratingReviewsList = ratingReviews;
        this.mkeys = mkeys;
        this.context = context;
    }

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
        Glide.with(context)
                .load(Uri.parse(currentRatingReview.getUser_image()))
                .apply(RequestOptions.circleCropTransform())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return ratingReviewsList.size();
    }

    public void setRatingReviewsList(List<RatingReview> ratingReviews){
        this.ratingReviewsList = ratingReviews;
        notifyDataSetChanged();
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
