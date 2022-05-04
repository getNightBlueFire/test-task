package service.console.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.time.LocalTime;

public class MyTest {

    private final LocalTime s = LocalTime.of(10, 21);
    private final LocalTime f = LocalTime.of(12, 22);
    private final Route rout = new Route("Posh", s, f);

    @Test
    public void testCompanyName() {

        assertEquals("Posh", rout.getBusCompany());
    }

    @Test
    public void testTimeStart() {
        assertEquals("10:21", rout.getStart().toString());
    }

    @Test
    public void testTimeFinish() {
        assertEquals("12:22", rout.getFinish().toString());
    }
}
