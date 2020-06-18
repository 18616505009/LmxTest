package com.winning.apiinspector.inspector;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 切点
 * 1.记录请求调用次数,持久化存储-接口调用前
 * 2.计算请求返回内容流量,存储到db
 *
 * @author lmx
 * @date 2020-06-14 19:28
 */
@Aspect
@EnableAspectJAutoProxy
@Component
@Slf4j
public class ResponseAop {

    /**
     * 由于切点指向某个包,因此要求对应包内只有接口的方法，业务方法请分离到业务层
     */
    @Pointcut("execution(** com.winning.apiinspector.controller.*.*(..))")
    public void api() {
    }

    /**
     * 其中参数获取方式参考 https://zhuanlan.zhihu.com/p/76837350
     *
     * @param joinPoint
     */
    @Async
    @Before("api()")
    public void recordCall(JoinPoint joinPoint) {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("before-接口路径：{}", request.getRequestURL().toString());
        log.info("before-请求类型：{}", request.getMethod());
        log.info("before-类方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("before-请求参数 : {} " + Arrays.toString(joinPoint.getArgs()));

    }

    @Async
    @AfterReturning(pointcut = "api()", returning = "resp")
    public void calcNetflow(Object resp) {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("afterReturning-方法返回值：{}", resp);
        log.info("afterReturning-接口路径：{}", request.getRequestURL().toString());
    }


}
