package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginpage.models.Producto;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static ShopAPI shopAPI;
    private List<Producto> productList = new ArrayList<>();
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutshop);

        Intent i = getIntent();
        userId = i.getStringExtra("name");

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ShopAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        shopAPI = retrofit.create(ShopAPI.class);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewShop);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Call<List<Producto>> productos = (Call<List<Producto>>) shopAPI.getProductList();

        productos.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                productList = response.body();
                mAdapter = new ShopAdapter(productList, getApplicationContext(), userId);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.i("listProducts", "Failure " + t.getMessage());
            }
        });




    }


}

