package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="producto")
public class Producto {
	@Id
	@Column (name="id_producto")
	@NotEmpty
	private int id_producto;
	
	@Column(name="Nombre")
	@NotEmpty
	private String name;
	
	@Column(name="Precio_Venta")
	@NotEmpty
	private int precio_venta;
	
	@Column(name="Precio_Compra")
	@NotEmpty
	private int precio_Compra;

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(int precio_venta) {
		this.precio_venta = precio_venta;
	}

	public int getPrecio_Compra() {
		return precio_Compra;
	}

	public void setPrecio_Compra(int precio_Compra) {
		this.precio_Compra = precio_Compra;
	}
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_catalogo")
		private Catalogo catalogo;
	

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}
	@JsonIgnore
    public boolean isNew() {
        return this.id_producto == 0;
    }
	 public String toString(){
	    	return new ToStringCreator(this)
	    			.append("id producto", this.getId_producto())
	                .append("Nombre", this.getName())
	                .append("precio compra", this.getPrecio_Compra())
	                .append("precio venta", this.getPrecio_venta())
	                .append("id catalogo", this.getCatalogo().getId_catalogo())

	                .toString();
	    	
	    }
	

	
	
	
}
