package com.example.user.mydemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.mydemo.R;

import java.util.ArrayList;

/**
 * Created by whq on 2017/9/7.
 */

public class FiveAdapter extends RecyclerView.Adapter<FiveAdapter.ItemViewHolder> {

    private Context mContext;
    private ArrayList<String> mListdata;
    private boolean[] isclick;//记录哪一个item是否选中
    public ArrayList<String> listDatas = new ArrayList<>();

    public FiveAdapter(Context mContext, ArrayList<String> mListdata) {
        this.mContext = mContext;
        this.mListdata = mListdata;
        this.isclick = new boolean[mListdata.size()];
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_five_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.tv_five_item.setText(mListdata.get(position));
        holder.cb_item.setOnCheckedChangeListener(null);
        holder.cb_item.setChecked(isclick[position]);
        holder.cb_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isclick[position] = isChecked;
                if (isChecked) {
                    listDatas.add(position + "");
                }/*else{
                    listDatas.remove(position);
                }*/
            }
        });
        holder.btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了第" + position + "个item", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mListdata.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_five_item;
        Button btn_select;
        CheckBox cb_item;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cb_item = (CheckBox) itemView.findViewById(R.id.cb_item);
            tv_five_item = (TextView) itemView.findViewById(R.id.tv_five_item);
            btn_select = (Button) itemView.findViewById(R.id.btn_five_item);
        }
    }

    public void remove() {
        if (listDatas.size() > 0) {
            for (int i = 0; i < listDatas.size(); i++) {
                Log.d("whq", "--------" + listDatas.get(i));
                mListdata.remove(listDatas.get(i));


            }
        }
        if (listDatas != null) {
            listDatas = null;
        }
        notifyDataSetChanged();
        if (isclick != null) {
            isclick = null;
            isclick = new boolean[mListdata.size()];
        }
        listDatas = new ArrayList<>();
    }

}
