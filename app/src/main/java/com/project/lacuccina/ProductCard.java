package com.project.lacuccina;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ProductCard extends AppCompatActivity {

    private RelativeLayout checkout;
    private static final String TAG = "PostPedido";
    String idOrder;

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
                SendItemOrder sendItemOrder = new SendItemOrder();
                sendItemOrder.execute("http://10.0.2.2:8081/pedido", idProduct);

                Intent intent = new Intent(ProductCard.this,CartActivity.class);
                intent.putExtra("id_order", idOrder);
                startActivity(intent);
            }
        });

    }

    public void setOrderId(String id){
        idOrder = id;
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

    private class SendItemOrder extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            //String retorno = WebService.buscaWS(strings[0]);
            HttpURLConnection connection = null;
            String idProduct = strings[1];
            String requestUrl = strings[0];
            String retorno = "";

            try {
                // Para adicionar ao pedido
                JSONObject postData = new JSONObject();
                postData.put("orderId", JSONObject.NULL);
                postData.put("menuId", Integer.parseInt(idProduct));
                postData.put("qtd", 1);

                URL url = new URL(requestUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setChunkedStreamingMode(0);

                OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        out, "UTF-8"));
                writer.write(postData.toString());
                writer.flush();

                int code = connection.getResponseCode();
                if (code !=  200) {
                    throw new IOException("Invalid response from server: " + code);
                }

                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    Log.i("data", line);
                    retorno = line;
                }
                return retorno;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return retorno;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null) {
                // Processar a resposta do servidor
                Log.d(TAG, "Resposta do servidor: " + response);
                setOrderId(response);
            } else {
                Log.e(TAG, "Resposta do servidor nula");
            }
        }
    }

}
