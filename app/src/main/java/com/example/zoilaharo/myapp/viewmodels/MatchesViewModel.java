package com.example.zoilaharo.myapp.viewmodels;

import android.util.Log;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.example.zoilaharo.myapp.Model.MatchesModel;
import com.example.zoilaharo.myapp.datamodels.MatchesDataModel;
import com.google.firebase.database.DataSnapshot;

public class MatchesViewModel {
    private MatchesDataModel model;


    public MatchesViewModel() {

        model = new MatchesDataModel();
    }

    public void addmatchItem(MatchesModel item) {
        model.addMatchItem(item);
    }
    public void getDataFromViewModel(Consumer<ArrayList<MatchesModel>> resultCallback) {

        model.getDataFromDataModel(
                (DataSnapshot dataSnapshot) -> {
                    ArrayList<MatchesModel> arrayOfusers= new ArrayList<>();
                    for (DataSnapshot matchesSnapshot : dataSnapshot.getChildren()) {
                        Log.i("dataSnapshotInsideViewModel: ", matchesSnapshot.getKey());
                        MatchesModel matches = matchesSnapshot.getValue(MatchesModel.class);
                        assert matches != null;
                        matches.uid = matchesSnapshot.getKey();
                        arrayOfusers.add(matches);
                    }
                    resultCallback.accept(arrayOfusers);
                },
                (databaseError -> System.out.println("Error reading Firebase data: " + databaseError))
        );
    }
    //updates the item in the database
    public void updateMatchesItem(MatchesModel person) {
        model.updateMatchesItemById(person);
    }
    public void clear() {
        model.clear();
    }
}
