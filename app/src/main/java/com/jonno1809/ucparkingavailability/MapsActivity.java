package com.jonno1809.ucparkingavailability;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;

import io.sentry.Sentry;
import io.sentry.android.AndroidSentryClientFactory;
import io.sentry.event.BreadcrumbBuilder;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        CarParkDetailsFragment
                .OnCarParkShapeSelectedListener {

    private GoogleMap mMap;
    private final String CARPARKDETAILS_FRAGMENT_TAG = "carParkDetailsFragment";
    private final String MAP_FRAGMENT_TAG = "mapFragment";
    private final String CURRENT_FRAGMENT_TAG = "currentFragment";
    private final String CARPARKS_TAG = "carParks";

    private HashMap<String, CarPark> carParks = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = this.getApplicationContext();
        String sentryDsn = "https://18624adbf94948f8ba771e915691d1da@sentry.io/1217471";
        Sentry.init(sentryDsn, new AndroidSentryClientFactory(context));

        setContentView(R.layout.activity_maps);

        // TODO: Clean everything up a little
        FragmentManager fragmentManager = getSupportFragmentManager();
        addBackStackChangedListener(fragmentManager);

        if (savedInstanceState != null) {
            Sentry.getContext().recordBreadcrumb(
                    new BreadcrumbBuilder().setMessage("Creating activity with savedInstanceState").build()
            );
            Fragment currentFragment = fragmentManager.getFragment(savedInstanceState, CURRENT_FRAGMENT_TAG);
            carParks = (HashMap<String, CarPark>) savedInstanceState.getSerializable(CARPARKS_TAG);
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, currentFragment)
                    .commit();
            if (currentFragment.getTag().equals(MAP_FRAGMENT_TAG)) {
                createMap((SupportMapFragment) currentFragment);
            }
        } else {
            SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager
                    .findFragmentById(R.id.fragmentContainer);
            createMap(mapFragment);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ucLatLng = new LatLng(-35.237894, 149.084055);
        mMap.addMarker(new MarkerOptions().position(ucLatLng).title("University of Canberra"));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (carParks.isEmpty()) {
            DownloadXmlTask downloadXmlTask = new DownloadXmlTask();
            String UC_URL = "https://www.canberra.edu.au/wsprd/UCMobile/parking.svc/availability";
            downloadXmlTask.execute(UC_URL);
        } else {
            addCarParkShapesToMap(carParks);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ucLatLng, 15));
    }

    @Override
    public void onCarParkShapeSelected(Uri uri) {

    }

    private void addBackStackChangedListener(final FragmentManager fragmentManager) {
        FragmentManager.OnBackStackChangedListener listener = new FragmentManager
                .OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fragmentManager.getBackStackEntryCount() == 0) {
                    displayActionBarUpArrow(false);
                    SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager
                            .findFragmentById(R.id.fragmentContainer);
                    createMap(mapFragment);
                } else {
                    displayActionBarUpArrow(true);
                }
            }
        };
        fragmentManager.addOnBackStackChangedListener(listener);
    }

    private class DownloadXmlTask extends AsyncTask<String, Void, HashMap<String, CarPark>> {

        @Override
        protected HashMap<String, CarPark> doInBackground(String... urls) {
            Sentry.getContext().recordBreadcrumb(
                    new BreadcrumbBuilder().setMessage("Attempt to run DownloadXmlTask").build());
            try {
                return getCarParkXmlFromNetwork(urls[0]);
            }
            catch (IOException e) {
                e.printStackTrace();
                Sentry.capture(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(HashMap<String, CarPark> result) {
            carParks = result;
            addCarParkShapesToMap(carParks);
        }
    }

    private void addCarParkShapesToMap(final HashMap<String, CarPark> carParks) {
        // For calculating colour percentages
        final int EMPTY_RED = 0;
        final int EMPTY_GREEN = 158;
        final int EMPTY_BLUE = 221;
        final int FULL_RGB = 180;

        if (carParks != null) {
            for (String carParkKey : carParks.keySet()) {
                CarPark carPark = carParks.get(carParkKey);
                String type = carPark.getType();
                float free = carPark.getFree();
                float capacity = carPark.getCapacity();

                Polygon carParkShape = mMap.addPolygon(carPark.getCarParkEdges());
                carParkShape.setClickable(true);
                carParkShape.setTag(carPark.getName());

                if (!type.contains("e-Permit") || capacity < 0) {
                    /* Purple */
                    carParkShape.setFillColor(Color.argb(153, 156, 117, 255));
                    carParkShape.setStrokeColor(Color.rgb(156, 117, 255));
                } else if (free == 0) {
                    /* Red */
                    carParkShape.setFillColor(Color.argb(153, 211, 0, 0));
                    carParkShape.setStrokeColor(Color.rgb(221, 0, 0));
                } else if (free < 15) {
                    /* Orange */
                    carParkShape.setFillColor(Color.argb(153, 255, 112, 56));
                    carParkShape.setStrokeColor(Color.rgb(255, 112, 56));
                } else {
                    /* Blue or grey */
                    float percent = free / capacity;
                    int red = FULL_RGB - Math.round((FULL_RGB - EMPTY_RED) * percent);
                    int green = FULL_RGB - Math.round((FULL_RGB - EMPTY_GREEN) * percent);
                    int blue = FULL_RGB - Math.round((FULL_RGB - EMPTY_BLUE) * percent);
                    int fillColour = Color.argb(153, red, green, blue);
                    int strokeColour = Color.rgb(red, green, blue);
                    carParkShape.setFillColor(fillColour);
                    carParkShape.setStrokeColor(strokeColour);
                }
            }
            GoogleMap.OnPolygonClickListener onPolygonClickListener = new GoogleMap.OnPolygonClickListener() {
                @Override
                public void onPolygonClick(Polygon polygon) {
                    String tag = String.valueOf(polygon.getTag());
                    CarPark carPark = carParks.get(tag);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment carParkDetailsFragment = CarParkDetailsFragment.newInstance(carPark);
                    fragmentTransaction.replace(R.id.fragmentContainer, carParkDetailsFragment, CARPARKDETAILS_FRAGMENT_TAG);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            };
            mMap.setOnPolygonClickListener(onPolygonClickListener);
        } else {
            Snackbar.make(findViewById(R.id.mapActivity), R.string.no_car_parks, Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    private void createMap(SupportMapFragment mapFragment) {
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
        }
        mapFragment.getMapAsync(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, mapFragment, MAP_FRAGMENT_TAG)
                .commit();
    }

    private HashMap<String, CarPark> getCarParkXmlFromNetwork(String urlString) throws IOException {
        InputStream stream = null;
        ParkingXMLParser parkingXMLParser = new ParkingXMLParser();
        HashMap<String, CarPark> carParks = null;

        try {
            stream = downloadUrl(urlString);
            carParks = parkingXMLParser.parse(stream);
        } catch (XmlPullParserException | IOException e) {
            Sentry.capture(e);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return carParks;
    }

    private InputStream downloadUrl(String urlString) throws IOException {
        Sentry.getContext().recordBreadcrumb(
                new BreadcrumbBuilder().setMessage("Attempting to download URL").build()
        );
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            return connection.getInputStream();
        } catch (UnknownHostException e) {
            Log.e("UnknownHostException", e.getMessage());

            /* Only let Sentry capture UnknownHostException if there is an active internet connection,
            * UnknownHostException isn't a bug if the user wasn't connected to the internet. */
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = null;
            if (connectivityManager != null) {
                activeNetwork = connectivityManager.getActiveNetworkInfo();
            }
            boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
            if (isConnected) {
                Sentry.capture(e);
            }
            return null;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void displayActionBarUpArrow(boolean isDisplayed) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(isDisplayed);
            actionBar.setDisplayShowHomeEnabled(isDisplayed);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String tag;
        if (fragmentManager.getBackStackEntryCount() == 0) {
            tag = MAP_FRAGMENT_TAG;
        } else {
            tag = CARPARKDETAILS_FRAGMENT_TAG;
        }
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        fragmentManager.putFragment(outState, CURRENT_FRAGMENT_TAG, fragment);
        outState.putSerializable(CARPARKS_TAG, carParks);
        super.onSaveInstanceState(outState);
    }
}
