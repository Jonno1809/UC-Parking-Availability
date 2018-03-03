package com.jonno1809.ucparkingavailability;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.HashSet;

/**
 * Created by Jonno on 22/02/2018.
 */

public class CarPark {
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFree() {
        return free;
    }

    public int getOccupied() {
        return occupied;
    }

    public LatLng getCoords() {
        return coords;
    }
    public String getType() {
        return type;
    }
    private final String name;
    private final int capacity;
    private final int free;
    private final int occupied;

    private final String type;

    private final LatLng coords;

    public PolygonOptions getCarParkEdges() {
        return carParkEdges;
    }

    private final PolygonOptions carParkEdges;

    public CarPark(String name, int capacity, int free, int occupied,
                   String type, LatLng coords, PolygonOptions carParkEdges) {
        this.name = name;
        this.capacity = capacity;
        this.free = free;
        this.occupied = occupied;
        this.type = type;
        this.coords = coords;
        this.carParkEdges = carParkEdges;
    }
}
