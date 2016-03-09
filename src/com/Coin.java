package com;

public enum Coin {

	PENNY(1), NICKEL(5), DIME(10), QUARTER(25);
	
	private final int value;
	
	private Coin(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

	public static Coin getCoinByValue(int value) {
		for (Coin coin : Coin.values()) {
			if (coin.value == value) {
				return coin;
			}
		}
		return null;
	}
}
