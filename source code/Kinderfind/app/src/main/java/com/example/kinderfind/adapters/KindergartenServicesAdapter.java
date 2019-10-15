package com.example.kinderfind.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kinderfind.R;
import com.example.kinderfind.models.KindergartenServices;

import java.util.ArrayList;
import java.util.List;

public class KindergartenServicesAdapter extends RecyclerView.Adapter<KindergartenServicesAdapter.KindergartenServiceHolder> {

    private List<KindergartenServices> kindergartenServices;
    private List<String> mkeys;

    public KindergartenServicesAdapter(List<KindergartenServices> kindergartenServices, List<String> mkeys) {
        this.kindergartenServices = kindergartenServices;
        this.mkeys = mkeys;
    }

    @NonNull
    @Override
    public KindergartenServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kindergarten_services_item, parent, false);
        return new KindergartenServiceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KindergartenServiceHolder holder, int position) {
        KindergartenServices currentService = kindergartenServices.get(position);
        holder.levelOffered.setText(currentService.getLevels_offered());
        holder.fee_pr.setText(currentService.getFee_pr());
        holder.fee_sg.setText(currentService.getFees_sc());
        holder.programHour.setText(currentService.getProgramme_hours());
        holder.secondLanguage.setText(currentService.getSecond_languages_offered());
        holder.registrationFee.setText(currentService.getRegistration_fee().toString());
    }

    @Override
    public int getItemCount() {
        return kindergartenServices.size();
    }

    public void setKindergartenServices(List<KindergartenServices> services){
        this.kindergartenServices = services;
        notifyDataSetChanged();
    }

    class KindergartenServiceHolder extends RecyclerView.ViewHolder{
        private TextView levelOffered, programHour, registrationFee, secondLanguage, fee_pr, fee_sg;

        public KindergartenServiceHolder(View itemView){
            super(itemView);
            levelOffered = itemView.findViewById(R.id.levels_offered);
            programHour = itemView.findViewById(R.id.program_hour);
            registrationFee = itemView.findViewById(R.id.registration_fee);
            secondLanguage = itemView.findViewById(R.id.second_language);
            fee_pr = itemView.findViewById(R.id.fee_pr);
            fee_sg = itemView.findViewById(R.id.fee_sg);
        }
    }

}
