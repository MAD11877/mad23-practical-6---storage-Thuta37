package com.example.madpractical_week6;

import static com.example.madpractical_week6.MyDBhandler.TABLE_USERS;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

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

        MyDBhandler dbHandler = new MyDBhandler(this, null, null,1);
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        // Check if the database is empty
        if (dbHandler.isDatabaseEmpty()) {
            // Database is empty, populate it with 20 user records

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

                dbHandler.addUser(user);
            }
        }

        List<User> userList = dbHandler.getUsers();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        UserAdapter mAdapter = new UserAdapter((ArrayList<User>) userList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        db.close();
    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}