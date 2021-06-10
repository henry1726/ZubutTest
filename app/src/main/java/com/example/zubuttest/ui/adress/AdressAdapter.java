package com.example.zubuttest.ui.adress;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zubuttest.data.entities.AdressEntity;
import com.example.zubuttest.databinding.ItemAdressBinding;

import java.util.ArrayList;
import java.util.List;

public class AdressAdapter extends RecyclerView.Adapter<AdressAdapter.ViewHolder>{

    List<AdressEntity> list = new ArrayList<>();

    void addItems(List<AdressEntity> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public AdressAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemAdressBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        ).getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.itemAdressBinding.setModel(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemAdressBinding itemAdressBinding;
        @SuppressLint("SetTextI18n")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemAdressBinding = DataBindingUtil.bind(itemView);
        }

    }
}
