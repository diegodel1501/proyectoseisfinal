package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Expositor;


public interface ExpositorRepository {
	
	 Collection<Expositor> findByName(String name) throws DataAccessException;
	 Expositor findById(int id_expositor) throws DataAccessException;
	   void save(Expositor expositor) throws DataAccessException;
	   
		Collection<Expositor> findAll() throws DataAccessException;
		

		void delete(Expositor expositor) throws DataAccessException;
	    void update(Expositor expositor) throws DataAccessException;


}
