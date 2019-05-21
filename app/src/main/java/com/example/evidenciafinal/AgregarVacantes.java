package com.example.evidenciafinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
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


public class AgregarVacantes extends Fragment {

    private EditText name_Op, name_Enter, des_Enter, comp_Enter;
    private Button btn_op;
    private ProgressBar loading_regist;
    public String id_session = "";
    private static String URL_REGIST = "https://androidproject6th.000webhostapp.com/workTitan/registerNew.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        id_session = getActivity().getIntent().getExtras().getString("id_User");

        View view = inflater.inflate(R.layout.fragment_agregar_vacantes, container, false);
        name_Op = view.findViewById(R.id.name_Op);
        name_Enter = view.findViewById(R.id.name_Enter);
        des_Enter = view.findViewById(R.id.des_Enter);
        comp_Enter = view.findViewById(R.id.comp_Enter);
        btn_op = view.findViewById(R.id.btn_op);
        loading_regist = view.findViewById(R.id.loading_regist);

        btn_op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addApplication();
            }
        });

        return view;
    }

    private void addApplication(){
        loading_regist.setVisibility(View.VISIBLE);
        btn_op.setVisibility(View.GONE);

        final String name_Op = this.name_Op.getText().toString().trim();
        final String name_Enter = this.name_Enter.getText().toString().trim();
        final String des_Enter = this.des_Enter.getText().toString().trim();
        final String comp_Enter = this.comp_Enter.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AgregarVacantes.this.getActivity(), "Se agrego la vacante exitosamente!", Toast.LENGTH_SHORT).show();
                        loading_regist.setVisibility(View.GONE);
                        btn_op.setVisibility(View.VISIBLE);
                        cleanUp();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AgregarVacantes.this.getActivity(), "Hubo un error: " + error.toString(), Toast.LENGTH_SHORT).show();
                        loading_regist.setVisibility(View.GONE);
                        btn_op.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_UserPost", id_session);
                params.put("name_Op", name_Op);
                params.put("name_Enter", name_Enter);
                params.put("des_Enter", des_Enter);
                params.put("comp_Enter", comp_Enter);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(stringRequest);

    }

    public void cleanUp(){
        name_Op.setText("");
        name_Enter.setText("");
        des_Enter.setText("");
        comp_Enter.setText("");
    }
}
