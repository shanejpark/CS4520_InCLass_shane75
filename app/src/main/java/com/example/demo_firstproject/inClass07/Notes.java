package com.example.demo_firstproject.inClass07;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo_firstproject.R;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Notes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Notes extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TOKEN = "token";

    // TODO: Rename and change types of parameters
    private String token;
    User user;

    public Notes() {
        // Required empty public constructor
    }


    public static Notes newInstance(String token) {
        Notes fragment = new Notes();
        Bundle args = new Bundle();
        args.putString(ARG_TOKEN, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString(ARG_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        TextView name, email;
        Button logOut, addNote;
        EditText newNote;
        RecyclerView notes;
        final NoteAdapter[] noteAdapter = new NoteAdapter[1];
        final NotesData[] notesData = new NotesData[1];
        RecyclerView.LayoutManager recyclerViewLayoutManager;

        Gson gson = new Gson();



        name = view.findViewById(R.id.textViewName);
        email = view.findViewById(R.id.textViewEmail);
        notes = view.findViewById(R.id.recyclerViewNotes);
        recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        notes.setLayoutManager(recyclerViewLayoutManager);

        OkHttpClient client = new OkHttpClient();

        Request userRequest = new Request.Builder()
                .url("http://dev.sakibnm.space:3000/api/auth/me")
                .addHeader("x-access-token", token)
                .build();

        client.newCall(userRequest).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Unable to get user info", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                user = gson.fromJson(response.body().charStream(), User.class);
                getActivity().runOnUiThread(() -> {
                    String nameText = String.format("Name: %s", user.getName());
                    String emailText = String.format("Email: %s", user.getEmail());
                    name.setText(nameText);
                    email.setText(emailText);
                });
            }
        });

        newNote = view.findViewById(R.id.editTextNote);
        addNote = view.findViewById(R.id.buttonAddNote);

        Request getNotesRequest = new Request.Builder()
                .url("http://dev.sakibnm.space:3000/api/note/getall")
                .addHeader("x-access-token", token)
                .build();

        client.newCall(getNotesRequest).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Unable to retrieve info", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notesData[0] = gson.fromJson(response.body().charStream(), NotesData.class);
                        noteAdapter[0] = new NoteAdapter(notesData[0].getNotes(), token, (InClass07) getActivity());
                        notes.setAdapter(noteAdapter[0]);
                    }
                });
            }
        });

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody formBody = new FormBody.Builder()
                        .add("text", newNote.getText().toString())
                        .build();
                Request addNoteRequest = new Request.Builder()
                        .url("http://dev.sakibnm.space:3000/api/note/post")
                        .addHeader("x-access-token", token)
                        .post(formBody)
                        .build();

                client.newCall(addNoteRequest).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "Unable to add note", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                postNote postNote = null;
                                try {
                                    postNote = gson.fromJson(response.body().string(), postNote.class);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                assert postNote != null;
                                NewNote note = postNote.getNote();
                                notesData[0].addNote(new Note(note.get_id(), note.getUserId(), note.getText(), note.get__v()));
                                noteAdapter[0].notifyDataSetChanged();
                            }
                        });
                    }
                });


            }
        });


        return view;
    }
}