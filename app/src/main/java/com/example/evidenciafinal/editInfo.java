package com.example.evidenciafinal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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


public class editInfo extends Fragment {

    private EditText nameUpdate, last_NameUpdate, emailUpdate, passwordUpdate;
    private Button btn_update;
    public String id_session = "";
    private static String URL_REGIST = "https://androidproject6th.000webhostapp.com/workTitan/updateInfo.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        id_session = getActivity().getIntent().getExtras().getString("id_User");
        View view = inflater.inflate(R.layout.fragment_edit_info, container, false);

        nameUpdate = view.findViewById(R.id.nameUpdate);
        last_NameUpdate = view.findViewById(R.id.l_nameUpdate);
        emailUpdate = view.findViewById(R.id.emailUpdate);
        passwordUpdate = view.findViewById(R.id.passUpdate);
        btn_update = view.findViewById(R.id.update);

        nameUpdate.setText(getActivity().getIntent().getExtras().getString("user_Name"));
        last_NameUpdate.setText(getActivity().getIntent().getExtras().getString("user_Last"));
        emailUpdate.setText(getActivity().getIntent().getExtras().getString("user_Email"));
        passwordUpdate.setText(getActivity().getIntent().getExtras().getString("user_Password"));

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

        return view;
    }

    private void updateUser(){

        final String nameUp = this.nameUpdate.getText().toString().trim();
        final String lnameUp = this.last_NameUpdate.getText().toString().trim();
        final String emailUp = this.emailUpdate.getText().toString().trim();
        final String passUp = this.passwordUpdate.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(editInfo.this.getActivity(), "Se actualizo correctamente! Inicie Sesion de Nuevo para efectuar cambios", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(editInfo.this.getActivity(), "Hubo un error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_NameUpdate", nameUp);
                params.put("user_LastUpdate", lnameUp);
                params.put("user_EmailUpdate", emailUp);
                params.put("user_PasswordUpdate", passUp);
                params.put("id_UserOp", id_session);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(stringRequest);

    }
}
