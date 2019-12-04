package co.edu.icesi.integration;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
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
import co.edu.icesi.services.BusService;
import co.edu.icesi.services.ConductorService;
import co.edu.icesi.services.RutaService;
import co.edu.icesi.services.ServicioService;

@SpringBootTest
public class ServicioTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private BusService busService;

	@Autowired
	private ConductorService conductorService;

	@Autowired
	private RutaService rutaService;

	@Autowired
	private ServicioService servicioService;

	private SimpleDateFormat format;

	private Tmio1Bus bus;

	private Tmio1Conductore conductor;

	private Tmio1Ruta ruta;

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
			assertTrue(servicioService.guardar(servicio).equals(servicio));
		} catch (BusNullException | ConductorNullException | RutaNullException | FechasNoConsistentesException
				| ServicioNullException e) {
			fail();
		}

	}

}
