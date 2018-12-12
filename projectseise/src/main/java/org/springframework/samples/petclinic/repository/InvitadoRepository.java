package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Invitado;

public interface InvitadoRepository {
	
	
    Collection<Invitado> findAll() throws DataAccessException;
    Invitado findById_invitado(int invitado) throws DataAccessException;
	
	 Collection<Invitado> findByNombre(String name) throws DataAccessException;
	 
	void save(Invitado invitado) throws DataAccessException;
	   
	void delete(Invitado invitado) throws DataAccessException;
	void update(Invitado invitado) throws DataAccessException;	
	 


	    
	   



}
