package com.socrata.FoodTruckFinder;

import com.socrata.FoodTruckFinder.view.FoodTruckView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@SpringBootApplication
public class FoodTruckFinderApplication {

    private static final ZoneId TIME_ZONE = ZoneId.of("America/Los_Angeles");
    private static final int FIRST_PAGE = 0;

    @Autowired
    FoodTruckView foodTruckView;

    public static void main(String args[]) {
        SpringApplication.run(FoodTruckFinderApplication.class);
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    // Execute code on startup of app
    @Bean
    public CommandLineRunner run() {
        return args -> {
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), TIME_ZONE);
            foodTruckView.startFoodTruckFinder(zonedDateTime, FIRST_PAGE);
        };
    }

}

// to run:
// $ ./gradlew build && java -jar build/libs/FoodTruckFinder-1.0.0.jar
// or
// $ ./gradlew bootRun
