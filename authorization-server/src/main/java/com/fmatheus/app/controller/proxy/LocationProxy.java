package com.fmatheus.app.controller.proxy;


import com.fmatheus.app.controller.dto.response.LocationResponse;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("location")
public interface LocationProxy {

    @RequestLine(value = "GET")
    LocationResponse findByCep();

}
