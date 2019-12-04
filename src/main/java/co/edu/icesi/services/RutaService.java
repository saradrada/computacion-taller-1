package co.edu.icesi.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.exceptions.FechaInvalidaException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.HoraInvalidaException;
import co.edu.icesi.exceptions.HoraNullException;
import co.edu.icesi.exceptions.HorasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.repositories.IRutaRepository;

@Service
public class RutaService implements IRutaService {

	@Autowired
	private IRutaRepository repository;

	@Override
	public Tmio1Ruta guardar(Tmio1Ruta ruta)
			throws RutaNullException, FechasNoConsistentesException, HorasNoConsistentesException, FechaNullException,
			HoraNullException, FechaInvalidaException, HoraInvalidaException {

		if (ruta == null) {
			throw new RutaNullException();
		} else if (ruta.getDiaInicio() == null || ruta.getDiaFin() == null) {
			throw new FechaNullException();
		} else if (ruta.getHoraInicio() == null || ruta.getHoraFin() == null) {
			throw new HoraNullException();
		} else if (ruta.getDiaInicio().compareTo(new BigDecimal(1)) == -1
				|| ruta.getDiaInicio().compareTo(new BigDecimal(7)) == 1
				|| ruta.getDiaFin().compareTo(new BigDecimal(1)) == -1
				|| ruta.getDiaFin().compareTo(new BigDecimal(7)) == 1) {
			throw new FechaInvalidaException();
		} else if (ruta.getHoraInicio().compareTo(new BigDecimal(0)) == -1
				|| ruta.getHoraInicio().compareTo(new BigDecimal(86399)) == 1
				|| ruta.getHoraFin().compareTo(new BigDecimal(0)) == -1
				|| ruta.getHoraFin().compareTo(new BigDecimal(86399)) == 1) {
			throw new HoraInvalidaException();
		} else if (ruta.getDiaFin().compareTo(ruta.getDiaInicio()) == -1) {
			throw new FechasNoConsistentesException();
		} else if (ruta.getHoraFin().compareTo(ruta.getHoraInicio()) == -1
				|| ruta.getHoraFin().compareTo(ruta.getHoraInicio()) == 0) {
			throw new HorasNoConsistentesException();
		} else {
			return repository.guardar(ruta);
		}
	}

	public void setRepository(IRutaRepository repository) {
		this.repository = repository;
	}
	
	

}
