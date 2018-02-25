package com.jonno1809.ucparkingavailability;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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
            Iterator<CarPark> carParkIterator = result.iterator();

            while (carParkIterator.hasNext()) {
                CarPark carPark = carParkIterator.next();

                HashSet carParkShape_coords = carPark.getShape_coords();
                Iterator coordsIterator = carParkShape_coords.iterator();
                PolygonOptions carParkEdges = new PolygonOptions();

                // This could need improvement
                while (coordsIterator.hasNext()) {
                    Object coord = coordsIterator.next();
                    String sLat = coord.toString().split(",")[0];
                    String sLng = coord.toString().split(",")[1];
                    double lat = Double.parseDouble(sLat);
                    double lng = Double.parseDouble(sLng);
                    LatLng shapeVertex = new LatLng(lat, lng);
                    carParkEdges.add(shapeVertex);
                }
                Polygon carParkShape = mMap.addPolygon(carParkEdges);
                carParkShape.setClickable(true);
                Marker carParkMarker = mMap.addMarker(new MarkerOptions()
                        .position(carPark.getCoords()));
                carParkMarker.setTitle(carPark.getName().toUpperCase());
                carParkMarker.setSnippet("Free spaces: " + carPark.getFree());
                carParkMarker.setVisible(false);
            }

            mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
                @Override
                public void onPolygonClick(Polygon polygon) {

                }
            });
        }
    }

    private List<CarPark> getCarParkXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        ParkingXMLParser parkingXMLParser = new ParkingXMLParser();
        List<CarPark> carParks = null;

        try {
            stream = downloadUrl(urlString);
            carParks = parkingXMLParser.parse(stream);
        } catch (Exception e){
            e.printStackTrace();
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
}
