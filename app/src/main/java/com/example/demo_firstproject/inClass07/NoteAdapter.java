package com.example.demo_firstproject.inClass07;

import android.annotation.SuppressLint;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_firstproject.R;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    ArrayList<Note> notes;
    String token;
    InClass07 activity;

    public NoteAdapter(ArrayList<Note> notes, String token, InClass07 activity) {
        this.notes = notes;
        this.token = token;
        this.activity = activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView noteText;
        private final Button deleteButton;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            this.noteText = view.findViewById(R.id.textViewIndividualNote);
            this.deleteButton = view.findViewById(R.id.buttonDeleteNote);
        }


        public TextView getNoteText() {
            return noteText;
        }

        public Button getDeleteButton() {
            return deleteButton;
        }
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        OkHttpClient client = new OkHttpClient();
        holder.getNoteText().setText(notes.get(position).getText());
        holder.getDeleteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestBody formBody = new FormBody.Builder()
                        .add("_id", notes.get(position).get_id())
                        .build();

                Request deleteRequest = new Request.Builder()
                        .url("http://dev.sakibnm.space:3000/api/note/delete")
                        .addHeader("x-access-token", token)
                        .post(formBody)
                        .build();

                client.newCall(deleteRequest).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, "Unable to delete note", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                notes.remove(holder.getAdapterPosition());
                                notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
