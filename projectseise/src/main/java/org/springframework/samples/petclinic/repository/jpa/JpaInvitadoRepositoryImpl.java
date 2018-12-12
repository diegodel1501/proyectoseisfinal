package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.repository.InvitadoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.samples.petclinic.model.Invitado;
@Repository
@Profile("jpa")
public class JpaInvitadoRepositoryImpl implements InvitadoRepository {	
    @PersistenceContext
    private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Invitado> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT invitado FROM Invitado invitado").getResultList();
	}

	@Override
	public void delete(Invitado invitado ) throws DataAccessException {
		this.em.remove(this.em.contains(invitado) ? invitado : this.em.merge(invitado));
	}

	@Override
	public Invitado findById_invitado(int Id_invitado) throws DataAccessException {
		return this.em.find(Invitado.class, Id_invitado);
	}

	@Override
	public void save(Invitado invitado) throws DataAccessException {
        this.em.persist(invitado);
	}

	@Override
	public void update(Invitado invitado) throws DataAccessException {
        this.em.merge(invitado);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Invitado> findByNombre(String name) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT invitado FROM Invitado invitado WHERE invitado.name LIKE :name");
        query.setParameter("name", name + "%");
        return query.getResultList();
	}

}
