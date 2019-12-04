package co.edu.icesi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.FormatoIncorrectoException;
import co.edu.icesi.model.DateValidator;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.repositories.IConductorRepository;

@Service
public class ConductorService implements IConductorService {

	@Autowired
	private IConductorRepository repository;

	@Override
	public Tmio1Conductore guardar(Tmio1Conductore conductor) throws ConductorNullException,
			FechasNoConsistentesException, FechaNullException, FormatoIncorrectoException {

		if (conductor == null) {
			throw new ConductorNullException();
		} else if (conductor.getFechaNacimiento() == null || conductor.getFechaContratacion() == null) {
			throw new FechaNullException();
		} else if (conductor.getFechaContratacion().compareTo(conductor.getFechaNacimiento()) == -1
				|| conductor.getFechaContratacion().compareTo(conductor.getFechaNacimiento()) == 0) {
			throw new FechasNoConsistentesException();
		} else {
			return repository.guardar(conductor);
		}
	}

	public void setRepository(IConductorRepository repository) {
		this.repository = repository;
	}

}
