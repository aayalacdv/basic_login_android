package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.example.loginpage.models.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private Button registerBtn;

    private EditText name;
    private EditText password;

    String userId;

    MediaPlayer sonidobotones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = (Button)findViewById(R.id.loginbtn);
        registerBtn = (Button)findViewById(R.id.sinupbtn);
        name = (EditText) findViewById(R.id.name);
        password = (EditText)findViewById(R.id.contra);


        /* BOTÓN DE LOG IN*/
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*sonidobotones = MediaPlayer.create(MainActivity.this, R.raw.botonesandroid);
                sonidobotones.start();*/
                userId = name.getText().toString();

                Usuario u = new Usuario(userId, password.getText().toString());

                Call<ResponseBody> call = MyApiAdapter.getApiService().loginUser(u);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        validateResponse(response);
                        if (response.isSuccessful())
                        {
                            /*para saltar al activity con los datos*/
                            perfilActivity();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("LOGIN","Failure: " + t.getMessage());
                    }
                });

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sonidobotones = MediaPlayer.create(MainActivity.this, R.raw.botonesandroid);
                //sonidobotones.start();
                SignUpActivity();
            }
        });
    }

    public void SignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void perfilActivity() {
        Intent intent = new Intent(this, PerfilActivity.class);
        intent.putExtra("name", userId);
        startActivity(intent);
    }

    public void validateResponse(Response<ResponseBody> response){
        if(response.code() == 201){
            Log.i("LOGIN","Login successfull");
            Toast.makeText(this, "Login successfull", Toast.LENGTH_SHORT ).show();
        }
        else{
            Log.i("LOGIN","Login unsuccessfull");
            Toast.makeText(this, "Login unsuccessfull, try again", Toast.LENGTH_SHORT ).show();
        }
    }




}