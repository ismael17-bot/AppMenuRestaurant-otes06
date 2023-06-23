package com.project.lacuccina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInActivity extends AppCompatActivity {

    Button Waiter;
    Button Menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Waiter = findViewById(R.id.id_Waiter);
        Menu = findViewById(R.id.id_Menu);

        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, MenuActivity.class);
                intent.putExtra("orderId", "");
                startActivity(intent);
            }
        });

        Waiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, OrdersActivity.class);
                startActivity(intent);
            }
        });
    }
}
