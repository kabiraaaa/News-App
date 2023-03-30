package com.example.newsapp;

import com.example.newsapp.models.newsheadlines;

import java.util.List;

public interface onFetchDataListener<newsApiResponse>{
    void onFetchdata(List<newsheadlines> list, String message);
    void onError(String message);
}
