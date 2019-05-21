package com.example.evidenciafinal;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class JobDescription extends AppCompatActivity {

    public String id_Session = "";
    public String id_Open = "";
    public String id_UserPost = "";
    public String URL_APPLY = "https://androidproject6th.000webhostapp.com/workTitan/apply_Job.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);
        vacantes vac = new vacantes();
        getSupportActionBar().hide();
        String op_name = getIntent().getExtras().getString("op_name");
        String comp_name = getIntent().getExtras().getString("comp_name");
        String comp_Enter = getIntent().getExtras().getString("comp_Enter");
        String des_Enter = getIntent().getExtras().getString("des_Enter");
        id_Open = getIntent().getExtras().getString("id_Open");
        id_UserPost = getIntent().getExtras().getString("id_UserPost");
        id_Session = vac.returnIdUser();

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView company = findViewById(R.id.company);
        TextView compensation = findViewById(R.id.compensation);
        TextView description = findViewById(R.id.description);

        company.setText(comp_name);
        compensation.setText(comp_Enter);
        description.setText(des_Enter);
        collapsingToolbarLayout.setTitle(op_name);

        FloatingActionButton fab = findViewById(R.id.apply);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Applying", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                addApplication();
            }
        });
    }

    private void addApplication(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_APPLY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(JobDescription.this, "Aplicaste Exitosamente!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(JobDescription.this, "Hubo un error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_UserPost", id_UserPost);
                params.put("id_Open", id_Open);
                params.put("id_UserOp", id_Session);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
