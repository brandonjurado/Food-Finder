package com.socrata.FoodTruckFinder.view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceViewTest {

    private final ServiceView serviceView = new ServiceView();

    @Test
    public void testWelcomeMessageWithTime() {
        // Capture standard output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call method under test
        ZonedDateTime dateTime = ZonedDateTime.of(2023, 4, 16, 12, 0, 0, 0, ZoneId.systemDefault());
        serviceView.welcomeMessageWithTime("Test Service", dateTime);

        // Verify output
        String expectedOutput = String.format("%n===== Test Service Currently Open @ 04/16/2023 - 12:00 PM =====%n%n");
        assertEquals(expectedOutput, outputStream.toString());

        // Reset standard output
        System.setOut(System.out);
    }
}

