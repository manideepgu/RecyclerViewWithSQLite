package com.example.recyclerviewwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static String database_name="chat.db";
    public static String table_name="communicationlog";
    public static String COL_1="ID";
    public static String COL_2="FR";
    public static String COL_3="TT";
    public static String COL_4="LOGS";
    public static String COL_5="TIME";



    public DataBaseHelper(Context context) {
        super(context, database_name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table_name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ,FR " +
                "TEXT ,TT TEXT ,LOGS TEXT ,Time TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean insertData(String from, String to, String chat) {
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = dateTime.format(myFormatObj);

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(COL_2,from);
        content.put(COL_3,to);
        content.put(COL_4,chat);
        content.put(COL_5,formattedDate);

        long success = db.insert(table_name,null,content);

        Log.d("TAG","Inside insertData"+String.valueOf(success));
        Log.d("TAG",from+"   "+to+"    "+chat);

        if(success==-1){
            return false;
        }
        else{
            return true;
        }


    }

    public Cursor ViewData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=db.rawQuery("select * from "+table_name,null);
        return cur;
    }

    public int Deletedata(Long ID){
        SQLiteDatabase db=this.getWritableDatabase();

        return db.delete(table_name,"ID=?",new String[]{ID.toString()});
    }

    public boolean update(Long ID,String from,String to,String chat,String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(COL_1, ID);
        content.put(COL_2, from);
        content.put(COL_3, to);
        content.put(COL_4, chat);
        content.put(COL_5, date);
        db.update(table_name, content, "ID=?", new String[]{ID.toString()});
        return true;
    }



}
