package com.example.newsappp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TechFragment extends Fragment {
    String api = "83ba88673b6146dcb6e13fc814647adf";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = HomeFragment.country;
    private String category = "technology";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        country = HomeFragment.country;
        View v = inflater.inflate(R.layout.technologyfragment, container, false);
        modelClassArrayList = new ArrayList<>();
        RecyclerView recyclerviewoftech = v.findViewById(R.id.recyclerviewoftech);
        recyclerviewoftech.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelClassArrayList);
        recyclerviewoftech.setAdapter(adapter);

        findNews();
        return v;
    }

    private void findNews() {
        Log.d("TechFragment", "Fetching news");
        ApiUtilities.getApiInterface().getNews(country, category, api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful() && response.body() != null) {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                    Log.d("TechFragment", "News fetched and updated");
                } else {
                    Log.e("TechFragment", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {
                Log.e("TechFragment", "Failed to fetch news", t);
            }
        });
    }
}



