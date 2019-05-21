package com.example.evidenciafinal.adapters;

import android.content.Context;
import android.content.Intent;
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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Jobs> mData;

    public RecyclerViewAdapter(Context mContext, List<Jobs> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.vacantes_row_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, JobDescription.class);
                i.putExtra("op_name", mData.get(viewHolder.getAdapterPosition()).getName_Op());
                i.putExtra("comp_name", mData.get(viewHolder.getAdapterPosition()).getName_Enter());
                i.putExtra("comp_Enter", mData.get(viewHolder.getAdapterPosition()).getComp_Enter());
                i.putExtra("des_Enter", mData.get(viewHolder.getAdapterPosition()).getDes_Enter());
                i.putExtra("id_Open", mData.get(viewHolder.getAdapterPosition()).getId_Open());
                i.putExtra("id_UserPost", mData.get(viewHolder.getAdapterPosition()).getId_UserPost());
                mContext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int i) {
        holder.op_name.setText(mData.get(i).getName_Op());
        holder.comp_Enter.setText(mData.get(i).getComp_Enter());
        holder.comp_name.setText(mData.get(i).getName_Enter());
        holder.des_Enter.setText(mData.get(i).getDes_Enter());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView op_name, comp_name, comp_Enter, des_Enter;
        LinearLayout view_container;

        public MyViewHolder(View itemView){
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            op_name = itemView.findViewById(R.id.op_name);
            comp_name = itemView.findViewById(R.id.comp_name);
            comp_Enter = itemView.findViewById(R.id.comp_Enter);
            des_Enter = itemView.findViewById(R.id.des_Enter);
        }
    }

}
