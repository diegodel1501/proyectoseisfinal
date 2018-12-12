package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.stereotype.Repository;
@Repository
@Profile("jpa")
public class JpaEventoRepositoryImpl implements EventoRepository {
	  @PersistenceContext
	    private EntityManager em;

		@SuppressWarnings("unchecked")
		@Override
		public Collection<Evento> findAll() throws DataAccessException {
			return this.em.createQuery("SELECT evento FROM Evento evento").getResultList();
		}

		@Override
		public void delete(Evento evento ) throws DataAccessException {
			this.em.remove(this.em.contains(evento) ? evento : this.em.merge(evento));
		}

		@Override
		public Evento findById_evento(int id_evento) throws DataAccessException {
			return this.em.find(Evento.class, id_evento);
		}

		@Override
		public void save(Evento evento) throws DataAccessException {
	        this.em.persist(evento);
		}

		@Override
		public void update(Evento evento) throws DataAccessException {
	        this.em.merge(evento);
		}
}		
		