package com.android.gss.guillegram;

import android.content.Intent;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.gss.guillegram.model.api.beans.Usuario;
import com.android.gss.guillegram.model.api.controllerI.ApiServiceI;
import com.android.gss.guillegram.util.ApiUtils;
import com.android.gss.guillegram.util.AppData;
import com.android.gss.guillegram.util.IPGetter;
import com.android.gss.guillegram.views.ContainerActivity;
import com.android.gss.guillegram.views.CreateAccountActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView createhere;
    private TextInputEditText username;
    private TextInputEditText password;
    private ApiServiceI apiServiceI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        apiServiceI = ApiUtils.getAPIService();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUsuario(username.getText().toString(), password.getText().toString());
            }
        });

        createhere = findViewById(R.id.createhere);

        createhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCreateAccount();
            }
        });
    }

    private void goCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    private void goContainerActivity() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    private void getUsuario(String username_mail, String contrasena) {
        if (username_mail != null && contrasena != null && !username_mail.equals("") && !contrasena.equals("")) {

            apiServiceI.getLogin(username_mail, contrasena).enqueue(new Callback<Usuario>() {
                Usuario usuario;

                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        //TODO Controlar que los datos de entrada no sean null o vacio
                        usuario = response.body();
                        AppData.setUsuario(usuario);
                        goContainerActivity();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    usuario = null;
                    Toast.makeText(getBaseContext(), "Usuario no encontrado",  Toast.LENGTH_SHORT).show();
                    AppData.setUsuario(usuario);
                }
            });
        }else{
            Toast.makeText(this, "Rellena todos los campos, por favor", Toast.LENGTH_SHORT).show();
        }
    }
}
