package com.sun.common.mysql.page;

/**
 * @author sungp
 * @date 2022年01月06日 14:27
 */

public class SunPage {

    private static final ThreadLocal<Page> PAGE_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置page对象
     * @author sungp
     * @date 2022/1/6 14:36
     * @param pageNum - 当前页
     * @param pageSize - 页大小
     */
    public static void setPage(Integer pageNum , Integer pageSize){
        PAGE_THREAD_LOCAL.set(new Page(pageNum , pageSize));
    }

    /**
     * 获取page对象
     * @author sungp
     * @date 2022/1/6 14:37
     */
    public static Page getPage(){
        return PAGE_THREAD_LOCAL.get();
    }

    /**
     * 情况pageThreadLocal - 当前线程
     * @author sungp
     * @date 2022/1/6 14:39
     */
    public static void clear(){
        PAGE_THREAD_LOCAL.remove();
    }

}
