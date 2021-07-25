package com.example.recyclerviewwithsqlite;

public class Model {
    String from,to,chat,date;
    long ID;

    public Model(long ID,String from, String to, String chat,String date) {
        this.ID=ID;
        this.from = from;
        this.to = to;
        this.chat = chat;
        this.date = date;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public long getID() {
        return ID;
    }

    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getChat() {
        return chat;
    }
}
