package com.example.evidenciafinal.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evidenciafinal.JobDescription;
import com.example.evidenciafinal.R;
import com.example.evidenciafinal.model.Jobs;

import java.util.List;

public class RecyclerViewApplicantsAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Jobs> mData;

    public RecyclerViewApplicantsAdapter(Context mContext, List<Jobs> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
