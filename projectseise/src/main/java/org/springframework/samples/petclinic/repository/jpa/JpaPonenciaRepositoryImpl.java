package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Ponencia;
import org.springframework.samples.petclinic.repository.PonenciaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public class JpaPonenciaRepositoryImpl implements PonenciaRepository {
	  @PersistenceContext
	    private EntityManager em;

		@SuppressWarnings("unchecked")
		@Override
		public Collection<Ponencia> findAll() throws DataAccessException {
			return this.em.createQuery("SELECT ponencia FROM Ponencia ponencia").getResultList();
		}

		@Override
		public void delete(Ponencia ponencia ) throws DataAccessException {
			this.em.remove(this.em.contains(ponencia) ? ponencia : this.em.merge(ponencia));
		}

		@Override
		public Ponencia findByIdPonencia(int IdPonencia) throws DataAccessException {
			return this.em.find(Ponencia.class, IdPonencia);
		}

		@Override
		public void save(Ponencia ponencia) throws DataAccessException {
	        this.em.persist(ponencia);
		}

		@Override
		public void update(Ponencia ponencia) throws DataAccessException {
	        this.em.merge(ponencia);
		}
		
	

	


}


