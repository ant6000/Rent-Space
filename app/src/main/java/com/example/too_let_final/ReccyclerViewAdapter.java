package com.example.too_let_final;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ReccyclerViewAdapter extends FirebaseRecyclerAdapter<DataModel,ReccyclerViewAdapter.myViewholder>
{

    public ReccyclerViewAdapter(@NonNull FirebaseRecyclerOptions<DataModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull DataModel model) {

        holder.Name.setText(model.getDescription());
        holder.Bed.setText(model.getBed());
        holder.Bath.setText(model.getBath());
        holder.Dining.setText(model.getDining());
        holder.Kitchen.setText(model.getKitchen());
        holder.Location.setText(model.getLocation());
        holder.Rent.setText(model.getRent());

        Glide.with(holder.imageView.getContext()).load(model.getImageUrl()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity =(AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper_,new detailsFragment(model.getDescription(),model.getBed(),model.getBath(),model.getDining(),model.getKitchen(),model.getLocation(),model.getRent(),model.getImageUrl())).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new myViewholder(view);
    }

    public class myViewholder extends RecyclerView.ViewHolder
    {

        ImageView imageView;
        TextView Name,Location,Bed,Bath,Dining,Kitchen,Rent;
        public myViewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_);
            Name = itemView.findViewById(R.id.house_name);
            Location = itemView.findViewById(R.id.house_location_);
            Bed = itemView.findViewById(R.id.bed_n);
            Bath = itemView.findViewById(R.id.bath_n);
            Dining = itemView.findViewById(R.id.dining_N);
            Kitchen = itemView.findViewById(R.id.kitchen_n);
            Rent = itemView.findViewById(R.id.house_rent);
        }
    }
}
