package com.msb.Utils;

import java.math.BigDecimal;

public class BigDecimalUtils {
    /**
     * 加法
     * @param v1
     * @param v2
     * @return
     */
    public static double add(double v1,double v2){
        BigDecimal v1Decimal=BigDecimal.valueOf(v1);
        BigDecimal v2Decimal=BigDecimal.valueOf(v2);
        return v1Decimal.add(v2Decimal).doubleValue();
    }

    /**
     * 减法
     * @param v1
     * @param v2
     * @return
     */
    public static double subtract(double v1,double v2){
        BigDecimal v1Decimal=BigDecimal.valueOf(v1);
        BigDecimal v2Decimal=BigDecimal.valueOf(v2);
        return v1Decimal.subtract(v2Decimal).doubleValue();
    }

    /**
     * 乘法
     * @param v1
     * @param v2
     * @return
     */
    public static double multiply(double v1,double v2){
        BigDecimal v1Decimal=BigDecimal.valueOf(v1);
        BigDecimal v2Decimal=BigDecimal.valueOf(v2);
        return v1Decimal.multiply(v2Decimal).doubleValue();
    }
    /**
     * 除法
     * @param v1
     * @param v2
     * @return
     */
    public static double divide(double v1,double v2){
        BigDecimal v1Decimal=BigDecimal.valueOf(v1);
        BigDecimal v2Decimal=BigDecimal.valueOf(v2);
        return v1Decimal.divide(v2Decimal).doubleValue();
    }
}
