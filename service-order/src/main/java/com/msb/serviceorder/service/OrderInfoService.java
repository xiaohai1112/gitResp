package com.msb.serviceorder.service;


import com.msb.constant.OrderConstant;
import com.msb.dao.OrderInfo;
import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import com.msb.serviceorder.mapper.OrderInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author child
 * @since 2025-10-20
 */
@Service
public class OrderInfoService {
    @Autowired
    OrderInfoMapper orderInfoMapper;
    public ResponseResult add(OrderRequest orderRequest){
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderRequest,orderInfo);
        orderInfo.setOrderStatus(OrderConstant.ORDER_START);
        LocalDateTime now=LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);
        orderInfoMapper.insert(orderInfo);
        return ResponseResult.success();
    }
}
