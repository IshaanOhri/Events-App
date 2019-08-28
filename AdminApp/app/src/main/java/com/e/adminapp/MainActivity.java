package com.e.adminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected static int CODE = 0;
    private Button attendenceButton, foodButton;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        attendenceButton = findViewById(R.id.attendenceButton);
        foodButton = findViewById(R.id.foodButton);

        attendenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CODE = 0;
                Intent intent = new Intent(MainActivity.this,Attendence.class);
                startActivity(intent);
            }
        });

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CODE = 1;
                Intent intent = new Intent(MainActivity.this,Food.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        
        if(backPressedTime + 2000 > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else 
        {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}
