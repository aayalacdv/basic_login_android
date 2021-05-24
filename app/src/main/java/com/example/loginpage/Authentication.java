package com.example.loginpage;

import com.example.loginpage.models.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Authentication {
    @POST("login/")
    Call<ResponseBody>loginUser(@Body Usuario user);

    @POST("signup/")
    Call<ResponseBody>signUpUser(@Body Usuario user);
}

