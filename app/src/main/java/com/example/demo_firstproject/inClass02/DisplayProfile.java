package com.example.demo_firstproject.inClass02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo_firstproject.R;

public class DisplayProfile extends AppCompatActivity {

    final String OS = "I use ";
    final String MOOD = "I am ";
    TextView name, email, os, mood;
    ImageView avatar, moodEmoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);
        setTitle(R.string.display_profile);
        name = findViewById(R.id.profile_name_text);
        email = findViewById(R.id.profile_email_text);
        os = findViewById(R.id.profile_os_text);
        mood = findViewById(R.id.profile_mood_text);
        avatar = findViewById(R.id.profile_avatar_image);
        moodEmoji = findViewById(R.id.profile_mood_image);

        if (getIntent() != null && getIntent().getExtras() != null) {
            Profile profile = getIntent().getParcelableExtra("user_profile");
            name.setText(profile.getName());
            email.setText(profile.getEmail());
            os.setText(String.format("%s%s", OS, profile.getOs()));
            String format = !profile.getMood().equals("Sad") ? "%s%s!": "%s%s";
            mood.setText(String.format(format, MOOD, profile.getMood()));
            avatar.setImageResource(profile.getAvatarID());
            moodEmoji.setImageResource(profile.getMoodID());
        }
    }
}