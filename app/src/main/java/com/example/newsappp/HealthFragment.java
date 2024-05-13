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

public class HealthFragment extends Fragment {

    String api = "83ba88673b6146dcb6e13fc814647adf";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = "in";
    private String category = "health";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        country = HomeFragment.country;
        View v = inflater.inflate(R.layout.healthfragment, container, false);
        modelClassArrayList = new ArrayList<>();
        RecyclerView recyclerviewofhealth = v.findViewById(R.id.recyclerviewofhealth);
        recyclerviewofhealth.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelClassArrayList);
        recyclerviewofhealth.setAdapter(adapter);

        Log.d("HealthFragment", "Fragment Loaded");
        findNews();
        return v;
    }

    private void findNews() {
        Log.d("HealthFragment", "Fetching news");
        ApiUtilities.getApiInterface().getNews(country, category, api).enqueue(new Callback<mainNews>() {
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful() && response.body() != null) {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                    Log.d("HealthFragment", "News fetched and updated");
                } else {
                    Log.e("HealthFragment", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {
                Log.e("HealthFragment", "Failed to fetch news", t);
            }
        });
    }
}
