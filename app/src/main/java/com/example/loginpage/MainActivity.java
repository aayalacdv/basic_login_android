package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

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

    MediaPlayer sonidobotones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = (Button)findViewById(R.id.loginbtn);
        registerBtn = (Button)findViewById(R.id.sinupbtn);


        /* BOTÓN DE LOG IN*/
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                name = (EditText) findViewById(R.id.name);
                password = (EditText)findViewById(R.id.contra);

                /*sonidobotones = MediaPlayer.create(MainActivity.this, R.raw.botonesandroid);
                sonidobotones.start();*/

                Call<ResponseBody> call = MyApiAdapter.getApiService().loginUser(new Usuario(name.getText().toString(),password.getText().toString()));

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        validateResponse(response);

                        /*para saltat al activity con los datos*/
                        openActivity3();
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
                name = (EditText) findViewById(R.id.name);
                password = (EditText)findViewById(R.id.contra);

                sonidobotones = MediaPlayer.create(MainActivity.this, R.raw.botonesandroid);
                sonidobotones.start();

                Call<ResponseBody> call = MyApiAdapter.getApiService().signUpUser(new Usuario(name.getText().toString(),password.getText().toString()));

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        validateResponse(response);

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("LOGIN","Failure: " + t.getMessage());
                    }
                });
            }
        });


        /*Register btn*/


        registerBtn.setOnClickListener(new View.OnClickListener(){
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

    public void openActivity3() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }


    public void validateResponse(Response<ResponseBody> response){
       if(response.code() == 201){
           Log.i("LOGIN","Login successfull");
       }
       else{
           Log.i("LOGIN","Login unsuccessfull");
       }
    }




}