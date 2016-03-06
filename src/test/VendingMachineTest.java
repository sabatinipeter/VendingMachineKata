package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.Coin;
import com.Product;
import com.VendingMachine;

public class VendingMachineTest {

	private VendingMachine vendingMachine;
	
	@Before
	public void setup() {
		vendingMachine = new VendingMachine();
		
		HashMap<Coin, Integer> bank = new HashMap<Coin, Integer>();
		bank.put(Coin.QUARTER, 10);
		bank.put(Coin.DIME, 10);
		bank.put(Coin.NICKEL, 10);
		bank.put(Coin.PENNY, 10);
		
		vendingMachine.setBank(bank);
	}

	@Test
	public void acceptCoins() {
		
		vendingMachine.add(Coin.NICKEL);
		assertEquals("5", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.DIME);
		assertEquals("15", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals("40", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.PENNY);
		assertEquals("40", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void selectProduct() {
		vendingMachine.select(Product.COLA);
		assertEquals("PRICE 100", vendingMachine.getDisplayMessage());
		assertEquals("INSERT COINS", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals("25", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals("50", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals("75", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals("100", vendingMachine.getDisplayMessage());
		
		vendingMachine.select(Product.COLA);
		assertEquals("THANK YOU", vendingMachine.getDisplayMessage());
		assertEquals("INSERT COINS", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void makeChange() {
		int value = 0;
		for (Coin coin : vendingMachine.getChange()) {
			value += coin.getValue();
		}
		assertEquals(0, value);
		
		vendingMachine.add(Coin.QUARTER);
		vendingMachine.add(Coin.QUARTER);
		vendingMachine.add(Coin.QUARTER);
		
		vendingMachine.select(Product.CANDY);
		
		value = 0;
		for (Coin coin : vendingMachine.getChange()) {
			value += coin.getValue();
		}
		assertEquals(10, value);
	}
}
