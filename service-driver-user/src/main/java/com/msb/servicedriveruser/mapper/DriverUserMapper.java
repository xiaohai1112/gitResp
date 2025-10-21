package com.msb.servicedriveruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msb.dao.DriverCarBindingRelationship;
import com.msb.dao.DriverUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DriverUserMapper extends BaseMapper<DriverUser> {
//    @Select("select count(*) from v_city_driver_user_work_status")
    int selectDriverByCityCodeCount(@Param("cityCode") String cityCode);
}
