package es.uma.informatica.sii.tarea2.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Valoracion
 *
 */
@Entity

public class Valoracion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idValoracion;
	@Column(nullable = false)
	private Integer valoracionNum;
	@Column(length = 512)
	private String comentario;
	
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Actividad actividad;
	
	

	public Valoracion() {
		super();
	}

	public Long getIdValoracion() {
		return idValoracion;
	}

	public void setIdValoracion(Long idValoracion) {
		this.idValoracion = idValoracion;
	}

	public Integer getValoracionNum() {
		return valoracionNum;
	}

	public void setValoracionNum(Integer valoracionNum) {
		this.valoracionNum = valoracionNum;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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
		result = prime * result + ((idValoracion == null) ? 0 : idValoracion.hashCode());
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
		Valoracion other = (Valoracion) obj;
		if (idValoracion == null) {
			if (other.idValoracion != null)
				return false;
		} else if (!idValoracion.equals(other.idValoracion))
			return false;
		return true;
	}
	
	

}