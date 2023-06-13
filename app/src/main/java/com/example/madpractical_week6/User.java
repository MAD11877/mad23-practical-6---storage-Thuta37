package com.example.madpractical_week6;

public class User {
    String Name;
    String Description;
    int Id;
    boolean Followed;

    public void User() {

    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String d) {
        Description = d;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setFollowed(boolean f) {
        Followed = f;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public int getId() {
        return Id;
    }

    public boolean getFollowed() {
        return Followed;
    }
}

