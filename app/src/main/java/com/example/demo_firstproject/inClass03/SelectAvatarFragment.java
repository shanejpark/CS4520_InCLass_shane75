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
    public SelectAvatarFragment() {
        // Required empty public constructor
    }

    public static SelectAvatarFragment newInstance() {
        SelectAvatarFragment fragment = new SelectAvatarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_select_avatar, container, false);
        view.setBackgroundColor(Color.WHITE);

        ImageView f1, f2, f3, m1, m2, m3;

        requireActivity().setTitle(R.string.select_avatar);

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