package com.sun.common.mysql.plugins;

import com.sun.common.mysql.page.Page;
import com.sun.common.mysql.page.SunPage;
import com.sun.common.mysql.utils.MyBatisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author sungp
 * @date 2022年01月06日 14:05
 */
@Intercepts(
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}
        )
)
@Slf4j
public class PagePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("----------------PagePlugin----------------------->");
        //获得StatementHandler对象
        StatementHandler statementHandler = MyBatisUtils.getNoProxyTarget(invocation.getTarget());
        //获得当前执行的SQL
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql().toLowerCase().trim().replaceAll("\\s+" , " ");

        //判断是否分页
        if (!sql.startsWith("select")) {
            //说明当前SQL非查询语句无需分页
            //直接放行
            return invocation.proceed();
        }

        //获取分页参数 - ThreadLocal
        Page page = SunPage.getPage();
        if (page == null || !page.isEnable()) {
            //说明当前没有设置分页参数，则无需分页
            return invocation.proceed();
        }

        //说明当前SQL是需要分页的
        log.debug("[Page] - page sql - [{}]", sql);

        //查询总条数
        Integer count = countSql(invocation, statementHandler, sql);
        log.debug("[Page] - count - {}", count);

        //校验page对象，页数与条数参数不对就设置默认值
        if (page.getPageNum() == null || page.getPageNum() < 1) {
            page.setPageNum(1);
        }
        if (page.getPageSize() == null || page.getPageSize() < 1) {
            page.setPageSize(10);
        }

        //设置Page对象
        //设置总条数
        page.setPageCount(count);
        //设置总页数
        page.setPageTotal(page.getPageCount() % page.getPageSize() == 0 ?
                page.getPageCount() / page.getPageSize() :
                page.getPageCount() / page.getPageSize() + 1);

        //如果查询的页数大于总页数，就查询最后一页
        if (page.getPageNum() > page.getPageTotal()){
            page.setPageNum(page.getPageTotal());
        }

        log.debug("[Page] - page object - {}", page);

        //开始进行SQL分页
        sql += " limit " + ((page.getPageNum() - 1) * page.getPageSize()) + "," + page.getPageSize();
        log.debug("[Page] - exec page sql - [{}]", sql);

        //MyBatis提供的工具类，使用反射修改BoundSql中的SQL
        MetaObject metaObject = SystemMetaObject.forObject(boundSql);
        metaObject.setValue("sql" , sql);

        return invocation.proceed();
    }

    /**
     * 执行查询总数量的SQL
     *
     * @author sungp
     * @date 2022/1/6 14:52
     */
    private Integer countSql(Invocation invocation, StatementHandler statementHandler, String sql) throws SQLException {

        //判断是否存在排序相关内容，如果存在截取掉
        int orderByIndex = sql.lastIndexOf("order by");
        if (orderByIndex != -1) {
            sql = sql.substring(0, orderByIndex);
        }

        //找到SQL中from的位置
        int fromIndex = MyBatisUtils.getFromIndex(0 , sql);

        //截取SQL
        String totalSql = "select count(*) as total " + sql.substring(fromIndex);
        log.debug("[Page] - total sql - [{}]", totalSql);

        //执行查询总条数
        //获取方法的参数connection
        Connection connection = (Connection) invocation.getArgs()[0];
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //创建预编译的会话对象
            preparedStatement = connection.prepareStatement(totalSql);
            //设置SQL参数
            statementHandler.parameterize(preparedStatement);
            //执行SQL
            resultSet = preparedStatement.executeQuery();
            //获取resultSet中的结果
            if (resultSet.next()) {
                return resultSet.getInt("total");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return -1;
    }

}
