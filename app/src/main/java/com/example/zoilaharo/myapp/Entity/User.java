package com.example.zoilaharo.myapp.Entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String email = "";

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    //"reminder_time" is the actual column name in the sql table.
    @ColumnInfo(name = "reminder_time")
    private String reminderTime;

    @ColumnInfo(name = "max_Distance")

    private String maxDistance;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "age_min")
    private int ageMin;

    @ColumnInfo(name = "age_max")
    private int ageMax;

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(String maxDistance) {
        this.maxDistance = maxDistance;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }
}