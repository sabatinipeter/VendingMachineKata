package com;

public class VendingMachine {

	public boolean accept(int coin) {
		if(coin == 5 || coin == 10 || coin == 25) {
			return true;
		} else {
			return false;	
		}
	}
}
