package co.edu.icesi.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.exceptions.BusNullException;
import co.edu.icesi.exceptions.BusTipoException;
import co.edu.icesi.exceptions.CapacidadNullException;
import co.edu.icesi.exceptions.GuardarBusException;
import co.edu.icesi.exceptions.TipoNullException;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.repositories.IBusRepository;

@Service
public class BusService implements IBusService {

	@Autowired
	private IBusRepository repository;

	@Override
	public Tmio1Bus guardar(Tmio1Bus bus)
			throws GuardarBusException, BusNullException, BusTipoException, TipoNullException, CapacidadNullException {

		if (bus == null) {
			throw new BusNullException();
		} else if (bus.getTipo() == null || bus.getTipo().equals("")) {
			throw new TipoNullException();
		} else if (bus.getCapacidad() == null) {
			throw new CapacidadNullException();
		} else if ((bus.getCapacidad().compareTo(new BigDecimal(0)) <= 0)) {
			throw new GuardarBusException();
		} else if (!(bus.getTipo().equals("T") || bus.getTipo().equals("P") || bus.getTipo().equals("A"))) {
			throw new BusTipoException();
		} else {
			repository.guardar(bus);
			return bus;
		}

	}
	
	public void setRepository(IBusRepository repository) {
		this.repository = repository;
	}

}
