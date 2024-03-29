package com.google.maps.android.data;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.geojson.GeoJsonPolygon;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;

public class MultiGeometryTest {
    MultiGeometry mg;

    @Test
    public void testGetGeometryType() throws Exception {
        ArrayList<LineString> lineStrings = new ArrayList<>();
        lineStrings.add(new LineString(
                new ArrayList<>(Arrays.asList(new LatLng(0, 0), new LatLng(50, 50)))));
        lineStrings.add(new LineString(
                new ArrayList<>(Arrays.asList(new LatLng(56, 65), new LatLng(23, 23)))));
        mg = new MultiGeometry(lineStrings);
        Assert.assertEquals("MultiGeometry", mg.getGeometryType());
        ArrayList<GeoJsonPolygon> polygons = new ArrayList<GeoJsonPolygon>();
        ArrayList<ArrayList<LatLng>> polygon = new ArrayList<ArrayList<LatLng>>();
        polygon.add(new ArrayList<LatLng>(
                Arrays.asList(new LatLng(0, 0), new LatLng(20, 20), new LatLng(60, 60),
                        new LatLng(0, 0))));
        polygons.add(new GeoJsonPolygon(polygon));
        polygon = new ArrayList<ArrayList<LatLng>>();
        polygon.add(new ArrayList<LatLng>(
                Arrays.asList(new LatLng(0, 0), new LatLng(50, 80), new LatLng(10, 15),
                        new LatLng(0, 0))));
        polygon.add(new ArrayList<LatLng>(
                Arrays.asList(new LatLng(0, 0), new LatLng(20, 20), new LatLng(60, 60),
                        new LatLng(0, 0))));
        polygons.add(new GeoJsonPolygon(polygon));
        mg = new MultiGeometry(polygons);
        Assert.assertEquals("MultiGeometry", mg.getGeometryType());
    }

    @Test
    public void testSetGeometryType() throws Exception {
        ArrayList<LineString> lineStrings = new ArrayList<>();
        lineStrings.add(new LineString(
                new ArrayList<>(Arrays.asList(new LatLng(0, 0), new LatLng(50, 50)))));
        lineStrings.add(new LineString(
                new ArrayList<>(Arrays.asList(new LatLng(56, 65), new LatLng(23, 23)))));
        mg = new MultiGeometry(lineStrings);
        Assert.assertEquals("MultiGeometry", mg.getGeometryType());
        mg.setGeometryType("MultiLineString");
        Assert.assertEquals("MultiLineString", mg.getGeometryType());
    }

    @Test
    public void testGetGeometryObject() throws Exception {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(new LatLng(0, 0)));
        points.add(new Point(new LatLng(5, 5)));
        points.add(new Point(new LatLng(10, 10)));
        mg = new MultiGeometry(points);
        Assert.assertEquals(points, mg.getGeometryObject());
        points = new ArrayList<>();
        mg = new MultiGeometry(points);
        Assert.assertEquals(new ArrayList<Point>(), mg.getGeometryObject());

        try {
            mg = new MultiGeometry(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Geometries cannot be null", e.getMessage());
        }
    }


}
