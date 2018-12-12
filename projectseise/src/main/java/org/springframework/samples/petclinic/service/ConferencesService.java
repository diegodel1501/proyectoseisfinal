package org.springframework.samples.petclinic.service;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;

public interface ConferencesService {
	// Servicios para Expositores
    Expositor findExpositorByID(int id) throws DataAccessException;
    Collection<Expositor> findAllExpositores() throws DataAccessException;
    void saveExpositor(Expositor expositor) throws DataAccessException;
    void updateExpositor(Expositor expositor) throws DataAccessException;
    void deleteExpositor(Expositor expositor) throws DataAccessException;

    // Servicios para Invitados
    Invitado findInvitadoById(int id_invitado) throws DataAccessException;
    Collection<Invitado> findAllInvitados() throws DataAccessException;
    void saveInvitado(Invitado invitado) throws DataAccessException;
    void updateInvitado(Invitado invitado) throws DataAccessException;
    void deleteInvitado(Invitado invitado) throws DataAccessException;

	// Servicios para Eventos
	Collection<Evento> findAllEventos() throws DataAccessException;
	Evento findEventoById(int id_evento) throws DataAccessException;
	void saveEvento(Evento evento) throws DataAccessException;
	void updateEvento(Evento evento) throws DataAccessException;
	void deleteEvento(Evento evento) throws DataAccessException;
	
	// Servicios para Catalogos
	Collection<Catalogo> findAllCatalogos() throws DataAccessException;
	Catalogo findCatalogoById(int id_catalogo) throws DataAccessException;
	void saveCatalogo(Catalogo catalogo) throws DataAccessException;
	void updateCatalogo(Catalogo catalogo) throws DataAccessException;
	void deleteCatalogo(Catalogo catalogo) throws DataAccessException;
	
	//servicios para producto
	Collection<Producto> findAllProductos() throws DataAccessException;
	Producto findProductoById(int id_producto) throws DataAccessException;
	void saveProducto (Producto producto) throws DataAccessException;
	void updateProducto(Producto producto) throws DataAccessException;
	void deleteProducto(Producto producto) throws DataAccessException;
	
	
	//servicios para Ponencia
	Collection<Ponencia> findAllPonencias() throws DataAccessException;
	Ponencia findPonenciaById(int idPonencia) throws DataAccessException;
	void savePonencia(Ponencia ponencia) throws DataAccessException;
	void updatePonencia(Ponencia ponencia) throws DataAccessException;
	void deletePonencia(Ponencia ponencia) throws DataAccessException;
	List<Ponencia> findAllPonenciaByExpositor(int id_expositor) throws DataAccessException;
	List<Ponencia> findAllPonenciaByEvento(int id_evento) throws DataAccessException;
	
	
	//servicios para boletos
	Collection<Boleto> findAllBoletos() throws DataAccessException;
	Boleto findBoletoById(int idBoleto) throws DataAccessException;
	void saveBoleto(Boleto boleto) throws DataAccessException;
	void updateBoleto(Boleto boleto) throws DataAccessException;
	void deleteBoleto(Boleto boleto) throws DataAccessException;
	
	
	
	// Adicionales
	
	List<Producto>  findAllProductosByCatalogo(int idCatalogo) throws DataAccessException;
	List<Catalogo> findAllCatalogoByEvento(int idEvento) throws DataAccessException;
	List<Ponencia> findAllPonenciasByEvento(int idPonencia) throws DataAccessException;
	List<Boleto> findAllBoletosByEvento(int idEvento) throws DataAccessException;
	List<Boleto> findAllBoletosByInvitado(int idInvitado) throws DataAccessException;
	

}
