package com.android.gss.guillegram.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.gss.guillegram.R;
import com.android.gss.guillegram.model.api.beans.Usuario;
import com.android.gss.guillegram.model.api.controllerI.ApiServiceI;
import com.android.gss.guillegram.util.ApiUtils;
import com.android.gss.guillegram.util.AppData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountActivity extends AppCompatActivity {

    private Button joinUs;
    private TextInputEditText email;
    private TextInputEditText name;
    private TextInputEditText user;
    private TextInputEditText password;
    private TextInputEditText confirm_Password;

    private ApiServiceI apiServiceI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_title_createAccount), true);


        joinUs = findViewById(R.id.joinUs);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        confirm_Password = findViewById(R.id.confirm_Password);

        apiServiceI = ApiUtils.getAPIService();

        joinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(email.getText().toString(),
                        name.getText().toString(),
                        user.getText().toString(),
                        password.getText().toString(),
                        confirm_Password.getText().toString());
            }
        });


    }

    public void showToolbar(String title, boolean btnUp) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(btnUp);
    }

    private void register(String email, String name, String user, String password, String confirm_password) {

        if (email != null && name != null && user != null && password != null && confirm_password != null &&
                !email.equals("") && !name.equals("") && !user.equals("") && !password.equals("") && !confirm_password.equals("")) {

            if (password.equals(confirm_password)) {
                // TODO Controlar que los datos de entrada no sean null o vacio
                Usuario u = new Usuario();
                u.setCorreo(email);
                u.setNombre(name);
                u.setNombreUsuario(user);
                u.setContrasena(password);
                apiServiceI.register(u).enqueue(new Callback<Usuario>() {
                    Usuario usuario;

                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            usuario = response.body();
                            AppData.setUsuario(usuario);
                            goContainerActivity();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        usuario = null;
                        Toast.makeText(getBaseContext(), "Ha habido un problema al registrarse", Toast.LENGTH_SHORT).show();
                        AppData.setUsuario(usuario);
                    }
                });
            } else {
                Toast.makeText(this, "Las contraseñas han de coincidir", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Rellena todos los campos, por favor", Toast.LENGTH_LONG).show();
        }
    }

    private void goContainerActivity() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }
}
