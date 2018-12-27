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

import com.loopj.android.http.*;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class Registro extends AppCompatActivity {
    EditText nombre, apellido, email;
    Button inscripcion;
    RequestQueue requestQueue;
    private AsyncHttpClient cliente;
    private Spinner escuela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        cliente = new AsyncHttpClient();
        escuela = (Spinner)findViewById(R.id.escuela);
        llenarSpinner();

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

    private void llenarSpinner() {
        String url = "https://hair-trigger-hinges.000webhostapp.com/conexion/getCategoria.php";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    cargarSpinner(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void cargarSpinner (String respuesta) {
        ArrayList<Escuela> lista = new ArrayList<Escuela>();
        try {
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for (int i = 0; i < jsonArreglo.length(); i++) {
                Escuela e = new Escuela();
                e.setNombre((jsonArreglo.getJSONObject(i).getString("nombre")));
                lista.add(e);
            }
            ArrayAdapter<Escuela> a = new ArrayAdapter<Escuela> (this, android.R.layout.simple_dropdown_item_1line, lista);
            escuela.setAdapter(a);
        }catch (Exception e) {
            e.printStackTrace();
        }
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
