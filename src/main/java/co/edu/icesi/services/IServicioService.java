package co.edu.icesi.services;

import co.edu.icesi.exceptions.BusNullException;
import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.exceptions.ServicioNullException;
import co.edu.icesi.model.Tmio1Servicio;

public interface IServicioService {

	public Tmio1Servicio guardar(Tmio1Servicio servicio)
			throws BusNullException, ConductorNullException, RutaNullException, FechasNoConsistentesException, ServicioNullException;
}
