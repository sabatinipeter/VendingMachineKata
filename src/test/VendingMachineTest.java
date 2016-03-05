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
	public void vendingMachineAcceptsValidCoins() {
		assertEquals(0, vendingMachine.getCurrentAmount());
		
		assertEquals(5, vendingMachine.add(Coin.NICKEL));
		assertEquals(15, vendingMachine.add(Coin.DIME));
		assertEquals(40, vendingMachine.add(Coin.QUARTER));
		assertEquals(40, vendingMachine.add(Coin.PENNIE));
	}
	
	@Test
	public void selectProduct() {
		assertEquals("INSERT COINS", vendingMachine.select(Product.COLA));
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals(25, vendingMachine.getCurrentAmount());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals(50, vendingMachine.getCurrentAmount());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals(75, vendingMachine.getCurrentAmount());
		
		vendingMachine.add(Coin.QUARTER);
		assertEquals(100, vendingMachine.getCurrentAmount());
		
		assertEquals("THANK YOU", vendingMachine.select(Product.COLA));
		assertEquals(0, vendingMachine.getCurrentAmount());
	}
}
