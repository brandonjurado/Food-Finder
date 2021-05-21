package com.socrata.FoodTruckFinder.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    public String latitude;
    public String longitude;
    public String human_address;
}
