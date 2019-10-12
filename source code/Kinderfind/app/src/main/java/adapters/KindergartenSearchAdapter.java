package adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import androidx.recyclerview.widget.RecyclerView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import models.Kindergarten;

import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kinderfind.R;

import java.util.ArrayList;

public class KindergartenSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Activity activity;
    private ArrayList<Kindergarten> filteredArrayList;
    private ArrayList<Kindergarten> originalArrayList;
    private ItemFilter mFilter = new ItemFilter();

    public KindergartenSearchAdapter(ArrayList<Kindergarten> arrayList, Activity activity) {
        this.activity = activity;
        this.originalArrayList = arrayList;
        this.filteredArrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kindergarten_row_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder vh = (MyViewHolder) holder;

//        vh.nameTextView.setText(filteredArrayList.get(position).getCentre_name());
//        vh.addressTextView.setText(filteredArrayList.get(position).getCenter_address());
//
//        if (filteredArrayList.get(position).getDistance() < 1000)
//            vh.distanceTextView.setText(filteredArrayList.get(position).getDistance() + "m");
//        else
//            vh.distanceTextView.setText(String.format("%.1fkm", filteredArrayList.get(position).getDistance() / 1000));
//
//        final Transformation transformation = new RoundedCornersTransformation(8, 0, RoundedCornersTransformation.CornerType.LEFT);
//
//        vh.materialRatingBar.setRating((float) filteredArrayList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        if (filteredArrayList == null)
            return 0;

        return filteredArrayList.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameTextView, addressTextView, distanceTextView;
        private MaterialRatingBar materialRatingBar;

        private MyViewHolder(View view) {
            super(view);

            nameTextView = view.findViewById(R.id.textview_name);
            distanceTextView = view.findViewById(R.id.textview_distance);
            addressTextView = view.findViewById(R.id.textview_address);
            materialRatingBar = view.findViewById(R.id.material_rating_bar);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            DetailActivity.detail = filteredArrayList.get(getAdapterPosition());
//            activity.startActivity(new Intent(activity, DetailActivity.class));
        }
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            ArrayList<Kindergarten> matches = new ArrayList<>();

            if (filterString.equalsIgnoreCase(""))
            {
                results.values = originalArrayList;
                results.count = originalArrayList.size();
            }
            else
            {
                for (int i = 0; i < originalArrayList.size(); i++) {
                    String name, address;

                    if (originalArrayList.get(i).getCentre_name() == null)
                        name = "";
                    else
                        name = originalArrayList.get(i).getCentre_name().toLowerCase();

                    if (originalArrayList.get(i).getCenter_address() == null)
                        address = "";
                    else
                        address = originalArrayList.get(i).getCenter_address().toLowerCase();

                    if (name.contains(filterString) || address.contains(filterString)) {
                        matches.add(originalArrayList.get(i));
                    }
                }

                results.values = matches;
                results.count = matches.size();
                Log.d("searchadapter", "performFiltering: " + results.count);
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredArrayList = (ArrayList<Kindergarten>) results.values;
            notifyDataSetChanged();
        }

    }

}
