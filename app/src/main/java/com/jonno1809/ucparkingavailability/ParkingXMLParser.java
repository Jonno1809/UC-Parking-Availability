package com.jonno1809.ucparkingavailability;

import android.util.Xml;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Jonno on 18/02/2018.
 * (With a little help from https://developer.android.com/training/basics/network-ops/xml.html)
 */

public class ParkingXMLParser {

    private static final String namespace = null;
    private final Map<String, LatLng> carParksCoordinates = createCoordinatesMap();

    private Map<String, LatLng> createCoordinatesMap() {
        Map<String, LatLng> coordinates = new HashMap<>();
        coordinates.put("K1", new LatLng(-35.240708, 149.086687));
        coordinates.put("W1", new LatLng(-35.240309, 149.083892));
        coordinates.put("P26", new LatLng(-35.242471, 149.094229));
        coordinates.put("P13", new LatLng(-35.241558, 149.087653));
        coordinates.put("P14", new LatLng(-35.241790, 149.085770));
        coordinates.put("P22", new LatLng(-35.239436, 149.088266));
        coordinates.put("P24", new LatLng(-35.240112, 149.086092));
        coordinates.put("P4", new LatLng( -35.238129, 149.089786));
        coordinates.put("P7", new LatLng( -35.236219, 149.081621));
        coordinates.put("P9a", new LatLng(-35.240822, 149.083737));
        coordinates.put("P9b", new LatLng(-35.241860, 149.083689));
        coordinates.put("P9c", new LatLng(-35.240857, 149.084590));
        coordinates.put("P9d", new LatLng(-35.241777, 149.084735));
        coordinates.put("P10", new LatLng(-35.237406, 149.086414));
        coordinates.put("P17", new LatLng(-35.236092, 149.086285));
        coordinates.put("P25", new LatLng(-35.238016, 149.082650));
        coordinates.put("P28", new LatLng(-35.234908, 149.087701));
        coordinates.put("R6", new LatLng(-35.239582, 149.082583));
        coordinates.put("R5", new LatLng(-35.240838, 149.080468));
        coordinates.put("R4", new LatLng(-35.240176, 149.080388));
        coordinates.put("R3", new LatLng( -35.239756, 149.080651));
        coordinates.put("R2", new LatLng(-35.239648, 149.079639));
        coordinates.put("R1", new LatLng(-35.238764, 149.075313));
        coordinates.put("P23", new LatLng(-35.239966, 149.088648));
        coordinates.put("P11", new LatLng(-35.237921, 149.086657));
        return coordinates;
    }


    Map<String, CarPark> parse(final InputStream inputStream) throws XmlPullParserException, IOException {
        try {
            if (inputStream != null) {
                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(inputStream, null);
                parser.nextTag();
                return readCarParks(parser);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return null;
    }

    private Map<String, CarPark> readCarParks(final XmlPullParser parser) throws
            XmlPullParserException, IOException {

        Map<String, CarPark> carParks = new HashMap<>();

        parser.require(XmlPullParser.START_TAG, namespace, "parking_availability");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals("car_parks")) {
                parser.require(XmlPullParser.START_TAG, namespace, "car_parks");
                while (parser.next() != XmlPullParser.END_TAG) {
                    CarPark carPark = readCarPark(parser);
                    carParks.put(carPark.getName(), carPark);
                }
            }
            else {
                skip(parser);
            }
        }

        return carParks;
    }


    private CarPark readCarPark(final XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, namespace, "car_park");
        String name = null;
        int capacity = 0;
        int free = 0;
        int occupied = 0;
        PolygonOptions carParkEdges = null;
        String type = null;
        LatLng coords;

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
                case "shape_coords": carParkEdges = readShapeCoords(parser); break;
                case "type": type = readType(parser); break;
                default: skip(parser);
            }
        }
        coords = carParksCoordinates.get(name);
        return new CarPark(name, capacity, free, occupied, type, coords, carParkEdges);
    }

    private String readName(final XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, "name");
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "name");
        return name;
    }

    private int readCapacity(final XmlPullParser parser) throws IOException, XmlPullParserException {
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

    private int readOccupancy(final XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, "occupancy");
        int occupied = Integer.parseInt(readText(parser));
        parser.require(XmlPullParser.END_TAG, namespace, "occupancy");
        return occupied;
    }

    // Note: UC stores coordinates as {lng:y, lat:x} for some reason
    private PolygonOptions readShapeCoords(final XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, "shape_coords");
        String temp = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "shape_coords");
        // Could probably be improved
        temp = temp.replaceAll(Pattern.quote("},{"),";");
        temp = temp.replaceAll("[{}:]","");

        PolygonOptions carParkEdges = new PolygonOptions();

        // Split coordinates and remove curly brackets
        for (String coord: temp.split(";")) {
            // Get coordinates only (without lng or lat labels in)
            String[] latLng = coord.split(",");
            String sLng = latLng[0].contains("lng") ? latLng[0] : latLng[1];
            String sLat = latLng[1].contains("lat") ? latLng[1] : latLng[0];
            sLng = sLng.replaceAll("[a-z]","");
            sLat = sLat.replaceAll("[a-z]","");
            double lat = Double.parseDouble(sLat);
            double lng = Double.parseDouble(sLng);
            LatLng shapeVertex = new LatLng(lat, lng);
            carParkEdges.add(shapeVertex);
        }
        return carParkEdges;
    }

    private String readType(final XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, "type");
        String type = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "type");
        return type;
    }

    private String readText(final XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(final XmlPullParser parser) throws XmlPullParserException, IOException {
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
}
