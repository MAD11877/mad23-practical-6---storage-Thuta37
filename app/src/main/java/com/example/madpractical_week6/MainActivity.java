package com.example.madpractical_week6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //User user = new User();
    //boolean followed = user.getFollowed();

    public void onFollowClick(View v) {
        if (user.getFollowed()) {
            Toast.makeText(getBaseContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
            Button button = (Button) findViewById(R.id.btnFollow);
            button.setText("FOLLOW");
            user.setFollowed(false);
        } else {
            Toast.makeText(getBaseContext(), "Followed", Toast.LENGTH_SHORT).show();
            Button button = (Button) findViewById(R.id.btnFollow);
            button.setText("UNFOLLOW");
            user.setFollowed(true);
        }

        // Update the database
        MyDBhandler dbHandler = new MyDBhandler(this, null, null, 1);
        dbHandler.updateUser(user);
    }

    public void onMessageClick(View v) {
        // MainActivity to MessageGroup
        Intent intent = new Intent(MainActivity.this, MessageGroup.class);
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("USER_OBJECT");
        if (user != null) {
            // Use the user object as needed
            String name = user.getName();
            String description = user.getDescription();
            boolean followed = user.getFollowed();

            // Update the UI with the user data
            TextView nametext = findViewById(R.id.textView);
            TextView descriptiontext = findViewById(R.id.textView2);
            Button followButton = findViewById(R.id.btnFollow);
            nametext.setText(name);
            descriptiontext.setText(description);

            if (followed) {
                followButton.setText("UNFOLLOW");
            } else {
                followButton.setText("FOLLOW");
            }
        }

    }
}