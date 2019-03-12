package com.citizenfour.yummy;

import android.content.Intent;
import android.graphics.Typeface;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtSlogan)
    TextView txtSlogan;

    @OnClick(R.id.btnSignUp)
    public void signUp(View view) {
        Intent signUp = new Intent(MainActivity.this, SignUp.class);
        startActivity(signUp);
    }

    @OnClick(R.id.btnSignIn)
    public void signIn(View view) {
        Intent signIn = new Intent(MainActivity.this, SignIn.class);
        startActivity(signIn);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //set the font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/NABILA.TTF");
        txtSlogan.setTypeface(typeface);
    }
}
