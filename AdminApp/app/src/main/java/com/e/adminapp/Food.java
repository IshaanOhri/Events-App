package com.e.adminapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Food extends AppCompatActivity {

    protected static TextView resultView;
    private TextView responseTextView;
    private ImageButton scanButton;
    final private int CAMERA_REQ_CODE = 1;
    private String regno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        resultView = findViewById(R.id.resultView);
        scanButton = findViewById(R.id.scanButton);
        responseTextView = findViewById(R.id.responseTextView);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_REQ_CODE);
                }
                else
                {
                    Intent intent = new Intent(Food.this, Scan.class);
                    startActivity(intent);
                }

            }
        });

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_REQ_CODE);
        }

        resultView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                regno = resultView.getText().toString();

                if (regno.matches("[1][2-9][a-zA-Z]{3}[0-9]{4}"))
                {
                    Call<ResponseBody> call = RetrofitClient
                            .getInstance()
                            .getAPI()
                            .userFood(regno);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.code() == 200)
                            {
                                responseTextView.setText("200:\nRefreshment to be served");
                            }
                            else if(response.code() == 208)
                            {
                                responseTextView.setText("208:\nRefreshment already served");
                            }
                            else if (response.code() == 400)
                            {
                                responseTextView.setText("400:\nBad Request");
                            }
                            else if (response.code() == 401)
                            {
                                responseTextView.setText("401:\nNot registered for the event");
                            }
                            else
                            {
                                responseTextView.setText("No response received");
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            responseTextView.setText("Error Occurred");

                        }
                    });
                }
                else
                {
                    responseTextView.setText("Incorrect RegNo");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
