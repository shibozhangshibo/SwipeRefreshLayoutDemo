package com.shibo.zzz.swiperefreshlayoutdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zzz on 2016/9/12.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    List<String> mDatas;
    private LayoutInflater inflater;
    private OnItemClickListener listener;


    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;

    }

    public MyAdapter(List<String> datas) {
        this.mDatas = datas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    holder.textView.setText(mDatas.get(position));
    }


    //返回数据源的个数
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int position,String city){
        mDatas.add(position,city);
        notifyItemInserted(position);

    }
    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if (listener !=null){
                        listener.onClick(v,getLayoutPosition(),
                                mDatas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    interface OnItemClickListener{
        void onClick(View v, int position, String city);
    }

}
