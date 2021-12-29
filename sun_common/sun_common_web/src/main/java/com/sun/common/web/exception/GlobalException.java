package com.sun.common.web.exception;

import com.sun.common.core.result.Codes;
import com.sun.common.core.result.R;
import com.sun.common.core.utils.RUtils;
import com.sun.common.web.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author sungp
 * @date 2021年12月28日 11:40
 * 全局异常处理 - 处理Controller抛出的异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    /**
     * 参数校验统一异常处理
     *
     * @param exception 异常
     * @return com.sun.common.core.result.R<java.util.Map < java.lang.String, java.lang.String>>
     * @author sungp
     * @date 2021/12/28 16:37
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<Set<String>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {

        //获得绑定的返回对象
        BindingResult result = exception.getBindingResult();

        Set<String> set = result.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());

//        Map<String , String> err = new HashMap<>();
//        if (result.hasErrors()) {
//            List<FieldError> list = result.getFieldErrors();
//            FieldError error;
//            for (FieldError fieldError : list) {
//                error = fieldError;
//                err.put(error.getField(), error.getDefaultMessage());
//            }
//        }

        log.error("[MethodArgumentNotValid - Exception] - 捕获到参数校验异常！", exception);
        return RUtils.createFail(Codes.PARAMETER_ERROR, set);
    }

    /**
     * spring提供的断言异常处理器
     * String user = null;
     * Assert.notNull(user , "用户信息不能为空");
     *
     * @param exception 异常
     * @return com.sun.common.core.result.R<T>
     * @author sungp
     * @date 2021/12/28 15:07
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public <T> R<T> illegalArgumentException(IllegalArgumentException exception) {
        log.error("[Service - Exception] - 捕获到业务异常信息！", exception);
        return RUtils.createFail(Codes.SERVER_EXCEPTION.getCode(), exception.getMessage());
    }


    /**
     * 自定义业务异常处理
     *
     * @param exception 异常
     * @return com.sun.common.core.result.R<T>
     * @author sungp
     * @date 2021/12/28 14:49
     */
    @ExceptionHandler(ServiceException.class)
    public <T> R<T> serviceExceptionHandler(ServiceException exception) {
        log.error("[Service - Exception] - 捕获到业务异常信息！", exception);
        return RUtils.createFail(exception.getCode(), exception.getMsg());
    }


    /**
     * 默认异常处理
     *
     * @param throwable 异常
     * @return com.sun.common.core.result.R<T>
     * @author sungp
     * @date 2021/12/28 11:50
     */
    @ExceptionHandler
    public <T> R<T> globalExceptionHandler(Throwable throwable) {
        log.error("[Global - Exception] - 捕获到全局异常！");

        //获取异常请求的信息
        HttpServletRequest request = HttpUtils.getRequest();

        String url = request.getRequestURL().toString();
        log.error("[Global - Exception] - 请求的url - {}", url);

//        Map<String, String[]> map = request.getParameterMap();
//        log.error("[Global - Exception] - 请求的参数 - {}", map);
//        BufferedReader br = null;
//        try {
//            br = request.getReader();
//            log.error("[Global - Exception] - 请求的参数 - {}", br.readLine());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        log.error("[Global - Exception] - 异常信息", throwable);

        return RUtils.createFail(Codes.SERVER_EXCEPTION);
    }

}
