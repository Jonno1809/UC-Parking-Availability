package com.jonno1809.ucparkingavailability;

import java.util.HashSet;

/**
 * Created by Jonno on 18/02/2018.
 */

public class CarPark {
    private final String name;
    private final int capacity;
    private final int free;
    private final HashSet shape_coords;
    private final String type;

    public CarPark(String name, int capacity, int free, HashSet shape_coords, String type) {
        this.name = name;
        this.capacity = capacity;
        this.free = free;
        this.shape_coords = shape_coords;
        this.type = type;
    }

}
