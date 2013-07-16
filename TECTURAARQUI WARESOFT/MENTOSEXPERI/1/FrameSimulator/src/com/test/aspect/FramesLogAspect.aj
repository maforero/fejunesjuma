package com.test.aspect;

import com.test.thread.ThreadManager;
import java.util.List;

/**
 * @class FramesLogAspect.aj
 * @author Felipe
 * @Date Jul 12, 2013
 * @since 1.0
 */
public aspect FramesLogAspect {

	pointcut logFrames() : execution(* ThreadManager.loadFrames(List<byte[]>));
	
	Object around(): logFrames() {
		Object ret = proceed();
		List<byte[]> frames = (List<byte[]>)thisJoinPoint.getArgs()[0];
		printFrames(frames);
		return ret;
	}
	
	private void printFrames(List<byte[]> frames) {
		System.out.println("before login frames -----------------------");
		for (byte[] bs : frames) {
			StringBuilder regularFrameBuilder = new StringBuilder();
			StringBuilder framesBuilder = new StringBuilder();
			for (byte b : bs) {
				framesBuilder.append(Integer.toBinaryString(b));
				framesBuilder.append(";");
				regularFrameBuilder.append(b);
				regularFrameBuilder.append(";");
			}

			System.out.println(regularFrameBuilder);
			System.out.println(framesBuilder);
		}
		
		System.out.println("after login frames -----------------------");
	}
}
