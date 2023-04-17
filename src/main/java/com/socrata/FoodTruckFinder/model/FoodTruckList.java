package com.socrata.FoodTruckFinder.model;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

/*
This class helps provide a clean table formatted output.
*/

@Value
@Builder
public class FoodTruckList {
    public List<FoodTruck> foodTrucks;

    private int getLongestNameInResults() {
        int longestName = Integer.MIN_VALUE;
        for (FoodTruck foodTruck : foodTrucks) {
            longestName = Math.max(longestName, foodTruck.getApplicant().length());
        }
        return longestName;
    }

    // improve readability by formatting result into table view
    private String columnWidth(String s, Integer length) {
        return String.format("%-" + length + "s", s);
    }

    public String toString() {
        int longestName = getLongestNameInResults();
        String tableColumns = columnWidth("NAME", longestName) + " ADDRESS\n";

        List<String> formattedFoodTruckResults = new ArrayList<>();
        for (FoodTruck foodTruck : foodTrucks) {
            formattedFoodTruckResults.add(String.format("%-" + longestName + "s %s", foodTruck.getApplicant(), foodTruck.getLocation()));
        }

        return tableColumns + String.join("\n", formattedFoodTruckResults);
    }
}
