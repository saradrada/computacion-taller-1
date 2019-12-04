
package co.edu.icesi.integration;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.FormatoIncorrectoException;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.repositories.ConductorRepository;
import co.edu.icesi.services.ConductorService;
@SpringBootTest
public class ConductorTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private ConductorService conductorService;

	private Tmio1Conductore conductor;

	private SimpleDateFormat format;

	@BeforeTest
	public void setUp() {
		conductorService = new ConductorService();
		conductorService.setRepository(new ConductorRepository());
		format = new SimpleDateFormat("yyyy-MM-dd");
	}

	/**
	 * Caso de prueba #2
	 */
	@Test
	public void agregarConductor() throws ParseException {

		conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(format.parse("2009-12-31"));
		conductor.setFechaNacimiento(format.parse("1999-12-31"));

		try {
			assertTrue(conductorService.guardar(conductor).equals(conductor));
		} catch (ConductorNullException | FechasNoConsistentesException | FechaNullException | FormatoIncorrectoException e) {
			fail();
		}
	}


	}

