package com.gu.cheng.tabviewpager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gc on 2016/8/18.
 */
public class XRecyclerAdapter extends RecyclerView.Adapter<XRecyclerAdapter.ViewHolder> {
    private List<String> mDataList;
    private Context mContext;


    public XRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<String> list){
        mDataList = list;
        notifyDataSetChanged();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_default, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.id_gc_item_tv);
        }

        public void setData(String data) {
            textView.setText(data);
        }
    }
}
