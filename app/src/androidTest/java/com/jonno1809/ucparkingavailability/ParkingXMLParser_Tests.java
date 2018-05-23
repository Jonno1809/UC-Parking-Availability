package com.jonno1809.ucparkingavailability;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import junit.framework.Assert;

import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ParkingXMLParser_Tests {

    private HashMap<String, CarPark> carParks = new HashMap<>();

    private void setUpCarParks() {
        ParkingXMLParser parkingXMLParser = new ParkingXMLParser();
        Context ctx = InstrumentationRegistry.getContext();
        String fileName = "CarParksXML_Test.xml";
//        File testXmlFile = ;
//        try {
//            InputStream inputStream = ctx.getAssets().open(fileName);
//            HashMap<String, CarPark> carParks = parkingXMLParser.parse(inputStream);
//            int i = 0;
//            for (String carParkKey : carParks.keySet()) {
//                CarPark carPark = carParks.get(carParkKey);
//                String latLng = "new LatLng(" + carPark.getCoords().latitude + "," + carPark.getCoords().longitude + ")";
//                List<LatLng> edges = carPark.getCarParkEdges().getPoints();
//                int index = 0;
////                List<String> temp = new LinkedList<>();
//                StringBuilder listLog = new StringBuilder("coords = new LinkedList<>();\n");
//                for (LatLng edge : edges) {
//                    listLog.append("coords.add(new LatLng(").append(edge.latitude).append(",").append(edge
//                            .longitude).append("));\n");
////                    temp.add(listLog.toString());
//
//                    index++;
//                }
//                Log.i("tag", listLog +
////                        "CarPark carPark" + i + " = new CarPark" +
////                        "(\"" + carPark.getName() + "\"," + carPark.getCapacity() +
////                        "," + carPark.getFree() + "," + carPark.getOccupied() + ",\"" + carPark.getType() + "\"," + latLng +
////                        ", new PolygonOptions().addAll(coords));\n"+
//                        "this.carParks.put(\""+carPark.getName()+"\", new CarPark"+"(\"" + carPark.getName() + "\"," + carPark.getCapacity
//                        () +
//                        "," + carPark.getFree() + "," + carPark.getOccupied() + ",\"" + carPark.getType() + "\"," + latLng +
//                        ", new PolygonOptions().addAll(coords)));");
//                i++;
//            }
//        } catch (XmlPullParserException | IOException e) {
//            e.printStackTrace();
//        }

        List<LatLng> coords = new LinkedList<>();
        coords.add(new LatLng(-35.240344,149.086734));
        coords.add(new LatLng(-35.240344,149.086573));
        coords.add(new LatLng(-35.240858,149.086574));
        coords.add(new LatLng(-35.240855,149.086769));
        coords.add(new LatLng(-35.24044,149.086763));
        coords.add(new LatLng(-35.240417,149.08675));
        coords.add(new LatLng(-35.240401,149.086736));
        coords.add(new LatLng(-35.240375,149.086734));
        this.carParks.put("K1", new CarPark("K1",27,0,0,"Childcare pickup/dropoff",new LatLng(-35.240708,149.086687), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.24015,149.088339));
        coords.add(new LatLng(-35.239968,149.088336));
        coords.add(new LatLng(-35.239958,149.088078));
        coords.add(new LatLng(-35.239817,149.088082));
        coords.add(new LatLng(-35.239817,149.088487));
        coords.add(new LatLng(-35.239823,149.088725));
        coords.add(new LatLng(-35.239867,149.088724));
        coords.add(new LatLng(-35.239867,149.088795));
        coords.add(new LatLng(-35.240107,149.088796));
        coords.add(new LatLng(-35.240105,149.088727));
        coords.add(new LatLng(-35.240149,149.088725));
        this.carParks.put("P23", new CarPark("P23",67,0,0,"Tenant parking",new LatLng(-35.239966,149.088648), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.239808,149.087635));
        coords.add(new LatLng(-35.239226,149.087616));
        coords.add(new LatLng(-35.239207,149.087866));
        coords.add(new LatLng(-35.238758,149.087844));
        coords.add(new LatLng(-35.238745,149.088413));
        coords.add(new LatLng(-35.239188,149.088432));
        coords.add(new LatLng(-35.239173,149.088855));
        coords.add(new LatLng(-35.239535,149.088834));
        coords.add(new LatLng(-35.239628,149.088343));
        coords.add(new LatLng(-35.239641,149.087978));
        coords.add(new LatLng(-35.23965,149.087785));
        coords.add(new LatLng(-35.239662,149.087734));
        coords.add(new LatLng(-35.2397,149.087699));
        coords.add(new LatLng(-35.23974,149.087686));
        coords.add(new LatLng(-35.239808,149.087689));
        coords.add(new LatLng(-35.239817,149.088039));
        coords.add(new LatLng(-35.239988,149.088037));
        coords.add(new LatLng(-35.239992,149.088328));
        coords.add(new LatLng(-35.240172,149.088331));
        coords.add(new LatLng(-35.24017,149.087211));
        coords.add(new LatLng(-35.239992,149.087207));
        coords.add(new LatLng(-35.239989,149.087632));
        this.carParks.put("P22", new CarPark("P22",384,376,8,"e-Permit parking",new LatLng(-35.239436,149.088266), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.238243,149.082879));
        coords.add(new LatLng(-35.237649,149.082893));
        coords.add(new LatLng(-35.237636,149.082461));
        coords.add(new LatLng(-35.238191,149.082442));
        this.carParks.put("P25", new CarPark("P25",0,0,0,"e-Permit/casual parking",new LatLng(-35.238016,149.08265), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.240183,149.086761));
        coords.add(new LatLng(-35.239975,149.086758));
        coords.add(new LatLng(-35.239966,149.08546));
        coords.add(new LatLng(-35.24017,149.08546));
        this.carParks.put("P24", new CarPark("P24",85,84,1,"e-Permit parking",new LatLng(-35.240112,149.086092), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.242135,149.093942));
        coords.add(new LatLng(-35.24235,149.093937));
        coords.add(new LatLng(-35.242359,149.093992));
        coords.add(new LatLng(-35.242658,149.093997));
        coords.add(new LatLng(-35.242706,149.093989));
        coords.add(new LatLng(-35.242743,149.093962));
        coords.add(new LatLng(-35.242793,149.093919));
        coords.add(new LatLng(-35.242839,149.09397));
        coords.add(new LatLng(-35.242762,149.094043));
        coords.add(new LatLng(-35.242705,149.094098));
        coords.add(new LatLng(-35.242664,149.094165));
        coords.add(new LatLng(-35.242609,149.094269));
        coords.add(new LatLng(-35.242579,149.094347));
        coords.add(new LatLng(-35.242579,149.094416));
        coords.add(new LatLng(-35.242576,149.094493));
        coords.add(new LatLng(-35.242057,149.0945));
        coords.add(new LatLng(-35.242049,149.094414));
        coords.add(new LatLng(-35.242087,149.094339));
        coords.add(new LatLng(-35.242118,149.094272));
        coords.add(new LatLng(-35.242125,149.094257));
        this.carParks.put("P26", new CarPark("P26",-1,0,0,"e-Permit parking",new LatLng(-35.242471,149.094229), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.23481,149.086951));
        coords.add(new LatLng(-35.235056,149.08694));
        coords.add(new LatLng(-35.235043,149.088426));
        coords.add(new LatLng(-35.234596,149.08841));
        coords.add(new LatLng(-35.234596,149.088094));
        coords.add(new LatLng(-35.234802,149.088094));
        this.carParks.put("P28", new CarPark("P28",100,0,0,"Health Hub services parking",new LatLng(-35.234908,149.087701), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.241283,149.084073));
        coords.add(new LatLng(-35.241283,149.08326));
        coords.add(new LatLng(-35.242259,149.083244));
        coords.add(new LatLng(-35.242246,149.084089));
        this.carParks.put("P9b", new CarPark("P9b",233,218,15,"e-Permit/casual parking",new LatLng(-35.24186,149.083689), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.24105,149.083404));
        coords.add(new LatLng(-35.240581,149.083506));
        coords.add(new LatLng(-35.240398,149.083596));
        coords.add(new LatLng(-35.2404,149.084029));
        coords.add(new LatLng(-35.241094,149.084023));
        coords.add(new LatLng(-35.241074,149.083402));
        this.carParks.put("P9a", new CarPark("P9a",103,38,65,"e-Permit/casual parking",new LatLng(-35.240822,149.083737), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.24128,149.084341));
        coords.add(new LatLng(-35.24224,149.084344));
        coords.add(new LatLng(-35.242228,149.085167));
        coords.add(new LatLng(-35.241345,149.085173));
        coords.add(new LatLng(-35.241289,149.085111));
        coords.add(new LatLng(-35.24128,149.084851));
        coords.add(new LatLng(-35.241252,149.084819));
        this.carParks.put("P9d", new CarPark("P9d",147,146,1,"e-Permit/casual parking",new LatLng(-35.241777,149.084735), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.240372,149.084883));
        coords.add(new LatLng(-35.240366,149.08429));
        coords.add(new LatLng(-35.241125,149.084285));
        coords.add(new LatLng(-35.241125,149.084907));
        this.carParks.put("P9c", new CarPark("P9c",152,16,136,"e-Permit/casual parking",new LatLng(-35.240857,149.08459), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.238432,149.074616));
        coords.add(new LatLng(-35.238989,149.074616));
        coords.add(new LatLng(-35.238992,149.074968));
        coords.add(new LatLng(-35.239002,149.074982));
        coords.add(new LatLng(-35.239054,149.074982));
        coords.add(new LatLng(-35.239052,149.075312));
        coords.add(new LatLng(-35.239213,149.075316));
        coords.add(new LatLng(-35.239218,149.075694));
        coords.add(new LatLng(-35.239168,149.075695));
        coords.add(new LatLng(-35.239176,149.075804));
        coords.add(new LatLng(-35.238985,149.075922));
        coords.add(new LatLng(-35.238937,149.075835));
        coords.add(new LatLng(-35.238875,149.075832));
        coords.add(new LatLng(-35.238809,149.07584));
        coords.add(new LatLng(-35.238809,149.076092));
        coords.add(new LatLng(-35.238615,149.076092));
        coords.add(new LatLng(-35.238611,149.075928));
        coords.add(new LatLng(-35.238607,149.075755));
        coords.add(new LatLng(-35.238604,149.075619));
        coords.add(new LatLng(-35.238665,149.075615));
        coords.add(new LatLng(-35.238659,149.075442));
        coords.add(new LatLng(-35.238652,149.075281));
        coords.add(new LatLng(-35.238654,149.075202));
        coords.add(new LatLng(-35.238434,149.0752));
        coords.add(new LatLng(-35.238431,149.074944));
        this.carParks.put("R1", new CarPark("R1",-1,0,0,"Student residence parking",new LatLng(-35.238764,149.075313), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.239311,149.078658));
        coords.add(new LatLng(-35.239599,149.078663));
        coords.add(new LatLng(-35.239607,149.078729));
        coords.add(new LatLng(-35.239678,149.078726));
        coords.add(new LatLng(-35.239692,149.079061));
        coords.add(new LatLng(-35.239687,149.079266));
        coords.add(new LatLng(-35.239687,149.079316));
        coords.add(new LatLng(-35.239983,149.07932));
        coords.add(new LatLng(-35.240186,149.079321));
        coords.add(new LatLng(-35.240288,149.079324));
        coords.add(new LatLng(-35.240593,149.079324));
        coords.add(new LatLng(-35.24059,149.079395));
        coords.add(new LatLng(-35.240652,149.079397));
        coords.add(new LatLng(-35.240655,149.079497));
        coords.add(new LatLng(-35.240644,149.079564));
        coords.add(new LatLng(-35.240627,149.079639));
        coords.add(new LatLng(-35.240581,149.079725));
        coords.add(new LatLng(-35.24051,149.079792));
        coords.add(new LatLng(-35.240434,149.079865));
        coords.add(new LatLng(-35.240344,149.079913));
        coords.add(new LatLng(-35.240222,149.079961));
        coords.add(new LatLng(-35.240177,149.079976));
        coords.add(new LatLng(-35.240189,149.080034));
        coords.add(new LatLng(-35.240149,149.080047));
        coords.add(new LatLng(-35.240082,149.080048));
        coords.add(new LatLng(-35.239983,149.080054));
        coords.add(new LatLng(-35.239896,149.080055));
        coords.add(new LatLng(-35.239833,149.080054));
        coords.add(new LatLng(-35.239762,149.080054));
        coords.add(new LatLng(-35.239754,149.080196));
        coords.add(new LatLng(-35.239752,149.080266));
        coords.add(new LatLng(-35.239717,149.080317));
        coords.add(new LatLng(-35.239653,149.080333));
        coords.add(new LatLng(-35.239523,149.08033));
        coords.add(new LatLng(-35.239467,149.080319));
        coords.add(new LatLng(-35.239432,149.080286));
        coords.add(new LatLng(-35.239397,149.080233));
        coords.add(new LatLng(-35.239391,149.080157));
        coords.add(new LatLng(-35.239388,149.080058));
        coords.add(new LatLng(-35.23939,149.080035));
        coords.add(new LatLng(-35.239322,149.080034));
        coords.add(new LatLng(-35.23932,149.079716));
        coords.add(new LatLng(-35.239323,149.079468));
        coords.add(new LatLng(-35.239323,149.079261));
        coords.add(new LatLng(-35.239328,149.079053));
        coords.add(new LatLng(-35.239325,149.07893));
        coords.add(new LatLng(-35.239318,149.078829));
        this.carParks.put("R2", new CarPark("R2",-1,0,0,"Student residence parking",new LatLng(-35.239648,149.079639), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.239744,149.080472));
        coords.add(new LatLng(-35.2398,149.080465));
        coords.add(new LatLng(-35.239854,149.080491));
        coords.add(new LatLng(-35.239782,149.080799));
        coords.add(new LatLng(-35.239732,149.080783));
        coords.add(new LatLng(-35.239738,149.08075));
        coords.add(new LatLng(-35.239684,149.080726));
        coords.add(new LatLng(-35.239638,149.080714));
        coords.add(new LatLng(-35.239684,149.08049));
        coords.add(new LatLng(-35.239731,149.080507));
        this.carParks.put("R3", new CarPark("R3",17,0,0,"Student residence parking",new LatLng(-35.239756,149.080651), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.240342,149.080314));
        coords.add(new LatLng(-35.240236,149.080341));
        coords.add(new LatLng(-35.240144,149.080361));
        coords.add(new LatLng(-35.240088,149.080374));
        coords.add(new LatLng(-35.239991,149.08039));
        coords.add(new LatLng(-35.239999,149.080447));
        coords.add(new LatLng(-35.240051,149.080444));
        coords.add(new LatLng(-35.240087,149.080439));
        coords.add(new LatLng(-35.240158,149.080424));
        coords.add(new LatLng(-35.240229,149.080405));
        coords.add(new LatLng(-35.240307,149.080388));
        coords.add(new LatLng(-35.240359,149.080373));
        this.carParks.put("R4", new CarPark("R4",17,0,0,"Student residence parking",new LatLng(-35.240176,149.080388), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.240483,149.080388));
        coords.add(new LatLng(-35.240903,149.080193));
        coords.add(new LatLng(-35.240926,149.080221));       coords.add(new LatLng(-35.240962,149.080207));
        coords.add(new LatLng(-35.24097,149.080231));
        coords.add(new LatLng(-35.241012,149.080217));
        coords.add(new LatLng(-35.241106,149.080472));
        coords.add(new LatLng(-35.241063,149.080495));
        coords.add(new LatLng(-35.241084,149.080554));
        coords.add(new LatLng(-35.24061,149.080773));
        this.carParks.put("R5", new CarPark("R5",83,0,0,"Student residence parking",new LatLng(-35.240838,149.080468), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.237975,149.089562));
        coords.add(new LatLng(-35.238271,149.089561));
        coords.add(new LatLng(-35.238281,149.089997));
        coords.add(new LatLng(-35.237977,149.089998));
        this.carParks.put("P4", new CarPark("P4",0,0,0,"e-Permit/casual parking",new LatLng(-35.238129,149.089786), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.240002,149.082131));
        coords.add(new LatLng(-35.240107,149.082273));
        coords.add(new LatLng(-35.239814,149.08256));
        coords.add(new LatLng(-35.239782,149.082521));
        coords.add(new LatLng(-35.239748,149.08255));
        coords.add(new LatLng(-35.239744,149.082622));
        coords.add(new LatLng(-35.239373,149.082618));
        coords.add(new LatLng(-35.239374,149.082562));
        coords.add(new LatLng(-35.239353,149.08256));
        coords.add(new LatLng(-35.239353,149.082491));
        coords.add(new LatLng(-35.239374,149.082491));
        coords.add(new LatLng(-35.239374,149.082431));
        coords.add(new LatLng(-35.239678,149.082433));
        coords.add(new LatLng(-35.239689,149.082496));
        coords.add(new LatLng(-35.239707,149.082497));
        coords.add(new LatLng(-35.239744,149.082462));
        coords.add(new LatLng(-35.239814,149.082395));
        coords.add(new LatLng(-35.239777,149.08235));
        this.carParks.put("R6", new CarPark("R6",58,0,0,"Student residence parking",new LatLng(-35.239582,149.082583), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.236409,149.080849));
        coords.add(new LatLng(-35.235303,149.080849));
        coords.add(new LatLng(-35.235297,149.081281));
        coords.add(new LatLng(-35.23578,149.081289));
        coords.add(new LatLng(-35.235799,149.082276));
        coords.add(new LatLng(-35.236409,149.082281));
        this.carParks.put("P7", new CarPark("P7",596,329,267,"e-Permit/casual parking",new LatLng(-35.236219,149.081621), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.2377,149.0867));
        coords.add(new LatLng(-35.23695,149.0867));
        coords.add(new LatLng(-35.23695,149.08628));
        coords.add(new LatLng(-35.2373,149.08629));
        coords.add(new LatLng(-35.2373,149.08555));
        coords.add(new LatLng(-35.2377,149.08553));
        this.carParks.put("P10", new CarPark("P10",267,257,10,"e-Permit/casual parking",new LatLng(-35.237406,149.086414), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.2377,149.086613));
        coords.add(new LatLng(-35.238085,149.086618));
        coords.add(new LatLng(-35.238087,149.08668));
        coords.add(new LatLng(-35.237695,149.086673));
        this.carParks.put("P11", new CarPark("P11",14,0,0,"Timed car spaces",new LatLng(-35.237921,149.086657), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.241583,149.085682));
        coords.add(new LatLng(-35.241902,149.085543));
        coords.add(new LatLng(-35.242017,149.085908));
        coords.add(new LatLng(-35.241685,149.086047));
        this.carParks.put("P14", new CarPark("P14",38,38,0,"e-Permit parking",new LatLng(-35.24179,149.08577), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.241785,149.087445));
        coords.add(new LatLng(-35.241856,149.087445));
        coords.add(new LatLng(-35.241865,149.087809));
        coords.add(new LatLng(-35.241131,149.087812));
        coords.add(new LatLng(-35.241128,149.087436));
        coords.add(new LatLng(-35.241335,149.087439));
        coords.add(new LatLng(-35.241335,149.087498));
        coords.add(new LatLng(-35.241772,149.087501));
        this.carParks.put("P13", new CarPark("P13",67,67,0,"e-Permit parking",new LatLng(-35.241558,149.087653), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.235374,149.085819));
        coords.add(new LatLng(-35.235371,149.086618));
        coords.add(new LatLng(-35.236905,149.086637));
        coords.add(new LatLng(-35.236896,149.085765));
        coords.add(new LatLng(-35.235436,149.08576));
        this.carParks.put("P17", new CarPark("P17",240,240,0,"e-Permit/casual parking",new LatLng(-35.236092,149.086285), new PolygonOptions().addAll(coords)));
        coords = new LinkedList<>();
        coords.add(new LatLng(-35.2402,149.08377));
        coords.add(new LatLng(-35.240386,149.083762));
        coords.add(new LatLng(-35.240386,149.083984));
        coords.add(new LatLng(-35.240198,149.083986));
        this.carParks.put("W1", new CarPark("W1",13,0,0,"Childcare pickup/dropoff",new LatLng(-35.240309,149.083892), new PolygonOptions().addAll(coords)));
    }

    @Test
    public void testParse(){
        setUpCarParks();

        ParkingXMLParser parkingXMLParser = new ParkingXMLParser();
        Context ctx = InstrumentationRegistry.getContext();
        String fileName = "CarParksXML_Test.xml";

        try {
            InputStream inputStream = ctx.getAssets().open(fileName);
            HashMap<String, CarPark> result = parkingXMLParser.parse(inputStream);
            Assert.assertEquals(result, carParks);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}
