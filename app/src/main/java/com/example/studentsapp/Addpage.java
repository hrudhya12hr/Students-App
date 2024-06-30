package com.example.studentsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Addpage extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    AppCompatButton b1,b2;
    String apiUrl="https://courseapplogix.onrender.com/addstudents";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addpage);

        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.lname);
        e3=(EditText) findViewById(R.id.college);
        e4=(EditText) findViewById(R.id.bday);
        e5=(EditText) findViewById(R.id.course);
        e6=(EditText) findViewById(R.id.mob);
        e7=(EditText) findViewById(R.id.mail);
        e8=(EditText) findViewById(R.id.address);
        b1=(AppCompatButton) findViewById(R.id.goback);
        b2=(AppCompatButton) findViewById(R.id.addbutton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getFname=e1.getText().toString();
                String getLname=e2.getText().toString();
                String getCollege=e3.getText().toString();
                String getDOB=e4.getText().toString();
                String getCourse=e5.getText().toString();
                String getMob=e6.getText().toString();
                String getMail=e7.getText().toString();
                String getAddress=e8.getText().toString();

                //JSON obj creation
                JSONObject student=new JSONObject();
                try {
                    student.put("firstname",getFname);
                    student.put("lastname",getLname);
                    student.put("college",getCollege);
                    student.put("dob",getDOB);
                    student.put("course",getCourse);
                    student.put("mobile",getMob);
                    student.put("email",getMail);
                    student.put("address",getAddress);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //json object request creation
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiUrl,
                        student,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "added succesfully", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                );
                //Request queue
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });


    }
}