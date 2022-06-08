package com.example.demo_firstproject.inClass07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
 * Use the {@link Register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Register.
     */
    // TODO: Rename and change types and number of parameters
    public static Register newInstance(String param1, String param2) {
        Register fragment = new Register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        EditText name, email, password;
        name = view.findViewById(R.id.editTextRegisterName);
        email = view.findViewById(R.id.editTextTextRegisterEmail);
        password = view.findViewById(R.id.editTextRegisterPassword);
        Button register = view.findViewById(R.id.buttonCreateAccount);
        OkHttpClient client = new OkHttpClient();

        register.setOnClickListener(v -> {
            RequestBody formBody = new FormBody.Builder()
                    .add("name", name.getText().toString())
                    .add("email", email.getText().toString())
                    .add("password", password.getText().toString())
                    .build();

            Request request = new Request.Builder()
                    .url("http://dev.sakibnm.space:3000/api/auth/register")
                    .post(formBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "Unable to register", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    Gson gson = new Gson();
                    Token token = gson.fromJson(response.body().charStream(), Token.class);
                    if (token.getToken() == null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "Already taken", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                    else {
                        sendData.fromFragment(token);
                    }
                }
            });
        });

        return view;
    }

    IToken sendData;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IToken) {
            sendData = (IToken) context;
        } else {
            throw new RuntimeException(context + " must implement IAvatar");
        }
    }

    public interface IToken {
        void fromFragment(Token token);
    }
}