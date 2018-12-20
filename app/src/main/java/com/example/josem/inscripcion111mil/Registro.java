package com.example.josem.inscripcion111mil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    EditText nombre, apellido, email;
    Button inscripcion;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
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
