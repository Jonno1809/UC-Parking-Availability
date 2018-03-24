package com.jonno1809.ucparkingavailability;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private final String UC_URL = "https://www.canberra.edu.au/wsprd/UCMobile/parking.svc/availability";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ucLatLng = new LatLng(-35.237894, 149.084055);
        mMap.addMarker(new MarkerOptions().position(ucLatLng).title("University of Canberra"));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        DownloadXmlTask downloadXmlTask = new DownloadXmlTask();
        downloadXmlTask.execute(UC_URL);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ucLatLng,15));

    }

    private class DownloadXmlTask extends AsyncTask<String, Void, List<CarPark>> {

        @Override
        protected List<CarPark> doInBackground(String... urls) {
            try {
                return getCarParkXmlFromNetwork(urls[0]);
            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<CarPark> result) {
            // Could be improved by changing car park list to hashmap or something
            Iterator<CarPark> carParkIterator = result.iterator();
            final HashMap<String, CarPark> carParkHashMap = new HashMap<>(result.size());

            while (carParkIterator.hasNext()) {
                final CarPark carPark = carParkIterator.next();

                Polygon carParkShape = mMap.addPolygon(carPark.getCarParkEdges());
                carParkShape.setClickable(true);
//                final Marker carParkMarker = mMap.addMarker(new MarkerOptions()
//                        .position(carPark.getCoords()));
//                carParkMarker.setTitle(carPark.getName().toUpperCase());
//                carParkMarker.setSnippet("Free spaces: " + carPark.getFree());
//                if (carPark.getFree() == 0) {
//                    carParkMarker.setVisible(false);
//                }
                String type = carPark.getType();
                if (!type.contains("e-Permit")) {
                    /* Purple */
                    carParkShape.setFillColor(Color.argb(153,156,117,255));
                    carParkShape.setStrokeColor(Color.argb(255, 156, 117, 255));
                } else if (carPark.getFree() == 0) {
                    /* Red */
                    carParkShape.setFillColor(Color.argb(153,211,0,0));
                    carParkShape.setStrokeColor(Color.argb(255,221,0,0));
                } else {
                    /* Grey */
                    carParkShape.setFillColor(Color.argb(153,180,180,180));
                    carParkShape.setStrokeColor(Color.argb(255,180,180,180));
                }
                carParkHashMap.put(carParkShape.getId(), carPark);
            }


            GoogleMap.OnPolygonClickListener onPolygonClickListener = new GoogleMap.OnPolygonClickListener() {
                @Override
                public void onPolygonClick(Polygon polygon) {
                    CarPark carPark = carParkHashMap.get(polygon.getId());
                    Intent intent = new Intent(getApplicationContext(), CarParkDetailsActivity.class);
                    intent.putExtra("carPark", (Parcelable) carPark);
                    startActivity(intent);
                }
            };
            mMap.setOnPolygonClickListener(onPolygonClickListener);
        }
    }

    private List<CarPark> getCarParkXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        ParkingXMLParser parkingXMLParser = new ParkingXMLParser();
        List<CarPark> carParks = null;

        try {
            stream = downloadUrl(urlString);
            carParks = parkingXMLParser.parse(stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return carParks;
    }

    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.connect();
        return connection.getInputStream();
    }

//    private void showCarParkDetailsOnClick(CarPark carPark,)
}
