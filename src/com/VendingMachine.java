package com;

public class VendingMachine {

	private int currentAmount;
	
	public int add(int coin) {
		if(coin == 5 || coin == 10 || coin == 25) {
			return currentAmount += coin;
		} else {
			return currentAmount;	
		}
	}
}
