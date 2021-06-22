package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.models.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {

    private Button shopBtn;
    private Button playBtn;
    private Button gameBtn;
    private Button userBtn;
    private TextView userIdText;
    private String userName;
    public static ShopAPI shopAPI;
    private Usuario u;
    private TextView moneyTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutperfil);
        shopBtn = (Button)findViewById(R.id.shopBtn);
        playBtn = (Button)findViewById(R.id.playBtn);
        userBtn = (Button)findViewById(R.id.userBtn);
        moneyTxt = findViewById(R.id.moneytxt);
        userIdText = findViewById(R.id.idUsertxt);

        Intent i = getIntent();
        userName = i.getStringExtra("name");
        userIdText.setText(userName);

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShopActivity.class);
                intent.putExtra("name", userName);
                startActivity(intent);
            }
        });

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserActivity.class);
                intent.putExtra("name", userName);
                startActivity(intent);
            }
        });

        Call<Usuario> userCall = MyApiAdapter.getApiService().getUser(userName);

        userCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                u = response.body();
                moneyTxt.setText(String.valueOf(u.getMoney()) + "€");
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.i("MoneyTxt", "Failure " + t.getMessage());
            }
        });



    }


}