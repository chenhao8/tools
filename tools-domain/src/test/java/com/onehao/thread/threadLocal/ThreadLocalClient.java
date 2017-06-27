package com.onehao.thread.threadLocal;

/**
 * 
 * ThreadLocalClient
 * chenhao
 * 2017年6月19日 上午10:11:32
 * @version 1.0.0
 * 
 */
public class ThreadLocalClient {

	public static void main(String[] args) {
		// 同一个Account
		Account account = new Account(888L);
		new Thread(new Writer(account)).start();
		new Thread(new Reader(account)).start();

		// 主线程
		for (int i = 0; i < 10; ++i) {
			sleep(10);
			System.out.println(Thread.currentThread().getName() + "-balance: " + account.getBalance());
		}
	}

	private static class Writer implements Runnable {

		private Account account;

		public Writer(Account account) {
			this.account = account;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; ++i) {
				account.setBalance((long) i);
				sleep(10);
				System.out.println(Thread.currentThread().getName() + "-balance: " + account.getBalance());
			}
		}
	}

	private static class Reader implements Runnable {

		private Account account;

		public Reader(Account account) {
			this.account = account;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; ++i) {
				sleep(10);
				System.out.println(Thread.currentThread().getName() + "-balance: " + account.getBalance());
			}
		}
	}

	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}