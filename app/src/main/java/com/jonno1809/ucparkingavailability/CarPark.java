package com.jonno1809.ucparkingavailability;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.List;

/**
 * Created by Jonno on 22/02/2018.
 * With help from http://en.proft.me/2017/02/28/pass-object-between-activities-android-parcelable/
 */

public class CarPark implements Parcelable {

    private final String name;
    private final int capacity;
    private final int free;
    private final int occupied;
    private final String type;
    private final LatLng coords;
    private final PolygonOptions carParkEdges;

    CarPark(final String name, final int capacity, final int free, final int occupied,
            final String type, final LatLng coords, final PolygonOptions carParkEdges) {
        this.name = name;
        this.capacity = capacity;
        this.free = free;
        this.occupied = occupied;
        this.type = type;
        this.coords = coords;
        this.carParkEdges = carParkEdges;
    }

    private CarPark(final Parcel in) {
        this.name = in.readString();
        this.capacity = in.readInt();
        this.free = in.readInt();
        this.occupied = in.readInt();
        this.type = in.readString();
        this.coords = in.readParcelable(LatLng.class.getClassLoader());
        this.carParkEdges = in.readParcelable(PolygonOptions.class.getClassLoader());
    }

    String getName() {
        return name;
    }

    int getCapacity() {
        return capacity;
    }

    int getFree() {
        return free;
    }

    int getOccupied() {
        return occupied;
    }

    LatLng getCoords() {
        return coords;
    }

    String getType() {
        return type;
    }

    PolygonOptions getCarParkEdges() {
        return carParkEdges;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<CarPark> CREATOR = new Parcelable.Creator<CarPark>() {

        @Override
        public CarPark createFromParcel(Parcel source) {
            return new CarPark(source);
        }

        @Override
        public CarPark[] newArray(int size) {
            return new CarPark[size];
        }
    };

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(this.getClass() == obj.getClass())) {
            return false;
        }

        CarPark carPark = (CarPark) obj;
        List<LatLng> carParkEdges = carPark.getCarParkEdges().getPoints();
        List<LatLng> thisCarParkEdges = this.getCarParkEdges().getPoints();

        return carPark.getName().equals(name) &&
                carPark.getType().equals(type) &&
                carPark.getOccupied() == occupied &&
                carPark.getFree() == free &&
                carPark.capacity == capacity &&
                carPark.getCoords().equals(coords) &&
                carParkEdges.size() == thisCarParkEdges.size() &&
                carParkEdges.containsAll(thisCarParkEdges);
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeInt(capacity);
        parcel.writeInt(free);
        parcel.writeInt(occupied);
        parcel.writeString(type);
        parcel.writeParcelable(coords, flags);
        parcel.writeParcelable(carParkEdges, flags);
    }
}
