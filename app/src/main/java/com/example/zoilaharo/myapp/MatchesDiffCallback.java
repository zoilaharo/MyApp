package com.example.zoilaharo.myapp;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.zoilaharo.myapp.Model.MatchesModel;

import java.util.List;

public class MatchesDiffCallback extends DiffUtil.Callback{

        private final List<MatchesModel> mOldMatchesList;
        private final List<MatchesModel> mNewMatchesList;

        public MatchesDiffCallback(List<MatchesModel> oldMatchesList, List<MatchesModel> newMatchesList) {
            this.mOldMatchesList = oldMatchesList;
            this.mNewMatchesList = newMatchesList;
        }

        @Override
        public int getOldListSize() {
            return mOldMatchesList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewMatchesList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldMatchesList.get(oldItemPosition).uid == mNewMatchesList.get(
                    newItemPosition).uid;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final MatchesModel oldMatches = mOldMatchesList.get(oldItemPosition);
            final MatchesModel newMatches = mNewMatchesList.get(newItemPosition);

            return oldMatches.name.equals(newMatches.name);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            // Implement method if you're going to use ItemAnimator
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }

}
