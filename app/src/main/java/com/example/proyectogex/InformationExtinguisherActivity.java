package com.example.proyectogex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class InformationExtinguisherActivity extends AppCompatActivity implements View.OnClickListener {

    /*Registro de los datos Extintor*/
    EditText etidExtintor, etfolio,etcapacidad,ettipo,etseguro,etrecargado,etubicacion,etfecha_A,etfecha_V;
    Button btnAgregar;

    RequestQueue requestQueue;

    private static final String URL1="http://10.0.0.2/extintorbd/save.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_extinguisher);

        /*Registro*/

        requestQueue = Volley.newRequestQueue(this);
        initUI();
        btnAgregar.setOnClickListener(this);
    }


    private void initUI(){
        etidExtintor=findViewById(R.id.et_id);
        etfolio=findViewById(R.id.et_folio);
        etcapacidad=findViewById(R.id.et_capacidad);
        ettipo=findViewById(R.id.et_tipo);
        etseguro=findViewById(R.id.et_seguro);
        etrecargado=findViewById(R.id.et_recargado);
        etubicacion=findViewById(R.id.et_ubicacion);
        etfecha_A=findViewById(R.id.et_fechaA);
        etfecha_V=findViewById(R.id.et_fechaV);

        btnAgregar=findViewById(R.id.btn_Agregar);
    }

    @Override
    public void onClick(View v){
        int id=v.getId();

        if (id == R.id.btn_Agregar){
            String idEx = etidExtintor.getText().toString().trim();
            String folio = etfolio.getText().toString().trim();
            String capacidad = etcapacidad.getText().toString().trim();
            String tipo = ettipo.getText().toString().trim();
            String seguro = etseguro.getText().toString().trim();
            String recargado = etrecargado.getText().toString().trim();
            String ubicacion= etubicacion.getText().toString().trim();
            String fechaA = etfecha_A.getText().toString().trim();
            String fechaV= etfecha_V.getText().toString().trim();

            createExtintor(idEx,folio,capacidad,tipo,seguro,recargado,ubicacion,fechaA,fechaV);
        }
        if(id==R.id.btn_Agregar){

        }
    }

    private void createExtintor(final String idEx,final String folio,final String capacidad,final String tipo,final String seguro,final String recargado,final String ubicacion,final String fechaA,final String fechaV)
    {
        StringRequest stringRequest= new StringRequest(
                Request.Method.POST,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(InformationExtinguisherActivity.this, "Correcto", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idEx", idEx);
                params.put("folio",folio);
                params.put("capacidad",capacidad);
                params.put("tipo",tipo);
                params.put("seguro",seguro);
                params.put("recargado",recargado);
                params.put("ubicacion",ubicacion);
                params.put("fechaA",fechaA);
                params.put("fechaV",fechaV);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }


}