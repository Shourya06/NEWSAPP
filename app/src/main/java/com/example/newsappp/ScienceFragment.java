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

public class ScienceFragment extends Fragment {

    String api = "83ba88673b6146dcb6e13fc814647adf";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = "in";
    private String category = "science";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        country = HomeFragment.country;
        View v = inflater.inflate(R.layout.sciencefragment, container, false);
        modelClassArrayList = new ArrayList<>();
        System.out.println("HELLO SCIENCE");
        RecyclerView recyclerviewofscience = v.findViewById(R.id.recyclerviewofscience);
        recyclerviewofscience.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelClassArrayList);
        recyclerviewofscience.setAdapter(adapter);

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
                    Log.d("ScienceFragement", "News fetched and updated");
                } else {
                    Log.e("ScienceFragement", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {
                Log.e("ScienceFragement", "Failed to fetch news", t);
            }
        });
    }


}