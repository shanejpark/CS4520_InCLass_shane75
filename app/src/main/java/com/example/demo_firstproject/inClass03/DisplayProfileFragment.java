package com.example.demo_firstproject.inClass03;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo_firstproject.R;
import com.example.demo_firstproject.inClass02.Profile;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PROFILE = "profile";

    // TODO: Rename and change types of parameters
    private Profile profile;

    public DisplayProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DisplayProfileFragment newInstance(Profile profile) {
        DisplayProfileFragment fragment = new DisplayProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PROFILE, profile);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profile = getArguments().getParcelable(ARG_PROFILE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_display_profile, container, false);

        view.setBackgroundColor(Color.WHITE);

        final String OS = "I use ";
        final String MOOD = "I am ";
        TextView name, email, os, mood;
        ImageView avatar, moodEmoji;

        requireActivity().setTitle(R.string.display_profile);
        name = view.findViewById(R.id.profile_name_text);
        email = view.findViewById(R.id.profile_email_text);
        os = view.findViewById(R.id.profile_os_text);
        mood = view.findViewById(R.id.profile_mood_text);
        avatar = view.findViewById(R.id.profile_avatar_image);
        moodEmoji = view.findViewById(R.id.profile_mood_image);

        name.setText(profile.getName());
        email.setText(profile.getEmail());
        os.setText(String.format("%s%s", OS, profile.getOs()));
        String format = !profile.getMood().equals("Sad") ? "%s%s!": "%s%s";
        mood.setText(String.format(format, MOOD, profile.getMood()));
        avatar.setImageResource(profile.getAvatarID());
        moodEmoji.setImageResource(profile.getMoodID());


        return view;
    }
}