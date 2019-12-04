package co.edu.icesi.services;

import co.edu.icesi.exceptions.BusNullException;
import co.edu.icesi.exceptions.BusTipoException;
import co.edu.icesi.exceptions.CapacidadNullException;
import co.edu.icesi.exceptions.GuardarBusException;
import co.edu.icesi.exceptions.TipoNullException;
import co.edu.icesi.model.Tmio1Bus;

public interface IBusService {

	public Tmio1Bus guardar(Tmio1Bus bus) throws GuardarBusException, BusNullException, BusTipoException, TipoNullException, CapacidadNullException;
}
