package com.cnpc.common.util;

public class LevelCodeUtil {
    /**
     * 获取下一个levelCode
     * @param levelCode
     * @param lCode
     * @return
     */
    public static  String getNextLevelCode(String levelCode,String lCode){
        if(null != lCode && lCode != ""){
            lCode = Integer.parseInt(lCode.substring(lCode.length()-6))+1+"";
            String str = "";
            int num = 6-lCode.length()%6;
            for (int i=0;i<num;i++){
                str = str + "0";
            }
            lCode = str + lCode;
        }else {
            lCode = "000001";
        }
        return  levelCode+lCode;
    }
}
