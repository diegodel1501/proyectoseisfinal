package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Producto;

public interface ProductoRepository {
	
	 Producto findById_producto(int id_Producto) throws DataAccessException;
	   void save(Producto producto) throws DataAccessException;
	   
		Collection<Producto> findAll() throws DataAccessException;

		void delete(Producto producto) throws DataAccessException;
    
	    void update(Producto producto) throws DataAccessException;

}
