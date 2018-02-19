package com.jonno1809.ucparkingavailability;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
    
    private CarPark readCarPark (XmlPullParser parser) throws XmlPullParserException, IOException {
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
                case "capacity": capacity = readCapacity(parser); break;
                case "free": free = readFree(parser); break;
                case "name": name = readName(parser); break;
                case "occupancy": occupied = readOccupancy(parser); break;
                case "shape_coords": shape_coords = readShapeCoords(parser); break;
                case "type": readType(parser); break;
                default: skip(parser);
            }
        }
        return new CarPark(name, capacity, free, occupied, shape_coords, type);
    }

    private String readName (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, "name");
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "name");
        return name;
    }

    private int readCapacity (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, "capacity");
        int capacity = Integer.parseInt(readText(parser));
        parser.require(XmlPullParser.END_TAG, namespace, "capacity");
        return capacity;
    }

    private int readFree (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, "free");
        int free = Integer.parseInt(readText(parser));
        parser.require(XmlPullParser.END_TAG, namespace, "free");
        return free;
    }

    private int readOccupancy (XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, "occupancy");
        int occupied = Integer.parseInt(readText(parser));
        parser.require(XmlPullParser.END_TAG, namespace, "occupancy");
        return occupied;
    }

    private HashSet readShapeCoords(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, "shape_coords");
        String temp = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "shape_coords");

        HashSet<String> coords = new LinkedHashSet<String>(25);

        for (String coord:temp.split("\\S{31},")) {
            coords.add(coord.substring(1,coord.length()-1));
        }
        return coords;
    }

    private String readType(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, "type");
        String type = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "type");
        return type;
    }

    private String readText (XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
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
    }
}
