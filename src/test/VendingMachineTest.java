package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.VendingMachine;

public class VendingMachineTest {

	private static final int NICKEL = 5;

	@Test
	public void vendingMachineAcceptsANickel() {
		VendingMachine vm = new VendingMachine();
		assertEquals(true, vm.accept(NICKEL));
	}
}
