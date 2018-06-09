package com.jonno1809.ucparkingavailability;

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

    @Before
    public void setup() throws Exception {
        List<LatLng> coords = new LinkedList<>();
        coords.add(new LatLng(-35.240344, 149.086734));
        coords.add(new LatLng(-35.240344, 149.086573));
        carPark = new CarPark("K1", 27, 0, 0,  "Childcare pickup/dropoff", new LatLng
                (-35.240708, 149.086687), new PolygonOptions().addAll(coords));
        mapsActivity = Robolectric.setupActivity(MapsActivity.class);
    }

    @Test
    public void shouldNotBeNull() {
        Assert.assertNotNull(mapsActivity);
    }

    @Test
    public void fragementContainerShouldNotBeNull() {
        Assert.assertNotNull(mapsActivity.getSupportFragmentManager().findFragmentById(R.id.fragmentContainer));
    }
}
