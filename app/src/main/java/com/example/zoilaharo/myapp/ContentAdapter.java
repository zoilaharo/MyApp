package com.example.zoilaharo.myapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
    // Set numbers of List in RecyclerView.
    private static final int LENGTH = 2;
//    private static final int LENGTH = 18;

    public ContentAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // no-op
    }

    @Override
    public int getItemCount() {
        return LENGTH;
    }
}