package com.example.user.mydemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.mydemo.R;

import java.util.List;

/**
 * Created by whq on 2018/5/23.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {

    List<String> list;
    Context context;
    OnItemClickListener onItemClickListener;
    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public MainAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /* View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item,
                 parent, false);
        return new MyHolder(view);*/
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.activity_main_item
                , parent, false));
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.tv.setText(list.get(position));
        if(onItemClickListener != null){
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.tv, holder.getLayoutPosition());
                }
            });
            holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.OnItemLongClick(holder.tv, holder.getLayoutPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_main_item);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void OnItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClick){
        this.onItemClickListener = mOnItemClick;
    }
}
