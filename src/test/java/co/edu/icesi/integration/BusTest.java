
package co.edu.icesi.integration;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.exceptions.BusNullException;
import co.edu.icesi.exceptions.BusTipoException;
import co.edu.icesi.exceptions.CapacidadNullException;
import co.edu.icesi.exceptions.GuardarBusException;
import co.edu.icesi.exceptions.TipoNullException;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.services.BusService;

@SpringBootTest
public class BusTest extends AbstractTestNGSpringContextTests {


	@Autowired
	private BusService busService;

	@BeforeTest
	public void initMocks() {
	}

	@Test
	public void testBusTipoT() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo("T");

		try {
			assertTrue(busService.guardar(bus).equals(bus));
		} catch (GuardarBusException | BusNullException | BusTipoException | TipoNullException
				| CapacidadNullException e) {
			fail();
		}

	}
	
	@Test
	public void testBusTipoA() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo("A");

		try {
			assertTrue(busService.guardar(bus).equals(bus));
		} catch (GuardarBusException | BusNullException | BusTipoException | TipoNullException
				| CapacidadNullException e) {
			fail();
		}

	}
	
	@Test
	public void testBusTipoP() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo("P");

		try {
			assertTrue(busService.guardar(bus).equals(bus));
		} catch (GuardarBusException | BusNullException | BusTipoException | TipoNullException
				| CapacidadNullException e) {
			fail();
		}

	}
}
