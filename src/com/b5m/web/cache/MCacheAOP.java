package com.b5m.web.cache;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.alibaba.fastjson.JSON;
import com.b5m.you.common.util.XMemCachedUtil;

@Aspect
public class MCacheAOP {

	private static Logger logger = Logger.getLogger(MCacheAOP.class);

	/**
	 * 切入点的业务逻辑
	 * 
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "execution(* com.b5m.you.service.impl.*.*(..))")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		// 通过链接点获取方法名
		String methodName = joinPoint.getSignature().getName();
		logger.info("doAround in pointcut method:" + methodName);
		// 获取所有的方法名
		Method[] methods = joinPoint.getTarget().getClass().getMethods();

		for (Method method : methods) {
			// 获取拦截方法
			if (method.getName().equals(methodName) && method.getGenericParameterTypes().length == joinPoint.getArgs().length) {
				MCache cache = method.getAnnotation(MCache.class);
				if (cache != null) {
					logger.info("doAround in for pointcut method:" + method.getName());
					String key = cache.key();
					int exp = cache.exp();
					int historyExp = cache.historyExp();

					if (("").equals(key)) {
						key = getMCacheKeyByMethod(joinPoint);
						logger.info("mcache source key:" + key);
					}
					if (key.length() > 225) {
						key = DigestUtils.md5Hex(key);
					}
					logger.info("mcache key:" + key);

					// 缓存的业务逻辑
					Object result = XMemCachedUtil.getCache(key);
					Integer num = XMemCachedUtil.getDataMemCached(result, key);
					if (num != 0) {
						result = joinPoint.proceed();
						XMemCachedUtil.setCache(key, result, exp);
						XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), result, historyExp);
					}

					return result;
				} else {
					logger.info("not cache @interface method:" + method.getName());
					return joinPoint.proceed();
				}
			}
		}

		return joinPoint.proceed();
	}

	private String getMCacheKeyByMethod(ProceedingJoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		StringBuilder key = new StringBuilder();
		key.append(className);
		key.append("_");
		key.append(methodName);
		key.append("_");
		for (Object arg : args) {
			if (arg instanceof HttpServletRequest) {
				continue;
			}
			if (arg instanceof HttpServletResponse) {
				continue;
			}
			key.append(JSON.toJSONString(arg));
			key.append("_");
		}
		return key.toString();
	}
}
