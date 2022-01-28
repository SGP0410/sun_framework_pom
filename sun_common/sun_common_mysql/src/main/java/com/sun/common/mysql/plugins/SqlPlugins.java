package com.sun.common.mysql.plugins;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;

/**
 * @author sungp
 * @date 2022年01月05日 16:48
 * SQL记录的mybatis插件
 *      - 记录SQL的执行时间
 *      - 记录当前SQL的执行语句
 * type - 表示当前要增强的内置对象的的类型（必须是内置四大对象中的一个）目标对象
 * method - 增强内置对象中具体的方法名
 * args - 增强方法的参数类型（避免方法重载而无法识别具体增强的方法是哪一个）
 */
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "query",//查
                args = {Statement.class, ResultHandler.class}
        ),
        @Signature(
                type = StatementHandler.class,
                method = "update",//增，删，改
                args = {Statement.class}
        )
})
@Slf4j
public class SqlPlugins implements Interceptor {

    /**
     * 拦截方法
     * @author sungp
     * @date 2022/1/5 17:06
     * @param invocation 被增强方法的信息：对象，方法，参数
     * @return java.lang.Object
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //获取当前执行的SQL语句
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql().replace("\n" , "").replace("\r" , "");
        log.info("[SQL] - exec sql - [{}]" , sql);



        //记录SQL的执行时间
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long ent = System.currentTimeMillis();
        log.info("[SQL] - exec sql time - [{}ms]" , (ent - start));

        return result;

    }

}
