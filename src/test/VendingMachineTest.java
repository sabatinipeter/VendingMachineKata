package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.Product;
import com.VendingMachine;

public class VendingMachineTest {

	private static final int PENNIE = 1;
	private static final int NICKEL = 5;
	private static final int DIME = 10;
	private static final int QUARTER = 25;

	@Test
	public void vendingMachineAcceptsValidCoins() {
		VendingMachine vm = new VendingMachine();
		assertEquals(5, vm.add(NICKEL));
		assertEquals(15, vm.add(DIME));
		assertEquals(40, vm.add(QUARTER));
		assertEquals(40, vm.add(PENNIE));
	}
	
	@Test
	public void selectProduct() {
		VendingMachine vm = new VendingMachine();
		
		assertEquals("INSERT COINS", vm.select(Product.COLA));
		
		vm.add(QUARTER);
		vm.add(QUARTER);
		vm.add(QUARTER);
		vm.add(QUARTER);
		
		assertEquals("THANK YOU", vm.select(Product.COLA));
	}
}
