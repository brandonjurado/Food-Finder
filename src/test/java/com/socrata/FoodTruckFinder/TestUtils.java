package com.socrata.FoodTruckFinder;

import com.socrata.FoodTruckFinder.model.FoodTruck;
import com.socrata.FoodTruckFinder.model.Location;

import java.util.Date;

public class TestUtils {

    public static FoodTruck createTestFoodTruck() {
        String applicant = "Bob's Burgers";
        return createTestFoodTruck(applicant);
    }

    public static FoodTruck createTestFoodTruck(String applicant) {
        String dayorder = "1";
        String dayOfWeekStr = "Monday";
        String startTime = "10:00";
        String endTime = "18:00";
        String permit = "12345";
        String location = "123 Main St";
        String locationDesc = "Corner of Main St and Elm St";
        String optionalText = "Optional text";
        String locationId = "ABC123";
        String start24 = "10:00";
        String end24 = "18:00";
        String cnn = "123456";
        Date addrDateCreate = new Date();
        Date addrDateModified = new Date();
        String block = "100";
        String lot = "A";
        String coldTruck = "Y";
        String x = "123.456";
        String y = "789.012";
        String latitude = "37.7756026684262";
        String longitude = "-122.413249943531";
        Location location2 = new Location(latitude, longitude, "123 Main St, San Francisco, CA");

        return new FoodTruck(dayorder, dayOfWeekStr, startTime, endTime, permit, location,
            locationDesc, optionalText, locationId, start24, end24, cnn, addrDateCreate, addrDateModified,
            block, lot, coldTruck, applicant, x, y, latitude, longitude, location2);
    }

}

