package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.VendingMachine;

public class VendingMachineTest {

	private static final int PENNIE = 1;
	private static final int NICKEL = 5;
	private static final int DIME = 10;
	private static final int QUARTER = 25;

	@Test
	public void vendingMachineAcceptsANickel() {
		VendingMachine vm = new VendingMachine();
		assertEquals(true, vm.accept(NICKEL));
		assertEquals(true, vm.accept(DIME));
		assertEquals(true, vm.accept(QUARTER));
		assertEquals(false, vm.accept(PENNIE));
		
	}
	
	
}
