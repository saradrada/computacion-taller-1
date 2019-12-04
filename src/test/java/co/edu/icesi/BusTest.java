
package co.edu.icesi;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.math.BigDecimal;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.exceptions.BusNullException;
import co.edu.icesi.exceptions.BusTipoException;
import co.edu.icesi.exceptions.CapacidadNullException;
import co.edu.icesi.exceptions.GuardarBusException;
import co.edu.icesi.exceptions.TipoNullException;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.repositories.IBusRepository;
import co.edu.icesi.services.BusService;

public class BusTest {

	@Mock
	private IBusRepository mockBus;

	@InjectMocks
	private BusService busService;

	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testBusTipoT() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo("T");

		try {
			when(mockBus.guardar(bus)).thenReturn(bus);
			assertTrue(busService.guardar(bus).equals(bus));
			verify(mockBus, times(1)).guardar(bus);
		} catch (GuardarBusException | BusNullException | BusTipoException | TipoNullException
				| CapacidadNullException e) {
			fail();
		}

	}
	

	@Test
	public void testBusNull() {
		try {
			busService.guardar(null);
		} catch (BusNullException e) {
			assertTrue(true);
		} catch (BusTipoException | TipoNullException | CapacidadNullException | GuardarBusException e) {
			fail();
		}

		verifyZeroInteractions(mockBus);
	}

	@Test
	public void testBusCapacidadIgualCero() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setTipo("P");
		bus.setCapacidad(new BigDecimal(0));

		try {
			busService.guardar(bus);
			fail();
		} catch (GuardarBusException e) {
			assertTrue(true);
		} catch (BusNullException | BusTipoException | TipoNullException | CapacidadNullException e) {
			fail();
		}

		verifyZeroInteractions(mockBus);
	}

	@Test
	public void testBusCapacidadMenorCero() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setTipo("T");
		bus.setCapacidad(new BigDecimal(-1));

		try {
			busService.guardar(bus);
			fail();
		} catch (GuardarBusException e) {
			assertTrue(true);
		} catch (BusNullException | BusTipoException | TipoNullException | CapacidadNullException b) {
			fail();
		}

		verifyZeroInteractions(mockBus);
	}

	@Test
	public void testBusOtroTipo() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo("Z");

		try {
			busService.guardar(bus);
			fail();
		} catch (BusTipoException  e) {
			assertTrue(true);
		} catch (BusNullException | GuardarBusException | TipoNullException | CapacidadNullException e) {
			fail();
		}
		
		verifyZeroInteractions(mockBus);

	}

	
	//************************
	
	@Test
	public void testBusTipoA() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo("A");

		try {
			when(mockBus.guardar(bus)).thenReturn(bus);
			assertTrue(busService.guardar(bus).equals(bus));
			verify(mockBus, times(1)).guardar(bus);
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
			when(mockBus.guardar(bus)).thenReturn(bus);
			assertTrue(busService.guardar(bus).equals(bus));
			verify(mockBus, times(1)).guardar(bus);
		} catch (GuardarBusException | BusNullException | BusTipoException | TipoNullException
				| CapacidadNullException e) {
			fail();
		}

	}
	
	@Test
	public void testCapacidadNull() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(null);
		bus.setTipo("A");

		try {
			busService.guardar(bus);
			fail();
		} catch (CapacidadNullException e) {
			assertTrue(true);
		} catch (BusNullException | BusTipoException | TipoNullException | GuardarBusException e) {
			
			fail();
		}
		verifyZeroInteractions(mockBus);
	}
	
	@Test
	public void testTipoNull() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo(null);

		try {
			busService.guardar(bus);
			fail();
		} catch ( TipoNullException e) {
			assertTrue(true);
		} catch (BusNullException | GuardarBusException | BusTipoException| CapacidadNullException e) {
			fail();
		}
		
		verifyZeroInteractions(mockBus);

	}
	
	@Test
	public void testTipoVacio() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo("");

		try {
			busService.guardar(bus);
			fail();
		} catch ( TipoNullException e) {
			assertTrue(true);
		} catch (BusNullException | GuardarBusException | BusTipoException| CapacidadNullException e) {
			fail();
		}
		
		verifyZeroInteractions(mockBus);

	}
}
