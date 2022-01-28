package com.sun.common.mysql.aop;

import com.sun.common.mysql.page.Page;
import com.sun.common.mysql.page.SunPage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author sungp
 * @date 2022年01月11日 12:58
 */
@Aspect
public class PagingAop {

    /**
     * 环绕增强所有添加@Paging的方法
     * @author sungp
     * @date 2022/1/11 12:59
     */
    @Around("@annotation(com.sun.common.mysql.annotation.Paging)")
    public Object pagingAop(ProceedingJoinPoint joinPoint) throws Throwable {

        //开启分页
        Page page = SunPage.getPage();
        if (page != null){
            //开启分页
            page.setEnable(true);
        }

        try {
            return joinPoint.proceed();
        } finally {
            if (page != null){
                //关闭分页
                page.setEnable(false);
            }
        }

    }

}
