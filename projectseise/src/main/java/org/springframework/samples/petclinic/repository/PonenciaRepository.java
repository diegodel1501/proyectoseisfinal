package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Ponencia;

public interface PonenciaRepository {

	
	Ponencia findByIdPonencia(int idPonencia) throws DataAccessException;
	   void save(Ponencia ponencia) throws DataAccessException;
	   
		Collection<Ponencia> findAll() throws DataAccessException;
		

		void delete(Ponencia ponencia) throws DataAccessException;
	
		
	    void update(Ponencia ponencia) throws DataAccessException;

}
