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
import org.springframework.samples.petclinic.model.Boleto;
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
@RequestMapping ( " / api / boleto " )
public class BoletoRestController {
	@Autowired
	private ConferencesService conferencesService;
	
		@PreAuthorize( "hasRole(@roles.BOLETO_ADMIN)" )
		@RequestMapping(value = " / {idBoleto}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public  ResponseEntity<Boleto> getBoleto(@PathVariable("idBoleto") int idboleto) {
			Boleto boleto = null;
			boleto = this.conferencesService.findBoletoById(idboleto);
			if (boleto == null) {
				return new ResponseEntity<Boleto>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Boleto>(boleto, HttpStatus.OK);
		}
			
	    @PreAuthorize( "hasRole(@roles.BOLETO_ADMIN)" )
		@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Collection<Boleto>> getAllBoletos() {
			Collection<Boleto> boletos = this.conferencesService.findAllBoletos();
			if (boletos.isEmpty()) {
				return new ResponseEntity<Collection<Boleto>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Collection<Boleto>>(boletos, HttpStatus.OK);
		}
		
		
		
		  @PreAuthorize( "hasRole(@roles.BOLETO_ADMIN)" )
			@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Boleto> addBoleto(@RequestBody @Valid Boleto boleto, BindingResult bindingResult,
					UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (boleto == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Boleto>(headers, HttpStatus.BAD_REQUEST);
				}
				this.conferencesService.saveBoleto(boleto);;
				headers.setLocation(ucBuilder.path("/api/boleto/{id}").buildAndExpand(boleto.getIdBoleto()).toUri());
				return new ResponseEntity<Boleto>(boleto, headers, HttpStatus.CREATED);
				
		  }	
		  @PreAuthorize( "hasRole(@roles.BOLETO_ADMIN)" )
			@RequestMapping(value = "/{idBoleto}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Boleto> updateBoleto(@PathVariable("idBoleto") int idboleto, @RequestBody @Valid Boleto boleto,
				BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (boleto == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Boleto>(headers, HttpStatus.BAD_REQUEST);
				}
				Boleto currentBoleto= this.conferencesService.findBoletoById(idboleto);
				if (currentBoleto == null) {
					return new ResponseEntity<Boleto>(HttpStatus.NOT_FOUND);
				}
				currentBoleto.setDetalle(boleto.getDetalle());
				currentBoleto.setEvento(boleto.getEvento());
				currentBoleto.setFecha(boleto.getFecha());
				currentBoleto.setIdBoleto(boleto.getIdBoleto());
				currentBoleto.setInvitado(boleto.getInvitado());
				currentBoleto.setName(boleto.getName());
				currentBoleto.setTotal(boleto.getTotal());
			
				this.conferencesService.saveBoleto(currentBoleto);
				
				return new ResponseEntity<Boleto>(currentBoleto, HttpStatus.NO_CONTENT);
			}
		  
		  @PreAuthorize( "hasRole(@roles.BOLETO_ADMIN)" )
			@RequestMapping(value = " / {idBoleto}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			@Transactional
			public ResponseEntity<Void> deleteBoleto(@PathVariable("idBoleto") int idboleto) {
				Boleto boleto = this.conferencesService.findBoletoById(idboleto);
				if (boleto == null) {
					return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				}
				this.conferencesService.deleteBoleto(boleto);;
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		  @PreAuthorize( "hasRole(@roles.INVITADO_ADMIN)" )
	        @RequestMapping(value = "/invitado/{id_Invitado}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	        public ResponseEntity<List<Boleto>> getBoletoByInvitado(@PathVariable("id_Invitado") int idinvitado){
	            List<Boleto> boletos = this.conferencesService.findAllBoletosByInvitado(idinvitado);
	            if(boletos.isEmpty()){
	                return new ResponseEntity<List<Boleto>>(HttpStatus.NOT_FOUND);
	            }
	            return new ResponseEntity<List<Boleto>>(boletos, HttpStatus.OK);
	        }
		  
		  @PreAuthorize( "hasRole(@roles.EVENTO_ADMIN)" )
	        @RequestMapping(value = "/evento/{id_evento}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	        public ResponseEntity<List<Boleto>> getBoletoByevento(@PathVariable("id_evento") int idEvento){
	            List<Boleto> boletos = this.conferencesService.findAllBoletosByEvento(idEvento);
	            if(boletos.isEmpty()){
	                return new ResponseEntity<List<Boleto>>(HttpStatus.NOT_FOUND);
	            }
	            return new ResponseEntity<List<Boleto>>(boletos, HttpStatus.OK);
	        }
		  

		  

}
