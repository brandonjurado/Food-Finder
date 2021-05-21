package com.socrata.FoodTruckFinder.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class FoodTruck {
    public String dayorder;
    public String dayofweekstr;
    public String starttime;
    public String endtime;
    public String permit;
    public String location;
    public String locationdesc;
    public String optionaltext;
    public String locationid;
    public String start24;
    public String end24;
    public String cnn;
    public Date addr_date_create;
    public Date addr_date_modified;
    public String block;
    public String lot;
    public String coldtruck;
    public String applicant;
    public String x;
    public String y;
    public String latitude;
    public String longitude;
    public Location location_2;
}