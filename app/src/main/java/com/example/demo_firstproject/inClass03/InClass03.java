package com.example.demo_firstproject.inClass03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo_firstproject.R;
import com.example.demo_firstproject.inClass02.Profile;

import java.util.Objects;
import java.util.regex.Pattern;

public class InClass03 extends AppCompatActivity implements SelectAvatarFragment.IAvatar {

    EditText name, email;
    TextView currentMood;
    ImageView avatar, moodEmoji;
    RadioGroup os;
    SeekBar mood;
    Button submit;
    String osText;
    int moodID, avatarID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class02);
        setTitle(R.string.edit_profile);

        osText = "";

        name = findViewById(R.id.name_input);
        email = findViewById(R.id.email_input);
        avatar = findViewById(R.id.avatar_image);
        avatarID = R.drawable.select_avatar;
        os = findViewById(R.id.os_radio_group);
        mood = findViewById(R.id.mood_seekbar);
        moodEmoji = findViewById(R.id.mood_emoji_image);
        submit = findViewById(R.id.submit_button);
        currentMood = findViewById(R.id.current_mood_text);

        os.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.android_radio_button) {
                osText = "Android";
            } else {
                osText = "iOS";
            }
        });

        mood.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    case 0:
                        moodID = R.drawable.angry;
                        currentMood.setText(R.string.angry);
                        break;
                    case 1:
                        moodID = R.drawable.sad;
                        currentMood.setText(R.string.sad);
                        break;
                    case 2:
                        moodID = R.drawable.happy;
                        currentMood.setText(R.string.happy);
                        break;
                    case 3:
                        moodID = R.drawable.awesome;
                        currentMood.setText(R.string.awesome);
                        break;
                }
                moodEmoji.setImageResource(moodID);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        avatar.setOnClickListener(v ->
                {
                    name.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    email.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragmentContainer, SelectAvatarFragment.newInstance())
                            .addToBackStack(null)
                            .commit();
                }
        );

        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);

        submit.setOnClickListener(v -> {
                    String nameString = name.getText().toString();
                    String emailString = email.getText().toString();
                    String moodText = currentMood.getText().toString();

                    if (nameString.equals("")) {
                        Toast.makeText(this, "Name must not be empty", Toast.LENGTH_LONG).show();
                    } else if (emailString.equals("") || !pattern.matcher(emailString).matches()) {
                        Toast.makeText(this, "Email input was invalid", Toast.LENGTH_LONG).show();
                    } else if (Objects.equals(osText, "")) {
                        Toast.makeText(this, "Select an OS", Toast.LENGTH_LONG).show();
                    } else if (avatarID == R.drawable.select_avatar) {
                        Toast.makeText(this, "Select an avatar", Toast.LENGTH_LONG).show();
                    } else {
                        Profile profile = new Profile(nameString,
                                emailString,
                                osText,
                                moodText,
                                avatarID,
                                moodID
                        );

                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.fragmentContainer,
                                        DisplayProfileFragment.newInstance(profile))
                                .addToBackStack(null)
                                .commit();
                    }
                }
        );
    }


    @Override
    public void fromFragment(int id) {
        avatarID = id;
        avatar.setImageResource(avatarID);
        getSupportFragmentManager().popBackStack();
        setTitle(R.string.edit_profile);
    }
}