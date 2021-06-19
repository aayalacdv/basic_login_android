package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    private RecyclerView recyclerViewObjeto;
    private RecyclerView adaptadorobjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerViewObjeto=(RecyclerView)findViewById(R.id.recyclerObjeto);
        recyclerViewObjeto=setLayoutManager(new LinearLayoutManager(this));
    }

    public List<ObjectModel> obtenerobjetos(){
        List<ObjetoModelo> objeto=new ArrayList<>();
        objeto.add( new ObjectModel("Pocion de vida","Bebida que recupera la salud del jugador",R.drawable.pocion));
    }
}