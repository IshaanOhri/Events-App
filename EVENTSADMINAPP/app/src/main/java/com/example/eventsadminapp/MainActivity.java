package com.example.eventsadminapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private String Register_number = "18BCE2086";
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        attendance_Marking(Register_number);

}

    private void attendance_Marking(final String register_number) {
        Pattern p = Pattern.compile(register_number);
        Matcher m = p.matcher("[1][5-9][a-zA-Z]{3}[0-9]{4}");

        if (m.matches()) {

            API apiService = ApiClient.getClient().create(API.class);

            Call<Response> call = apiService.userAttendance(register_number);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    // THE STATUS CODE RETURNED IS IN THE RANGE 200 to 299
                    if (response.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Attendance Marked",Toast.LENGTH_LONG).show();
                    }
                    else if(response.code()==400){
                        Toast.makeText(MainActivity.this,"Bad Request",Toast.LENGTH_LONG).show();

                    }
                    else if (response.code()==401){
                        Toast.makeText(MainActivity.this,"User not Registered",Toast.LENGTH_LONG);
                    }

                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_LONG).show();
                }
            });
        } else {

        Toast.makeText(MainActivity.this,"Invalid Reg No",Toast.LENGTH_LONG).show();
        }

    }

    private void foodDistribution(String register_number) {

        Pattern p = Pattern.compile(register_number);
        Matcher m = p.matcher("[1][5-9][a-zA-Z]{3}[0-9]{4}");

        if (m.matches()) {

            API apiService = ApiClient.getClient().create(API.class);

            Call<Response> call = apiService.foodDistribution(register_number);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    // THE STATUS CODE RETURNED IS IN THE RANGE 200 to 299
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Food to be given to the participant",Toast.LENGTH_LONG).show();
                    } else if (response.code() == 208) {
                        Toast.makeText(MainActivity.this, "Refreshments Already Served", Toast.LENGTH_LONG).show();
                    } else if (response.code() == 400) {
                        Toast.makeText(MainActivity.this, "BAD REQUEST", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 401) {
                        Toast.makeText(MainActivity.this, "User Not Registered", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                    Toast.makeText(MainActivity.this, "Failed to retrieve the user", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "INVALID REGESTRATION NUMBER FORMAT", Toast.LENGTH_LONG).show();
        }

}



}