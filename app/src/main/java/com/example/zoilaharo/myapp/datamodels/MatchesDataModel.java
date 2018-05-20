package com.example.zoilaharo.myapp.datamodels;

import android.util.Log;

import java.util.HashMap;
import java.util.function.Consumer;

import com.example.zoilaharo.myapp.Model.MatchesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MatchesDataModel {
    private DatabaseReference mDatabase;
    private HashMap<DatabaseReference, ValueEventListener> listeners;


    public MatchesDataModel() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listeners = new HashMap<>();
    }


    public void getDataFromDataModel(Consumer<DataSnapshot> dataChangedCallback, Consumer<DatabaseError> dataErrorCallback) {
        // This is where we can construct our path
        DatabaseReference dataFirebaseRef = mDatabase.child("matches");
        ValueEventListener dataFirebaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataChangedCallback.accept(dataSnapshot);
                Log.i("dataSnapshotInsideModel: ", dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dataErrorCallback.accept(databaseError);
            }


        };
        dataFirebaseRef.addValueEventListener(dataFirebaseListener);
        listeners.put(dataFirebaseRef, dataFirebaseListener);
    }

    public void addMatchItem(MatchesModel item) {
        DatabaseReference matchItemsRef = mDatabase.child("matches");
        matchItemsRef.push().setValue(item);
    }
    //updates person by id
    public void updateMatchesItemById(MatchesModel matches) {
        DatabaseReference matchesItemsRef = mDatabase.child("matches");
        matchesItemsRef.child(matches.uid).setValue(matches);
    }
    public void clear() {
        // Clear all the listeners onPause
        listeners.forEach(Query::removeEventListener);
    }

}
