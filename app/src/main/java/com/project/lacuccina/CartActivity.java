package com.project.lacuccina;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.lacuccina.adapter.Ad_Cart_Order;
import com.project.lacuccina.adapter.Ad_Menu;
import com.project.lacuccina.adapter.Ad_Orders;
import com.project.lacuccina.model.CartOrder;
import com.project.lacuccina.model.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class CartActivity extends AppCompatActivity {

    Ad_Cart_Order ad_card_order;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Button  continueButton;
    Button  backButton;
    String orderId;
    String cartArray[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");

        setContentView(R.layout.activity_cart);
        TextView id = findViewById(R.id.id_Title_tx);
        SearchCart searchCart = new SearchCart();

        CloseOrder closeOrder = new CloseOrder();

        //searchCart.execute("http://10.0.2.2:8081/pedido/"+orderId);
        searchCart.execute("http://10.0.2.2:8081/menu");

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

        recyclerView = findViewById(R.id.id_recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        continueButton = findViewById(R.id.id_continue);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, OrdersActivity.class);
                closeOrder.execute("http://10.0.2.2:8081/pedido/finish", orderId);
                startActivity(intent);
            }
        });

        backButton = findViewById(R.id.id_backMenu);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MenuActivity.class);
                intent.putExtra("orderId", orderId);
                startActivity(intent);
            }
        });
    }

    public void setItensMenu(String menu) {
        try {
            JSONArray jsonArray = new JSONArray(menu);
            cartArray = new String[jsonArray.length()][5];
            // Percorrer o JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                cartArray[i][0] = String.valueOf(jsonObject.getInt("id"));
                cartArray[i][1] = jsonObject.getString("description");
                cartArray[i][2] = jsonObject.getString("title");
                cartArray[i][3] = jsonObject.getString("url");
                cartArray[i][4] = String.valueOf(jsonObject.getInt("price"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ad_card_order = new Ad_Cart_Order(this, getData(cartArray), orderId);
        recyclerView.setAdapter(ad_card_order);

    }

    private class CloseOrder extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String idPedido = strings[1];
            String requestUrl = strings[0];

            // Para adicionar ao pedido
            JSONObject postData = new JSONObject();

            try {
                postData.put("orderId", idPedido);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String retorno = WebService.postWS(requestUrl, String.valueOf(postData));
            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {
            setItensMenu(s);
        }
    }

    private class SearchCart extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String retorno = WebService.buscaWS(strings[0]);
            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {
            setItensMenu(s);
        }
    }

    private ArrayList<CartOrder> getData(String[][] cartArray) {

        ArrayList<CartOrder> arrayList = new ArrayList<>();

        // Percorrer o JSONArray
        for (int i = 0; i < cartArray.length; i++) {

            int url;

            if (Objects.equals(cartArray[i][3], "R.drawable.fetuccine")) {
                url = R.drawable.fetuccine;
            } else if (cartArray[i][3].trim().equals("R.drawable.molho_sugo")) {
                url = R.drawable.molho_sugo;
            } else if (Objects.equals(cartArray[i][3], "R.drawable.nhoque_4_queijos")) {
                url = R.drawable.nhoque_4_queijos;
            } else if (Objects.equals(cartArray[i][3], "R.drawable.carbonara")) {
                url = R.drawable.carbonara;
            } else if (Objects.equals(cartArray[i][3], "R.drawable.nhoque_fughi")) {
                url = R.drawable.nhoque_fughi;
            }else{
                url = R.drawable.bolonhesa;
            }

            arrayList.add(new CartOrder(url, cartArray[i][2], cartArray[i][1], Integer.parseInt(cartArray[i][4]), cartArray[i][0], 2));
        }

        return arrayList;
    }
}
