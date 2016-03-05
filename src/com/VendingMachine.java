package com;

public class VendingMachine {

	private int currentAmount;
	
	private static final String MESSAGE_PRICE = "PRICE";
	private static final String MESSAGE_THANK_YOU = "THANK YOU";
	private static final String MESSAGE_INSERT_COINS = "INSERT COINS";
	
	public int add(Coin coin) {
		if(coin.getValue() == 5 || coin.getValue() == 10 || coin.getValue() == 25) {
			return currentAmount += coin.getValue();
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
