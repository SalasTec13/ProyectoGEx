package com.example.proyectogex;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class InformationExtinguisherActivity extends AppCompatActivity{

    /*Registro de los datos Extintor*/
    EditText etidExtintor, etfolio,etcapacidad,
            ettipo,etubicacion,etseguro,etrecargado;
    Button btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_extinguisher);

        /*Registro*/

        etidExtintor=findViewById(R.id.et_id);
        etfolio=findViewById(R.id.et_folio);
        etcapacidad=findViewById(R.id.et_capacidad);
        ettipo=findViewById(R.id.et_tipo);
        etubicacion=findViewById(R.id.et_ubicacion);
        etseguro=findViewById(R.id.et_seguro);
        etrecargado=findViewById(R.id.et_recargado);

        btnAgregar=findViewById(R.id.btn_Agregar);
        btnAgregar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                insertarDatos();
            }
        });

    }
    private void insertarDatos(){
        final String IdExtintor = etidExtintor.getText().toString().trim();
        final String folio = etfolio.getText().toString().trim();
        final String capacidad = etcapacidad.getText().toString().trim();
        final String tipo = ettipo.getText().toString().trim();
        final String ubicacion= etubicacion.getText().toString().trim();
        final String seguro = etseguro.getText().toString().trim();
        final String recargado = etrecargado.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");

        if(IdExtintor.isEmpty()){
            Toast.makeText(this, "Ingrese el N.Extintor", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(folio.isEmpty()){
            Toast.makeText(this, "Ingrese el Folio", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(capacidad.isEmpty()){
            Toast.makeText(this, "Ingrese la Capacidad", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(tipo.isEmpty()){
            Toast.makeText(this, "Ingrese el Tipo", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(ubicacion.isEmpty()){
            Toast.makeText(this, "Ingrese la Ubicacion", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(seguro.isEmpty()){
            Toast.makeText(this, "Ingrese si tiene el seguro", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(recargado.isEmpty()){
            Toast.makeText(this, "Ingrese si esta Recargado", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            progressDialog.show();
            StringRequest request=new StringRequest(Request.Method.POST, "https://proyectogexapp.000webhostapp.com/crud/insertar.php",
                    new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Registro exitoso")) {
                        Toast.makeText(InformationExtinguisherActivity.this, "Datos Insertados", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        //Aqui cambia a la pantalla
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(InformationExtinguisherActivity.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(InformationExtinguisherActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Nullable
                @Override
                protected Map<String,String> getParams() throws AuthFailureError{
                    Map<String,String> params=new HashMap<String,String>();
                    params.put("IdExtintor",IdExtintor);
                    params.put("folio",folio);
                    params.put("capacidad",capacidad);
                    params.put("tipo",tipo);
                    params.put("ubicacion",ubicacion);
                    params.put("seguro",seguro);
                    params.put("recargado",recargado);
                    return params;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(InformationExtinguisherActivity.this);
            requestQueue.add(request);
        }

    }
    @Override
    public  void onBackPressed(){
        super.onBackPressed();
        finish();
    }

}