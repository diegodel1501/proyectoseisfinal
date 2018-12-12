package org.springframework.samples.petclinic.model;

import java.io.Serializable;
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
@Table(name="expositor")
public class Expositor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id_Expositor")
	@NotEmpty
	private int id_expositor;
	
	@Column(name = "Nombre")
    @NotEmpty
    private String name;

    @Column(name = "email")
    @NotEmpty
    private String email;
	
	@Column(name = "sexo")
    @NotEmpty
    private char sexo;
	
	@Column(name = "fono")
    @NotEmpty
    private int fono;

	@OneToMany(mappedBy = "expositor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Ponencia> ponencias;
	
	public int getId() {
        return id_expositor;
    }

    public void setId(int id_invitado) {
        this.id_expositor = id_invitado;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
	public char getSexo(){
		return this.sexo;
	}
	public void setSexo(char sexo){
		this.sexo=sexo;
	}
	public int getFono(){
		return this.fono;
	}
	public void setFono(int fono){
		this.fono=fono;
	}
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
	        Ponencia.setExpositor(this);
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
	    
	    @Override
	    public String toString(){
	    	return new ToStringCreator(this)
	    			.append("id Expositor", this.getId())
	                .append("Nombre", this.getName())
	                .append("Email", this.getEmail())
	                .append("sexo", this.getSexo())
	                .append("telefono", this.getFono())
	                .toString();
	    	
	    }
	    
	
	
}
