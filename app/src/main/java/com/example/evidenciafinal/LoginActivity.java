package com.example.evidenciafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.evidenciafinal.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password_login;
    private Button btn_login;
    private TextView link_regist;
    private ProgressBar loading;
    public String id_User = "";
    public String user_Type = "";
    public String user_Name = "";
    public String user_Email = "";
    public String user_Password = "";
    public String user_Last = "";
    private static String URL_LOGIN = "https://androidproject6th.000webhostapp.com/workTitan/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loading = findViewById(R.id.loading);
        email = findViewById(R.id.email);
        password_login = findViewById(R.id.password_login);
        btn_login = findViewById(R.id.btn_login);
        link_regist = findViewById(R.id.link_regist);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mPass = password_login.getText().toString().trim();

                if( !mEmail.isEmpty() || !mPass.isEmpty()){
                    Login(mEmail, mPass);
                } else {
                    email.setError("Please insert Email");
                    password_login.setError("Please insert password");
                }

                cleanUp();
            }
        });

        link_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                cleanUp();
            }
        });
    }

    private void Login(final String email, final String password_login){
        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    User u = new User();
                                    user_Email = object.getString("user_Email");
                                    user_Name = object.getString("user_Name");
                                    user_Password = object.getString("user_Password");
                                    user_Last = object.getString("user_Last");
                                    id_User = object.getString("id_User").trim();
                                    u.setIdUser(id_User);
                                    user_Type = object.getString("user_Type").trim();
                                }
                            }

                            if (user_Type.equals("employee")){
                                Intent i = new Intent(LoginActivity.this, HomePageEmployee.class);
                                i.putExtra("id_User", id_User);
                                i.putExtra("user_Email", user_Email);
                                i.putExtra("user_Name", user_Name);
                                i.putExtra("user_Last", user_Last);
                                i.putExtra("user_Password", user_Password);
                                startActivity(i);
                            }else if (user_Type.equals("employer")){
                                Intent i = new Intent(LoginActivity.this, HomePage.class);
                                i.putExtra("id_User", id_User);
                                i.putExtra("user_Email", user_Email);
                                i.putExtra("user_Name", user_Name);
                                i.putExtra("user_Last", user_Last);
                                i.putExtra("user_Password", user_Password);
                                startActivity(i);
                            }

                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);

                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Hubo un error en tu email o contraseña. Intenta de nuevo", Toast.LENGTH_SHORT).show();

                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Hubo un error en tu email o contraseña. Intenta de nuevo", Toast.LENGTH_SHORT).show();

                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password_login", password_login);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void cleanUp(){
        email.getText().clear();
        password_login.getText().clear();
    }
}
