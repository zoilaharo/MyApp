package com.example.zoilaharo.myapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.example.zoilaharo.myapp.Entity.User;
import com.example.zoilaharo.myapp.dao.UserDao;

@Database(entities = {User.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
