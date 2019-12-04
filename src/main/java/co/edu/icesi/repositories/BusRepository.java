package co.edu.icesi.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.edu.icesi.exceptions.BusNullException;
import co.edu.icesi.exceptions.GuardarBusException;
import co.edu.icesi.model.Tmio1Bus;

@Repository
public class BusRepository implements IBusRepository {

	private Map<Integer, Tmio1Bus> buses;

	public BusRepository() {
		buses = new HashMap<Integer, Tmio1Bus>();
	}

	@Override
	public Tmio1Bus guardar(Tmio1Bus bus){

		buses.put(bus.getId(), bus);
		return bus;

	}

}
