package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class VendingMachine {
	
	private int currentAmount;
	private String displayMessage;
	private boolean hasProductBeenSelected;
	
	private static final Coin[] VALID_COINS = { Coin.QUARTER, Coin.DIME, Coin.NICKEL };
	private static final String MESSAGE_PRICE = "PRICE";
	private static final String MESSAGE_SOLD_OUT = "SOLD OUT";
	private static final String MESSAGE_THANK_YOU = "THANK YOU";
	private static final String MESSAGE_INSERT_COINS = "INSERT COINS";
	private static final String MESSAGE_EXACT_CHANGE_ONLY = "EXACT CHANGE ONLY";
	
	private Map<Coin, Integer> bank = new HashMap<Coin, Integer>();
	private Map<Product, Integer> inventory = new HashMap<Product, Integer>();
	private List<Coin> returnCoins = new ArrayList<Coin>();
	
	public void add(Coin coin) {
		if(Arrays.asList(VALID_COINS).contains(coin)) {
			currentAmount += coin.getValue();
			
			Integer currentBankCount = bank.get(coin) == null ? 0 : bank.get(coin);
			bank.put(coin, currentBankCount + 1);
		} else {
			returnCoins.add(coin);
		}
	}
	
	public void select(Product product) {
		hasProductBeenSelected = true;
		Integer currentCount = inventory.get(product) == null ? 0 : inventory.get(product);
		if(currentCount > 0) {
			if(currentAmount < product.getValue()) {
				displayMessage = MESSAGE_PRICE + " " + product.getValue();
			} else {
				currentAmount -= product.getValue();
				displayMessage = MESSAGE_THANK_YOU;
			}
		} else {
			displayMessage = MESSAGE_SOLD_OUT;
		}
	}
	
	public String getDisplayMessage() {
		if(showExactChangeMessage()) {
			return MESSAGE_EXACT_CHANGE_ONLY;
		}
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
				Integer currentBankCount = bank.get(coin) == null ? 0 : bank.get(coin);
				if(currentBankCount > 0) {
					bank.put(coin, currentBankCount - 1);
					currentAmount -= coin.getValue();
					returnCoins.add(coin);
				}
			}
		}
		return returnCoins;
	}
	
	private boolean showExactChangeMessage() {
		for (Entry<Product, Integer> inventory : inventory.entrySet()) {
			int productQuantity = inventory.getValue();
			if(productQuantity == 0) {
				continue;
			}
			int productPrice = inventory.getKey().getValue();
			while(productPrice > 0) {
				for (Coin coin : VALID_COINS) {
					int remainder = productPrice % coin.getValue();
					if(remainder > 0) {
						int change = remainder - coin.getValue();
						if(change < 0) {
							if(!bankCoversAmount(Math.abs(change))) {
								return true;
							}
						}
					}
				}
				productPrice -= VALID_COINS[0].getValue();
			}
		}
		return false;
	}

	private boolean bankCoversAmount(int amount) {
		Coin coin = Coin.getCoinByValue(amount);
		Integer currentBankCount = bank.get(coin) == null ? 0 : bank.get(coin);
		return currentBankCount > 0 ? true : false;
	}

	public void setBank(Map<Coin, Integer> bank) {
		this.bank = bank;
	}

	public void setInventory(HashMap<Product, Integer> inventory) {
		this.inventory = inventory;
	}
}
