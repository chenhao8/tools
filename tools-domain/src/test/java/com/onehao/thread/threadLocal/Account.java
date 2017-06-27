package com.onehao.thread.threadLocal;

/**
 * 
 * Account
 * chenhao
 * 2017年6月19日 上午10:10:41
 * @version 1.0.0
 * 
 */
public class Account {

	private ThreadLocal<Long> balance = new ThreadLocal<>();

	public Account(Long balance) {
		this.balance.set(balance);
	}

	public Long getBalance() {
		return balance.get();
	}

	public void setBalance(Long balance) {
		this.balance.set(balance);
	}
}