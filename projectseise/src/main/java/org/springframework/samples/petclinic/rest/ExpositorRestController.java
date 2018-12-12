package org.springframework.samples.petclinic.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.service.ConferencesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.samples.petclinic.model.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;



import org.springframework.http.MediaType;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping ( "/api/expositor " )
public class ExpositorRestController {
	@Autowired
	private ConferencesService conferencesService;
	
		@PreAuthorize( "hasRole(@roles.EXPOSITOR_ADMIN)" )
		@RequestMapping(value = " / {id_Expositor}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public  ResponseEntity<Expositor> getExpositor(@PathVariable("id_Expositor") int id_expositor) {
			Expositor expositor = null;
			expositor = this.conferencesService.findExpositorByID(id_expositor);
			if (expositor == null) {
				return new ResponseEntity<Expositor>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Expositor>(expositor, HttpStatus.OK);
		}
			
	    @PreAuthorize( "hasRole(@roles.EXPOSITOR_ADMIN)" )
		@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Collection<Expositor>> getAllExpositores() {
			Collection<Expositor> expositores = this.conferencesService.findAllExpositores();
			if (expositores.isEmpty()) {
				return new ResponseEntity<Collection<Expositor>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Collection<Expositor>>(expositores, HttpStatus.OK);
		}
		
		
		
		  @PreAuthorize( "hasRole(@roles.EXPOSITOR_ADMIN)" )
			@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Expositor> addExpositor(@RequestBody @Valid Expositor expositor, BindingResult bindingResult,
					UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (expositor == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Expositor>(headers, HttpStatus.BAD_REQUEST);
				}
				this.conferencesService.saveExpositor(expositor);;
				headers.setLocation(ucBuilder.path("/api/expositor/{id}").buildAndExpand(expositor.getId()).toUri());
				return new ResponseEntity<Expositor>(expositor, headers, HttpStatus.CREATED);
				
		  }	
		  @PreAuthorize( "hasRole(@roles.EXPOSITOR_ADMIN)" )
			@RequestMapping(value = "/{id_Expositor}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Expositor> updateExpositor(@PathVariable("id_Expositor") int idexpositor, @RequestBody @Valid Expositor expositor,
				BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (expositor == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Expositor>(headers, HttpStatus.BAD_REQUEST);
				}
				Expositor currentExpositor= this.conferencesService.findExpositorByID(idexpositor);
				if (currentExpositor == null) {
					return new ResponseEntity<Expositor>(HttpStatus.NOT_FOUND);
				}
				currentExpositor.setEmail(expositor.getEmail());
				currentExpositor.setName(expositor.getName());
				currentExpositor.setFono(expositor.getFono());
				currentExpositor.setSexo(expositor.getSexo());
				currentExpositor.setId(expositor.getId());
				this.conferencesService.saveExpositor(currentExpositor);
				
				return new ResponseEntity<Expositor>(currentExpositor, HttpStatus.NO_CONTENT);
			}
		  
		  @PreAuthorize( "hasRole(@roles.EXPOSITOR_ADMIN)" )
			@RequestMapping(value = " / {id_Expositor}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			@Transactional
			public ResponseEntity<Void> deleteExpositor(@PathVariable("id_Expositor") int idexpositor) {
				Expositor expositor = this.conferencesService.findExpositorByID(idexpositor);
				if (expositor == null) {
					return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				}
				this.conferencesService.deleteExpositor(expositor);;
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		  

		  
		  

		
}
