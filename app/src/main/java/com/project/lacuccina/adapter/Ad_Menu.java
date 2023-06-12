package com.project.lacuccina.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.project.lacuccina.ProductCard;
import com.project.lacuccina.R;
import com.project.lacuccina.adapter.holder.Holder_Menu;
import com.project.lacuccina.model.Food;

import java.util.ArrayList;

public class Ad_Menu extends RecyclerView.Adapter<Holder_Menu> {

    Context mContext;

    ArrayList<Food> data;

    public Ad_Menu(Context context, ArrayList<Food> data) {
        this.mContext = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder_Menu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu, viewGroup, false);
        return new Holder_Menu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_Menu holder_arrivals, int position) {
        final Food food = data.get(position);
        Glide.with(mContext).load(food.getUrlImage()).into(holder_arrivals.getImageView());
        holder_arrivals.getTitle().setText(food.getTitle());
        holder_arrivals.getBody().setText(food.getDesc());
        holder_arrivals.getPrice().setText("R$ " + food.getPrice());
        holder_arrivals.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductCard.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
