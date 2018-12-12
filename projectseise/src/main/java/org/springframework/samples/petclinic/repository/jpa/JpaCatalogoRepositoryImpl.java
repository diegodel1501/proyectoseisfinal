package org.springframework.samples.petclinic.repository.jpa;
		

	import java.util.Collection;

	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;

	import org.springframework.context.annotation.Profile;
	import org.springframework.dao.DataAccessException;
	import org.springframework.samples.petclinic.model.Catalogo;
	import org.springframework.samples.petclinic.repository.CatalogoRepository;
	import org.springframework.stereotype.Repository;
	@Repository
	@Profile("jpa")
	public class  JpaCatalogoRepositoryImpl implements CatalogoRepository {
		  @PersistenceContext
		    private EntityManager em;

			@SuppressWarnings("unchecked")
			@Override
			public Collection<Catalogo> findAll() throws DataAccessException {
				return this.em.createQuery("SELECT catalogo FROM Catalogo catalogo").getResultList();
			}

			@Override
			public void delete(Catalogo catalogo ) throws DataAccessException {
				this.em.remove(this.em.contains(catalogo) ? catalogo : this.em.merge(catalogo));
			}

			@Override
			public Catalogo findById_catalogo(int id_catalogo) throws DataAccessException {
				return this.em.find(Catalogo.class, id_catalogo);
			}

			@Override
			public void save(Catalogo catalogo) throws DataAccessException {
		        this.em.persist(catalogo);
			}

			@Override
			public void update(Catalogo catalogo) throws DataAccessException {
		        this.em.merge(catalogo);
			}
	}		
