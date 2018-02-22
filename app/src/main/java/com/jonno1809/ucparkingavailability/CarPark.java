package com.jonno1809.ucparkingavailability;

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

    public HashSet getShape_coords() {
        return shape_coords;
    }

    public String getType() {
        return type;
    }

    private final String name;
    private final int capacity;
    private final int free;
    private final int occupied;
    private final HashSet shape_coords;
    private final String type;

    public CarPark(String name, int capacity, int free, int occupied, HashSet shape_coords,
                   String type) {
        this.name = name;
        this.capacity = capacity;
        this.free = free;
        this.occupied = occupied;
        this.shape_coords = shape_coords;
        this.type = type;
    }
}
