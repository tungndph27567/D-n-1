package com.example.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ClassUser.class}, version = 2)
public abstract class ClassDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "Class.db";
    public static ClassDatabase instance;

    public static synchronized ClassDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ClassDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;

    }

    public abstract ClassDAO dao();
}
