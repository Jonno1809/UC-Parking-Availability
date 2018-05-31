package com.jonno1809.ucparkingavailability;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class CarPark_Tests {

    @Test
    public void testEquals() {
        List<LatLng> coords = new LinkedList<>();
        coords.add(new LatLng(-35.240344,149.086734));
        coords.add(new LatLng(-35.240344,149.086573));
        CarPark carPark1 = new CarPark("K1",27,0,0,"Childcare pickup/dropoff",new LatLng(-35.240708,149.086687), new PolygonOptions().addAll(coords));
        CarPark carPark2 = new CarPark("K1",27,0,0,"Childcare pickup/dropoff",new LatLng(-35.240708,149.086687), new PolygonOptions().addAll(coords));
        Assert.assertEquals(carPark1, carPark2);
        coords.remove(0);
        CarPark carPark3 = new CarPark("K1",27,0,0,"Childcare pickup/dropoff",new LatLng(-35.240708,149.086687), new PolygonOptions().addAll(coords));
        Assert.assertNotEquals(carPark1, carPark3);
    }
}
