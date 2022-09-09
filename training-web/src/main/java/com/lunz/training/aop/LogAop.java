package com.lunz.training.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * @description: 日志切面
 * @author: purityKid
 * @create: 2022-06-16
 **/
@Aspect
@Slf4j
public class LogAop {


	@Pointcut("@annotation(com.lunz.training.annotation.RequestLog)")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void aopLog(JoinPoint point) {
		// 获取目标方法
		String methodName = point.getSignature().getDeclaringTypeName().concat(".").concat(point.getSignature().getName());

		// 获取方法参数
		String params = Arrays.toString(point.getArgs());

		log.info("调用方法：[{}],参数: {}", methodName, params);

	}
}
