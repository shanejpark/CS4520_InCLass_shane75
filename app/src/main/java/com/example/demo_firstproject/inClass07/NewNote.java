package com.example.demo_firstproject.inClass07;

public class NewNote {
    String userId, text, _id;
    int __v;


    public NewNote(String userId, String text, String _id, int __v) {
        this.userId = userId;
        this.text = text;
        this._id = _id;
        this.__v = __v;
    }

    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public String get_id() {
        return _id;
    }

    public int get__v() {
        return __v;
    }
}
