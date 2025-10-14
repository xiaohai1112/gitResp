package com.msb.servicemap.service;

import com.msb.constant.UrlDirectionConstant;
import com.msb.dao.DicDistrict;
import com.msb.dao.ResponseResult;
import com.msb.servicemap.mapper.DistrictMapper;
import com.msb.servicemap.remote.MapDistritctClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.logging.log4j.Level.getLevel;

@Service
public class DicDistrictService {
    @Autowired
    private MapDistritctClient mapDistritctClient;
    @Autowired
    private DistrictMapper districtMapper;

    public ResponseResult dicDistrict(String keywords) {
        //请求地图
        String district = mapDistritctClient.district(keywords);
        System.out.println(district);
        //解析结果
        JSONObject districtJson = JSONObject.fromObject(district);
        int status = districtJson.getInt(UrlDirectionConstant.STATUS);
        if (status == 1) {
            JSONArray countryArray = districtJson.getJSONArray(UrlDirectionConstant.DISTRICTS);
            if (countryArray.size() != 0) {
                for (int i = 0; i < countryArray.size(); i++) {
                    JSONObject country = countryArray.getJSONObject(i);
                    String countryAdcode = country.getString(UrlDirectionConstant.ADCODE);
                    String countryName = country.getString(UrlDirectionConstant.NAME);
                    String countryParentAddressCode = "0";
                    String countryLevel = country.getString(UrlDirectionConstant.LEVEL);
                    getDic(countryAdcode, countryName, countryLevel, countryParentAddressCode);
                    JSONArray provinceArray = country.getJSONArray(UrlDirectionConstant.DISTRICTS);
                    for (int j = 0; j < provinceArray.size(); j++) {
                        JSONObject province = provinceArray.getJSONObject(j);
                        String provinceAdcode = province.getString(UrlDirectionConstant.ADCODE);
                        String provinceName = province.getString(UrlDirectionConstant.NAME);
                        String provinceParentAddressCode = countryAdcode;
                        String provinceLevel = province.getString(UrlDirectionConstant.LEVEL);
                        getDic(provinceAdcode, provinceName, provinceLevel, provinceParentAddressCode);
                        JSONArray cityArray = province.getJSONArray(UrlDirectionConstant.DISTRICTS);
                        for (int k = 0; k < cityArray.size(); k++) {
                            JSONObject city = cityArray.getJSONObject(k);
                            String cityAdcode = city.getString(UrlDirectionConstant.ADCODE);
                            String cityName = city.getString(UrlDirectionConstant.NAME);
                            String cityParentAddressCode = provinceAdcode;
                            String cityLevel = city.getString(UrlDirectionConstant.LEVEL);
                            getDic(cityAdcode, cityName, cityLevel, cityParentAddressCode);
                            JSONArray districtArray = city.getJSONArray(UrlDirectionConstant.DISTRICTS);
                            for (int a = 0; a < districtArray.size(); a++) {
                                JSONObject districts = districtArray.getJSONObject(a);
                                String districtsAdcode = districts.getString(UrlDirectionConstant.ADCODE);
                                String districtsName = districts.getString(UrlDirectionConstant.NAME);
                                String districtsParentAddressCode = cityAdcode;
                                String districtsLevel = districts.getString(UrlDirectionConstant.LEVEL);
                                if (UrlDirectionConstant.STREET.equals(districtsLevel)) {
                                    continue;
                                }
                                getDic(districtsAdcode, districtsName, districtsLevel, districtsParentAddressCode);
                            }
                        }
                    }
                }
            }
        }
        return ResponseResult.success();
    }

    public void getDic(String addressCode, String addressName, String level, String parentAddressCode) {
        DicDistrict dicDistrict = new DicDistrict();
        dicDistrict.setAddressCode(addressCode);
        dicDistrict.setAddressName(addressName);

        int levelInt = getLevel(level);
        dicDistrict.setLevel(levelInt);

        dicDistrict.setParentAddressCode(parentAddressCode);
        //插入数据库
        districtMapper.insert(dicDistrict);
    }

    public int getLevel(String level) {
        int levelInt = 0;
        if ("country".equals(level.trim())) {
            levelInt = 0;
        } else if ("province".equals(level.trim())) {
            levelInt = 1;
        } else if (level.trim().equals("city")) {
            levelInt = 2;
        } else if ("district".equals(level.trim())) {
            levelInt = 3;
        }
        return levelInt;
    }
}
