package com.example.josem.inscripcion111mil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    EditText nombre, apellido, email;
    Button inscripcion;
    RequestQueue requestQueue;
    private Spinner escuela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        escuela = (Spinner)findViewById(R.id.escuela);

        ArrayList<String> escuelas = new ArrayList<>();

        escuelas.add("Buenos Aires");
        escuelas.add("Santa Fe");
        escuelas.add("Mendoza");


        ArrayAdapter adp = new ArrayAdapter(Registro.this, android.R.layout.simple_spinner_dropdown_item, escuelas);

        escuela.setAdapter(adp);
        escuela.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elemento = (String) escuela.getAdapter().getItem(position);

                Toast.makeText(Registro.this,"Seleccionaste: " + elemento, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        nombre = (EditText)findViewById(R.id.nombre);
        apellido = (EditText)findViewById(R.id.apellido);
        email = (EditText)findViewById(R.id.email);
        inscripcion = (Button)findViewById(R.id.inscripcion);
        requestQueue = Volley.newRequestQueue(this);

        inscripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDatos();
            }
        });

    }

    private void enviarDatos() {
        String url = "https://hair-trigger-hinges.000webhostapp.com/conexion/agregar.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String >();
                map.put("nombre", nombre.getText().toString());
                map.put("apellido", apellido.getText().toString());
                map.put("email", email.getText().toString());
                return map;
            }
        };
        requestQueue.add(request);
    }
}
