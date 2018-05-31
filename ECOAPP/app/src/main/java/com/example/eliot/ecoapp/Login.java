package com.example.eliot.ecoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity{
EditText txtUsu, txtPas;
Button btnEntrar, btnRegist;
TextView txvOlvidar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    txtUsu=(EditText) findViewById(R.id.txtUsu);
    txtPas=(EditText) findViewById(R.id.txtPas);
    btnEntrar=(Button) findViewById(R.id.btnEntrar);

    Button btnreg = (Button) findViewById(R.id.btnRegist);
    btnreg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), Registro.class);
            startActivityForResult(intent, 0);
        }
    });
    TextView txvOlvi = (TextView) findViewById(R.id.txvOlvido);
    txvOlvi.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), Recupera.class);
            startActivityForResult(intent, 0);
        }
    });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //conRetrofit(null);
    }

    public void conRetrofit(View v) {
        Log.e("PRESIONO","SE PRESIONO");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(InterfaceRetro.url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceRetro request = retrofit.create(InterfaceRetro.class);
        Call<List<DtoUsuario>> requestDatas = request.validaLogin(txtUsu.getText().toString(), txtPas.getText().toString());
        requestDatas.enqueue(new Callback<List<DtoUsuario>>() {
            @Override
            public void onResponse(Call<List<DtoUsuario>> call, Response<List<DtoUsuario>> response) {
                List<DtoUsuario> usaurio= response.body();
                if(usaurio.size()>0)
                        llameVentana();
            }

            @Override
            public void onFailure(Call<List<DtoUsuario>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void llameVentana(){
        Intent i=new Intent(Login.this,MenuPrincipal.class);
        i.putExtra("cod", txtUsu.getText().toString());
        startActivity(i);
    }
}
