package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class VendingMachine {
	
	private int currentAmount;
	private String displayMessage;
	private boolean hasProductBeenSelected;
	
	private static final Coin[] VALID_COINS = { Coin.QUARTER, Coin.DIME, Coin.NICKEL };
	private static final String MESSAGE_PRICE = "PRICE";
	private static final String MESSAGE_THANK_YOU = "THANK YOU";
	private static final String MESSAGE_INSERT_COINS = "INSERT COINS";
	
	private Map<Coin, Integer> bank;
	private List<Coin> returnCoins = new ArrayList<Coin>();
	
	public void add(Coin coin) {
		if(Arrays.asList(VALID_COINS).contains(coin)) {
			currentAmount += coin.getValue();
			
			Integer currentBankCount = bank.get(coin);
			bank.put(coin, currentBankCount + 1);
		} else {
			returnCoins.add(coin);
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
	
	public List<Coin> getChange() {
		for (Coin coin : VALID_COINS) {
			int howMany = currentAmount / coin.getValue();
			for (int i = 0; i < howMany; i++) {
				Integer currentBankCount = bank.get(coin);
				if(currentBankCount > 0) {
					bank.put(coin, currentBankCount - 1);
					currentAmount -= coin.getValue();
					returnCoins.add(coin);
				}
			}
		}
		return returnCoins;
	}
	
	public void setBank(Map<Coin, Integer> bank) {
		this.bank = bank;
	}
}
