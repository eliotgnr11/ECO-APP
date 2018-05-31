package com.example.eliot.ecoapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceRetro {

    public final static String url="http://10.42.0.226/WebServiceEco/";

    @GET("valida.php")
    Call<List<DtoUsuario>> validaLogin(@Query("usu") String usu, @Query("pas") String pass);

}
