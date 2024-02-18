package com.fmatheus.app.controller.proxy.service;

import com.fmatheus.app.controller.dto.response.LocationResponse;

public interface FeignLocationService {

    LocationResponse findByCep(String cep);
}
