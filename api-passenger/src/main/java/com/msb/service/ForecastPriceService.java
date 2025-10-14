package com.msb.service;

import com.msb.dao.ResponseResult;
import com.msb.remote.ServicePriceClient;
import com.msb.request.ForecastPriceDTO;
import com.msb.responese.ForecastPriceResponese;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {
    @Autowired
    private ServicePriceClient servicePriceClient;
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        log.info("出发经度："+depLongitude);
        log.info("出发纬度："+depLatitude);
        log.info("目的经度："+destLongitude);
        log.info("目的纬度："+destLatitude);

        log.info("调用service-price");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<ForecastPriceResponese> forecast = servicePriceClient.forecast(forecastPriceDTO);
        double price = forecast.getData().getPrice();

        ForecastPriceResponese forecastPriceResponese = new ForecastPriceResponese();
        forecastPriceResponese.setPrice(price);
        return ResponseResult.success(forecastPriceResponese);
    }
}
