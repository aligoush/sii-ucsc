package com.ucsc.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Matriculacion
 *
 */
@Entity

public class Matriculacion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JoinColumn
	private Usuario usuario;
	@Id
	@ManyToOne
	@JoinColumn
	private Titulacion titulacion;
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	
	
	
	public Matriculacion() {
		super();
	}   

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Titulacion getTitulacion() {
		return titulacion;
	}
	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}
	
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulacion == null) ? 0 : titulacion.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matriculacion other = (Matriculacion) obj;
		if (titulacion == null) {
			if (other.titulacion != null)
				return false;
		} else if (!titulacion.equals(other.titulacion))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	
}
