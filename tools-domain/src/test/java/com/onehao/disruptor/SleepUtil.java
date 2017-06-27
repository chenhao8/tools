package com.onehao.disruptor;

/**
 * 
 * SleepUtil
 * chenhao
 * 2017年6月21日 下午3:37:54
 * @version 1.0.0
 * 
 */
public class SleepUtil {

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}