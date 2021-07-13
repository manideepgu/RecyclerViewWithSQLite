package com.example.recyclerviewwithsqlite;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BodyFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Model> datalist;
    Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_body, container, false);


        Log.d("TAG","inside Body Fragment");

        initData();
        initRecyclerView(view);

        return view;
    }

    public void initRecyclerView(View view){
        Log.d("TAG","Inside init Recycler View@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Log.d("TAG",datalist.toString());
        recyclerView=view.findViewById(R.id.rec_chat);
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new Adapter(datalist);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Log.d("TAG","Exiting init Recycler View@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    public void initData(){
        Cursor cur=MainActivity.mydb.ViewData();
        int counter=0;
        datalist=new ArrayList<>();
        while (cur.moveToNext()){
            datalist.add(new Model(cur.getLong(0),cur.getString(1),cur.getString(2),
                    cur.getString(3),
                    cur.getString(4)));
            Log.d("TAG",datalist.toString());

        }
    }

}