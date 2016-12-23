package com.example.jack.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 72408 on 2016/12/23.
 */

public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;

    public SimpleAdapter(Context context , List<String>datas) {
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_simple_textview , parent ,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int pos){

        mDatas.add("Inset One");
        notifyItemInserted(pos);
    }

    public void delete(int pos){

        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }



}


class MyViewHolder extends ViewHolder{

    TextView tv ;
    public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.id_recyclerView);
    }
}
