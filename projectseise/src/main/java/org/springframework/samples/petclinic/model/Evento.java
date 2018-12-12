package org.springframework.samples.petclinic.model;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="evento")
public class Evento implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_evento")
	@NotEmpty
	private int id_evento;
	
	@Column(name="Nombre")
	@NotEmpty
	private String name;
	
	@Column(name="Fecha")
	@NotEmpty
	private Date fecha;
	
	@OneToMany(mappedBy = "evento", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Boleto> boletos;

	public int getId() {
		return id_evento;
	}

	public void setId(int id_evento) {
		this.id_evento = id_evento;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@JsonIgnore
	
	protected Set<Boleto> getBoletosInternal() {
        if (this.boletos == null) {
            this.boletos = new HashSet<>();
        }
        return this.boletos;
    }
	protected void setBoletosInternal(Set<Boleto> boletos) {
        this.boletos = boletos;
    }
	
	  public void addBoleto(Boleto boleto) {
	    	getBoletos().add(boleto);
	        boleto.setEvento(this);
	  }
	  public Boleto getBoletos(int idBoleto) {
	        return getBoletos(idBoleto, false);
	  }
	    

	public void setBoletos(Set<Boleto> boletos) {
		this.boletos = boletos;
	}
	
	  public List<Boleto> getBoletos() {
	        List<Boleto> sortedBoleto = new ArrayList<>(getBoletosInternal());
	        PropertyComparator.sort(sortedBoleto, new MutableSortDefinition("idBoleto", true, true));
	        return Collections.unmodifiableList(sortedBoleto);
	    }
	  
	  
	    public Boleto getBoletos(int idBoleto, boolean ignoreNew) {
	        for (Boleto boleto: getBoletosInternal()) {
	            if (!ignoreNew || !boleto.isNew()) {
	                int compId = boleto.getIdBoleto();
	            
	                if (compId==idBoleto) {
	                    return boleto;
	                }
	            }
	        }
	        return null;
	    }
	    
	    
	    @OneToMany(mappedBy = "evento", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		@JsonIgnore
		private Set<Catalogo> catalogos;
	    
	    @JsonIgnore
		protected Set<Catalogo> getCatalogosInternal() {
		        if (this.catalogos == null) {
		            this.catalogos = new HashSet<>();
		        }
		        return this.catalogos;
		    }
		 protected void setCatalogosInternal(Set<Catalogo> catalogos) {
		        this.catalogos = catalogos;
		    }
	 
		  public List<Catalogo> getCatalogos() {
		        List<Catalogo> sortedCatalogo= new ArrayList<>(getCatalogosInternal());
		        PropertyComparator.sort(sortedCatalogo, new MutableSortDefinition("id_catalogo", true, true));
		        return Collections.unmodifiableList(sortedCatalogo);
		    }
		  
		  public void addCatalogo(Catalogo catalogo) {
		    	getCatalogos().add(catalogo);
		        catalogo.setEvento(this);
		    }
		    

		
		    public Catalogo getCatalogos(int id_catalogo) {
		        return getCatalogos(id_catalogo, false);
		    }

		  
		    public Catalogo getCatalogos(int id_catalogo, boolean ignoreNew) {
		        for (Catalogo catalogo: getCatalogosInternal()) {
		            if (!ignoreNew || !catalogo.isNew()) {
		                int compId = catalogo.getId_catalogo();
		            
		                if (compId==id_catalogo) {
		                    return catalogo;
		                }
		            }
		        }
		        return null;
		    }
		    
		    @OneToMany(mappedBy = "evento", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
			@JsonIgnore
			private Set<Ponencia> ponencias;
		    @JsonIgnore
			protected Set<Ponencia> getPonenciasInternal() {
			        if (this.ponencias == null) {
			            this.ponencias = new HashSet<>();
			        }
			        return this.ponencias;
			    }
			 protected void setponenciasInternal(Set<Ponencia> ponencias) {
			        this.ponencias = ponencias;
			    }
		 
			  public List<Ponencia> getponencias() {
			        List<Ponencia> sortedPonencia = new ArrayList<>(getPonenciasInternal());
			        PropertyComparator.sort(sortedPonencia, new MutableSortDefinition("idPonencia", true, true));
			        return Collections.unmodifiableList(sortedPonencia);
			    }
			  
			  public void addPonencia(Ponencia Ponencia) {
			    	getponencias().add(Ponencia);
			        Ponencia.setEvento(this);
			    }
			    

			
			    public Ponencia getponencias(int idPonencia) {
			        return getponencias(idPonencia, false);
			    }

			  
			    public Ponencia getponencias(int idPonencia, boolean ignoreNew) {
			        for (Ponencia Ponencia: getPonenciasInternal()) {
			            if (!ignoreNew || !Ponencia.isNew()) {
			                int compId = Ponencia.getIdPonencia();
			            
			                if (compId==idPonencia) {
			                    return Ponencia;
			                }
			            }
			        }
			        return null;
			    }
			    
			    public String toString(){
			    	return new ToStringCreator(this)
			    		
			                .append("fecha", this.getFecha())
			                .append("nombre", this.getName())
			                .append("id evento", this.getId())

			                .toString();

			    } 
		    
		    
			    
	
	
	
	

}
