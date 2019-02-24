package com.android.gss.guillegram;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.gss.guillegram.model.api.beans.Usuario;
import com.android.gss.guillegram.model.api.controllerI.ApiControllerI;
import com.android.gss.guillegram.util.ApiUtils;
import com.android.gss.guillegram.util.AppData;
import com.android.gss.guillegram.util.RetrofitClient;
import com.android.gss.guillegram.views.ContainerActivity;
import com.android.gss.guillegram.views.CreateAccountActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView createhere;
    private TextInputEditText username;
    private TextInputEditText password;
    private ApiControllerI apiControllerI;
    private AppData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        apiControllerI = ApiUtils.getApiUtils();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = getUsuario(username.getText().toString(), password.getText().toString());
                if (usuario != null) {
                    goContainerActivity(v);
                }
            }
        });

        createhere = findViewById(R.id.createhere);

        createhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCreateAccount(v);
            }
        });


    }

    private void goCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    private void goContainerActivity(View view) {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    private Usuario getUsuario(String username_mail, String contrasena) {
        if (username_mail != null && contrasena != null && !username_mail.equals("") && !contrasena.equals("")) {
            apiControllerI.getLogin(username_mail, contrasena).enqueue(new Callback<Usuario>() {

                Usuario usuario;

                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        usuario = response.body();
                        AppData.setUsuario(usuario);
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    usuario = null;
                    AppData.setUsuario(usuario);
                }
            });
        }
        return AppData.getUsuario();
    }
}
