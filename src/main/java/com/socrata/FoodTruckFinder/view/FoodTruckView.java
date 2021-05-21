package com.socrata.FoodTruckFinder.view;
import com.socrata.FoodTruckFinder.model.FoodTruck;
import com.socrata.FoodTruckFinder.model.FoodTruckList;
import com.socrata.FoodTruckFinder.service.FoodTruckService;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Scanner;

@Component
public class FoodTruckView extends ServiceView {

    private final FoodTruckService service;

    FoodTruckView(FoodTruckService service) {
        this.service = service;
    }

    public void startFoodTruckFinder(ZonedDateTime currentTime, int currentPage) {
        welcomeMessageWithTime("Food Trucks", currentTime);
        displayFoodTrucks(currentTime, currentPage);
    }

    private void displayFoodTrucks(ZonedDateTime currentTime, int currentPage) {
        List<FoodTruck> foodTrucks = service.getResponse(currentTime, currentPage, PAGE_SIZE);
        displayFoodTrucksInPage(foodTrucks, currentPage);
        promptUserAction(currentTime, currentPage);
    }

    private void promptUserAction(ZonedDateTime currentTime, int currentPage) {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.nextLine().equalsIgnoreCase("x")) {
            displayFoodTrucks(currentTime, currentPage + 10);
        }
        exitMessage("Exiting Food Truck Finder...");
    }

    private void displayFoodTrucksInPage(List<FoodTruck> foodTrucks, int page) {
        FoodTruckList foodTruckList = FoodTruckList.builder().foodTrucks(foodTrucks).build();
        if (page == 0 && foodTrucks.isEmpty()) {
            exitMessage("Sorry, no food trucks are open at this time.");
        } else if (foodTrucks.size() < 10) {
            exitMessage("You have reached the end of list for open food trucks.");
        } else {
            System.out.println(foodTruckList);
            System.out.println("Continue to next page? On the line below enter 'x' to exit or any other key to continue: ");
        }
    }
}