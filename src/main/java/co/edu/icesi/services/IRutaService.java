package co.edu.icesi.services;

import co.edu.icesi.exceptions.FechaInvalidaException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.HoraInvalidaException;
import co.edu.icesi.exceptions.HoraNullException;
import co.edu.icesi.exceptions.HorasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.model.Tmio1Ruta;

public interface IRutaService {

	public Tmio1Ruta guardar(Tmio1Ruta ruta)
			throws RutaNullException, FechasNoConsistentesException, HorasNoConsistentesException, FechaNullException,
			HoraNullException, FechaInvalidaException, HoraInvalidaException;

}
