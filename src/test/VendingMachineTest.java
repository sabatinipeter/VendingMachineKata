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
		HashMap<Coin, Integer> bank = new HashMap<Coin, Integer>();
		bank.put(Coin.NICKEL, 1);
		vendingMachine.setBank(bank);
		
		HashMap<Product, Integer> inventory = new HashMap<Product, Integer>();
		inventory.put(Product.COLA, 1);
		vendingMachine.setInventory(inventory);
		
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
		HashMap<Coin, Integer> bank = new HashMap<Coin, Integer>();
		bank.put(Coin.DIME, 1);
		bank.put(Coin.NICKEL, 1);
		vendingMachine.setBank(bank);
		
		HashMap<Product, Integer> inventory = new HashMap<Product, Integer>();
		inventory.put(Product.CANDY, 1);
		vendingMachine.setInventory(inventory);
		
		int value = 0;
		for (Coin coin : vendingMachine.getChange()) {
			value += coin.getValue();
		}
		assertEquals(0, value);
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals("25", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals("50", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals("75", vendingMachine.getDisplayMessage());
		
		vendingMachine.select(Product.CANDY);
		
		value = 0;
		for (Coin coin : vendingMachine.getChange()) {
			value += coin.getValue();
		}
		assertEquals(10, value);
	}
	
	@Test
	public void returnCoins() {
		vendingMachine.add(Coin.QUARTER);
		vendingMachine.add(Coin.DIME);
		assertEquals("35", vendingMachine.getDisplayMessage());
		
		vendingMachine.getChange();
		
		int value = 0;
		for (Coin coin : vendingMachine.getChange()) {
			value += coin.getValue();
		}
		assertEquals(35, value);
		assertEquals("INSERT COINS", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void soldOut() {
		vendingMachine.add(Coin.QUARTER);
		vendingMachine.select(Product.CHIPS);
		
		assertEquals("SOLD OUT", vendingMachine.getDisplayMessage());
		assertEquals("25", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.QUARTER);
		vendingMachine.select(Product.CHIPS);
		
		assertEquals("SOLD OUT", vendingMachine.getDisplayMessage());
	}
	
	@Test 
	public void exactChangeOnly() {
		HashMap<Product, Integer> inventory = new HashMap<Product, Integer>();
		inventory.put(Product.COLA, 1);
		vendingMachine.setInventory(inventory);
		
		vendingMachine.setBank(new HashMap<Coin, Integer>());
		
		String displayMessage = vendingMachine.getDisplayMessage();
		assertEquals("EXACT CHANGE ONLY", displayMessage);
		
		HashMap<Coin, Integer> bank = new HashMap<Coin, Integer>();
		bank.put(Coin.NICKEL, 1);
		vendingMachine.setBank(bank);
		
		displayMessage = vendingMachine.getDisplayMessage();
		assertEquals("INSERT COINS", displayMessage);
		
		inventory = new HashMap<Product, Integer>();
		inventory.put(Product.CANDY, 1);
		vendingMachine.setInventory(inventory);
		
		bank = new HashMap<Coin, Integer>();
		bank.put(Coin.NICKEL, 1);
		bank.put(Coin.DIME, 0);
		vendingMachine.setBank(bank);
		
		displayMessage = vendingMachine.getDisplayMessage();
		assertEquals("EXACT CHANGE ONLY", displayMessage);
		
		bank = new HashMap<Coin, Integer>();
		bank.put(Coin.NICKEL, 1);
		bank.put(Coin.DIME, 1);
		vendingMachine.setBank(bank);
		
		displayMessage = vendingMachine.getDisplayMessage();
		assertEquals("INSERT COINS", displayMessage);
	}
}
