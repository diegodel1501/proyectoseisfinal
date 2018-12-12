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
@RequestMapping ( " / api / invitado " )
public class InvitadoRestController {
	@Autowired
	private ConferencesService conferencesService;
	
		@PreAuthorize( "hasRole(@roles.INVITADO_ADMIN)" )
		@RequestMapping(value = " / {id_Invitado}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public  ResponseEntity<Invitado> getInvitado(@PathVariable("id_Invitado") int idinvitado) {
			Invitado invitado = null;
			invitado = this.conferencesService.findInvitadoById(idinvitado);
			if (invitado == null) {
				return new ResponseEntity<Invitado>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Invitado>(invitado, HttpStatus.OK);
		}
			
	    @PreAuthorize( "hasRole(@roles.INVITADO_ADMIN)" )
		@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Collection<Invitado>> getAllInvitados() {
			Collection<Invitado> invitados = this.conferencesService.findAllInvitados();
			if (invitados.isEmpty()) {
				return new ResponseEntity<Collection<Invitado>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Collection<Invitado>>(invitados, HttpStatus.OK);
		}
		
		
		
		  @PreAuthorize( "hasRole(@roles.INVITADO_ADMIN)" )
			@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Invitado> addInvitado(@RequestBody @Valid Invitado invitado, BindingResult bindingResult,
					UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (invitado == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Invitado>(headers, HttpStatus.BAD_REQUEST);
				}
				this.conferencesService.saveInvitado(invitado);;
				headers.setLocation(ucBuilder.path("/api/invitado/{id}").buildAndExpand(invitado.getId()).toUri());
				return new ResponseEntity<Invitado>(invitado, headers, HttpStatus.CREATED);
				
		  }	
		  @PreAuthorize( "hasRole(@roles.INVITADO_ADMIN)" )
			@RequestMapping(value = "/{id_Invitado}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Invitado> updateInvitado(@PathVariable("id_Invitado") int idinvitado, @RequestBody @Valid Invitado invitado,
				BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (invitado == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Invitado>(headers, HttpStatus.BAD_REQUEST);
				}
				Invitado currentInvitado= this.conferencesService.findInvitadoById(idinvitado);
				if (currentInvitado == null) {
					return new ResponseEntity<Invitado>(HttpStatus.NOT_FOUND);
				}
				currentInvitado.setEmail(invitado.getEmail());
				currentInvitado.setName(invitado.getName());
				currentInvitado.setFono(invitado.getFono());
				currentInvitado.setSexo(invitado.getSexo());
				currentInvitado.setId(invitado.getId());
				this.conferencesService.saveInvitado(currentInvitado);
				
				return new ResponseEntity<Invitado>(currentInvitado, HttpStatus.NO_CONTENT);
			}
		  
		  @PreAuthorize( "hasRole(@roles.INVITADO_ADMIN)" )
			@RequestMapping(value = " / {id_Invitado}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			@Transactional
			public ResponseEntity<Void> deleteInvitado(@PathVariable("id_Invitado") int idinvitado) {
				Invitado invitado = this.conferencesService.findInvitadoById(idinvitado);
				if (invitado == null) {
					return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				}
				this.conferencesService.deleteInvitado(invitado);;
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		  

		  
		  

		
}
