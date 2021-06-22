package com.example.loginpage;

import android.content.Intent;
import android.nfc.FormatException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpage.models.Producto;
import com.example.loginpage.models.ShopProduct;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompraActivity extends AppCompatActivity {
    private String userId;
    private String urlProduct;
    private String idProduct;
    private TextView idProductTxt;
    private EditText cantidad;
    private Button compraBtn;
    public static ShopAPI shopAPI;

    public void validateResponse(Response<ResponseBody> response) {
        if (response.code() == 201) {
            Log.i("SIGNUP", "Compra efectuada");
            Toast.makeText(this, "Compra efectuada", Toast.LENGTH_SHORT ).show();
        } else {
            Log.i("SIGNUP", "No se ha podido efectuar la compra");
            Toast.makeText(this, "No se ha podido efectuar la compra", Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compra);

        idProductTxt = findViewById(R.id.idProductTxt);
        cantidad = findViewById(R.id.cantidadTxt);
        compraBtn = findViewById(R.id.doneBtn);

        Intent i = getIntent();
        userId = i.getStringExtra("name");
        urlProduct = i.getStringExtra("url");
        idProduct = i.getStringExtra("producto");

        idProductTxt.setText(idProduct);

        compraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

                    Retrofit retrofit = new Retrofit.Builder().baseUrl(ShopAPI.ENDPOINT)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okHttpClient)
                            .build();

                    shopAPI = retrofit.create(ShopAPI.class);

                    ShopProduct compra = new ShopProduct(idProduct, userId, Integer.valueOf(cantidad.getText().toString()));

                    Call<ResponseBody> call = (Call<ResponseBody>) shopAPI.buyProduct(compra);

                    call.enqueue(new Callback<ResponseBody>() {
                                     @Override
                                     public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                         validateResponse(response);
                                     }

                                     @Override
                                     public void onFailure(Call<ResponseBody> call, Throwable t) {
                                         Log.i("compra", "Failure: " + t.getMessage());
                                     }

                                 }
                    );
                } catch (Exception e) {
                    Log.i("compra", "Exception: " + e.getMessage());
                }
            }
        });
    }
}