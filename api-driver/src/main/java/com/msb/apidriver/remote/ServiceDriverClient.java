package com.msb.apidriver.remote;

import com.msb.dao.Car;
import com.msb.dao.DriverUser;
import com.msb.dao.ResponseResult;
import com.msb.responese.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("service-driver-user")
public interface ServiceDriverClient {
    @RequestMapping(method = RequestMethod.PUT,value = "/user")
    public ResponseResult updateDriver(@RequestBody DriverUser driverUser);

    /**
     * @PathVariable：用于获取路径变量，即 URL 路径中占位符形式的参数。
     * 比如在 URL http://example.com/api/user/123 中，123 是路径变量，它在定义 URL 映射时会使用占位符表示。
     * @param driverPhone
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> findDriverByPhone(@PathVariable String driverPhone);

    /**
     * @RequestParam：用于获取查询参数，也就是 URL 中 ? 后面的参数，多个参数之间用 & 连接。
     * 例如在 URL http://example.com/api/user?name=John&age=30 中，name 和 age 就是查询参数。
     * @param carId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/car")
    public ResponseResult<Car> getCarById(@RequestParam Long carId);
}
