package com.socrata.FoodTruckFinder.view;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceView {

    public static final int PAGE_SIZE = 10;
    private static final String BANNER = "=============================";

    public void welcomeMessageWithTime(String serviceName, ZonedDateTime zonedDateTime) {
        String dateString = zonedDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy - hh:mm a"));
        System.out.printf("%n===== %s Currently Open @ %s =====%n%n", serviceName, dateString);
    }

    public void exitMessage(String exitMessage) {
        System.out.println("\n" + BANNER + "\n" + exitMessage + "\n" + BANNER);
        System.exit(0);
    }
}