package com.example.kinderfind.adapters;

import android.app.Activity;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kinderfind.R;
import com.example.kinderfind.activities.InformationActivity;
import com.example.kinderfind.activities.MapsActivity;
import com.example.kinderfind.models.Kindergarten;
import com.google.protobuf.Internal;

import java.util.List;
import java.util.ArrayList;
import android.widget.Filter;
import android.widget.Filterable;

public class KindergartenAdapter extends RecyclerView.Adapter <KindergartenAdapter.KindergartenHolder> {

    private Activity activity;
    private ArrayList<Kindergarten> filteredArrayList = new ArrayList<>();
    private ArrayList<Kindergarten> originalArrayList = new ArrayList<>();

    public KindergartenAdapter(ArrayList<Kindergarten> arrayList, Activity activity){
        this.activity = activity;
        this.originalArrayList = arrayList;
        this.filteredArrayList = arrayList;
    }

    @NonNull
    @Override
    public KindergartenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kindergarten_item, parent, false);
        return new KindergartenHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KindergartenHolder holder, int position) {
        Kindergarten currentKindergarten = originalArrayList.get(position);
        holder.textViewName.setText(currentKindergarten.getCentre_name());
        holder.textViewAdd.setText((currentKindergarten.getCenter_address()));
    }

    @Override
    public int getItemCount() {
        if (filteredArrayList == null)
            return 0;

        return filteredArrayList.size();
    }

    public void setKindergartens(ArrayList<Kindergarten> kindergartens){
        this.originalArrayList = kindergartens;
        notifyDataSetChanged();
    }

    class KindergartenHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewName;
        private TextView textViewAdd;

        public KindergartenHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.kindergarten_name_tv);
            textViewAdd = itemView.findViewById(R.id.kindergarten_add_tv);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            InformationActivity.kindergarten = filteredArrayList.get(getAdapterPosition());
            activity.startActivity(new Intent(activity, InformationActivity.class));
        }
    }
}
