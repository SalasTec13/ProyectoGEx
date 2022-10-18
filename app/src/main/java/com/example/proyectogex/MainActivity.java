package com.example.proyectogex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    /*variable para cambiar de ventana(Main a Information extinguisher)*/
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*metodo para cambiar de ventana(Main a Information extinguisher)*/
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InformationExtinguisherActivity.class);
                startActivity(i);
            }
        });
    }

}