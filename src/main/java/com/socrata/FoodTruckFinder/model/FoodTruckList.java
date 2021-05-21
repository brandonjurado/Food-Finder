package com.socrata.FoodTruckFinder.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/*
This class helps provide a clean table formatted output.
*/

@Value
@Builder
public class FoodTruckList {
    public List<com.socrata.FoodTruckFinder.model.FoodTruck> foodTrucks;

    private int getLongestNameInResults() {
        int longestName = Integer.MIN_VALUE;
        for (com.socrata.FoodTruckFinder.model.FoodTruck foodTruck : foodTrucks) {
            longestName = Math.max(longestName, foodTruck.getApplicant().length());
        }
        return longestName;
    }

    // improve readability by formatting result into table view
    private String columnWidth(String s, Integer length) {
        StringBuilder columnWidthSpacing = new StringBuilder(s);
        while (columnWidthSpacing.length() < length) {
            columnWidthSpacing.append(" ");
        }
        return columnWidthSpacing.toString();
    }

    public String toString() {
        int longestName = getLongestNameInResults();
        String tableColumns = columnWidth("NAME", longestName) + " ADDRESS\n";
        StringBuilder formattedFoodTruckResults = new StringBuilder(tableColumns);

        for (FoodTruck foodTruck : foodTrucks) {
            formattedFoodTruckResults.append(columnWidth(foodTruck.getApplicant(), longestName))
                    .append(" ")
                    .append(columnWidth(foodTruck.getLocation(), longestName))
                    .append("\n");
        }

        return formattedFoodTruckResults.toString();
    }
}
