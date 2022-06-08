package com.example.demo_firstproject.inClass07;

public class Note {
    String _id, userId, text;
    int __v;

    public Note(String _id, String userId, String text, int __v) {
        this._id = _id;
        this.userId = userId;
        this.text = text;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public int get__v() {
        return __v;
    }
}
