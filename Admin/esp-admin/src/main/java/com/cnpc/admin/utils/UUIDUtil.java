package com.cnpc.admin.utils;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取32位uuid
     * @return
     */
    public static String get32UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
