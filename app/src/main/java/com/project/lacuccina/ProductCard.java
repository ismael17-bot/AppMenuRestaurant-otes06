package com.project.lacuccina;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.lacuccina.adapter.Ad_Menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Map;
import java.util.Objects;

public class ProductCard extends AppCompatActivity {

    private RelativeLayout checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_product_card);

        String idProduct = intent.getStringExtra("id_product");

        SearchItem searchMenu = new SearchItem();
        searchMenu.execute("http://10.0.2.2:8081/menu/food/"+idProduct);

        TextView addButton;
        addButton = findViewById(R.id.id_add_to_cart);
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

        checkout = findViewById(R.id.id_checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductCard.this, CartActivity.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductCard.this,CartActivity.class);
                startActivity(intent);
            }
        });

    }

    public void setItensMenu(String menu) {
        ImageView imageView = findViewById(R.id.id_img_item);
        TextView txtTitulo = findViewById(R.id.id_Product_Name);
        TextView txtDesc = findViewById(R.id.id_Desc_Item);
        TextView txtPrice = findViewById(R.id.id_product_price);

        try {

            JSONObject jsonObject = new JSONObject(menu);
            int url;

            if (Objects.equals(jsonObject.getString("url"), "R.drawable.fetuccine")) {
                url = R.drawable.fetuccine;
            } else if (jsonObject.getString("url").trim().equals("R.drawable.molho_sugo")) {
                url = R.drawable.molho_sugo;
            } else if (Objects.equals(jsonObject.getString("url"), "R.drawable.nhoque_4_queijos")) {
                url = R.drawable.nhoque_4_queijos;
            } else if (Objects.equals(jsonObject.getString("url"), "R.drawable.carbonara")) {
                url = R.drawable.carbonara;
            } else if (Objects.equals(jsonObject.getString("url"), "R.drawable.nhoque_fughi")) {
                url = R.drawable.nhoque_fughi;
            }else{
                url = R.drawable.bolonhesa;
            }

            imageView.setImageResource(url);
            txtTitulo.setText(jsonObject.getString("title"));
            txtDesc.setText(jsonObject.getString("description"));
            txtPrice.setText("R$ " + jsonObject.getInt("price"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private class SearchItem extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String retorno = WebService.buscaWS(strings[0]);
            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {
            //System.out.println(s);
            setItensMenu(s);
        }
    }

}
