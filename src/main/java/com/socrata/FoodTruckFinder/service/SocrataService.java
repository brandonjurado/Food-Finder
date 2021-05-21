package com.socrata.FoodTruckFinder.service;

import java.time.ZonedDateTime;
import java.util.List;

public interface SocrataService<T> {

    String buildServiceQuery(ZonedDateTime zonedDateTime, int page, int pageSize);

    List<T> getResponse(ZonedDateTime currentUserTime, int page, int pageSize);
}
