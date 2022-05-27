package com.example.demo_firstproject.inClass05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo_firstproject.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InClass05 extends AppCompatActivity {
    ImageView image, previous, next;
    Button go;
    TextView loadingText;
    EditText searchKeywordInput;
    ProgressBar loadingProgress;
    List<String> urlList = new ArrayList<>();
    String[] keywordList;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class05);
        setTitle("Image Search");

        image = findViewById(R.id.loaded_image);
        previous = findViewById(R.id.previous_button);
        next = findViewById(R.id.next_button);
        go = findViewById(R.id.go_button);
        loadingText = findViewById(R.id.loading_text);
        searchKeywordInput = findViewById(R.id.search_keyword_input);
        loadingProgress = findViewById(R.id.loading_progress_bar);

        OkHttpClient client = new OkHttpClient();

        if (isOnline()) {
            Request keywordRequest = new Request.Builder()
                    .url("http://dev.sakibnm.space/apis/images/keywords")
                    .build();
            client.newCall(keywordRequest).enqueue(new okhttp3.Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        keywordList = response.body().string().split(",");
                    }
                }

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Request Failed", Toast.LENGTH_SHORT).show());
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
        }


        go.setOnClickListener(v -> {
            if (isOnline()) {
                HttpUrl url = HttpUrl.parse("http://dev.sakibnm.space/apis/images/retrieve").newBuilder()
                        .addQueryParameter("keyword",
                                searchKeywordInput.getText().toString().trim().replaceAll("\\s+", "_").toLowerCase(Locale.ROOT))
                        .build();

                Request imageRequest = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(imageRequest).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Request Failed", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            searchKeywordInput.onEditorAction(EditorInfo.IME_ACTION_DONE);
                            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Successfully retrieved images", Toast.LENGTH_SHORT).show());
                            String string = response.body().string();
                            urlList = Arrays.asList(string.trim().split("\\s"));
                            index = 0;
                            runOnUiThread(() -> loadImage());
                        } else {
                            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Keyword not found", Toast.LENGTH_SHORT).show());
                            runOnUiThread(() -> image.setImageResource(0));
                        }
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }

        });

        previous.setOnClickListener(v -> {
            if (urlList.size() > 1) {
                if (index == 0) {
                    index = urlList.size() - 1;
                } else {
                    index -= 1;
                }
                loadImage();
            }
        });

        next.setOnClickListener(v -> {
            if (urlList.size() > 1) {
                if (index == urlList.size() - 1) {
                    index = 0;
                } else {
                    index += 1;
                }
                loadImage();
            }
        });
    }

    private void loadImage() {
        if (isOnline()) {
            loadingProgress.setVisibility(View.VISIBLE);
            loadingText.setVisibility(View.VISIBLE);
            Picasso.get().load(urlList.get(index)).into(image, new Callback() {
                @Override
                public void onSuccess() {
                    loadingProgress.setVisibility(View.GONE);
                    loadingText.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {
                    loadingProgress.setVisibility(View.GONE);
                    loadingText.setVisibility(View.GONE);
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Unable to load image: " + e.toString(), Toast.LENGTH_SHORT).show());
                }
            });
        } else {
            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show());
        }
    }

    /**
     * Found on Stack Overflow
     * https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
     *
     * @return whether or not the device is connected to a network
     */
    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}