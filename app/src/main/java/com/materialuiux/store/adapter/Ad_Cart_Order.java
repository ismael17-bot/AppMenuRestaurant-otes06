package com.materialuiux.store.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.materialuiux.store.R;
import com.materialuiux.store.adapter.holder.Holder_Cart_Order;

public class Ad_Cart_Order extends RecyclerView.Adapter<Holder_Cart_Order> {


    @NonNull
    @Override
    public Holder_Cart_Order onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cart_order, viewGroup, false);
        return new  Holder_Cart_Order(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_Cart_Order holder_cart_order, int i) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

}
