package com.project.lacuccina;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.project.lacuccina.adapter.Ad_Menu;
import com.project.lacuccina.model.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    GridLayoutManager gridLayoutManager;
    Ad_Menu ad_menu;
    String[][] menuArray;

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

        SearchMenu searchMenu = new SearchMenu();
        searchMenu.execute("http://10.0.2.2:8081/menu");

    }

    public void setItensMenu(String menu) {
        try {
            JSONArray jsonArray = new JSONArray(menu);
            menuArray = new String[jsonArray.length()][5];
            // Percorrer o JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                menuArray[i][0] = String.valueOf(jsonObject.getInt("id"));
                menuArray[i][1] = jsonObject.getString("description");
                menuArray[i][2] = jsonObject.getString("title");
                menuArray[i][3] = jsonObject.getString("url");
                menuArray[i][4] = String.valueOf(jsonObject.getInt("price"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ad_menu = new Ad_Menu(this, getData(menuArray));
        recyclerview.setAdapter(ad_menu);
    }

    private class SearchMenu extends AsyncTask<String, String, String>{

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

    private ArrayList<Food> getData(String[][] menuArray) {

        ArrayList<Food> arrayList = new ArrayList<>();

        // Percorrer o JSONArray
        for (int i = 0; i < menuArray.length; i++) {

            int url;

            if (Objects.equals(menuArray[i][3], "R.drawable.fetuccine")) {
                url = R.drawable.fetuccine;
            } else if (menuArray[i][3].trim().equals("R.drawable.molho_sugo")) {
                url = R.drawable.molho_sugo;
            } else if (Objects.equals(menuArray[i][3], "R.drawable.nhoque_4_queijos")) {
                url = R.drawable.nhoque_4_queijos;
            } else if (Objects.equals(menuArray[i][3], "R.drawable.carbonara")) {
                url = R.drawable.carbonara;
            } else if (Objects.equals(menuArray[i][3], "R.drawable.nhoque_fughi")) {
                url = R.drawable.nhoque_fughi;
            }else{
                System.out.println(menuArray[i][3]);
                url = R.drawable.bolonhesa;
            }

            arrayList.add(new Food(url, menuArray[i][2], menuArray[i][1], Integer.parseInt(menuArray[i][4]), menuArray[i][0]));
        }

        return arrayList;
    }

}
