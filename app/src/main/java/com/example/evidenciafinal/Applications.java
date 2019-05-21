package com.example.evidenciafinal;

import android.app.Application;
import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.evidenciafinal.adapters.RecyclerViewAdapter;
import com.example.evidenciafinal.model.Jobs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Applications extends Fragment {

    private final String JSON_URL = "http://androidproject6th.000webhostapp.com/workTitan/readOpUser.php";
    private String id_session = "";
    private RequestQueue requestQueue;
    private List<Jobs> lstJobs;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lstJobs = new ArrayList<>();

        id_session = getActivity().getIntent().getExtras().getString("id_User");

        View view = inflater.inflate(R.layout.fragment_apply, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewapplyId);

        jsonRequest();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setuprecyclerview(lstJobs);
    }

    private void jsonRequest(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObjectData = new JSONObject(response);
                            JSONArray jsonArray = jsonObjectData.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject json = jsonArray.getJSONObject(i);
                                Jobs jobs = new Jobs();
                                jobs.setName_Op(json.getString("name_Op"));
                                jobs.setComp_Enter(json.getString("comp_Enter"));
                                jobs.setName_Enter(json.getString("name_Enter"));
                                jobs.setDes_Enter(json.getString("des_Enter"));
                                lstJobs.add(jobs);
                            }
                        }catch (JSONException e){
                            Toast.makeText(Applications.this.getActivity(), "Hubo un error en el formato JSON", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                        setuprecyclerview(lstJobs);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Applications.this.getActivity(), "Hubo un error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_User", id_session);
                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(Applications.this.getActivity());
        requestQueue.add(stringRequest);
}

    private void setuprecyclerview(List<Jobs> lstJobs) {
        RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter(this.getActivity(), lstJobs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        recyclerView.setAdapter(viewAdapter);
    }

}
