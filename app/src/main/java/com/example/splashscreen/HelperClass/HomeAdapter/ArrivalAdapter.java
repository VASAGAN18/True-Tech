package com.example.splashscreen.HelperClass.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreen.R;

import java.util.ArrayList;

public class ArrivalAdapter extends RecyclerView.Adapter<ArrivalAdapter.ArrivalViewHolder> {

    ArrayList<ArrivalHelperClass> ArrivalLocations;

    public ArrivalAdapter(ArrayList<ArrivalHelperClass> arrivalLocations) {
        ArrivalLocations = arrivalLocations;
    }

    @NonNull
    @Override
    public ArrivalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_arrival_card_design, parent, false);
        ArrivalViewHolder arrivalViewHolder = new ArrivalViewHolder(view);
        return arrivalViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArrivalViewHolder holder, int position) {

        ArrivalHelperClass arrivalHelperClass = ArrivalLocations.get(position);

        holder.image.setImageResource(arrivalHelperClass.getImage());
        holder.title.setText(arrivalHelperClass.getTitle());
        holder.desc.setText(arrivalHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {

        return ArrivalLocations.size();
    }

    public static class ArrivalViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;

        public ArrivalViewHolder(@NonNull View itemView) {
            super(itemView);

//            Hooks

            image  = itemView.findViewById(R.id.newarrival_cardImage);
            title  = itemView.findViewById(R.id.newarrival_cardTitle);
            desc  = itemView.findViewById(R.id.newarrival_cardDescription);
        }
    }
}
