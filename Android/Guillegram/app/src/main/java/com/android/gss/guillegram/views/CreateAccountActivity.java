package com.android.gss.guillegram.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.android.gss.guillegram.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_title_createAccount),true );
    }

    public void showToolbar(String title, boolean btnUp){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(btnUp);
    }
}
