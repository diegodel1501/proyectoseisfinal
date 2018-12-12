package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Catalogo;

public interface CatalogoRepository {
	
	 Catalogo findById_catalogo(int id_catalogo) throws DataAccessException;
	   void save(Catalogo catalogo) throws DataAccessException;
	   
		Collection<Catalogo> findAll() throws DataAccessException;
		

		void delete(Catalogo catalogo) throws DataAccessException;

	    
	    void update(Catalogo catalogo) throws DataAccessException;


}
