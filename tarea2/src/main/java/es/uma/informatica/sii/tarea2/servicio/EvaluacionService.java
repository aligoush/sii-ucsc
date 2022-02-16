package es.uma.informatica.sii.tarea2.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import javax.enterprise.context.ApplicationScoped;

import es.uma.informatica.sii.tarea2.modelo.Actividad;
import es.uma.informatica.sii.tarea2.modelo.Evaluacion;
import es.uma.informatica.sii.tarea2.modelo.Usuario;

@ApplicationScoped
public class EvaluacionService {
	private final static String[] nombres;
	private final static Random rand = new Random();
	
	private List<Evaluacion> evaluaciones =null;
	
	private List<Actividad> actividades = null;
	
	private List<Usuario> alumnos = null;
	
	private boolean cargadoRandom=false;
	static {
		nombres = new String[10];
		nombres[0] = "Jaime Francisco";
		nombres[1] = "Andrea";
		nombres[2] = "Ruth Silvana";
		nombres[3] = "Ariana";
		nombres[4] = "Luis Felipe";
		nombres[5] = "Hansel Andres";
		nombres[6] = "Aniyensy Sarai";
		nombres[7] = "Karla Paulette";
		nombres[8] = "Montserrat Carolina";
		nombres[9] = "Lisset Vianey";
	}
	
	public List<Evaluacion> createEvaluacion(){
		if(cargadoRandom) {
			return evaluaciones;
		}
		List<Evaluacion> salida = new ArrayList<Evaluacion>();
		Evaluacion aux;
		Usuario profesor;
		Usuario alumno;
		Actividad actividad;
		alumnos = new ArrayList<Usuario>();
		actividades = new ArrayList<Actividad>();
		for(int i =0; i<100;i++) {
			aux= new Evaluacion();
			profesor= new Usuario();
			alumno= new Usuario();
			actividad = new Actividad();
			aux.setId((long) i);
			actividad.setIdActividad(i);
			profesor.setNombre(nombres[rand.nextInt(10)]);
			alumno.setNombre(nombres[rand.nextInt(10)]);
			actividad.setNombre("Voluntariado "+i);
			alumno.setId((long) i);
			aux.setAlumno_evaluado(alumno);
			aux.setProfesor_evaluador(profesor);
			aux.setActividad(actividad);
			aux.setNota(rand.nextInt(11));
			actividades.add(actividad);
			alumnos.add(alumno);
			salida.add(aux);
		}
		
		evaluaciones=salida;
		cargadoRandom=true;
		return salida;
	}
	
	public void addEvaluacion(Evaluacion evaluacion,int i) {
		if(i<0)
			evaluaciones.add(evaluacion);
		else
			evaluaciones.set(i, evaluacion);
	}
	
	public void editEvaluacion(Evaluacion evaluacion,int i) {
		addEvaluacion(evaluacion, i);
	}
	public void deleteEvaluacion(int i) {
		evaluaciones.remove(i);
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public List<Usuario> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Usuario> alumnos) {
		this.alumnos = alumnos;
	}
	

}
