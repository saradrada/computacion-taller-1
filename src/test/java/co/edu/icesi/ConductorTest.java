package co.edu.icesi;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.FormatoIncorrectoException;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.repositories.IConductorRepository;
import co.edu.icesi.services.ConductorService;

public class ConductorTest {

	@Mock
	private IConductorRepository mockConductor;

	@InjectMocks
	private ConductorService conductorService;

	private Tmio1Conductore conductor;

	private SimpleDateFormat format;

	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeTest	
	public void setUp() {
		format = new SimpleDateFormat("yyyy-MM-dd");
	}

	@Test
	public void agregarConductor() throws ParseException {

		conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(format.parse("2009-12-31"));
		conductor.setFechaNacimiento(format.parse("1999-12-31"));

		when(mockConductor.guardar(conductor)).thenReturn(conductor);
		try {
			assertTrue(conductorService.guardar(conductor).equals(conductor));
		} catch (ConductorNullException | FechasNoConsistentesException | FechaNullException | FormatoIncorrectoException e) {
			
			fail();
		}
		verify(mockConductor, times(1)).guardar(conductor);
	}

	@Test
	public void agregarConductorNull() {

		try {
			conductorService.guardar(null);
		} catch (ConductorNullException e) {
			assertTrue(true);
		} catch (FechasNoConsistentesException | FechaNullException | FormatoIncorrectoException e) {
			fail();
		}
		verifyZeroInteractions(mockConductor);

	}

	@Test
	public void agregarConductorFechasIguales() throws ParseException {

		conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(format.parse("1999-10-15"));
		conductor.setFechaNacimiento(format.parse("1999-10-15"));

		try {
			conductorService.guardar(conductor);
			fail();
		} catch (ConductorNullException | FechaNullException | FormatoIncorrectoException e) {
			fail();
		} catch (FechasNoConsistentesException e) {
			assertTrue(true);
		}
		verifyZeroInteractions(mockConductor);
	}

	@Test
	public void agregarConductorFechaContratacionMenor() throws ParseException {

		conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(format.parse("1999-10-15"));
		conductor.setFechaNacimiento(format.parse("2010-11-29"));

		try {
			conductorService.guardar(conductor);
			fail();
		} catch (FechasNoConsistentesException e) {
			assertTrue(true);
		} catch (ConductorNullException | FechaNullException | FormatoIncorrectoException e) {
			fail();
		}
		verifyZeroInteractions(mockConductor);
	}

	@Test
	public void agregarConductorFechaContratacionNull() throws ParseException {

		conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(null);
		conductor.setFechaNacimiento(format.parse("2010-11-29"));

		try {
			conductorService.guardar(conductor);
			fail();
		} catch (FechaNullException e) {
			assertTrue(true);
		} catch (ConductorNullException | FechasNoConsistentesException | FormatoIncorrectoException e) {
			fail();
		}
		verifyZeroInteractions(mockConductor);
	}

	@Test
	public void agregarConductorFechaNacimientoNull() throws ParseException {

		conductor = new Tmio1Conductore();
		conductor.setFechaNacimiento(null);
		conductor.setFechaContratacion(format.parse("2008-01-09"));

		try {
			conductorService.guardar(conductor);
			fail();
		} catch (FechaNullException e) {
			assertTrue(true);
		} catch (ConductorNullException | FechasNoConsistentesException | FormatoIncorrectoException e) {
			fail();
		}
		verifyZeroInteractions(mockConductor);
	}
}
