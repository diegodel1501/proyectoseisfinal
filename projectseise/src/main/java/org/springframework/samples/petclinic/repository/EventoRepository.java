package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Evento;

public interface EventoRepository {
	
	 Evento findById_evento(int id_evento) throws DataAccessException;
	   void save(Evento evento) throws DataAccessException;
	   
		Collection<Evento> findAll() throws DataAccessException;
		

		void delete(Evento evento) throws DataAccessException;
			    
	    void update(Evento evento) throws DataAccessException;


}
