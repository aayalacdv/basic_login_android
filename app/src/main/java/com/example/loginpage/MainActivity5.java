package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity5 extends AppCompatActivity {

    private Button salirperfilBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        salirperfilBtn = (Button)findViewById(R.id.salirperfilbtn);

        salirperfilBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });
    }
    public void openActivity4() {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }
}