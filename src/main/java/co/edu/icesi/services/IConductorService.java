package co.edu.icesi.services;

import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.FormatoIncorrectoException;
import co.edu.icesi.model.Tmio1Conductore;

public interface IConductorService {

	public Tmio1Conductore guardar(Tmio1Conductore conductor) throws ConductorNullException, FechasNoConsistentesException, FechaNullException, FormatoIncorrectoException;

}
