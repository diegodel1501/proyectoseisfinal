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
@Table(name="invitado")

public class Invitado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id_Invitado")
	@NotEmpty
	private int id_invitado;
	
	@Column(name = "name")
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

	@OneToMany(mappedBy = "invitado", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Boleto> boletos;
	
	public int getId() {
        return id_invitado;
    }

    public void setId(int id_invitado) {
        this.id_invitado = id_invitado;
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
	protected Set<Boleto> getBoletosInternal() {
	        if (this.boletos == null) {
	            this.boletos = new HashSet<>();
	        }
	        return this.boletos;
	    }
	 protected void setBoletosInternal(Set<Boleto> boletos) {
	        this.boletos = boletos;
	    }
 
	  public List<Boleto> getBoletos() {
	        List<Boleto> sortedBoleto = new ArrayList<>(getBoletosInternal());
	        PropertyComparator.sort(sortedBoleto, new MutableSortDefinition("idBoleto", true, true));
	        return Collections.unmodifiableList(sortedBoleto);
	    }
	  
	  public void addBoleto(Boleto boleto) {
	    	getBoletos().add(boleto);
	        boleto.setInvitado(this);
	    }
	    

	
	    public Boleto getBoletos(int idBoleto) {
	        return getBoletos(idBoleto, false);
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
	    
	    @Override
	    public String toString(){
	    	return new ToStringCreator(this)
	    			.append("id invitado", this.getId())
	                .append("Nombre", this.getName())
	                .append("Email", this.getEmail())
	                .append("sexo", this.getSexo())
	                .append("telefono", this.getFono())
	                .toString();
	    	
	    }
	    
	   
		
	    

}
