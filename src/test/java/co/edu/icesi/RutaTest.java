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

import co.edu.icesi.exceptions.FechaInvalidaException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.HoraInvalidaException;
import co.edu.icesi.exceptions.HoraNullException;
import co.edu.icesi.exceptions.HorasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.repositories.IRutaRepository;
import co.edu.icesi.services.RutaService;

public class RutaTest {

	@Mock
	private IRutaRepository mockRuta;

	@InjectMocks
	private RutaService rutaService;

	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAgregarRuta() {

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));

		when(mockRuta.guardar(ruta)).thenReturn(ruta);
		try {
			assertTrue(rutaService.guardar(ruta).equals(ruta));
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| HoraNullException | FechaInvalidaException | HoraInvalidaException e) {
			fail();
		}
		verify(mockRuta, times(1)).guardar(ruta);

	}

	@Test
	public void testRutaNull() {
		try {
			rutaService.guardar(null);
		} catch (RutaNullException e) {
			assertTrue(true);
		} catch (FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException | HoraNullException
				| FechaInvalidaException | HoraInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testDiaInicioNull() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(null);

		try {
			rutaService.guardar(ruta);
			fail();
		} catch (FechaNullException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | HoraNullException
				| FechaInvalidaException | HoraInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testDiaFinNull() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaFin(null);

		try {
			rutaService.guardar(ruta);
			fail();
		} catch (FechaNullException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | HoraNullException
				| FechaInvalidaException | HoraInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testHoraInicioNull() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraFin(new BigDecimal(1));
		ruta.setHoraInicio(null);

		try {
			rutaService.guardar(ruta);
			fail();
		} catch (HoraNullException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| FechaInvalidaException | HoraInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testHoraFinNull() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(null);

		try {
			rutaService.guardar(ruta);
			fail();
		} catch (HoraNullException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| FechaInvalidaException | HoraInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testDiaInicioMayor() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(6));
		ruta.setDiaFin(new BigDecimal(2));
		ruta.setHoraInicio(new BigDecimal(450));
		ruta.setHoraFin(new BigDecimal(8900));

		try {
			rutaService.guardar(ruta);
		} catch (FechasNoConsistentesException e) {
			assertTrue(true);
		} catch (RutaNullException | HorasNoConsistentesException | FechaNullException | HoraNullException
				| FechaInvalidaException | HoraInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testHoraInicioMayor() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(2));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(5000));
		ruta.setHoraFin(new BigDecimal(100));

		try {
			rutaService.guardar(ruta);
		} catch (HorasNoConsistentesException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | FechaNullException | HoraNullException
				| FechaInvalidaException | HoraInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testDiaInicioNegativo() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(-1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));

		try {
			rutaService.guardar(ruta);
			fail();
		} catch (FechaInvalidaException e) {

			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| HoraNullException | HoraInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testDiaFinNegativo() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(-3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));
		try {
			rutaService.guardar(ruta);
			fail();
		} catch (FechaInvalidaException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| HoraNullException | HoraInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testHoraInicioNegativa() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(-1));
		ruta.setHoraFin(new BigDecimal(1000));

		try {
			rutaService.guardar(ruta);
			fail();
		} catch (HoraInvalidaException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| HoraNullException | FechaInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testHoraFinNegativa() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(-1000));

		try {
			rutaService.guardar(ruta);
			fail();
		} catch (HoraInvalidaException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| HoraNullException | FechaInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testHorasIguales() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1));

		try {
			rutaService.guardar(ruta);
			fail();
		} catch (HorasNoConsistentesException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | FechaNullException | HoraNullException
				| FechaInvalidaException | HoraInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testDiaInicioMayorLimite() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(8));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));

		try {
			rutaService.guardar(ruta);
			fail();
		} catch (FechaInvalidaException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| HoraNullException | HoraInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testDiaFinMayorLimite() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(8));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));

		try {
			rutaService.guardar(ruta);
			fail();
		} catch (FechaInvalidaException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| HoraNullException | HoraInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}

	@Test
	public void testHoraInicioMayorLimite() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(86400));
		ruta.setHoraFin(new BigDecimal(1000));

		try {
			rutaService.guardar(ruta);
			fail();
		} catch (HoraInvalidaException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| HoraNullException | FechaInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}
	
	@Test
	public void testHoraFinMayorLimite() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(86400));

		try {
			rutaService.guardar(ruta);
			fail();
		} catch (HoraInvalidaException e) {
			assertTrue(true);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| HoraNullException | FechaInvalidaException e) {
			fail();
		}
		verifyZeroInteractions(mockRuta);
	}
}
