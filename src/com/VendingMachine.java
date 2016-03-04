package com;

public class VendingMachine {

	public boolean accept(int coin) {
		if(coin == 5) {
			return true;
		} else {
			return false;	
		}
	}
}
