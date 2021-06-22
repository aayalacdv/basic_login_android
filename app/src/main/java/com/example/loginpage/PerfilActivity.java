package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerfilActivity extends AppCompatActivity {

    private Button shopBtn;
    private Button playBtn;
    private Button gameBtn;
    private Button userBtn;
    private TextView userIdText;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutperfil);
        shopBtn = (Button)findViewById(R.id.shopBtn);
        playBtn = (Button)findViewById(R.id.shopBtn);
        gameBtn = (Button)findViewById(R.id.shopBtn);
        userBtn = (Button)findViewById(R.id.shopBtn);
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



    }


}