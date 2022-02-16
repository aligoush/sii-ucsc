package es.uma.informatica.sii.tarea3.entidades;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Evaluacion
 *
 */
@Entity

public class Evaluacion implements Serializable {
	
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private Usuario alumno_evaluado;
	
	@ManyToOne
	@JoinColumn
	private Actividad actividad;
	
	@ManyToOne
	private Usuario profesor_evaluador;
    @Column(nullable = false)
	private Integer nota;
	private static final long serialVersionUID = 1L;
	
	private Boolean visible = true;

	public Evaluacion() {
		super();
	}   
	public Integer getNota() {
		return this.nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public Usuario getAlumno_evaluado() {
		return alumno_evaluado;
	}
	public void setAlumno_evaluado(Usuario alumno_evaluado) {
		this.alumno_evaluado = alumno_evaluado;
	}
	public Actividad getActividad() {
		return actividad;
	}
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	public Usuario getProfesor_evaluador() {
		return profesor_evaluador;
	}
	public void setProfesor_evaluador(Usuario profesor_evaluador) {
		this.profesor_evaluador = profesor_evaluador;
	}
	
	
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actividad == null) ? 0 : actividad.hashCode());
		result = prime * result + ((alumno_evaluado == null) ? 0 : alumno_evaluado.hashCode());
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
		Evaluacion other = (Evaluacion) obj;
		if (actividad == null) {
			if (other.actividad != null)
				return false;
		} else if (!actividad.equals(other.actividad))
			return false;
		if (alumno_evaluado == null) {
			if (other.alumno_evaluado != null)
				return false;
		} else if (!alumno_evaluado.equals(other.alumno_evaluado))
			return false;
		return true;
	}
	
	
	
	
	
}
