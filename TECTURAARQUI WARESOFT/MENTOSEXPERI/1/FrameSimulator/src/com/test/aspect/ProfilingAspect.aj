package com.test.aspect;

/**
 * @class ProfilingAspect.aj
 * @author Felipe
 * @Date Jul 12, 2013
 * @since 1.0
 */
public aspect ProfilingAspect {

	pointcut profileMethod() : execution(public * *.*(..));

	Object around(): profileMethod() {
		long init = getTime();
		Object ret = proceed();
		long end = getTime();
		System.out.println(thisJoinPointStaticPart.getSignature() + " took "
				+ (end - init) + " nanoseconds");
		return ret;
	}
	
	/**
	 * @return
	 */
	private long getTime() {
		long init = System.nanoTime();
		return init;
	}
}
