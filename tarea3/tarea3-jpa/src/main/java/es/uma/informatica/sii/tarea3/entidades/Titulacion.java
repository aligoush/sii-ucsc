package es.uma.informatica.sii.tarea3.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Titulacion
 *
 */
@Entity

public class Titulacion implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTitulacion;
	@Column(nullable = false, length = 30)
	private String nombreTitulacion;
	
	@OneToMany(mappedBy = "titulacion",fetch = FetchType.EAGER)
	private List<Matriculacion> matriculas;
	
	private Boolean visible = true;
	
	
	

	public Titulacion() {
		super();
	}   
	public Long getIdTitulacion() {
		return this.idTitulacion;
	}

	public void setIdTitulacion(Long idTitulacion) {
		this.idTitulacion = idTitulacion;
	}   
	public String getNombreTitulacion() {
		return this.nombreTitulacion;
	}

	public void setNombreTitulacion(String nombreTitulacion) {
		this.nombreTitulacion = nombreTitulacion;
	}
	public List<Matriculacion> getMatriculas() {
		return matriculas;
	}
	public void setMatriculas(List<Matriculacion> matriculas) {
		this.matriculas = matriculas;
	}
	
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTitulacion == null) ? 0 : idTitulacion.hashCode());
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
		Titulacion other = (Titulacion) obj;
		if (idTitulacion == null) {
			if (other.idTitulacion != null)
				return false;
		} else if (!idTitulacion.equals(other.idTitulacion))
			return false;
		return true;
	}
	
	
	
}
