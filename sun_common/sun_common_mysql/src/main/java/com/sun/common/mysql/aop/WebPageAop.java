package com.sun.common.mysql.aop;

import com.sun.common.mysql.page.Page;
import com.sun.common.mysql.page.SunPage;
import com.sun.common.mysql.plugins.BasePageResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sungp
 * @date 2022年01月11日 11:13
 * web层分页参数aop
 */

@Aspect
public class WebPageAop {


    /**
     * 环绕增强
     * 增强所有RestController,Controller注解类下的所有方法
     *
     * @author sungp
     * @date 2022/1/11 11:15
     */
    @Around("@within(org.springframework.web.bind.annotation.RestController)||" +
            "@within(org.springframework.stereotype.Controller)")
    public Object pageAop(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取页面参数
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //获取参数
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");

        //判断是否存在
        if (!StringUtils.isEmpty(pageNum) && !StringUtils.isEmpty(pageSize)){
            //设置分页参数
            SunPage.setPage(Integer.parseInt(pageNum) , Integer.parseInt(pageSize));
        }
        try {
            Object result = joinPoint.proceed();
            Page page = SunPage.getPage();
            if (result instanceof BasePageResult && page != null && page.getPageCount() != null){
                BasePageResult basePageResult = (BasePageResult) result;
                basePageResult.setPage(page);
            }
            return result;
        } finally {
            //清空SunPage中的数据
            SunPage.clear();
        }
    }

}
