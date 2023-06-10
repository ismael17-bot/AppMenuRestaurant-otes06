package com.materialuiux.store;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CallWaiter extends AppCompatActivity {

    Button Main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);

        Main = findViewById(R.id.id_MainBtn);

        Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CallWaiter.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}
