package com.supermanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.user)
    public TextView user;

    @InjectView(R.id.pass)
    public TextView pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.sign_in)
    public void signIn() {

        String userValue = user.getText().toString();
        String passValue = pass.getText().toString();

        SharedPreferences settings = getSharedPreferences("SESSION", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user", userValue);
        editor.putString("pass", passValue);

        editor.commit();

        Intent intent = new Intent(this, TeamsActivity.class);
        startActivity(intent);
    }
}
