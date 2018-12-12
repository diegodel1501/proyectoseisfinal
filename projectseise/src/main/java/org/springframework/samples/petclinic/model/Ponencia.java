package org.springframework.samples.petclinic.model;

import java.io.Serializable;
import java.sql.Time;

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
@Table(name="ponencia")
public class Ponencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idPonencia")
	@NotEmpty
	private int idPonencia;
	
	@Column(name="Nombre")
	@NotEmpty
	private String name;
	
	@Column(name="Hora_Inicio")
	@NotEmpty
	private Time hora_inicio;
	
	@Column(name="Hora_Termino")
	@NotEmpty
	private Time hora_Termino;
	
	 @ManyToOne(optional = false, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_evento")
	 private Evento evento;
	 @ManyToOne(optional = false , fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_expositor")
	 private Expositor expositor;
	 
	
	public int getIdPonencia() {
		return idPonencia;
	}

	public void setIdPonencia(int idPonencia) {
		this.idPonencia = idPonencia;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public Time getHora_Termino() {
		return hora_Termino;
	}

	public void setHora_Termino(Time hora_Termino) {
		this.hora_Termino = hora_Termino;
	}
	
	@JsonIgnore
	 public Evento getEvento() {
			return evento;
		}

		public void setEvento(Evento evento) {
			this.evento = evento;
		}
		
	    public boolean isNew() {
	        return this.idPonencia == 0;
	    }
	    
		@JsonIgnore

		public Expositor getExpositor() {
			return expositor;
		}

		public void setExpositor(Expositor expositor) {
			this.expositor = expositor;
		}
	    public String toString(){
	    	return new ToStringCreator(this)
	    			.append("id ponencia", this.getIdPonencia())
	                .append("Nombre", this.getName())
	                .append("id evento", this.getEvento().getId())
	                .append("id expositor", this.getExpositor().getId())
	                .append("Hora Inicio", this.getHora_inicio())
	                .append("Hora termino", this.getHora_Termino())

	                .toString();
	    	
	    }
		
		
	    


	

}
