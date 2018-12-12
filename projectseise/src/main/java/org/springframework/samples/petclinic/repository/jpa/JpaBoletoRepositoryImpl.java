	package org.springframework.samples.petclinic.repository.jpa;
	import java.util.Collection;
	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;
	import org.springframework.context.annotation.Profile;
	import org.springframework.dao.DataAccessException;
	import org.springframework.samples.petclinic.model.Boleto;
	import org.springframework.samples.petclinic.repository.BoletoRepository;
	import org.springframework.stereotype.Repository;
	@Repository
	@Profile("jpa")
	public class  JpaBoletoRepositoryImpl implements BoletoRepository {
		  @PersistenceContext
		    private EntityManager em;

			@SuppressWarnings("unchecked")
			@Override
			public Collection<Boleto> findAll() throws DataAccessException {
				return this.em.createQuery("SELECT boleto FROM Boleto boleto").getResultList();
			}

			@Override
			public void delete(Boleto boleto ) throws DataAccessException {
				this.em.remove(this.em.contains(boleto) ? boleto : this.em.merge(boleto));
			}

			@Override
			public Boleto findById(int idBoleto) throws DataAccessException {
				return this.em.find(Boleto.class, idBoleto);
			}

			@Override
			public void save(Boleto boleto) throws DataAccessException {
		        this.em.persist(boleto);
			}

			@Override
			public void update(Boleto boleto) throws DataAccessException {
		        this.em.merge(boleto);
			}
	}		
