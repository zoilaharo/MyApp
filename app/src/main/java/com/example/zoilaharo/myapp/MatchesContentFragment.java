package com.example.zoilaharo.myapp;

        import android.location.Location;
        import android.location.LocationListener;
        import android.os.AsyncTask;
        import android.os.Bundle;
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

        import com.example.zoilaharo.myapp.Entity.User;
        import com.example.zoilaharo.myapp.Model.MatchesModel;
        import com.example.zoilaharo.myapp.viewmodels.MatchesViewModel;

        import java.lang.ref.WeakReference;
        import java.util.ArrayList;
        import java.util.List;

        import static android.media.CamcorderProfile.get;
        import static com.android.volley.VolleyLog.TAG;


public class MatchesContentFragment extends Fragment {
    private int TENMILES = 16094;
    private int DISTANCE = 0;
    private MatchesRecyclerViewAdapter adapter;
    private MatchesViewModel viewModel;
    private OnListFragmentInteractionListener mListener;
    public static final String ARG_COLUMN_COUNT = "column-count";
    public static final String ARG_DATA_SET = "matches";
    private int mColumnCount = 6;
    private List<MatchesModel> mMatches;
    double longitudeNetwork, latitudeNetwork;
     android.location.LocationManager locationManager;
    private UserAccount.Operation operation;
    String email;
    List<User> users = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);

         locationManager = (android.location.LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (checkLocation()) {
            toggleNetworkUpdates(recyclerView);
        }
        viewModel = new MatchesViewModel();

        adapter = new MatchesRecyclerViewAdapter(mMatches, mListener);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }
    public void setOperation(UserAccount.Operation operation) {
        this.operation = operation;
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

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(MatchesModel item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated()");
    }

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
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Log.i(TAG, "onDetach()");
    }

  private boolean checkLocation() {
        if(!isLocationEnabled()) {
            showAlert();
        }
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER);
    }


        public void toggleNetworkUpdates(View view) {
        if(!checkLocation()) {
            return;
        }
          if (android.support.v4.app.ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED ||
                android.support.v4.app.ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(android.location.LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListenerNetwork);
                android.widget.Toast.makeText(getActivity(), R.string.network_provider_started_running, android.widget.Toast.LENGTH_LONG).show();
            }

    }
    private void showAlert() {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.enable_location)
            .setMessage(getString(R.string.location_message))
            .setPositiveButton(R.string.location_settings, (paramDialogInterface, paramInt) -> {
                android.content.Intent myIntent = new android.content.Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(myIntent);
            })
            .setNegativeButton(R.string.location_cancel, (paramDialogInterface, paramInt) -> {});
        dialog.show();
    }
   private final android.location.LocationListener locationListenerNetwork = new android.location.LocationListener() {
        public void onLocationChanged(android.location.Location location) {
            if (operation != null) {

                email = operation.email;


            }
            new MatchesContentFragment.GetUserTask(MatchesContentFragment.this, email, location).execute();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };
    private class GetUserTask extends AsyncTask<Void, Void, User> {

        private WeakReference<Fragment> weakFragment;
        private String email;
        private Location location;

        public GetUserTask(android.support.v4.app.Fragment fragment, String email, Location location) {
            weakFragment = new WeakReference<>(fragment);
            this.email = email;
            this.location = location;
        }

        @Override
        protected User doInBackground(Void... voids) {
            Fragment fragment = weakFragment.get();
            if (fragment == null) {
                return null;
            }

            AppDatabase db = AppDatabaseSingleton.getDatabase(fragment.getActivity());
            String[] emails = { email };

            users = db.userDao().loadAllByIds(emails);

            if(users.size() <= 0 || users.get(0) == null) {
                return null;
            }
            return users.get(0);
        }

        @Override
        protected void onPostExecute(User user) {
            MatchesContentFragment fragment = (MatchesContentFragment) weakFragment.get();
            if (user == null || fragment == null) {
                return;
            }
            DISTANCE = Integer.parseInt(user.getMaxDistance());

            ArrayList<MatchesModel> tempMatch = new ArrayList<MatchesModel>();

            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();

            viewModel.getDataFromViewModel(
                    (ArrayList<MatchesModel> matches) -> {

                        for(int x = 0; x < matches.size(); x++)
                        {
                            double lat1 = Double.parseDouble(matches.get(x).lat);
                            double long1 = Double.parseDouble(matches.get(x).longitude);
                            float[] distance = new float[1];

                            Location.distanceBetween(latitudeNetwork, longitudeNetwork, lat1, long1, distance);
                            if (distance[0] < DISTANCE) {
                                tempMatch.add(matches.get(x));
                            }
                            adapter.updateMatchesListItems(tempMatch);
                        }

                    }
            );


        }
    }

}