package com;

public enum Product {

	COLA(100), CHIPS(50), CANDY(65);
	
	private final int value;
	
	private Product(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
