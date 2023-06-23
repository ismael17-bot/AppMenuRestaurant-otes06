package com.project.lacuccina.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.project.lacuccina.MenuActivity;
import com.project.lacuccina.ProductCard;
import com.project.lacuccina.R;
import com.project.lacuccina.WebService;
import com.project.lacuccina.adapter.holder.Holder_Cart_Order;
import com.project.lacuccina.model.CartOrder;
import com.project.lacuccina.model.Food;

import java.util.ArrayList;

public class Ad_Cart_Order extends RecyclerView.Adapter<Holder_Cart_Order> {
    String orderId;
    Context mContext;

    ArrayList<CartOrder> data;

    public Ad_Cart_Order(Context context, ArrayList<CartOrder> data, String orderId) {
        this.mContext = context;
        this.data = data;
        this.orderId = orderId;
    }

    @NonNull
    @Override
    public Holder_Cart_Order onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cart_order, viewGroup, false);
        return new  Holder_Cart_Order(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_Cart_Order holder_cart_order, int i) {
        final CartOrder cart = data.get(i);
        Glide.with(mContext).load(cart.getUrlImage()).into(holder_cart_order.getImageView());
        holder_cart_order.getTitle().setText(cart.getTitle());
        holder_cart_order.getBody().setText(cart.getDesc());
        holder_cart_order.getPrice().setText("R$ " + cart.getPrice());
        holder_cart_order.getId().setText(cart.getId());
        holder_cart_order.getQtd().setText(" " + cart.getQtd());

        ImageView lessBtn = holder_cart_order.itemView.findViewById(R.id.id_less_btn);
        ImageView plusBtn = holder_cart_order.itemView.findViewById(R.id.id_plus_btn);
        LinearLayout cancelBtn = holder_cart_order.itemView.findViewById(R.id.id_cancel_item);

        lessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQtd =  cart.getQtd() - 1;
                holder_cart_order.getQtd().setText(" " + newQtd);
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQtd =  cart.getQtd() + 1;
                holder_cart_order.getQtd().setText(" " + newQtd);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
