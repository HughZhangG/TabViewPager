package com.gu.cheng.tabviewpager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Created by gc on 2016/8/18.
 */
public class MenuAdapter extends SwipeMenuAdapter<MenuAdapter.MenuHolder> {

    private List<String> mDatas;

    public void setData(List<String> list){
         mDatas = list;
        notifyDataSetChanged();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_default,parent,false);
    }

    @Override
    public MenuHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new MenuHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        holder.setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    static class MenuHolder extends RecyclerView.ViewHolder {
        private  TextView mText;

        public MenuHolder(View itemView) {
            super(itemView);
            this.mText = (TextView) itemView.findViewById(R.id.id_gc_item_tv);
        }

        public void setData(String data) {
            this.mText.setText(data);
        }
    }
}
