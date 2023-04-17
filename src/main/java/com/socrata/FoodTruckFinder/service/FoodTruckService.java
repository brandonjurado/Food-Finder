package com.socrata.FoodTruckFinder.service;

import com.socrata.FoodTruckFinder.model.FoodTruck;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FoodTruckService implements SocrataService {

    private static final String BASE_URL = "http://data.sfgov.org/resource/bbb8-hzi6.json?";
    private final com.socrata.FoodTruckFinder.service.RestClientService service;

    public FoodTruckService(RestClientService service) {
        this.service = service;
    }

    @Override
    public List<FoodTruck> getResponse(ZonedDateTime currentUserTime, int page, int pageSize) {
        try {
            String query = buildServiceQuery(currentUserTime, page, pageSize);
            return Arrays.asList(service.sendRequest(query, FoodTruck[].class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public String buildServiceQuery(ZonedDateTime currentUserTime, int page, int pageSize) {
        // Note: Socrata API Sunday = 0, java.time Sunday = 7
        int dayOfWeek = currentUserTime.getDayOfWeek().getValue() % 7;

        return new StringBuilder(BASE_URL)
            .append("dayorder=").append(dayOfWeek)
            .append("&$order=applicant ASC")
            .append("&$offset=").append(page)
            .append("&$limit=").append(pageSize)
            .append("&$where=").append(getHourQuery(currentUserTime))
            .toString();
    }

    private String getHourQuery(ZonedDateTime localDateTime) {
        String hourOfDay = formatHour(localDateTime.getHour());
        return "start24<=\"" + hourOfDay + "\" and end24>\"" + hourOfDay + "\"";
    }

    // String concatenation will compile to StringBuilder however StringBuilder appeared easier to read
    private String formatHour(int hour) {
        StringBuilder hourString = new StringBuilder();

        if (hour < 10) {
            hourString.append("0");
        }

        return hourString.append(hour).append(":00").toString();
    }
}
