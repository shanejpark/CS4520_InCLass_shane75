package com.example.demo_firstproject.inClass07;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.demo_firstproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link start#newInstance} factory method to
 * create an instance of this fragment.
 */
public class start extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public start() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment start.
     */
    // TODO: Rename and change types and number of parameters
    public static start newInstance(String param1, String param2) {
        start fragment = new start();
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
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        Button login, register;
        login = view.findViewById(R.id.buttonLogin);
        register = view.findViewById(R.id.buttonRegister);
        login.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.InClass07,
                        new Login())
                .addToBackStack(null)
                .commit());
        register.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.InClass07,
                        new Register())
                .addToBackStack(null)
                .commit());
        return view;
    }
}