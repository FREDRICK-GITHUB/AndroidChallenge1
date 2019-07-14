package com.mobile.androidchallenge1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView txt1,txt2,txt3,txt4,txt5,txt6;
    EditText etx1,etx2,etx3,etx4,etx5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("My Profile");

        ImageView imageView = (ImageView) findViewById(R.id.profImg);
         txt1 = (TextView) findViewById(R.id.prof_name);
         txt2 = (TextView) findViewById(R.id.trackt);
         txt3 = (TextView) findViewById(R.id.countryt);
         txt4 = (TextView) findViewById(R.id.emailt);
         txt5 = (TextView) findViewById(R.id.phonenot);
         txt6 = (TextView) findViewById(R.id.slackt);

         etx1 = (EditText) findViewById(R.id.track);
         etx2 = (EditText) findViewById(R.id.country);
         etx3 = (EditText) findViewById(R.id.email);
         etx4 = (EditText) findViewById(R.id.phoneno);
         etx5 = (EditText) findViewById(R.id.slack);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
