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

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder> {

    ArrayList<TopRatedHelperClass> TopRatedLocations;

    public TopRatedAdapter(ArrayList<TopRatedHelperClass> TopRatedLocations) {
        this.TopRatedLocations = TopRatedLocations;
    }

    @NonNull
    @Override
    public TopRatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toprated_card_design, parent, false);
        TopRatedViewHolder TopRatedViewHolder = new TopRatedViewHolder(view);
        return TopRatedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedViewHolder holder, int position) {

        TopRatedHelperClass TopRatedHelperClass = TopRatedLocations.get(position);

        holder.image.setImageResource(TopRatedHelperClass.getImage());
        holder.title.setText(TopRatedHelperClass.getTitle());

    }

    @Override
    public int getItemCount() {

        return TopRatedLocations.size();
    }

    public static class TopRatedViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        public TopRatedViewHolder(@NonNull View itemView) {
            super(itemView);

//            Hooks

            image = itemView.findViewById(R.id.toprated_image);
            title = itemView.findViewById(R.id.toprated_title);
        }
    }
}
