package com.example.zoilaharo.myapp;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zoilaharo.myapp.Model.MatchesModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

public class MatchesRecyclerViewAdapter extends RecyclerView.Adapter<MatchesRecyclerViewAdapter.ViewHolder>
{
    // Set numbers of List in RecyclerView.
    private ArrayList<MatchesModel> aMatches;
    private final MatchesContentFragment.OnListFragmentInteractionListener mListener;
    private static final String TAG = MainActivity.class.getSimpleName();


    public MatchesRecyclerViewAdapter(ArrayList<MatchesModel> items, MatchesContentFragment.OnListFragmentInteractionListener listener) {
        this.aMatches = items;
        this.mListener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_matches, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesRecyclerViewAdapter.ViewHolder holder, int position) {
        //Add values from firebase to an arraylist of Matches
        holder.vMatches = aMatches.get(position);
        holder.name.setText(aMatches.get(position).name);
        holder.simageUrl = aMatches.get(position).imageUrl;

        Picasso.get().load(holder.simageUrl).into(holder.picture);
        holder.liked = aMatches.get(position).liked;
    }

   @Override
    public int getItemCount() {

        if(aMatches != null) {
            return aMatches.size();
        } else {
            Log.i(TAG, "getItemCount() equals zero");
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView picture;
        public final TextView name;
        public ImageButton btnlike;
        public MatchesModel vMatches;
        public Boolean liked;
        public String simageUrl;
        public final View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            picture = (ImageView) view.findViewById(R.id.matches_image);
            name = (TextView) view.findViewById(R.id.matches_title);
            btnlike = (ImageButton) view.findViewById(R.id.like_button);
        }


    }

//    public void updateMatchesListItems(List<MatchesModel> matches) {
//        final MatchesDiffCallback diffCallback = new MatchesDiffCallback(this.aMatches, matches);
//        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
//
//        this.aMatches.clear();
//        this.aMatches.addAll(matches);
//        diffResult.dispatchUpdatesTo(this);
//    }
}
