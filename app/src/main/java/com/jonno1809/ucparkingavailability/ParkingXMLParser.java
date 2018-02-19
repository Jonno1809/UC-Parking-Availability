package com.jonno1809.ucparkingavailability;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Jonno on 18/02/2018.
 */

public class ParkingXMLParser {

    private static final String namespace = null;

    public List parse(InputStream inputStream) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);
            parser.nextTag();
            return readCarParks(parser);
        } finally {
            inputStream.close();
        }
    }

    private List readCarParks(XmlPullParser parser) throws XmlPullParserException, IOException {
        List car_parks = new ArrayList();

        parser.require(XmlPullParser.START_TAG, namespace, "car_parks");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals("car_park")) {
                car_parks.add(readCarPark(parser));
            }
            else {
                skip(parser);
            }
        }

        return car_parks;
    }

    public class CarPark {
        private final String name;
        private final int capacity;
        private final int free;
        private final int occupied;
        private final HashSet shape_coords;
        private final String type;

        public CarPark(String name, int capacity, int free, int occupied, HashSet shape_coords, String type) {
            this.name = name;
            this.capacity = capacity;
            this.free = free;
            this.occupied = occupied;
            this.shape_coords = shape_coords;
            this.type = type;
        }

        private CarPark readCarPark(XmlPullParser parser) throws XmlPullParserException, IOException {
            parser.require(XmlPullParser.START_TAG, namespace, "car_park");
            String name = null;
            int capacity = 0;
            int free = 0;
            int occupied = 0;
            HashSet shape_coords = null;
            String type = null;

            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }

                String parserName = parser.getName();
                switch (parserName){
                    case "capacity": readCapacity(parser); break;
                    case "free": readFree(parser); break;
                    case "name": readName(parser); break;
                    case "occupancy": readOccupancy(parser); break;
                    case "shape_coords": readShapeCoords(parser); break;
                    case "type": readType(parser); break;
                    default: skip(parser);
                }
            }
            return new CarPark(name, capacity, free, occupied, shape_coords, type);
        }

    }

}
