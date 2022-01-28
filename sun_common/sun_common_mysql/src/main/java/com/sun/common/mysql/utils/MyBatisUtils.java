package com.sun.common.mysql.utils;

import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * @author sungp
 * @date 2022年01月11日 10:27
 */

public class MyBatisUtils {

    /**
     * 通过invocation获取非代理目标
     *
     * @author sungp
     * @date 2022/1/11 10:29
     */
    public static <T> T getNoProxyTarget(Object target) {
        MetaObject metaObject = SystemMetaObject.forObject(target);
        while (metaObject.hasGetter("h")) {
            //说明当前是一个代理对象
            target = metaObject.getValue("h.target");
            metaObject = SystemMetaObject.forObject(target);
        }
        return (T) target;
    }

    /**
     * 获得主查询的from的位置
     * @author sungp
     * @date 2022/1/11 14:48
     */
    public static int getFromIndex(int beginIndex , String sql){
        int fromIndex = sql.indexOf("from");
        if (fromIndex == -1){
            return fromIndex;
        }
        //括号计数器
        int count = 0;
        //当前需要查询括号位置的下标
        int selectIndex = fromIndex;
        //正括号下标
        int bIndex = -1;
        while ((bIndex = sql.lastIndexOf("(" , selectIndex)) != -1){
            count++;
            selectIndex = bIndex - 1;
        }

        selectIndex = fromIndex;
        //反括号下标
        int eIndex = -1;
        while ((eIndex = sql.lastIndexOf(")" , selectIndex)) != -1){
            count--;
            selectIndex = eIndex - 1;
        }

        if (count == 0){
            return fromIndex;
        }else {
            return getFromIndex(fromIndex + 1 , sql);
        }

    }

}
