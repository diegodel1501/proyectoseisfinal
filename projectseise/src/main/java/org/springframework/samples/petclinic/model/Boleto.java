package org.springframework.samples.petclinic.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="boleto")
public class Boleto implements Serializable   {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="idBoleto")
	@NotEmpty
	private int idBoleto;
	@Column(name="total")
	@NotEmpty
	private int total;
	@Column(name="detalle")
	@NotEmpty
	private String Detalle;
	@Column(name="name")
	@NotEmpty
	private String name;
	@Column(name="fecha")
	@NotEmpty
	private Date fecha;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		Detalle = detalle;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

	public int getIdBoleto() {
		return idBoleto;
	}
	public void setIdBoleto(int idBoleto) {
		this.idBoleto = idBoleto;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_invitado")
		private Invitado invitado;

	public Invitado getInvitado() {
		return invitado;
	}

	public void setInvitado(Invitado invitado) {
		this.invitado = invitado;
	}
	@JsonIgnore
    public boolean isNew() {
        return this.idBoleto == 0;
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
	
    @Override
    public String toString(){
    	return new ToStringCreator(this)
    			.append("id boleto", this.getIdBoleto())
                .append("Total", this.getTotal())
                .append("detalle", this.getDetalle())
                .append("Total", this.getFecha())
                .append("Total", this.getName())
                .append("invitado",this.getEvento().getId())
                .append("invitado",this.getInvitado().getId())

                .toString();
    	
    }
	

   
	
	
	
}
