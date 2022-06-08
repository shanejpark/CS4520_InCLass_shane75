package com.example.demo_firstproject.inClass07;

import java.util.ArrayList;

public class NotesData {
    ArrayList<Note> notes;


    public NotesData(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void addNote(Note note) {
        notes.add(note);
    }
}
