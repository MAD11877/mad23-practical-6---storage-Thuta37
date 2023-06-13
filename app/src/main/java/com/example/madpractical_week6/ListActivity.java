package com.example.madpractical_week6;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        List<User> userList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            User user = new User();

            // Randomize the name as an integer
            int randomName = getRandomInt(0, 10000000);
            String name = "Name: " + randomName;
            user.setName(name);

            // Randomize the description (with integers)
            int randomDescription = getRandomInt(0, 10000000);
            String description = "Description: " + randomDescription;
            user.setDescription(description);

            // Randomize the value of Followed
            boolean randomFollowed = getRandomInt(0, 1) == 1;
            user.setFollowed(randomFollowed);

            userList.add(user);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        UserAdapter mAdapter = new UserAdapter((ArrayList<User>) userList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}