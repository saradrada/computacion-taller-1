package co.edu.icesi;

import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.exceptions.BusNullException;
import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.exceptions.ServicioNullException;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.repositories.IBusRepository;
import co.edu.icesi.repositories.IConductorRepository;
import co.edu.icesi.repositories.IRutaRepository;
import co.edu.icesi.repositories.IServicioRepository;
import co.edu.icesi.services.ServicioService;

public class ServicioTest {

	@Mock
	private IServicioRepository mockServicio;

	@Mock
	private IBusRepository mockBus;

	@Mock
	private IConductorRepository mockConductor;

	@Mock
	private IRutaRepository mockRuta;

	@InjectMocks
	private ServicioService servicioService;

	private SimpleDateFormat format;

	private Tmio1Bus bus;

	private Tmio1Conductore conductor;

	private Tmio1Ruta ruta;

	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeTest
	public void setUp() throws ParseException {

		format = new SimpleDateFormat("yyyy-MM-dd");

		bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(1));
		bus.setTipo("T");

		conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(format.parse("2009-12-31"));
		conductor.setFechaNacimiento(format.parse("1999-12-31"));

		ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));

		when(mockBus.guardar(bus)).thenReturn(bus);
		when(mockConductor.guardar(conductor)).thenReturn(conductor);
		when(mockRuta.guardar(ruta)).thenReturn(ruta);

	}

	@Test
	public void agregarServicio() throws ParseException {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmioFechaInicio(format.parse("2009-12-31"));
		servicio.setTmioFechaFin(format.parse("2019-10-14"));
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);

		try {
			when(mockServicio.guardar(servicio)).thenReturn(servicio);
			assertTrue(servicioService.guardar(servicio).equals(servicio));
		} catch (BusNullException | ConductorNullException | RutaNullException | FechasNoConsistentesException
				| ServicioNullException e) {
			fail();
		}

	}

	@Test
	public void testServicioNull() {
		try {
			servicioService.guardar(null);
		} catch (ServicioNullException e) {
			assertTrue(true);
		} catch (BusNullException | ConductorNullException | RutaNullException | FechasNoConsistentesException e) {
			fail();
		}
		verifyZeroInteractions(mockBus);
		verifyZeroInteractions(mockConductor);
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testBusNull() {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmio1Bus(null);
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);

		try {
			servicioService.guardar(servicio);
			fail();
		} catch (BusNullException e) {
			assertTrue(true);
		} catch (ConductorNullException | RutaNullException | FechasNoConsistentesException | ServicioNullException e) {
			fail();
		}
		verifyZeroInteractions(mockBus);
		verifyZeroInteractions(mockConductor);
		verifyZeroInteractions(mockRuta);

	}

	@Test
	public void testConductorNull() {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(null);
		servicio.setTmio1Ruta(ruta);

		try {
			servicioService.guardar(servicio);
			fail();
		} catch (ConductorNullException e) {
			assertTrue(true);
		} catch (BusNullException | RutaNullException | FechasNoConsistentesException | ServicioNullException e) {
			fail();
		}

		verifyZeroInteractions(mockBus);
		verifyZeroInteractions(mockConductor);
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testRutaNull() {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(null);

		try {
			servicioService.guardar(servicio);
			fail();
		} catch (RutaNullException e) {
			assertTrue(true);
		} catch (BusNullException | ConductorNullException | FechasNoConsistentesException | ServicioNullException e) {
			fail();
		}

		verifyZeroInteractions(mockBus);
		verifyZeroInteractions(mockConductor);
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testFechaContratacionMayor() throws ParseException {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmioFechaInicio(format.parse("2009-12-31"));
		servicio.setTmioFechaFin(format.parse("2019-10-14"));
		servicio.setTmio1Bus(bus);
		conductor.setFechaContratacion(format.parse("2018-02-13"));
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);

		try {
			servicioService.guardar(servicio);
		} catch (FechasNoConsistentesException e) {
			assertTrue(true);
		} catch (BusNullException | ConductorNullException | RutaNullException | ServicioNullException e) {
			fail();
		}
		verifyZeroInteractions(mockBus);
		verifyZeroInteractions(mockConductor);
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testFechaFinMenor() throws ParseException {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmioFechaInicio(format.parse("2009-12-31"));
		servicio.setTmioFechaFin(format.parse("2001-10-14"));
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);

		try {
			servicioService.guardar(servicio);
		} catch (FechasNoConsistentesException e) {
			assertTrue(true);
		} catch (BusNullException | ConductorNullException | RutaNullException | ServicioNullException e) {
			fail();
		}
		verifyZeroInteractions(mockBus);
		verifyZeroInteractions(mockConductor);
		verifyZeroInteractions(mockRuta);
	}

	// **************************************************

	@Test
	public void testFechaConductorNoConsistente() throws ParseException {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setTmioFechaInicio(format.parse("2009-12-31"));
		servicio.setTmioFechaFin(format.parse("2019-10-14"));
		servicio.setTmio1Bus(bus);
		conductor.setFechaContratacion(format.parse("2010-11-03"));
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);

		try {
			servicioService.guardar(servicio);
		} catch (FechasNoConsistentesException e) {
			assertTrue(true);
		} catch (BusNullException | ConductorNullException | RutaNullException | ServicioNullException e) {
			fail();
		}
		verifyZeroInteractions(mockBus);
		verifyZeroInteractions(mockConductor);
		verifyZeroInteractions(mockRuta);

	}

}
