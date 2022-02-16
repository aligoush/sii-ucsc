package com.ucsc.entities;

import java.util.List;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@Column(length = 10)
	private String dni;
	@Column(nullable = false, length = 20)
	private String nombre;
	@Column(nullable = false, length = 20)
	private String apellido1;
	@Column(length = 20)
	private String apellido2;
	@Column(nullable = false, length = 50)
	private String email;
	@Column(nullable = false, length = 50)
	private String direccion;
	@Column(nullable = false, length = 15)
	private String telefono;
	@Column(nullable = false, length = 512)
	private String imagen;
	private Integer distanciaMax;
	@Column(nullable = false, length = 15)
	private String disponibilidad;
	private Integer cursoActual;
	@Column(nullable = false, length = 30)
	private String tipoUsuario;
	@Column(nullable = false, length = 20)
	private String contrasena;
	
	//Matriculas del usuario
	@OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	Set<Matriculacion> matriculas;
	
	//Notificaciones del usuario
	@OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	List<Notificacion> notificaciones;
	
	//Solicitudes del usuario
	@OneToMany(mappedBy="usuario",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	List<Solicitud> solicitudes;
	
	//Valoraciones del usuario a una actividad
	@OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	List<Valoracion> valoraciones;
	
	//Para alumno(tipo=alumno) sus evaluaciones de las distintas actividades a las que participe
	@OneToMany(mappedBy = "alumno_evaluado",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	List<Evaluacion> evaluaciones;
	
	//Para profesor las evaluaciones que ha evaluado
	@OneToMany(mappedBy = "profesor_evaluador",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	List<Evaluacion> evaluadas;
	
	//Actividades que un profesor coordina
	@ManyToMany
	@JoinTable(
			name = "Coordinar",
			joinColumns = @JoinColumn(name="usuario_id"),
			inverseJoinColumns = @JoinColumn(name="actividad_id")
			)
	private List<Actividad> actividades_coordinadas;
	
	@OneToMany(mappedBy = "organizacion",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Proyecto> proyectos;
	
	

	public Usuario() {
		super();
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido1() {
		return apellido1;
	}



	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}



	public String getApellido2() {
		return apellido2;
	}



	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getImagen() {
		return imagen;
	}



	public void setImagen(String imagen) {
		this.imagen = imagen;
	}



	public Integer getDistanciaMax() {
		return distanciaMax;
	}



	public void setDistanciaMax(Integer distanciaMax) {
		this.distanciaMax = distanciaMax;
	}



	public String getDisponibilidad() {
		return disponibilidad;
	}



	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}



	public Integer getCursoActual() {
		return cursoActual;
	}



	public void setCursoActual(Integer cursoActual) {
		this.cursoActual = cursoActual;
	}



	public String getTipoUsuario() {
		return tipoUsuario;
	}



	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}



	public String getContrasena() {
		return contrasena;
	}



	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}



	public Set<Matriculacion> getMatriculas() {
		return matriculas;
	}



	public void setMatriculas(Set<Matriculacion> matriculas) {
		this.matriculas = matriculas;
	}



	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}



	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}



	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}



	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
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



	public List<Evaluacion> getEvaluadas() {
		return evaluadas;
	}



	public void setEvaluadas(List<Evaluacion> evaluadas) {
		this.evaluadas = evaluadas;
	}



	public List<Actividad> getActividades_coordinadas() {
		return actividades_coordinadas;
	}



	public void setActividades_coordinadas(List<Actividad> actividades_coordinadas) {
		this.actividades_coordinadas = actividades_coordinadas;
	}



	public List<Proyecto> getProyectos() {
		return proyectos;
	}



	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Usuario other = (Usuario) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	
	
	
}
