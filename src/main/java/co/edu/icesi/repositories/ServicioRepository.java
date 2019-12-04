package co.edu.icesi.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServicioPK;

@Repository
public class ServicioRepository implements IServicioRepository {

	private Map<Tmio1ServicioPK, Tmio1Servicio> servicios;

	public ServicioRepository() {
		servicios = new HashMap<Tmio1ServicioPK, Tmio1Servicio>();
	}

	@Override
	public Tmio1Servicio guardar(Tmio1Servicio servicio) {
		servicios.put(servicio.getId(), servicio);
		return servicio;
	}

}
