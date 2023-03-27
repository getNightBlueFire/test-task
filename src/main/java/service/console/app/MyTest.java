package service.console.app;

import static org.junit.Assert.*;
import org.junit.Test;

import service.console.app.BusTime;
import service.console.app.Route;

public class MyTest {

	private final BusTime s = new BusTime(10,21);
	private final BusTime f = new BusTime(12,22);
	private final Route rout = new Route("Posh", s, f);

	@Test
	public void testCompanyName() {

		assertEquals("Posh", rout.getBusCompany());
	}

	@Test
	public void testTimeStart() {
		assertEquals("10:21", s.getBusTime());
	}

	@Test
	public void testTimeFinish() {
		assertEquals("12:22", f.getBusTime());
	}

	@Test
	public void testTime() {
		assertNotEquals(f.getBusTime(), s.getBusTime());
	}
	@Test
	public void TestIvan(){System.out.println("Привет от ивана!");}
}

