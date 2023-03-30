package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.newsapp.models.newsApiResponse;
import com.example.newsapp.models.newsheadlines;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    customAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestManager requestManager = new requestManager(this);
        requestManager.getNewsHeadlines(listener,null);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    private final onFetchDataListener<newsApiResponse> listener = new onFetchDataListener<newsApiResponse>() {
        @Override
        public void onFetchdata(List<newsheadlines> list, String message) {
            showNews(list);
        }
        @Override
        public void onError(String message) {

        }
    };
    private void showNews(List<newsheadlines> list) {
        recyclerView = findViewById(R.id.NewsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new customAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
        finishAffinity();
    }
}