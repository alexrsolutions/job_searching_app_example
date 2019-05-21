package com.example.evidenciafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText name, l_name, password_user, c_password, email;
    private Button btn_regist;
    private TextView link_logIn;
    Spinner mySpinner;
    String users[] = {"employee", "employer"};
    private String user_type = "";
    ArrayAdapter<String> arrayAdapter;
    private ProgressBar loading;
    private static String URL_REGIST = "https://androidproject6th.000webhostapp.com/workTitan/register_user.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading = findViewById(R.id.loading);
        name = findViewById(R.id.name);
        l_name = findViewById(R.id.l_name);
        email = findViewById(R.id.email);
        password_user = findViewById(R.id.password_user);
        c_password = findViewById(R.id.c_password);
        btn_regist = findViewById(R.id.btn_regist);
        link_logIn = findViewById(R.id.logIn);

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
        link_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        mySpinner = (Spinner) findViewById(R.id.spinner);
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, users);
        mySpinner.setAdapter(arrayAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user_type = users[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                user_type = "employee";
            }
        });
    }

    private void Regist(){
       loading.setVisibility(View.VISIBLE);
       btn_regist.setVisibility(View.GONE);

       final String name = this.name.getText().toString().trim();
       final String l_name = this.l_name.getText().toString().trim();
       final String email = this.email.getText().toString().trim();
       final String password_user = this.password_user.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Register Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("l_name", l_name);
                params.put("email", email);
                params.put("password_user", password_user);
                params.put("user_type", user_type);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
