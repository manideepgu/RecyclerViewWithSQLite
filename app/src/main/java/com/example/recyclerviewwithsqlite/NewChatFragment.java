package com.example.recyclerviewwithsqlite;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class NewChatFragment extends Fragment {
    View view;
    Button btn_submitchat;
    EditText edit_from,edit_to,edit_chat;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_chat, container, false);

        btn_submitchat=view.findViewById(R.id.btn_submit);
        edit_from=view.findViewById(R.id.edit_from);
        edit_to=view.findViewById(R.id.edit_to);
        edit_chat=view.findViewById(R.id.edit_chat);

        btn_submitchat.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                boolean isinserted = MainActivity.mydb.insertData(edit_from.getText().toString(),
                        edit_to.getText().toString(),
                edit_chat.getText().toString());

                if(isinserted==true){
                    edit_from.setText("");
                    edit_to.setText("");
                    edit_chat.setText("");
                }
            }
        });

        Log.d("TAG","inside New Chat Fragment");

        return view;
    }
}