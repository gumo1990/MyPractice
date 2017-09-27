package com.example.user.mydemo.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.example.user.mydemo.Beans.Fruits;

/**
 * Created by whq on 2017/8/15.
 */

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHoler> {

    private List<Fruits> mFruitList;

    static class ViewHoler extends RecyclerView.ViewHolder{

        TextView fruitname;
        View fruit;

        public ViewHoler(View itemView) {
            super(itemView);
          /*  fruitname = (TextView) itemView.findViewById(R.id.tv_item);
            fruit = itemView;*/


        }
    }
    @Override
    public ViewHoler onCreateViewHolder(final ViewGroup parent, int viewType) {
       /* View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item,parent,false);
        final ViewHoler holer = new ViewHoler(view);
        holer.fruitname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"点击了第"+mFruitList.get(holer.getAdapterPosition()).getName()+"个",Toast.LENGTH_LONG).show();
            }
        });
        holer.fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"点击了第"+mFruitList.get(holer.getAdapterPosition()).getName()+"个View",Toast.LENGTH_LONG).show();
            }
        });*/
        return null;
    }

    public TextAdapter(List<Fruits> mFruitList) {
        this.mFruitList = mFruitList;
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        Fruits fruits = mFruitList.get(position);
        holder.fruitname.setText(fruits.getName());
    }


    @Override
    public int getItemCount() {
        return mFruitList.size();
    }


}
