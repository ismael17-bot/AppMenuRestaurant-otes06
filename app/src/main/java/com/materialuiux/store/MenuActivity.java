package com.materialuiux.store;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.materialuiux.store.adapter.Ad_Menu;
import com.materialuiux.store.model.Food;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    GridLayoutManager gridLayoutManager;
    Ad_Menu ad_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerview = findViewById(R.id.recyclerview);
        gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        ad_menu = new Ad_Menu(this, getData());

        recyclerview.setAdapter(ad_menu);

    }

    private ArrayList<Food> getData() {
        ArrayList<Food> arrayList = new ArrayList<>();
        arrayList.add(new Food(R.drawable.fetuccine, "Fetuccine Alfredo", "Creme de leite fresco, queijo parmesão ralado e manteiga", 29));
        arrayList.add(new Food(R.drawable.bolonhesa, "Macarrão à Bolonhesa", "Carne bovina moída, tomates, cebolas e semais temperos para compor um delicioso molho", 32));
        arrayList.add(new Food(R.drawable.carbonara, "Macarrão Carbonara", "Ovos, bacon e queijo parmesão ralado", 32));
        arrayList.add(new Food(R.drawable.molho_sugo, "Macarrão ao Molho Sugo", "Molho feito a base de tomates frescos e temperos", 28));
        arrayList.add(new Food(R.drawable.nhoque_4_queijos, "Nhoque 4 Queijos", "Parmesão, provolone, mozzarella e gorgonzola", 34));
        arrayList.add(new Food(R.drawable.nhoque_fughi, "Nhoque ao Molho Fughi", "Cogumelos Fughi, creme de leite e temperos frescos", 34));
        return arrayList;
    }

}
