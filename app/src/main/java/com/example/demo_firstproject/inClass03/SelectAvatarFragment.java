package com.example.demo_firstproject.inClass03;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.demo_firstproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectAvatarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectAvatarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectAvatarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectAvatarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectAvatarFragment newInstance(String param1, String param2) {
        SelectAvatarFragment fragment = new SelectAvatarFragment();
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
        View view = inflater.inflate(R.layout.activity_select_avatar, container, false);
        view.setBackgroundColor(Color.WHITE);

        ImageView f1, f2, f3, m1, m2, m3;

        f1 = view.findViewById(R.id.avatar_f_1);
        f2 = view.findViewById(R.id.avatar_f_2);
        f3 = view.findViewById(R.id.avatar_f_3);
        m1 = view.findViewById(R.id.avatar_m_1);
        m2 = view.findViewById(R.id.avatar_m_2);
        m3 = view.findViewById(R.id.avatar_m_3);

        f1.setOnClickListener(v -> sendData.fromFragment(R.drawable.avatar_f_1));

        f2.setOnClickListener(v -> sendData.fromFragment(R.drawable.avatar_f_2));

        f3.setOnClickListener(v -> sendData.fromFragment(R.drawable.avatar_f_3));

        m1.setOnClickListener(v -> sendData.fromFragment(R.drawable.avatar_m_1));

        m2.setOnClickListener(v -> sendData.fromFragment(R.drawable.avatar_m_2));

        m3.setOnClickListener(v -> sendData.fromFragment(R.drawable.avatar_m_3));

        return view;


    }

    IAvatar sendData;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IAvatar) {
            sendData = (IAvatar) context;
        } else {
            throw new RuntimeException(context + " must implement IAvatar");
        }
    }

    public interface IAvatar {
        void fromFragment(int id);
    }
}