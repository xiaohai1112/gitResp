package com.msb.serviceprice.service;

import com.msb.constant.CommonStatusEnum;
import com.msb.dao.PriceRule;
import com.msb.dao.ResponseResult;
import com.msb.request.ForecastPriceDTO;
import com.msb.responese.DistanceResponse;
import com.msb.responese.ForecastPriceResponese;
import com.msb.serviceprice.mapper.PriceRuleMapper;
import com.msb.serviceprice.romate.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ForecastPriceService {
    @Autowired
    private PriceRuleMapper priceRuleMapper;
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
        Map<String,Object> map=new HashMap<>();
        map.put("city_code","11000");
        map.put("vehicle_type","1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(map);
        if (priceRules.size()==0){
            return ResponseResult.fail(CommonStatusEnum.PICE_RULE_NOT_EXIST.getCode(),CommonStatusEnum.PICE_RULE_NOT_EXIST.getValue());
        }
        PriceRule priceRule = priceRules.get(0);
        log.info("根据距离、时长和计价规则，计算价格");
        double price = getprice(distance, duration, priceRule);

        ForecastPriceResponese forecastPriceResponese = new ForecastPriceResponese();
        forecastPriceResponese.setPrice(price);
        return ResponseResult.success(forecastPriceResponese);
    }

    /**
     * 根据距离、时长和计价规则来计算最终价格
     * @param distance 距离
     * @param duration 时长
     * @param priceRule 计价规则
     * @return
     */

    private static double getprice(Integer distance,Integer duration,PriceRule priceRule){
        BigDecimal price = new BigDecimal(0);
        //起步价
        Double startFare = priceRule.getStartFare();
        BigDecimal startFareDecimal = new BigDecimal(startFare);
        price=price.add(startFareDecimal);

        //总里程 m
        BigDecimal distanceDecimal = new BigDecimal(distance);
        //总里程 km                                                      /1000              保留2位小数         四舍五入
        BigDecimal distanceMileDecimal = distanceDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
        Integer startMile = priceRule.getStartMile();
        //起步里程
        BigDecimal startMileDecimal = new BigDecimal(startMile);
        //多出里程
        double distanceSubtract = distanceMileDecimal.subtract(startMileDecimal).doubleValue();
        //最终收费里程
        Double mile = distanceSubtract > 0 ? distanceSubtract : 0;
        BigDecimal mileDecimal = new BigDecimal(mile);
        //计价单元 元/km
        Double unitPricePerMile = priceRule.getUnitPricePerMile();
        BigDecimal unitPricePerMileDecimal = new BigDecimal(unitPricePerMile);
        //里程价
        BigDecimal multiply = mileDecimal.multiply(unitPricePerMileDecimal).setScale(2,BigDecimal.ROUND_HALF_UP);
        price=price.add(multiply);
        //总时长 秒
        BigDecimal durationDecimal = new BigDecimal(duration);
        //总时长 分
        BigDecimal durationMinDecimal = durationDecimal.divide(new BigDecimal(60),2,BigDecimal.ROUND_HALF_UP);
        //计时单价
        Double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        BigDecimal unitPricePerMinuteDecimal = new BigDecimal(unitPricePerMinute);
        //时长价
        BigDecimal piceMultiply = durationMinDecimal.multiply(unitPricePerMinuteDecimal);
        price=price.add(piceMultiply).setScale(2,BigDecimal.ROUND_HALF_UP);

        return price.doubleValue();
    }

//    public static void main(String[] args) {
//        PriceRule priceRule = new PriceRule();
//        priceRule.setStartFare(10.0);
//        priceRule.setStartMile(3);
//        priceRule.setUnitPricePerMile(1.8);
//        priceRule.setUnitPricePerMinute(0.5);
//        double getprice = getprice(6500, 1800, priceRule);
//        System.out.println(getprice);
//    }
}
