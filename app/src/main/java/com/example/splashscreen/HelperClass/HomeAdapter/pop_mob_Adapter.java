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

public class pop_mob_Adapter extends RecyclerView.Adapter<pop_mob_Adapter.pop_mob_ViewHolder> {

    ArrayList<pop_mob_HelperClass> pop_mob_Locations;

    public pop_mob_Adapter(ArrayList<pop_mob_HelperClass> pop_mob_Locations) {
        this.pop_mob_Locations = pop_mob_Locations;
    }

    @NonNull
    @Override
    public pop_mob_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_mobilescard_design, parent, false);
        pop_mob_ViewHolder pop_mob_ViewHolder = new pop_mob_ViewHolder(view);
        return pop_mob_ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull pop_mob_ViewHolder holder, int position) {

        pop_mob_HelperClass pop_mob_HelperClass = pop_mob_Locations.get(position);

        holder.image.setImageResource(pop_mob_HelperClass.getImage());
        holder.title.setText(pop_mob_HelperClass.getTitle());
        holder.desc.setText(pop_mob_HelperClass.getDescription());

    }

    @Override
    public int getItemCount() {

        return pop_mob_Locations.size();
    }

    public static class pop_mob_ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, desc;

        public pop_mob_ViewHolder(@NonNull View itemView) {
            super(itemView);

//            Hooks

            image = itemView.findViewById(R.id.popular_mobiles_image);
            title = itemView.findViewById(R.id.popular_mobiles_title);
            desc = itemView.findViewById(R.id.popular_mobiles_desc);
        }
    }
}
