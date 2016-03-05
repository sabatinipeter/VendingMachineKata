package test;

import static org.junit.Assert.*;

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
		assertEquals(0, vendingMachine.getCurrentAmount());
		
		vendingMachine.add(Coin.NICKEL);
		assertEquals(5, vendingMachine.getCurrentAmount());
		
		vendingMachine.add(Coin.DIME);
		assertEquals(15, vendingMachine.getCurrentAmount());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals(40, vendingMachine.getCurrentAmount());
		
		vendingMachine.add(Coin.PENNY);
		assertEquals(40, vendingMachine.getCurrentAmount());
	}
	
	@Test
	public void selectProduct() {
		vendingMachine.select(Product.COLA);
		assertEquals("PRICE 100", vendingMachine.getDisplayMessage());
		assertEquals("INSERT COINS", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals(25, vendingMachine.getCurrentAmount());
		assertEquals("25", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals(50, vendingMachine.getCurrentAmount());
		assertEquals("50", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals(75, vendingMachine.getCurrentAmount());
		assertEquals("75", vendingMachine.getDisplayMessage());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals(100, vendingMachine.getCurrentAmount());
		assertEquals("100", vendingMachine.getDisplayMessage());
		
		vendingMachine.select(Product.COLA);
		assertEquals("THANK YOU", vendingMachine.getDisplayMessage());
		assertEquals(0, vendingMachine.getCurrentAmount());
		
		assertEquals("INSERT COINS", vendingMachine.getDisplayMessage());
	}
}
