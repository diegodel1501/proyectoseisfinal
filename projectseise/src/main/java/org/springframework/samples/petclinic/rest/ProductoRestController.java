package org.springframework.samples.petclinic.rest;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Producto;
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
@RequestMapping ( " / api / producto " )

public class ProductoRestController {
	@Autowired
	private ConferencesService conferencesService;
	
		@PreAuthorize( "hasRole(@roles.PRODUCTO_ADMIN)" )
		@RequestMapping(value = " / {id_producto}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public  ResponseEntity<Producto> getproducto(@PathVariable("id_producto") int idproducto) {
			Producto producto = null;
			producto = this.conferencesService.findProductoById(idproducto);
			if (producto == null) {
				return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		}
			
	    @PreAuthorize( "hasRole(@roles.PRODUCTO_ADMIN)" )
		@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Collection<Producto>> getAllproductos() {
			Collection<Producto> productos = this.conferencesService.findAllProductos();
			if (productos.isEmpty()) {
				return new ResponseEntity<Collection<Producto>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Collection<Producto>>(productos, HttpStatus.OK);
		}
		
		
		
		  @PreAuthorize( "hasRole(@roles.PRODUCTO_ADMIN)" )
			@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Producto> addproducto(@RequestBody @Valid Producto producto, BindingResult bindingResult,
					UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (producto == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Producto>(headers, HttpStatus.BAD_REQUEST);
				}
				this.conferencesService.saveProducto(producto);;
				headers.setLocation(ucBuilder.path("/api/producto/{id}").buildAndExpand(producto.getId_producto()).toUri());
				return new ResponseEntity<Producto>(producto, headers, HttpStatus.CREATED);
				
		  }	
		  @PreAuthorize( "hasRole(@roles.PRODUCTO_ADMIN)" )
			@RequestMapping(value = "/{id_producto}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Producto> updateproducto(@PathVariable("id_producto") int idproducto, @RequestBody @Valid Producto producto,
				BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
				BindingErrorsResponse errors = new BindingErrorsResponse();
				HttpHeaders headers = new HttpHeaders();
				if (bindingResult.hasErrors() || (producto == null)) {
					errors.addAllErrors(bindingResult);
					headers.add("errors", errors.toJSON());
					return new ResponseEntity<Producto>(headers, HttpStatus.BAD_REQUEST);
				}
				Producto currentproducto= this.conferencesService.findProductoById(idproducto);
				if (currentproducto == null) {
					return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
				}
				currentproducto.setCatalogo(producto.getCatalogo());
				currentproducto.setId_producto(producto.getId_producto());
				currentproducto.setName(producto.getName());
				currentproducto.setPrecio_Compra(producto.getPrecio_Compra());
				currentproducto.setPrecio_venta(producto.getPrecio_venta());
				this.conferencesService.saveProducto(currentproducto);
				
				return new ResponseEntity<Producto>(currentproducto, HttpStatus.NO_CONTENT);
			}
		  
		  @PreAuthorize( "hasRole(@roles.PRODUCTO_ADMIN)" )
			@RequestMapping(value = " / {id_producto}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			@Transactional
			public ResponseEntity<Void> deleteproducto(@PathVariable("id_producto") int idproducto) {
			  Producto producto = this.conferencesService.findProductoById(idproducto);
				if (producto == null) {
					return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				}
				this.conferencesService.deleteProducto(producto);;
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		  

		  
		  

		
}


