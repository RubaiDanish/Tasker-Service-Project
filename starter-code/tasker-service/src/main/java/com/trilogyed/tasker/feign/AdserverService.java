package com.trilogyed.tasker.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

@FeignClient(name = "adserver-service")
public interface AdserverService {
    @RequestMapping(value = "/ad", method = RequestMethod.GET)
    String getAd();


        }
