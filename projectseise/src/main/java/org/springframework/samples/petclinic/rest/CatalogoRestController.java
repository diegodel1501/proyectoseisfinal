package org.springframework.samples.petclinic.rest;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Catalogo;
import org.springframework.samples.petclinic.service.ConferencesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping ( " / api / catalogo " )
public class CatalogoRestController {

	@Autowired
	private ConferencesService conferencesService;
	
		@PreAuthorize( "hasRole(@roles.CATALOGO_ADMIN)" )
		@RequestMapping(value = " / {id_catalogo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public  ResponseEntity<Catalogo> getCatalogo(@PathVariable("id_catalogo") int idcatalogo) {
			Catalogo catalogo = null;
			catalogo = this.conferencesService.findCatalogoById(idcatalogo);
			if (catalogo == null) {
				return new ResponseEntity<Catalogo>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Catalogo>(catalogo, HttpStatus.OK);
		}
			
	    @PreAuthorize( "hasRole(@roles.CATALOGO_ADMIN)" )
		@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Collection<Catalogo>> getAllCatalogos() {
			Collection<Catalogo> catalogos = this.conferencesService.findAllCatalogos();
			if (catalogos.isEmpty()) {
				return new ResponseEntity<Collection<Catalogo>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Collection<Catalogo>>(catalogos, HttpStatus.OK);
		}
		
		
		
		  @PreAuthorize( "hasRole(@roles.CATALOGO_ADMIN)" )
			@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Catalogo> addCatalogo(@RequestBody @Valid Catalogo catalogo, BindingResult bindingResult,
					UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (catalogo == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Catalogo>(headers, HttpStatus.BAD_REQUEST);
				}
				this.conferencesService.saveCatalogo(catalogo);;
				headers.setLocation(ucBuilder.path("/api/catalogo/{id}").buildAndExpand(catalogo.getId_catalogo()).toUri());
				return new ResponseEntity<Catalogo>(catalogo, headers, HttpStatus.CREATED);
				
		  }	
		  @PreAuthorize( "hasRole(@roles.CATALOGO_ADMIN)" )
			@RequestMapping(value = "/{id_catalogo}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Catalogo> updateCatalogo(@PathVariable("id_catalogo") int idcatalogo, @RequestBody @Valid Catalogo catalogo,
				BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (catalogo == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Catalogo>(headers, HttpStatus.BAD_REQUEST);
				}
				Catalogo currentCatalogo= this.conferencesService.findCatalogoById(idcatalogo);
				if (currentCatalogo == null) {
					return new ResponseEntity<Catalogo>(HttpStatus.NOT_FOUND);
				}
				currentCatalogo.setCantidad_productos(catalogo.getCantidad_productos());
				currentCatalogo.setEvento(catalogo.getEvento());
				currentCatalogo.setId_catalogo(catalogo.getId_catalogo());
				currentCatalogo.setNombre_catalogo(catalogo.getNombre_catalogo());
				this.conferencesService.saveCatalogo(currentCatalogo);
				
				return new ResponseEntity<Catalogo>(currentCatalogo, HttpStatus.NO_CONTENT);
			}
		  
		  @PreAuthorize( "hasRole(@roles.CATALOGO_ADMIN)" )
			@RequestMapping(value = " / {id_catalogo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			@Transactional
			public ResponseEntity<Void> deleteCatalogo(@PathVariable("id_catalogo") int idcatalogo) {
				Catalogo catalogo = this.conferencesService.findCatalogoById(idcatalogo);
				if (catalogo == null) {
					return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				}
				this.conferencesService.deleteCatalogo(catalogo);;
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		  @PreAuthorize( "hasRole(@roles.EVENTO_ADMIN)" )
	        @RequestMapping(value = "/evento/{id_evento}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	        public ResponseEntity<List<Catalogo>> getCatalogoByEvento(@PathVariable("id_evento") int idevento){
	            List<Catalogo> catalogos = this.conferencesService.findAllCatalogoByEvento(idevento);
	            if(catalogos.isEmpty()){
	                return new ResponseEntity<List<Catalogo>>(HttpStatus.NOT_FOUND);
	            }
	            return new ResponseEntity<List<Catalogo>>(catalogos, HttpStatus.OK);
	        }
		  

		  
		  
}
