package com.msb.Utils;

public class SsePlushPrefixUtils {
    public static final String connect="$";
    public static String getConnect(Long userId,String identy){
        return userId+connect+identy;
    }
}
