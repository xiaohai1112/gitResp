package com.msb.serviceprice.controller;

import com.msb.dao.ResponseResult;
import com.msb.request.ForecastPriceDTO;

import com.msb.serviceprice.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastPriceController {
    @Autowired
    private PriceService priceService;
    @PostMapping("/forecaset-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO){
        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();
        String cityCode = forecastPriceDTO.getCityCode();
        String vehicleType = forecastPriceDTO.getVehicleType();
        return priceService.forecastPrice(depLongitude,depLatitude,destLongitude,destLatitude,cityCode,vehicleType);
    }
    @PostMapping("/calculatePrice")
    public ResponseResult<Double> calculatePrice(@RequestParam Integer distance, @RequestParam Integer duration, @RequestParam String cityCode,@RequestParam String vehicleType){
        return priceService.calculatePrice(distance,duration,cityCode,vehicleType);
    }

}
