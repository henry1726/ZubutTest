package com.example.zubuttest.ui.adress;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zubuttest.R;
import com.example.zubuttest.data.entities.AdressEntity;
import com.example.zubuttest.databinding.ItemAdressBinding;

import java.util.ArrayList;
import java.util.List;

public class AdressAdapter extends RecyclerView.Adapter<AdressAdapter.ViewHolder> {

    List<AdressEntity> list = new ArrayList<>();
    private OnAdressSelected listener;

    void addItems(List<AdressEntity> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public AdressAdapter(OnAdressSelected listener) {
        this.listener = listener;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        if (position % 2 != 0)
            holder.itemAdressBinding.clAdress.setBackgroundColor(Color.parseColor("#2196f3"));
        else
            holder.itemAdressBinding.clAdress.setBackgroundColor(Color.parseColor("#0000FF"));

        viewHolder.itemAdressBinding.tvName.setText(list.get(position).getName() +" "+ (position+1));
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
            itemAdressBinding.clAdress.setOnClickListener(v -> {
                Log.e("adapter", "Presioné cardview");
                listener.onClickAdress(list.get(getAdapterPosition()).getName(), list.get(getAdapterPosition()).getAdress(), list.get(getAdapterPosition()).getCoordinate());
            });
        }

    }
}
