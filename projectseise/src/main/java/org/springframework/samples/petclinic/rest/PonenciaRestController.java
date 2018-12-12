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
import org.springframework.samples.petclinic.model.Ponencia;
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
@RequestMapping ( " / api / ponencia " )

public class PonenciaRestController {
	@Autowired
	private ConferencesService conferencesService;
	
		@PreAuthorize( "hasRole(@roles.PONENCIA_ADMIN)" )
		@RequestMapping(value = " / {idPonencia}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public  ResponseEntity<Ponencia> getPonencia(@PathVariable("idPonencia") int idponencia) {
			Ponencia ponencia = null;
			ponencia = this.conferencesService.findPonenciaById(idponencia);
			if (ponencia == null) {
				return new ResponseEntity<Ponencia>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Ponencia>(ponencia, HttpStatus.OK);
		}
			
	    @PreAuthorize( "hasRole(@roles.PONENCIA_ADMIN)" )
		@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Collection<Ponencia>> getAllPonencias() {
			Collection<Ponencia> ponencias = this.conferencesService.findAllPonencias();
			if (ponencias.isEmpty()) {
				return new ResponseEntity<Collection<Ponencia>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Collection<Ponencia>>(ponencias, HttpStatus.OK);
		}
		
		
		
		  @PreAuthorize( "hasRole(@roles.PONENCIA_ADMIN)" )
			@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Ponencia> addPonencia(@RequestBody @Valid Ponencia ponencia, BindingResult bindingResult,
					UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (ponencia == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Ponencia>(headers, HttpStatus.BAD_REQUEST);
				}
				this.conferencesService.savePonencia(ponencia);;
				headers.setLocation(ucBuilder.path("/api/ponencia/{id}").buildAndExpand(ponencia.getIdPonencia()).toUri());
				return new ResponseEntity<Ponencia>(ponencia, headers, HttpStatus.CREATED);
				
		  }	
		  @PreAuthorize( "hasRole(@roles.PONENCIA_ADMIN)" )
			@RequestMapping(value = "/{idPonencia}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Ponencia> updatePonencia(@PathVariable("idPonencia") int idponencia, @RequestBody @Valid Ponencia ponencia,
				BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (ponencia == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Ponencia>(headers, HttpStatus.BAD_REQUEST);
				}
				Ponencia currentPonencia= this.conferencesService.findPonenciaById(idponencia);
				if (currentPonencia == null) {
					return new ResponseEntity<Ponencia>(HttpStatus.NOT_FOUND);
				}
				currentPonencia.setEvento(ponencia.getEvento());
				currentPonencia.setExpositor(ponencia.getExpositor());
				currentPonencia.setHora_inicio(ponencia.getHora_inicio());
				currentPonencia.setHora_Termino(ponencia.getHora_Termino());
				currentPonencia.setIdPonencia(ponencia.getIdPonencia());
				currentPonencia.setName(ponencia.getName());
				this.conferencesService.savePonencia(currentPonencia);
				
				return new ResponseEntity<Ponencia>(currentPonencia, HttpStatus.NO_CONTENT);
			}
		  
		  @PreAuthorize( "hasRole(@roles.PONENCIA_ADMIN)" )
			@RequestMapping(value = " / {idPonencia}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			@Transactional
			public ResponseEntity<Void> deletePonencia(@PathVariable("idPonencia") int idponencia) {
				Ponencia ponencia = this.conferencesService.findPonenciaById(idponencia);
				if (ponencia == null) {
					return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				}
				this.conferencesService.deletePonencia(ponencia);;
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		  
		  @PreAuthorize( "hasRole(@roles.EVENTO_ADMIN)" )
	        @RequestMapping(value = "/evento/{id_evento}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	        public ResponseEntity<List<Ponencia>> getPonenciaByEvento(@PathVariable("id_evento") int idevento){
	            List<Ponencia> ponencias = this.conferencesService.findAllPonenciaByEvento(idevento);
	            if(ponencias.isEmpty()){
	                return new ResponseEntity<List<Ponencia>>(HttpStatus.NOT_FOUND);
	            }
	            return new ResponseEntity<List<Ponencia>>(ponencias, HttpStatus.OK);
	        }
		  

		  
		  

		
}

