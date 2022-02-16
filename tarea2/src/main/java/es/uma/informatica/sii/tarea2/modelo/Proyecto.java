package es.uma.informatica.sii.tarea2.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Proyecto
 *
 */
@Entity

public class Proyecto implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProyecto;
    @Column(nullable = false, length = 50)
	private String nombre;
    @Column(length = 512)
	private String descripcion;
	private String imagen;
	
	// Actividades asociadas
	@OneToMany(mappedBy = "proyecto",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Actividad> actividades;
	
	// Organizacion que creo el proyecto
	@ManyToOne
	private Usuario organizacion;
	
	public Proyecto() {
		super();
	}   
	public Integer getIdProyecto() {
		return this.idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}   
	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public List<Actividad> getActividades() {
		return actividades;
	}
	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
	public Usuario getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Usuario organizacion) {
		this.organizacion = organizacion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProyecto == null) ? 0 : idProyecto.hashCode());
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
		Proyecto other = (Proyecto) obj;
		if (idProyecto == null) {
			if (other.idProyecto != null)
				return false;
		} else if (!idProyecto.equals(other.idProyecto))
			return false;
		return true;
	}
	
	
}
