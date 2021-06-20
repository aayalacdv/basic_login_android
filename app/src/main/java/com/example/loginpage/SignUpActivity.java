package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.models.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity {

    private Button done;
    private EditText name;
    private EditText surname;
    private EditText idUser;
    private EditText age;
    private EditText password;
    private EditText passwordConf;

    public void validateResponse(Response<ResponseBody> response){
        if(response.code() == 201){
            Log.i("SIGNUP","Login successfull");
            Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT ).show();
        }
        else{
            Log.i("SIGNUP","Login unsuccessfull");
            Toast.makeText(this, "Sign up not successful", Toast.LENGTH_SHORT ).show();
        }
    }

    public void errorPasswords(){
        Toast.makeText(this, "The passwords are differents", Toast.LENGTH_SHORT ).show();
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        done = (Button)findViewById(R.id.continueBtn);
        name = (EditText) findViewById(R.id.nameEditxt);
        surname = (EditText) findViewById(R.id.surnameEditxt);
        idUser = (EditText) findViewById(R.id.idEditxt);
        age = (EditText) findViewById(R.id.ageEditxt);
        password = (EditText) findViewById(R.id.passwordEditxt);
        passwordConf = (EditText) findViewById(R.id.confirmPasswordEditxt);

        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    if (password.getText().toString().compareTo(passwordConf.getText().toString()) == 0) {
                        int edad = Integer.parseInt(age.getText().toString());
                        Usuario u = new Usuario(idUser.getText().toString(), name.getText().toString(), surname.getText().toString(), password.getText().toString(), edad);

                        Call<ResponseBody> call = MyApiAdapter.getApiService().signUpUser(u);

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                validateResponse(response);
                                if(response.isSuccessful())
                                {
                                    openMainActivity();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.i("SIGNUP", "Failure: " + t.getMessage());
                            }
                        });
                    } else {
                        errorPasswords();
                    }
                }
                catch (Exception e)
                {
                    Log.i("SIGNUP", "Exception: " + e.getMessage());
                }

            }
        });

    }


}