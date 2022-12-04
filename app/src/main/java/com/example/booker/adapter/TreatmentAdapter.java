package com.example.booker.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booker.R;
import com.example.booker.model.Treatment;

import java.util.ArrayList;


public class TreatmentAdapter extends RecyclerView.Adapter<TreatmentAdapter.ViewHolder> {

    ArrayList<Treatment> treatments;
    onListItemListener listener;


    public TreatmentAdapter(onListItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TreatmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.service_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TreatmentAdapter.ViewHolder holder, int position) {
        holder.treatment_name.setText(treatments.get(position).getTreatment_name());
        holder.description.setText(treatments.get(position).getDescription());
        holder.price.setText(String.valueOf(treatments.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return treatments.size();
    }

    public void setServiceItems(ArrayList<Treatment> servicesList) {
        this.treatments = servicesList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView treatment_name;
        TextView description;
        TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            treatment_name = itemView.findViewById(R.id.treatment_name);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.treatment_price);
            itemView.setOnClickListener(view -> listener.onClicked(treatments.get(getAdapterPosition())));
        }
    }

    public interface onListItemListener {
        void onClicked(Treatment treatment);
    }
}
