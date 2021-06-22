package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpage.models.Producto;
import com.example.loginpage.models.ShopProduct;
import com.example.loginpage.models.Usuario;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {
    private TextView nameTxt;
    private TextView surnameTxt;
    private TextView ageTxt;
    private Usuario u;
    String userName;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static ShopAPI shopAPI;
    private List<ShopProduct> productList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        nameTxt = findViewById(R.id.nameTxt);
        surnameTxt = findViewById(R.id.surnameTxt);
        ageTxt = findViewById(R.id.ageTxt);

        Intent i = getIntent();
        userName = i.getStringExtra("name");

        Call<Usuario> userCall = MyApiAdapter.getApiService().getUser(userName);

        userCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                u = response.body();
                String age = Integer.toString(u.getEdad());
                nameTxt.setText("Name: " + u.getName());
                surnameTxt.setText("Surname: " + u.getSurname());
                ageTxt.setText("Age: " + age);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.i("MoneyTxt", "Failure " + t.getMessage());
            }
        });

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ShopAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        shopAPI = retrofit.create(ShopAPI.class);

        recyclerView = (RecyclerView)findViewById(R.id.rvUser);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Call<List<ShopProduct>> productos = (Call<List<ShopProduct>>) shopAPI.getProductsByUser(userName);

        productos.enqueue(new Callback<List<ShopProduct>>() {
            @Override
            public void onResponse(Call<List<ShopProduct>> call, Response<List<ShopProduct>> response) {
                productList = response.body();
                mAdapter = new InventarioAdapter(productList, getApplicationContext());
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<ShopProduct>> call, Throwable t) {
                Log.i("listProductsUser", "Failure " + t.getMessage());
            }
        });

    }




}
