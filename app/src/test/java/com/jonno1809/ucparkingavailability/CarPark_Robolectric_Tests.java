package com.jonno1809.ucparkingavailability;

import android.os.Parcel;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

@RunWith(RobolectricTestRunner.class)
public class CarPark_Robolectric_Tests {
    @Test
    public void testCarParkIsParcelable() {
        List<LatLng> coords = new LinkedList<>();
        coords.add(new LatLng(-35.240344, 149.086734));
        coords.add(new LatLng(-35.240344, 149.086573));
        CarPark carPark = new CarPark("K1", 27, 0, 0, "Childcare pickup/dropoff", new LatLng(-35.240708,
                149.086687), new PolygonOptions().addAll(coords));

        Parcel parcel = Parcel.obtain();
        carPark.writeToParcel(parcel, carPark.describeContents());
        parcel.setDataPosition(0);

        CarPark createdFromParcel = CarPark.CREATOR.createFromParcel(parcel);
        Assert.assertThat(createdFromParcel.getName(), is("K1"));
        Assert.assertThat(createdFromParcel.getCapacity(), is(27));
        Assert.assertThat(createdFromParcel.getFree(), is(0));
        Assert.assertThat(createdFromParcel.getOccupied(), is(0));
        Assert.assertThat(createdFromParcel.getType(), is("Childcare pickup/dropoff"));
        Assert.assertThat(createdFromParcel.getCoords(), is(new LatLng(-35.240708, 149.086687)));
        List<LatLng> carParkEdges = new PolygonOptions().addAll(coords).getPoints();
        List<LatLng> parcelEdges = createdFromParcel.getCarParkEdges().getPoints();
        Assert.assertEquals(parcelEdges, carParkEdges);
    }
}
