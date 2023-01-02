package com.example.too_let_final;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toolbar;

import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.Query;



public class NewsFeedFragment extends Fragment {
    private  static final String ARG_PARAM1 = "param1";
    private  static final String ARG_PARAM2 = "param2";


    private RecyclerView recyclerView;
    ReccyclerViewAdapter adapter;
    Toolbar toolbar;
    MenuItem menuItem;
    SearchView searchView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_news_feed, container, false);

        recyclerView=(RecyclerView) view.findViewById(R.id.recview_);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<DataModel> options =
                new FirebaseRecyclerOptions.Builder<DataModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("NewUserData"), DataModel.class)
                        .build();

        adapter =new ReccyclerViewAdapter(options);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        menuItem = menu.findItem(R.id.search_id);
        super.onCreateOptionsMenu(menu, inflater);


        searchView =(SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });
    }

    private void processSearch(String s) {

        FirebaseRecyclerOptions<DataModel> options =
                new FirebaseRecyclerOptions.Builder<DataModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("NewUserData").orderByChild("location").startAt(s).endAt(s +"\uf8ff"), DataModel.class)
                        .build();

        adapter = new ReccyclerViewAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}