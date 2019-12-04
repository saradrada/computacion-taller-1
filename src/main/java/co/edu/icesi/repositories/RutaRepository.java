package co.edu.icesi.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Tmio1Ruta;

@Repository
public class RutaRepository implements IRutaRepository {

	private Map<Integer, Tmio1Ruta> rutas;

	public RutaRepository() {
		rutas = new HashMap<Integer, Tmio1Ruta>();
	}

	@Override
	public Tmio1Ruta guardar(Tmio1Ruta ruta){

		rutas.put(ruta.getId(), ruta);
		return ruta;

	}

}
