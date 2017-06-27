package com.onehao.disruptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 
 * ListClient
 * chenhao
 * 2017年6月21日 下午3:37:47
 * @version 1.0.0
 * 
 */
public class ListClient {

	private static List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());

	private static Runnable runnable = new Runnable() {
		@Override
		public void run() {
			for (int i = 0; i < 100; ++i) {
				SleepUtil.sleep(10);
				list.add(i);
			}
		}
	};

	public static void main(String[] args) {
		new Thread(runnable).start();
		new Thread(runnable).start();
	}
}