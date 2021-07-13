package com.example.recyclerviewwithsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btn_newchat,btn_viewchat;

    static DataBaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_newchat = findViewById(R.id.btn_newchat);
        btn_viewchat = findViewById(R.id.btn_viewchat);




        mydb=new DataBaseHelper(this);


        btn_newchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new NewChatFragment());
            }
        });

        btn_viewchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new BodyFragment());
            }
        });



    }

    public void changeFragment(Fragment fragment){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.frg_body,fragment);
        transaction.commit();
    }


}