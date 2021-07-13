package com.example.recyclerviewwithsqlite;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Model> datalist;

    public Adapter(ArrayList<Model> datalist) {
        this.datalist = datalist;
        Log.d("TAG","Inside Adapter                             " + datalist);
    }


    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        Log.d("TAG","Inside OncreateViewHolder ##########################");



        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        String from =datalist.get(position).getFrom();
        String to =datalist.get(position).getTo();
        String chat=datalist.get(position).getChat();
        String date=datalist.get(position).getDate();
        Log.d("TAG","Inside OnBindViewHolder %%%%%%%%%%%%%%%%%%%%");
        holder.setData(from,to,chat,date);



    }

    @Override
    public int getItemCount() {
        Log.d("TAG","Inside ITEM COUNT");
        Log.d("TAG",String.valueOf(datalist.size()));
        return datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvfrom,tvto,tvchat,tvdate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvfrom=itemView.findViewById(R.id.from);
            tvto=itemView.findViewById(R.id.To);
            tvchat=itemView.findViewById(R.id.chat);
            tvdate=itemView.findViewById(R.id.date);

            tvfrom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    Model data =datalist.get(position);
                    Long ID = data.getID();
                    String from = "Manideep";
                    String to = data.getTo();
                    String chat = data.getChat();
                    String datetime = data.getDate();
                    MainActivity.mydb.update(ID,from,to,chat,datetime);
                    notifyItemChanged(position);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    Model data= datalist.get(position);
                    String from = data.getFrom();
                    String to = data.getTo();
                    String chat = data.getChat();
                    String datetime = data.getDate();

                    Toast.makeText(v.getContext(),"The position is "+String.valueOf(getAbsoluteAdapterPosition()),
                            Toast.LENGTH_SHORT).show();

                    showmessage(from,to,chat,datetime);
                }
            });
        }

        public void setData(String from, String to, String chat,String date) {
            tvfrom.setText(from);
            tvto.setText(to);
            tvchat.setText(chat);
            tvdate.setText(date);


        }

        public void showmessage(String from, String to,String chat, String datetime){
            AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
            alert.setCancelable(true);
            alert.setTitle(datetime);
            alert.setMessage("From: "+from+"\n"+"To: "+to+"\n\n"+chat);

            alert.setPositiveButton("Want to Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int position = getAbsoluteAdapterPosition();
                    long ID=datalist.get(position).getID();
                    MainActivity.mydb.Deletedata(ID);
                    notifyItemRemoved(position);
                    Toast.makeText(itemView.getContext(),
                            "You pressed Yes" + String.valueOf(position),
                            Toast.LENGTH_SHORT).show();

                }
            });

            alert.setNegativeButton("Dont delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(itemView.getContext(), "You Pressed No", Toast.LENGTH_SHORT).show();
                }
            });

            alert.show();
        }
    }


}
