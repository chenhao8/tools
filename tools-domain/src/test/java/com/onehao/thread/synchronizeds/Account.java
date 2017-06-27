package com.onehao.thread.synchronizeds;

/**
 * @author chenhao
 *
 */
public class Account {

	private double balance;

	public Account() {
	}

	public Account(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void reduceBalance(double count) {
		this.balance -= count;
	}
}