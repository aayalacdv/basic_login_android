package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginpage.models.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private Button loginBtn;
    private Button signupBtn;

    private EditText name;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = (Button)findViewById(R.id.loginbtn);
        signupBtn = (Button)findViewById(R.id.sinupbtn);


        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                name = (EditText) findViewById(R.id.name);
                password = (EditText)findViewById(R.id.contra);

                Call<ResponseBody> call = MyApiAdapter.getApiService().loginUser(new Usuario(name.getText().toString(),password.getText().toString()));

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

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (EditText) findViewById(R.id.name);
                password = (EditText)findViewById(R.id.contra);

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