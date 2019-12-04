package co.edu.icesi.integration;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.exceptions.FechaInvalidaException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.HoraInvalidaException;
import co.edu.icesi.exceptions.HoraNullException;
import co.edu.icesi.exceptions.HorasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.repositories.RutaRepository;
import co.edu.icesi.services.RutaService;

@SpringBootTest
public class RutaTest extends AbstractTestNGSpringContextTests{

	@Autowired
	private RutaService rutaService;
	
	@BeforeTest
	public void setUp() {
		rutaService = new RutaService();
		rutaService.setRepository(new RutaRepository());
	}
	
	@Test
	public void testAgregarRuta() {

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));

		try {
			assertTrue(rutaService.guardar(ruta).equals(ruta));
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| HoraNullException | FechaInvalidaException | HoraInvalidaException e) {
			fail();
		}

	}
}
