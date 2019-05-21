package com.example.evidenciafinal;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.evidenciafinal.adapters.RecyclerViewAdapter;
import com.example.evidenciafinal.model.Jobs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class vacantes extends Fragment {

    private final String JSON_URL = "http://androidproject6th.000webhostapp.com/workTitan/readOP.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Jobs> lstJobs;
    private RecyclerView recyclerView;
    public static String id_session = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lstJobs = new ArrayList<>();
        id_session = getActivity().getIntent().getExtras().getString("id_User");
        View view = inflater.inflate(R.layout.fragment_vacantes, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewId);
        jsonRequest();
        return view;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setuprecyclerview(lstJobs);
    }

    private void jsonRequest(){
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++){
                    try{
                        jsonObject = response.getJSONObject(i);
                        Jobs jobs = new Jobs();
                        jobs.setName_Op(jsonObject.getString("name_Op"));
                        jobs.setComp_Enter(jsonObject.getString("comp_Enter"));
                        jobs.setName_Enter(jsonObject.getString("name_Enter"));
                        jobs.setDes_Enter(jsonObject.getString("des_Enter"));
                        jobs.setId_Open(jsonObject.getString("id_Open"));
                        jobs.setId_UserPost(jsonObject.getString("id_UserPost"));
                        lstJobs.add(jobs);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }

                setuprecyclerview(lstJobs);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(vacantes.this.getActivity());
        requestQueue.add(request);
    }

    private void setuprecyclerview(List<Jobs> lstJobs) {
        RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter(this.getActivity(), lstJobs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        recyclerView.setAdapter(viewAdapter);
    }

    public String returnIdUser(){
        return id_session;
    }

}
