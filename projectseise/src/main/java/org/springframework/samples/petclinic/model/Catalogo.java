package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="catalogo")
public class Catalogo {
	@Id
	@Column(name="ID_Catalogo")
	@NotEmpty
	private int id_catalogo;
	
	@Column(name="cantidad de Productos")
	@NotEmpty
	private int cantidad_productos;
	
	@Column(name="Nombre_catalogo")
	@NotEmpty
	private String nombre_catalogo;
	
	@OneToMany(mappedBy = "catalogo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Producto> productos;

	public int getId_catalogo() {
		return id_catalogo;
	}

	public void setId_catalogo(int id_catalogo) {
		this.id_catalogo = id_catalogo;
	}

	public int getCantidad_productos() {
		return cantidad_productos;
	}

	public void setCantidad_productos(int cantidad_productos) {
		this.cantidad_productos = cantidad_productos;
	}

	public String getNombre_catalogo() {
		return nombre_catalogo;
	}

	public void setNombre_catalogo(String nombre_catalogo) {
		this.nombre_catalogo = nombre_catalogo;
	}
	
	@JsonIgnore
	protected Set<Producto> getProductosInternal() {
        if (this.productos == null) {
            this.productos = new HashSet<>();
        }
        return this.productos;
    }
 protected void setProductosInternal(Set<Producto> productos) {
        this.productos = productos;
    }

  public List<Producto> getProductos() {
        List<Producto> sortedProducto = new ArrayList<>(getProductosInternal());
        PropertyComparator.sort(sortedProducto, new MutableSortDefinition("id_producto", true, true));
        return Collections.unmodifiableList(sortedProducto);
    }
  
  public void addProducto(Producto producto) {
    	getProductos().add(producto);
        producto.setCatalogo(this);
    }
    


    public Producto getProductos(int id_producto) {
        return getProductos(id_producto, false);
    }

  
    public Producto getProductos(int id_producto, boolean ignoreNew) {
        for (Producto producto: getProductosInternal()) {
            if (!ignoreNew || !producto.isNew()) {
                int compId = producto.getId_producto();
            
                if (compId==id_producto) {
                    return producto;
                }
            }
        }
        return null;
    }
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_evento")
		private Evento evento;

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
    
	public boolean isNew() {
        return this.id_catalogo == 0;
    }
    @Override
    public String toString(){
    	return new ToStringCreator(this)
    			.append("id catalogo", this.getId_catalogo())
                .append("Cantidad de productos", this.getCantidad_productos())
                .append("nombre", this.getNombre_catalogo())
                .append("id evento", this.getEvento().getId())

                .toString();
    	
    }
	
	
	
	
	
	
}
