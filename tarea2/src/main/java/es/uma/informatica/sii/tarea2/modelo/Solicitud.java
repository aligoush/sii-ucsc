package es.uma.informatica.sii.tarea2.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Solicitud
 *
 */
@Entity

public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSolicitud;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
    @Column(nullable = false, length = 50)
	private String estadoSolicitud;
	
	//Usuario que pide la solicitud
	@ManyToOne
	private Usuario usuario;
	
	//Actividad asociada
	@ManyToOne
	private Actividad actividad;

	
	public Solicitud() {
		super();
	}   
	public Integer getIdSolicitud() {
		return this.idSolicitud;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
	}   
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}   
	public String getEstadoSolicitud() {
		return this.estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Actividad getActividad() {
		return actividad;
	}
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSolicitud == null) ? 0 : idSolicitud.hashCode());
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
		Solicitud other = (Solicitud) obj;
		if (idSolicitud == null) {
			if (other.idSolicitud != null)
				return false;
		} else if (!idSolicitud.equals(other.idSolicitud))
			return false;
		return true;
	}
	
	
}
