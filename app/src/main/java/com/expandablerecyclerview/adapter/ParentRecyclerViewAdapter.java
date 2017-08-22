package com.expandablerecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.expandablerecyclerview.R;
import com.expandablerecyclerview.data.ListData;

import java.util.ArrayList;

/**
 * Created by krunal on 8/22/2017.
 */

public class ParentRecyclerViewAdapter extends RecyclerView.Adapter<ParentRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ListData> mArrayList;

    public ParentRecyclerViewAdapter(Context context, ArrayList<ListData> arrayList) {
        mContext = context;
        mArrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_parent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.parentTitle.setText(mArrayList.get(position).getName());


        holder.childRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.childRecyclerView.setAdapter(new ChildRecyclerViewAdapter(mContext, mArrayList.get(position).getSubCategories()));

        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mArrayList.get(position).isListVisible()) {
                    holder.childRecyclerView.setVisibility(View.GONE);
                    mArrayList.get(position).setListVisible(false);
                } else {
                    holder.childRecyclerView.setVisibility(View.VISIBLE);
                    mArrayList.get(position).setListVisible(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinearLayout;
        RecyclerView childRecyclerView;
        TextView parentTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = itemView.findViewById(R.id.row_ll);
            parentTitle = itemView.findViewById(R.id.txt_title_row_parent);
            childRecyclerView = itemView.findViewById(R.id.rv_child);
        }
    }
}
