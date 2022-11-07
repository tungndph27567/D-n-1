package com.example.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {user.class}, version = 1)
public abstract class userDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "user.db";
    private static userDatabase instance;

    public static synchronized userDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), userDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract userDAO dao();
}
