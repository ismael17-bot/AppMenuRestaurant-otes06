package com.materialuiux.store.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.materialuiux.store.R;
import com.materialuiux.store.adapter.holder.Holder_Orders;

public class Ad_Orders extends RecyclerView.Adapter<Holder_Orders> {

    @NonNull
    @Override
    public Holder_Orders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_orders, viewGroup, false);
        return new Holder_Orders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_Orders holder_address, int i) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
