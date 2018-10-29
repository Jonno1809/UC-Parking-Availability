package com.jonno1809.ucparkingavailability;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.LinkedList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MapsActivity_Robolectric_Tests {

    private MapsActivity mapsActivity;
    Context context;
    private CarPark carPark;

    @Before
    public void setup() throws Exception {
        List<LatLng> coords = new LinkedList<>();
        coords.add(new LatLng(-35.240344, 149.086734));
        coords.add(new LatLng(-35.240344, 149.086573));
        carPark = new CarPark("K1", 27, 0, 0, "Childcare pickup/dropoff", new LatLng
                (-35.240708, 149.086687), new PolygonOptions().addAll(coords));
        Intent intent = new Intent();
        mapsActivity = Robolectric.buildActivity(MapsActivity.class).create().resume()
                .get();
        mapsActivity = Robolectric.setupActivity(MapsActivity.class);
        context = mapsActivity.getApplicationContext();
    }

    @Test
    public void shouldNotBeNull() {
        Assert.assertNotNull(mapsActivity);
    }

    @Test
    public void fragementContainerShouldNotBeNull() {
        Assert.assertNotNull(mapsActivity.getSupportFragmentManager().findFragmentById(R.id.fragmentContainer));
    }


    /* TODO: Figure out how Robolectric works (lol) */
//    @Test
//    public void testMenuItemsContainCorrectStrings() {
//        RoboMenu menu = new RoboMenu();
//        Assert.assertNotNull(menu);
//
////        RoboMenuItem satelliteMenuItem = (RoboMenuItem) menu.add(0,0, 0, R.id.satelliteMapMenuItem);
////        RoboMenuItem plainMenuItem = (RoboMenuItem) menu.add(0,1,1, R.id.normalMapMenuItem);
////        RoboMenuItem terrainMenuItem = (RoboMenuItem) menu.add(0,3,2, R.id.terrain);
////        RoboMenuItem hybridMenuItem = (RoboMenuItem) menu.add(0,3,3, R.id.hybridMapMenuItem);
//
//        MenuItem satelliteMenuItem = menu.findItem(R.id.satelliteMapMenuItem);
//        MenuItem plainMenuItem = new RoboMenuItem(R.id.normalMapMenuItem);
//        MenuItem terrainMenuItem = new RoboMenuItem(R.id.terrainMapMenuItem);
//        MenuItem hybridMenuItem = new RoboMenuItem(R.id.hybridMapMenuItem);
////        mapsActivity.onCreateOptionsMenu(menu);
//        Assert.assertEquals("Satellite view", satelliteMenuItem.getTitle());
//        Assert.assertEquals("Normal view", plainMenuItem.getTitle());
//        Assert.assertEquals("Terrain view", terrainMenuItem.getTitle());
//        Assert.assertEquals("Hybrid view", hybridMenuItem.getTitle());
//    }
//
//    @Test
//    public void testMapTypeMenuItemsDisplayCorrectMapType() {
//        MenuItem menuItem = new RoboMenuItem(R.id.satelliteMapMenuItem);
//        GoogleMap googleMap = mapsActivity.getMap();
//        mapsActivity.onOptionsItemSelected(menuItem);
//        Assert.assertEquals(GoogleMap.MAP_TYPE_SATELLITE, googleMap.getMapType());
//
//        menuItem = new RoboMenuItem(R.id.normalMapMenuItem);
//        mapsActivity.onOptionsItemSelected(menuItem);
//        Assert.assertEquals(GoogleMap.MAP_TYPE_NORMAL, googleMap.getMapType());
//
//        menuItem = new RoboMenuItem(R.id.terrainMapMenuItem);
//        mapsActivity.onOptionsItemSelected(menuItem);
//        Assert.assertEquals(GoogleMap.MAP_TYPE_TERRAIN, googleMap.getMapType());
//
//        menuItem = new RoboMenuItem(R.id.hybridMapMenuItem);
//        mapsActivity.onOptionsItemSelected(menuItem);
//        Assert.assertEquals(GoogleMap.MAP_TYPE_HYBRID, googleMap.getMapType());
//    }
}
