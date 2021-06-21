package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity4 extends AppCompatActivity {

    private Button playBtn;
    private Button perfilBtn;
    private Button rankingBtn;
    private Button webBtn;
    private Button tiendaBtn;
    private Button inventarioBtn;
    private Button salirmenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        playBtn = (Button)findViewById(R.id.playbtn);
        perfilBtn = (Button)findViewById(R.id.perfilbtn);
        rankingBtn = (Button)findViewById(R.id.rankingbtn);
        webBtn = (Button)findViewById(R.id.webbtn);
        tiendaBtn = (Button)findViewById(R.id.tiendabtn);
        salirmenuBtn = (Button)findViewById(R.id.salirperfilbtn);
        inventarioBtn = (Button)findViewById(R.id.inventariobtn);

        playBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        perfilBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity5();
            }
        });

        rankingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        webBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        tiendaBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });

        inventarioBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        salirmenuBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

    }

    public void openActivity5() {
        Intent intent = new Intent(this, MainActivity5.class);
        startActivity(intent);

    }

    public void openActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void openActivity3() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}