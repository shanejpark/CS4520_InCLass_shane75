package com.example.demo_firstproject.inClass07;

public class User {
    String _id, name, email;
    int __v;


    public User(String _id, String name, String email, int __v) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int get__v() {
        return __v;
    }
}
