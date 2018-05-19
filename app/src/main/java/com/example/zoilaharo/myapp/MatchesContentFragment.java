package com.example.zoilaharo.myapp;

        import android.os.Bundle;
        import android.os.Parcelable;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.content.Context;

        import com.example.zoilaharo.myapp.Model.MatchesModel;
        import com.example.zoilaharo.myapp.viewmodels.MatchesViewModel;
        import java.util.ArrayList;

        import static com.android.volley.VolleyLog.TAG;


public class MatchesContentFragment extends Fragment {
    private RecyclerView recyclerView;
    private MatchesRecyclerViewAdapter adapter;
    private OnListFragmentInteractionListener mListener;
    private Parcelable recylerViewState;
    public static final String ARG_COLUMN_COUNT = "column-count";
    public static final String ARG_DATA_SET = "matches";
    private int mColumnCount = 6;
    ArrayList<MatchesModel> mMatches;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mMatches = getArguments().getParcelableArrayList(ARG_DATA_SET);
        }
        Log.i(TAG, "onCreate()");
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        // Instantiate view model
        MatchesViewModel viewModel = new MatchesViewModel();
        // Set the adapter
        viewModel.getDataFromViewModel(
                (ArrayList<MatchesModel> matches) -> {
                    MatchesRecyclerViewAdapter adapter = new MatchesRecyclerViewAdapter(matches, mListener);
                    recyclerView.setAdapter(adapter);
                    //recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    if (recylerViewState != null) {
                        recyclerView.getLayoutManager().onRestoreInstanceState(recylerViewState);
                    }
                    recyclerView.setLayoutManager(layoutManager);
                }
        );
        return recyclerView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnListFragmentInteractionListener){
            mListener = (OnListFragmentInteractionListener) context;
        } else{
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        Log.i(TAG, "onAttach()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Log.i(TAG, "onDetach()");
    }


    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(MatchesModel item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated()");
    }
}