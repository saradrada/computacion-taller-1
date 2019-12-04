package co.edu.icesi.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.model.Tmio1Conductore;

@Repository
public class ConductorRepository implements IConductorRepository {

	private Map<String, Tmio1Conductore> conductores;

	public ConductorRepository() {
		conductores = new HashMap<String, Tmio1Conductore>();
	}

	@Override
	public Tmio1Conductore guardar(Tmio1Conductore conductor) {

		conductores.put(conductor.getCedula(), conductor);
		return conductor;

	}

}
