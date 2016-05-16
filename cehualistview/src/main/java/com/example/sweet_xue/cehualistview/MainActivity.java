package com.example.sweet_xue.cehualistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(this, SimpleActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, DifferentMenuActivity.class));
                break;
        }
    }

}
