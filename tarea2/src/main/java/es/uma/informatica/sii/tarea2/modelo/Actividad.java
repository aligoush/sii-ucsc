package es.uma.informatica.sii.tarea2.modelo;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Actividad
 *
 */
@Entity

public class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;
	
        @Id 
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idActividad;
        @Column(nullable = false, length = 50)
        private String nombre;
        @Column(nullable = false, length = 512)
        private String descripcion;
        @Column(nullable = false)
        private Integer numPersonasRequeridas;
        @Column(nullable = false)
        private Boolean validacion;
        @Column(length = 50)
        private String tipo;
        private String imagen;
        private Integer numHorasRequeridas;
        @Temporal(TemporalType.DATE)
        private Date fechaInicio;
        @Temporal(TemporalType.DATE)
        private Date fechaFin;
        @Column(nullable = false, length = 100)
        private String ubicacion;
	
	//Solicitudes de la actividad
	@OneToMany(mappedBy = "actividad",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Solicitud> solicitudes;
	//Proyecto al que esta asociada
	@ManyToOne
	private Proyecto proyecto;
	
	//Valoraciones de usuario
	@OneToMany(mappedBy = "actividad",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Valoracion> valoraciones;
	
	//Evaluaciones de los alumnos participantes
	@OneToMany(mappedBy = "actividad",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Evaluacion> evaluaciones;
	
	//Profesores coordinadores de la actividad
	@ManyToMany(mappedBy = "actividades_coordinadas")
	private List<Usuario> coordinadores;
	
	

	public Actividad() {
		super();
	}

	public Integer getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Integer idActividad) {
		this.idActividad = idActividad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getNumPersonasRequeridas() {
		return numPersonasRequeridas;
	}

	public void setNumPersonasRequeridas(Integer numPersonasRequeridas) {
		this.numPersonasRequeridas = numPersonasRequeridas;
	}

	public Boolean getValidacion() {
		return validacion;
	}

	public void setValidacion(Boolean validacion) {
		this.validacion = validacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Integer getNumHorasRequeridas() {
		return numHorasRequeridas;
	}

	public void setNumHorasRequeridas(Integer numHorasRequeridas) {
		this.numHorasRequeridas = numHorasRequeridas;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
		
	}
	
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public List<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}

	public void setEvaluaciones(List<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}

	public List<Usuario> getCoordinadores() {
		return coordinadores;
	}

	public void setCoordinadores(List<Usuario> coordinadores) {
		this.coordinadores = coordinadores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idActividad == null) ? 0 : idActividad.hashCode());
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
		Actividad other = (Actividad) obj;
		if (idActividad == null) {
			if (other.idActividad != null)
				return false;
		} else if (!idActividad.equals(other.idActividad))
			return false;
		return true;
	}


	
	
	
	
	
	
	
}
