package com.example.too_let_final;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link detailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detailsFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    String Name,Bed,Bath,Dinning,Kitchen,Location,Rent,ImageUrl;
    ImageView imgDetails;

    public detailsFragment() {
        // Required empty public constructor
    }

    public detailsFragment(String Name,String Bed, String Bath, String Dining,String Kitchen,String Location, String Rent,String ImageUrl){

        this.Name = Name;
        this.Bed = Bed;
        this.Bath = Bath;
        this.Dinning = Dining;
        this.Kitchen = Kitchen;
        this.Location = Location;
        this.Rent = Rent;
        this.ImageUrl = ImageUrl;

    }

    public static detailsFragment newInstance(String param1, String param2) {
        detailsFragment fragment = new detailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_details, container, false);
        ImageView img = view.findViewById(R.id.image_holder);
        TextView  Dname = view.findViewById(R.id.houseName);
        TextView  Dbed = view.findViewById(R.id.bed_d);
        TextView  Dbath = view.findViewById(R.id.bath_d);
        TextView  Ddining = view.findViewById(R.id.dining_d);
        TextView  Dkitchen = view.findViewById(R.id.kitchen_d);
        TextView  Dlocation = view.findViewById(R.id.location_d);
        TextView  Drent = view.findViewById(R.id.rent_d);



        Dname.setText(Name);
        Dbed.setText(Bed);
        Dbath.setText(Bath);
        Ddining.setText(Dinning);
        Dkitchen.setText(Kitchen);
        Dlocation.setText(Location);
        Drent.setText(Rent);


        Glide.with(getContext()).load(ImageUrl).into(img);
        return view;
    }

    public void OnbackPraced()
    {
        AppCompatActivity activity =(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper_,new NewsFeedFragment()).addToBackStack(null).commit();
    }

}