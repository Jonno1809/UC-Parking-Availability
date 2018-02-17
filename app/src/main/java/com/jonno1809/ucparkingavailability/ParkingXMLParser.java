package com.jonno1809.ucparkingavailability;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
            return readFeed(parser);
        } finally {
            inputStream.close();
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List car_parks = new ArrayList();

        parser.require(XmlPullParser.START_TAG, namespace, "feed");
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

    private Object readCarPark(XmlPullParser parser) {
    }
}
