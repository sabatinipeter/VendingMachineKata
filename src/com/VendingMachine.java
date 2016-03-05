package com;

public class VendingMachine {

	private int currentAmount;
	private String displayMessage;
	private boolean hasProductBeenSelected;
	
	private static final String MESSAGE_PRICE = "PRICE";
	private static final String MESSAGE_THANK_YOU = "THANK YOU";
	private static final String MESSAGE_INSERT_COINS = "INSERT COINS";
	
	public void add(Coin coin) {
		if(coin.getValue() == 5 || coin.getValue() == 10 || coin.getValue() == 25) {
			currentAmount += coin.getValue();
		}
	}

	public void select(Product product) {
		hasProductBeenSelected = true;
		if(currentAmount < product.getValue()) {
			displayMessage = MESSAGE_PRICE + " " + product.getValue();
		} else {
			currentAmount -= product.getValue();
			displayMessage = MESSAGE_THANK_YOU;
		}
	}

	public String getDisplayMessage() {
		if(hasProductBeenSelected == true) {
			hasProductBeenSelected = false;
			return displayMessage;
		} else {
			return currentAmount > 0 ? String.valueOf(currentAmount) : MESSAGE_INSERT_COINS;
		}
	}
}
