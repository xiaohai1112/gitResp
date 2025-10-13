package com.msb.serviceprice.service;

import com.msb.dao.ResponseResult;
import com.msb.request.ForecastPriceDTO;
import com.msb.responese.DistanceResponse;
import com.msb.responese.ForecastPriceResponese;
import com.msb.serviceprice.romate.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {
    @Autowired
    private ServiceMapClient serviceMapClient;
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        log.info("出发经度："+depLongitude);
        log.info("出发纬度："+depLatitude);
        log.info("目的经度："+destLongitude);
        log.info("目的纬度："+destLatitude);
        log.info("调用地图服务，查询距离和时长");

        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);

        ResponseResult<DistanceResponse> result = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = result.getData().getDistance();
        Integer duration = result.getData().getDuration();
        log.info("距离："+distance+",时长："+duration);


        log.info("读取计价规则");
        log.info("根据距离、时长和计价规则，计算价格");
        ForecastPriceResponese forecastPriceResponese = new ForecastPriceResponese();
        forecastPriceResponese.setPrice(13.14);
        return ResponseResult.success(forecastPriceResponese);
    }
}
