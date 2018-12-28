package com.example.josem.inscripcion111mil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        siguiente = (Button)findViewById(R.id.buttoncontinuar);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(MainActivity.this, Registro.class);
                startActivity(siguiente);
            }
        });
    }
}
