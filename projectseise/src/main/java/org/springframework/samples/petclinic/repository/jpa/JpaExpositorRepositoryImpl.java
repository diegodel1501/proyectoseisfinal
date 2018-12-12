package org.springframework.samples.petclinic.repository.jpa;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.repository.ExpositorRepository;
import org.springframework.stereotype.Repository;
import org.springframework.samples.petclinic.model.Expositor;
@Repository
@Profile("jpa")
public class JpaExpositorRepositoryImpl  implements ExpositorRepository {	
    @PersistenceContext
    private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Expositor> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT expositor FROM Expositor expositor").getResultList();
	}

	@Override
	public void delete(Expositor expositor ) throws DataAccessException {
		this.em.remove(this.em.contains(expositor) ? expositor : this.em.merge(expositor));
	}

	@Override
	public Expositor findById(int Id_expositor) throws DataAccessException {
		return this.em.find(Expositor.class, Id_expositor);
	}

	@Override
	public void save(Expositor expositor) throws DataAccessException {
        this.em.persist(expositor);
	}

	@Override
	public void update(Expositor expositor) throws DataAccessException {
        this.em.merge(expositor);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Expositor> findByName(String name) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT invitado FROM Expositor expositor  WHERE expositor.name LIKE :name");
        query.setParameter("name", name + "%");
        return query.getResultList();
	}

}

