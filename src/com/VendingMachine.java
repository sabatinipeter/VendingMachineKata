package com;

public class VendingMachine {

	private int currentAmount;
	
	private static final String MESSAGE_PRICE = "PRICE";
	private static final String MESSAGE_THANK_YOU = "THANK YOU";
	private static final String MESSAGE_INSERT_COINS = "INSERT COINS";
	
	public int add(int coin) {
		if(coin == 5 || coin == 10 || coin == 25) {
			return currentAmount += coin;
		} else {
			return currentAmount;	
		}
	}

	public String select(Product product) {
		if(currentAmount < product.getValue()) {
			return MESSAGE_INSERT_COINS;
		} else {
			return MESSAGE_THANK_YOU;
		}
	}
}
