package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.ProductoRepository;
import org.springframework.stereotype.Repository;
@Repository
@Profile("jpa")
public class JpaProductoRepositoryImpl implements ProductoRepository {
	  @PersistenceContext
	    private EntityManager em;

		@SuppressWarnings("unchecked")
		@Override
		public Collection<Producto> findAll() throws DataAccessException {
			return this.em.createQuery("SELECT producto FROM Producto producto").getResultList();
		}

		@Override
		public void delete(Producto producto ) throws DataAccessException {
			this.em.remove(this.em.contains(producto) ? producto : this.em.merge(producto));
		}

		@Override
		public Producto findById_producto(int id_producto) throws DataAccessException {
			return this.em.find(Producto.class, id_producto);
		}

		@Override
		public void save(Producto producto) throws DataAccessException {
	        this.em.persist(producto);
		}

		@Override
		public void update(Producto producto) throws DataAccessException {
	        this.em.merge(producto);
		}
}		
		