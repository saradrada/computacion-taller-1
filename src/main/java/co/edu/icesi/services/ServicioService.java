package co.edu.icesi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.exceptions.BusNullException;
import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.exceptions.ServicioNullException;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.repositories.IServicioRepository;

@Service
public class ServicioService implements IServicioService {

	@Autowired
	private IServicioRepository repository;

	@Override
	public Tmio1Servicio guardar(Tmio1Servicio servicio) throws BusNullException, ConductorNullException,
			RutaNullException, FechasNoConsistentesException, ServicioNullException {

		if (servicio == null) {
			throw new ServicioNullException();
		} else if (servicio.getTmio1Bus() == null) {
			throw new BusNullException();
		} else if (servicio.getTmio1Conductore() == null) {
			throw new ConductorNullException();
		} else if (servicio.getTmio1Ruta() == null) {
			throw new RutaNullException();
		} else if (servicio.getTmioFechaInicio().compareTo(servicio.getTmioFechaFin()) == 1) {
			throw new FechasNoConsistentesException();
		} else if (servicio.getTmio1Conductore().getFechaContratacion().compareTo(servicio.getTmioFechaInicio()) == 1) {
			throw new FechasNoConsistentesException();
		} else {
			return repository.guardar(servicio);
		}
	}

}
