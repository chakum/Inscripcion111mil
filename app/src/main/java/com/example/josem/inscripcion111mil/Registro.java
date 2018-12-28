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
    EditText nombre, apellido, email, dni, cuil, dia, mes, ano, telefono, calle, numero;
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
        dni = (EditText)findViewById(R.id.dni);
        cuil = (EditText)findViewById(R.id.cuil);
        telefono = (EditText)findViewById(R.id.telefono);
        calle = (EditText)findViewById(R.id.calle);
        numero = (EditText)findViewById(R.id.numero);


        inscripcion = (Button)findViewById(R.id.inscripcion);
        requestQueue = Volley.newRequestQueue(this);

        inscripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText().toString().equals("") || apellido.getText().toString().equals("") || email.getText().toString().equals("") || dni.getText().toString().equals("") || cuil.getText().toString().equals("") || telefono.getText().toString().equals("") || calle.getText().toString().equals("") || numero.getText().toString().equals("")) {
                    Toast.makeText(Registro.this, "Faltan llenar campos", Toast.LENGTH_SHORT).show();
                }
                else {
                    enviarDatos();
                }

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
                Toast.makeText(Registro.this, "Se ha inscrito satisfactoriamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registro.this, "Ocurrio un error al inscribirse", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String >();
                map.put("nombre", nombre.getText().toString());
                map.put("apellido", apellido.getText().toString());
                map.put("email", email.getText().toString());
                map.put("provincia", escuela.getSelectedItem().toString());
                map.put("documento", dni.getText().toString());
                map.put("cuil", cuil.getText().toString());
                /*map.put("dia", dni.getText().toString());
                map.put("mes", mes.getText().toString());
                map.put("ano", ano.getText().toString());*/
                map.put("telefono", telefono.getText().toString());
                map.put("calle", calle.getText().toString());
                map.put("numero", numero.getText().toString());
                return map;

            }
        };
        requestQueue.add(request);
    }
}
