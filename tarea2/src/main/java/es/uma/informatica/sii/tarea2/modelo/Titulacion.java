package es.uma.informatica.sii.tarea2.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Set;

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
	private Integer idTitulacion;
	@Column(nullable = false, length = 30)
	private String nombreTitulacion;
	
	@OneToMany(mappedBy = "titulacion",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Matriculacion> matriculas;
	
	
	

	public Titulacion() {
		super();
	}   
	public Integer getIdTitulacion() {
		return this.idTitulacion;
	}

	public void setIdTitulacion(Integer idTitulacion) {
		this.idTitulacion = idTitulacion;
	}   
	public String getNombreTitulacion() {
		return this.nombreTitulacion;
	}

	public void setNombreTitulacion(String nombreTitulacion) {
		this.nombreTitulacion = nombreTitulacion;
	}
	public Set<Matriculacion> getMatriculas() {
		return matriculas;
	}
	public void setMatriculas(Set<Matriculacion> matriculas) {
		this.matriculas = matriculas;
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
