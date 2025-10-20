package com.msb.serviceprice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msb.constant.CommonStatusEnum;
import com.msb.dao.PriceRule;
import com.msb.dao.ResponseResult;
import com.msb.serviceprice.mapper.PriceRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author child
 * @since 2025-10-20
 */
@Service
public class PriceRuleService {
    @Autowired
    PriceRuleMapper priceRuleMapper;
    public ResponseResult add(@RequestBody PriceRule priceRule){
        //拼接fareType
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode +"$"+ vehicleType;
        priceRule.setFareType(fareType);
        //添加版本号
//        Map<String,Object> map=new HashMap<>();
//        map.put("city_code",cityCode);
//        map.put("vehicle_type",vehicleType);
        //以上出现问题：添加版本号，前两个字段无法唯一确定一条记录，   找最大的版本号，需要排序

        QueryWrapper<PriceRule> priceRuleQueryWrapper = new QueryWrapper<>();
        priceRuleQueryWrapper.eq("city_code",cityCode);
        priceRuleQueryWrapper.eq("vehicle_type",vehicleType);
        priceRuleQueryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRuleList=priceRuleMapper.selectList(priceRuleQueryWrapper);
        Integer fareVersion=0;
        if (priceRuleList.size()>0){
            return ResponseResult.fail(CommonStatusEnum.PICE_RULE_EXIST.getCode(),CommonStatusEnum.PICE_RULE_EXIST.getValue());
        }
        priceRule.setFareVersion(++fareVersion);
        priceRuleMapper.insert(priceRule);
        return ResponseResult.success();
    }

    /**
     * 修改计价规则
     * @param priceRule
     * @return
     */
    public ResponseResult edit(PriceRule priceRule){
        //拼接fareType
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode +"$"+ vehicleType;
        priceRule.setFareType(fareType);
        //添加版本号
        QueryWrapper<PriceRule> priceRuleQueryWrapper = new QueryWrapper<>();
        priceRuleQueryWrapper.eq("city_code",cityCode);
        priceRuleQueryWrapper.eq("vehicle_type",vehicleType);
        priceRuleQueryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRuleList=priceRuleMapper.selectList(priceRuleQueryWrapper);
        Integer fareVersion=0;
        if (priceRuleList.size()>0){
            PriceRule lasetPriceRule=priceRuleList.get(0);
            Double unitPricePerMile = lasetPriceRule.getUnitPricePerMile();
            Double unitPricePerMinute = lasetPriceRule.getUnitPricePerMinute();
            Integer startMile = lasetPriceRule.getStartMile();
            Double startFare = lasetPriceRule.getStartFare();
            if (unitPricePerMile.doubleValue()==priceRule.getUnitPricePerMile().doubleValue()
            && unitPricePerMinute.doubleValue()==priceRule.getUnitPricePerMinute().doubleValue()
            && startFare.doubleValue()==priceRule.getStartFare().doubleValue()
            && startMile.intValue()==priceRule.getStartMile().intValue()){
                return ResponseResult.fail(CommonStatusEnum.PICE_RULE_NO_CHANGE.getCode(),CommonStatusEnum.PICE_RULE_NO_CHANGE.getValue());
            }
            fareVersion=lasetPriceRule.getFareVersion();
        }
        priceRule.setFareVersion(++fareVersion);
        priceRuleMapper.insert(priceRule);
        return ResponseResult.success();
    }
}
