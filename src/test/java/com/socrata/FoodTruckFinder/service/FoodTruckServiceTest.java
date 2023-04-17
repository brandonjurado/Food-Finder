package com.socrata.FoodTruckFinder.service;

import com.socrata.FoodTruckFinder.TestUtils;
import com.socrata.FoodTruckFinder.model.FoodTruck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

public class FoodTruckServiceTest {

    private final RestClientService restClientService = new RestClientService(null);
    private final FoodTruckService foodTruckService = new FoodTruckService(restClientService);

    private static final FoodTruck TEST_FOOD_TRUCK = TestUtils.createTestFoodTruck();

    @Test
    public void testBuildServiceQuery() {
        ZonedDateTime dateTime = ZonedDateTime.of(2022, 4, 17, 12, 0, 0, 0, ZoneId.systemDefault());
        String expectedQuery = "http://data.sfgov.org/resource/bbb8-hzi6.json?dayorder=0&$order=applicant ASC&$offset=1&$limit=10&$where=start24<=\"12:00\" and end24>\"12:00\"";
        String actualQuery = foodTruckService.buildServiceQuery(dateTime, 1, 10);
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void testGetResponse() {
        List<FoodTruck> expectedFoodTrucks = Collections.singletonList(TEST_FOOD_TRUCK);
        RestClientService restClientServiceMock = Mockito.mock(RestClientService.class);
        Mockito.when(restClientServiceMock.sendRequest(Mockito.anyString(), Mockito.any(Class.class)))
            .thenReturn(expectedFoodTrucks.toArray(new FoodTruck[0]));
        FoodTruckService foodTruckService = new FoodTruckService(restClientServiceMock);
        List<FoodTruck> actualFoodTrucks = foodTruckService.getResponse(ZonedDateTime.now(), 0, 10);
        Assertions.assertEquals(expectedFoodTrucks, actualFoodTrucks);
    }

    @Test
    public void testGetResponseWithException() {
        RestClientService restClientServiceMock = Mockito.mock(RestClientService.class);
        Mockito.when(restClientServiceMock.sendRequest(Mockito.anyString(), Mockito.any(Class.class)))
            .thenThrow(RuntimeException.class);
        FoodTruckService foodTruckService = new FoodTruckService(restClientServiceMock);
        List<FoodTruck> actualFoodTrucks = foodTruckService.getResponse(ZonedDateTime.now(), 0, 10);
        Assertions.assertEquals(Collections.emptyList(), actualFoodTrucks);
    }
}
