package com.expandablerecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.expandablerecyclerview.R;

import java.util.ArrayList;

/**
 * Created by krunal on 8/22/2017.
 */

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> mArrayList;
    private OnSubItemClick mOnSubItemClick;

    public ChildRecyclerViewAdapter(Context context, ArrayList<String> subCategories) {
        mOnSubItemClick = (OnSubItemClick) context;
        mArrayList = subCategories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.childText.setText(mArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView childText;

        public ViewHolder(View itemView) {
            super(itemView);
            childText = itemView.findViewById(R.id.txt_row_child);
            childText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnSubItemClick.onSubItemClick(mArrayList.get(getAdapterPosition()));
                }
            });
        }
    }
}
