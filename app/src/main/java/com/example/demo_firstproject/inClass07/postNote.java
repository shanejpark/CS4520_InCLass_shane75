package com.example.demo_firstproject.inClass07;

public class postNote {
    boolean posted;
    NewNote note;

    public postNote(boolean posted, NewNote note) {
        this.posted = posted;
        this.note = note;
    }

    public boolean isPosted() {
        return posted;
    }

    public NewNote getNote() {
        return note;
    }
}
