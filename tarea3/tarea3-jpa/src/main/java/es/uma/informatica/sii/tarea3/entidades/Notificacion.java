package es.uma.informatica.sii.tarea3.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Notificacion
 *
 */
@Entity

public class Notificacion implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNotificacion;
    @Column(nullable = false, length = 50)
	private String titulo;
    @Column(nullable = false, length = 512)
	private String descripcion;
	
	//Usuario asociado a la notificacion
	@ManyToOne
	private Usuario usuario;

	public Notificacion() {
		super();
	}   

	public Long getIdNotificacion() {
		return this.idNotificacion;
	}

	public void setId(Long idNotificacion) {

		this.idNotificacion = idNotificacion;
	}   
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}   
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setIdNotificacion(Long idNotificacion) {
		this.idNotificacion = idNotificacion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idNotificacion == null) ? 0 : idNotificacion.hashCode());
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
		Notificacion other = (Notificacion) obj;
		if (idNotificacion == null) {
			if (other.idNotificacion != null)
				return false;
		} else if (!idNotificacion.equals(other.idNotificacion))
			return false;
		return true;
	}
	
	
}
