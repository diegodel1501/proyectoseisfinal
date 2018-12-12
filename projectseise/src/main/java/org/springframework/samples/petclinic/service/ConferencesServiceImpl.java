package org.springframework.samples.petclinic.service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConferencesServiceImpl implements ConferencesService {

    private InvitadoRepository invitadoRepository;
    private ExpositorRepository expositorRepository;
    private BoletoRepository boletoRepository;
    private CatalogoRepository catalogoRepository;
    private EventoRepository eventoRepository;
    private PonenciaRepository ponenciaRepository;
    private ProductoRepository productoRepository;
    @Autowired
    
    public ConferencesServiceImpl(InvitadoRepository invitadoRepository, ExpositorRepository expositorRepository,
			BoletoRepository boletoRepository, CatalogoRepository catalogoRepository, EventoRepository eventoRepository,
			PonenciaRepository ponenciaRepository,ProductoRepository productoRepository) {
		super();
		this.invitadoRepository = invitadoRepository;
		this.expositorRepository = expositorRepository;
		this.boletoRepository = boletoRepository;
		this.catalogoRepository = catalogoRepository;
		this.eventoRepository = eventoRepository;
		this.ponenciaRepository = ponenciaRepository;
		this.productoRepository = productoRepository;
	}
  
    
    // Servicios para invitado
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Invitado> findAllInvitados() throws DataAccessException {
        return invitadoRepository.findAll();
    }

	@Override
    @Transactional(readOnly = true)
    public Invitado findInvitadoById(int id_invitado) throws DataAccessException {
        Invitado invitado = null;
        try {
            invitado = invitadoRepository.findById_invitado(id_invitado);  
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            return null;
        }
        return invitado;
    }

    @Override
    @Transactional
    public void saveInvitado(Invitado invitado) throws DataAccessException {
        invitadoRepository.save(invitado);
    }
    
    @Override
    @Transactional
    public void updateInvitado(Invitado invitado) throws DataAccessException {
        invitadoRepository.update(invitado);
    }
    
    @Override
    @Transactional
    public void deleteInvitado(Invitado invitado) throws DataAccessException {

        invitadoRepository.delete(invitado);
    }
    
	// servicios para expositores    
    @Override
    @Transactional(readOnly = true)
    public Collection<Expositor> findAllExpositores() throws DataAccessException {
        return expositorRepository.findAll();
    }

	@Override
    @Transactional(readOnly = true)
    public Expositor findExpositorByID(int id_expositor) throws DataAccessException {
        Expositor expositor = null;
        try {
            expositor = expositorRepository.findById(id_expositor);  
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            return null;
        }
        return expositor;
    }

    @Override
    @Transactional
    public void saveExpositor(Expositor expositor) throws DataAccessException {
        expositorRepository.save(expositor);
    }
    
    @Override
    @Transactional
    public void updateExpositor(Expositor expositor) throws DataAccessException {
        expositorRepository.update(expositor);
    }
    
    @Override
    @Transactional
    public void deleteExpositor(Expositor expositor) throws DataAccessException {
    	 expositorRepository.delete(expositor);
   
    }
    
    // servicios para evento
    @Override
    @Transactional(readOnly = true)
    public Collection<Evento> findAllEventos() throws DataAccessException {
        return eventoRepository.findAll();
    }

	@Override
    @Transactional(readOnly = true)
    public Evento findEventoById(int id_evento) throws DataAccessException {
        Evento evento = null;
        try {
            evento = eventoRepository.findById_evento(id_evento);  
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            return null;
        }
        return evento;
    }

    @Override
    @Transactional
    public void saveEvento(Evento evento) throws DataAccessException {
        eventoRepository.save(evento);
    }
    
    @Override
    @Transactional
    public void updateEvento(Evento evento) throws DataAccessException {
        eventoRepository.update(evento);
    }
    
    @Override
    @Transactional
    public void deleteEvento(Evento evento) throws DataAccessException {
    	eventoRepository.delete(evento);
    
    }

    // servicios para catalogo
    @Override
    @Transactional(readOnly = true)
    public Collection<Catalogo> findAllCatalogos() throws DataAccessException {
        return catalogoRepository.findAll();
    }

	@Override
    @Transactional(readOnly = true)
    public Catalogo findCatalogoById(int id_catalogo) throws DataAccessException {
        Catalogo catalogo = null;
        try {
            catalogo = catalogoRepository.findById_catalogo(id_catalogo);  
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            return null;
        }
        return catalogo;
    }

    @Override
    @Transactional
    public void saveCatalogo(Catalogo catalogo) throws DataAccessException {
        catalogoRepository.save(catalogo);
    }
    
    @Override
    @Transactional
    public void updateCatalogo(Catalogo catalogo) throws DataAccessException {
        catalogoRepository.update(catalogo);
    }
    
    @Override
    @Transactional
    public void deleteCatalogo(Catalogo catalogo) throws DataAccessException {
    
        catalogoRepository.delete(catalogo);
    }    

    
    // servicios para producto
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Producto> findAllProductos() throws DataAccessException {
        return productoRepository.findAll();
    }

	@Override
    @Transactional(readOnly = true)
    public Producto findProductoById(int id_producto) throws DataAccessException {
        Producto producto = null;
        try {
            producto = productoRepository.findById_producto(id_producto);  
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            return null;
        }
        return producto;
    }

    @Override
    @Transactional
    public void saveProducto(Producto producto) throws DataAccessException {
        productoRepository.save(producto);
    }
    
    @Override
    @Transactional
    public void updateProducto(Producto producto) throws DataAccessException {
        productoRepository.update(producto);
    }
    
    @Override
    @Transactional
    public void deleteProducto(Producto producto) throws DataAccessException {
        productoRepository.delete(producto);

    
    }
    
    
    // servicios para boleto
    @Override
    @Transactional(readOnly = true)
    public Collection<Boleto> findAllBoletos() throws DataAccessException {
        return boletoRepository.findAll();
    }

	@Override
    @Transactional(readOnly = true)
    public Boleto findBoletoById(int id_boleto) throws DataAccessException {
        Boleto boleto = null;
        try {
            boleto = boletoRepository.findById(id_boleto);  
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            return null;
        }
        return boleto;
    }

    @Override
    @Transactional
    public void saveBoleto(Boleto boleto) throws DataAccessException {
        boletoRepository.save(boleto);
    }
    
    @Override
    @Transactional
    public void updateBoleto(Boleto boleto) throws DataAccessException {
        boletoRepository.update(boleto);
    }
    
    @Override
    @Transactional
    public void deleteBoleto(Boleto boleto) throws DataAccessException {
    	   boletoRepository.delete(boleto);
    	
    }
    
    
    // servicios para Ponencia
    @Override
    @Transactional(readOnly = true)
    public Collection<Ponencia> findAllPonencias() throws DataAccessException {
        return ponenciaRepository.findAll();
    }

	@Override
    @Transactional(readOnly = true)
    public Ponencia findPonenciaById(int idPonencia) throws DataAccessException {
        Ponencia ponencia = null;
        try {
            ponencia = ponenciaRepository.findByIdPonencia(idPonencia);  
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            return null;
        }
        return ponencia;
    }

    @Override
    @Transactional
    public void savePonencia(Ponencia ponencia) throws DataAccessException {
    	ponenciaRepository.save(ponencia);
    }
    
    @Override
    @Transactional
    public void updatePonencia(Ponencia ponencia) throws DataAccessException {
    	ponenciaRepository.update(ponencia);
    }
    
    @Override
    @Transactional
    public void deletePonencia(Ponencia ponencia) throws DataAccessException {

    	ponenciaRepository.delete(ponencia);
    	
    }
    @Override
    @Transactional(readOnly = true)
    public List<Ponencia> findAllPonenciaByExpositor(int id_expositor) throws DataAccessException{
    	Expositor expositor = null;
        try {
            expositor = expositorRepository.findById(id_expositor);
            List<Ponencia> ponencias = expositor.getponencias();
            if (ponencias==null) {
            	List<Ponencia> lista = new ArrayList<Ponencia>();
            	return lista;
            } else {
            	return ponencias;
            }
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
    
    }
    @Override
    @Transactional(readOnly = true)
    public List<Ponencia> findAllPonenciaByEvento(int id_evento) throws DataAccessException{
    	Evento evento = null;
        try {
            evento =	eventoRepository.findById_evento(id_evento);
            List<Ponencia> ponencias = evento.getponencias();
            if (ponencias==null) {
            	List<Ponencia> lista2 = new ArrayList<Ponencia>();
            	return lista2;
            } else {
            	return ponencias;
            }
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
    
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAllProductosByCatalogo(int idCatalogo) throws DataAccessException{
    	Catalogo catalogo = null;
        try {
            catalogo =	catalogoRepository.findById_catalogo(idCatalogo);
            List<Producto> productos = catalogo.getProductos();
            if (productos==null) {
            	List<Producto> lista = new ArrayList<Producto>();
            	return lista;
            } else {
            	return productos;
            }
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
    
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<Catalogo> findAllCatalogoByEvento(int idEvento) throws DataAccessException{
    	Evento evento = null;
        try {
            evento =	eventoRepository.findById_evento(idEvento);
            List<Catalogo> catalogos = evento.getCatalogos();
            		if (catalogos==null) {
            	List<Catalogo> lista = new ArrayList<Catalogo>();
            	return lista;
            } else {
            	return catalogos;
            }
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
    
    }
    @Override
    @Transactional(readOnly = true)
    public List<Ponencia> findAllPonenciasByEvento(int idEvento) throws DataAccessException{
    	Evento evento = null;
        try {
            evento =	eventoRepository.findById_evento(idEvento);
            List<Ponencia> ponencias = evento.getponencias();
            		if (ponencias==null) {
            	List<Ponencia> lista = new ArrayList<Ponencia>();
            	return lista;
            } else {
            	return ponencias;
            }
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
    
    }
   
    @Override
    @Transactional(readOnly = true)
    public List<Boleto> findAllBoletosByEvento(int idEvento) throws DataAccessException{
    	Evento evento = null;
        try {
            evento =	eventoRepository.findById_evento(idEvento);
            List<Boleto> boletos = evento.getBoletos();
            		if (boletos==null) {
            	List<Boleto> lista = new ArrayList<Boleto>();
            	return lista;
            } else {
            	return boletos;
            }
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
    
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Boleto> findAllBoletosByInvitado(int idInvitado) throws DataAccessException{
    	Invitado invitado = null;
        try {
            invitado =	invitadoRepository.findById_invitado(idInvitado);
            List<Boleto> boletos = invitado.getBoletos();
            		if (boletos==null) {
            	List<Boleto> lista = new ArrayList<Boleto>();
            	return lista;
            } else {
            	return boletos;
            }
        } catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
    
    }
    
    
    
    
    
    
    
} 
  