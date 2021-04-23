package com.trilogyed.tasker.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

@FeignClient(name = "adserver-service")
public interface AdserverService {

    @RequestMapping(value = "/adserver/advertisement/{advertisement}", method = RequestMethod.GET)
    default String getAdvertisementForTask(@PathVariable String advertisement){

        String[] ads = {
                "Home Equity Loans @ 3.87% APR",
                "Click HERE to qualify for a new car loan!",
                "Super Sale on summer clothes!",
                "Summer Show Blowout!!! 30% to 50% off!!!!",
                "Get a new phone NOW!",
                "Alaskan Cruises for as low as $700!",
                "BOGO large 2 topping pizzas!",
                "Free installation with the purchase of 100 square feet of carpet!"
        };

        Random random = new Random();
        return ads[random.nextInt(8)];
        }
}
