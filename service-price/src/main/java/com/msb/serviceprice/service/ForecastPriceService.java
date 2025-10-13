package com.msb.serviceprice.service;

import com.msb.dao.ResponseResult;
import com.msb.responese.ForecastPriceResponese;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        log.info("出发经度："+depLongitude);
        log.info("出发纬度："+depLatitude);
        log.info("目的经度："+destLongitude);
        log.info("目的纬度："+destLatitude);
        log.info("调用地图服务，查询距离和时长");
        log.info("读取计价规则");
        log.info("根据距离、时长和计价规则，计算价格");
        ForecastPriceResponese forecastPriceResponese = new ForecastPriceResponese();
        forecastPriceResponese.setPrice(13.14);
        return ResponseResult.success(forecastPriceResponese);
    }
}
