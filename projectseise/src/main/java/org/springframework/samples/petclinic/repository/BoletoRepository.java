package org.springframework.samples.petclinic.repository;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;
public interface BoletoRepository {
	
	Collection<Boleto> findAll() throws DataAccessException;
	Boleto findById(int id_boleto) throws DataAccessException;
	void save(Boleto boleto) throws DataAccessException;
	void update(Boleto boleto) throws DataAccessException;
	void delete(Boleto boleto) throws DataAccessException;

}
