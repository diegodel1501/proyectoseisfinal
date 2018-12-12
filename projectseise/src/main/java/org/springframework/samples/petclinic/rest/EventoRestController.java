package org.springframework.samples.petclinic.rest;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.*;
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
@RequestMapping ( " / api / evento " )
public class EventoRestController {
	@Autowired
	private ConferencesService conferencesService;
	
		@PreAuthorize( "hasRole(@roles.EVENTO_ADMIN)" )
		@RequestMapping(value = " / {id_evento}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public  ResponseEntity<Evento> getEvento(@PathVariable("id_evento") int idevento) {
			Evento evento = null;
			evento = this.conferencesService.findEventoById(idevento);
			if (evento == null) {
				return new ResponseEntity<Evento>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Evento>(evento, HttpStatus.OK);
		}
			
	    @PreAuthorize( "hasRole(@roles.EVENTO_ADMIN)" )
		@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Collection<Evento>> getAllEventos() {
			Collection<Evento> eventos = this.conferencesService.findAllEventos();
			if (eventos.isEmpty()) {
				return new ResponseEntity<Collection<Evento>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Collection<Evento>>(eventos, HttpStatus.OK);
		}
		
		
		
		  @PreAuthorize( "hasRole(@roles.EVENTO_ADMIN)" )
			@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Evento> addEvento(@RequestBody @Valid Evento evento, BindingResult bindingResult,
					UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (evento == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Evento>(headers, HttpStatus.BAD_REQUEST);
				}
				this.conferencesService.saveEvento(evento);;
				headers.setLocation(ucBuilder.path("/api/evento/{id}").buildAndExpand(evento.getId()).toUri());
				return new ResponseEntity<Evento>(evento, headers, HttpStatus.CREATED);
				
		  }	
		  @PreAuthorize( "hasRole(@roles.EVENTO_ADMIN)" )
			@RequestMapping(value = "/{id_evento}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Evento> updateEvento(@PathVariable("id_evento") int idevento, @RequestBody @Valid Evento evento,
				BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (evento == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Evento>(headers, HttpStatus.BAD_REQUEST);
				}
				Evento currentEvento= this.conferencesService.findEventoById(idevento);
				if (currentEvento == null) {
					return new ResponseEntity<Evento>(HttpStatus.NOT_FOUND);
				}
				currentEvento.setFecha(evento.getFecha());
				currentEvento.setName(evento.getName());
				currentEvento.setId(evento.getId());;
				this.conferencesService.saveEvento(currentEvento);
				
				return new ResponseEntity<Evento>(currentEvento, HttpStatus.NO_CONTENT);
			}
		  
		  @PreAuthorize( "hasRole(@roles.EVENTO_ADMIN)" )
			@RequestMapping(value = " / {id_evento}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			@Transactional
			public ResponseEntity<Void> deleteEvento(@PathVariable("id_evento") int idevento) {
				Evento evento = this.conferencesService.findEventoById(idevento);
				if (evento == null) {
					return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				}
				this.conferencesService.deleteEvento(evento);;
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		  

		  
		  

		
}

