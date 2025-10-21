package com.msb.serviceprice.controller;


import com.msb.dao.PriceRule;
import com.msb.dao.ResponseResult;
import com.msb.serviceprice.service.PriceRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author child
 * @since 2025-10-20
 */
@RestController
@RequestMapping("/price")
@Slf4j
public class PriceRuleController {
    @Autowired
    private PriceRuleService priceRuleService;
    @PostMapping("/add")
    public ResponseResult add(@RequestBody PriceRule priceRule){
        return priceRuleService.add(priceRule);
    }
    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody PriceRule priceRule){
        return priceRuleService.edit(priceRule);
    }
    @GetMapping("/check-latest-version")
    public ResponseResult<PriceRule> checkLatestVersion(@RequestParam String fareType){
        return priceRuleService.checkLatestVersion(fareType);
    }
    @GetMapping("/is-latest-version")
    public ResponseResult<Boolean> isLatestVersion(@RequestParam String fareType,@RequestParam Integer fareVersion){
        return priceRuleService.isLatestVersion(fareType,fareVersion);
    }
    @GetMapping("/is-exits")
    public ResponseResult<Boolean> isExits(@RequestBody PriceRule priceRule){
        return priceRuleService.getBy(priceRule);
    }
}
