package com.msb.servicemap.contorller;

import com.msb.dao.ResponseResult;
import com.msb.request.ForecastPriceDTO;
import com.msb.responese.DistanceResponse;
import com.msb.servicemap.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direction")
public class DistanceController {
    @Autowired
    private DistanceService distanceService;
    @GetMapping("/driving")
    public ResponseResult driving(@RequestBody ForecastPriceDTO forecastPriceDTO){
        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();

        return distanceService.driving(depLongitude,depLatitude,destLongitude,destLatitude);
    }
}
