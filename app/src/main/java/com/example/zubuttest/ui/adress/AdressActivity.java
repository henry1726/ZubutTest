package com.example.zubuttest.ui.adress;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zubuttest.R;

public class AdressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.act_login_container, AdressFragment.newInstance())
                    .commit();
    }
}
