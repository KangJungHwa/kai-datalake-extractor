package com.kai.smart.platform.util;

import org.apache.atlas.AtlasClientV2;

public class AtlasConnectionUtils {
//    static String address;
//
//    static String username;
//
//    static String password;
//
//    @Value("${atlas.rest.address}")
//    public void setAddress(String address) {
//        this.address = address;
//    }
//    @Value("${atlas.rest.username}")
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    @Value("${atlas.rest.password}")
//    public void setPassword(String password) {
//        this.password = password;
//    }
    public static AtlasClientV2 create(String address,String username,String password ) {
        return new AtlasClientV2(new String[]{address}, new String[]{username, password});
    }
}
