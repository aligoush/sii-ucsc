package es.uma.informatica.sii.tarea2.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.tarea2.modelo.Actividad;
import es.uma.informatica.sii.tarea2.modelo.Evaluacion;
import es.uma.informatica.sii.tarea2.modelo.Usuario;
import es.uma.informatica.sii.tarea2.servicio.EvaluacionService;

@Named("dtEvaluacionView")
@ApplicationScoped
public class EvaluacionController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	//Variables

	private List<Evaluacion> evaluaciones;
	
	private String id;
	

	private Actividad actividadParaEval;
	
	private String idRecogidaAct;
	

	private List<Usuario> alumnos;
	
	private Long userEvaluado;
	
	private Evaluacion evalEditable;
	
	private List<Actividad> actividades;
	
	private int nota;
	@Inject
	private EvaluacionService service;
	//Habría que inyectar aqui el servicio de actividades
	private Evaluacion verEval;
	
	
	
	//PostConstruct
	@PostConstruct
	public void init() {
		//findAll
		evaluaciones= service.createEvaluacion();
		actividades = service.getActividades();
		setListAlumnos();
	}
	public void setListAlumnos() {
		alumnos = service.getAlumnos();
	}
	
	
	
	//Postload
	public void postLoad() {
		Integer ident = null;
		try {
			ident = Integer.parseInt(id);
		}catch (Exception e) {

			verEval= evaluaciones.get(0);
			verEval.setNota(-1);
			return;
		}
		//findById
		if(ident!=null && ident>=0) {
			verEval=evaluaciones.get(ident);
		}else {
		}
	}
	
	public void postLoadEdit() {
		Integer ident = null;
		try {
			ident = Integer.parseInt(id);
		}catch (Exception e) {
		return;
		
		}
		evalEditable=service.createEvaluacion().get(ident);
		
	}

	public void postLoadEliminar() {
		postLoad();
	}

	public String doCrearEval() {
		idRecogidaAct=null;
		return "verEvaluaciones.xhtml";
	}
	
	//Post
	public String doActualizarEval() {
		return "verEvaluaciones.xhtml";
	}
	public String doSetAsig() {
		return "evaluar.xhtml";
	}
	

	//Getters Setters
	public List<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}

	public void setService(EvaluacionService service) {
		this.service = service;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Evaluacion getVerEval() {
		return verEval;
	}

	public void setVerEval(Evaluacion verEval) {
		this.verEval = verEval;
	}



	public Actividad getActividadParaEval() {
		return actividadParaEval;
	}

	public void setActividadParaEval(Actividad actividadParaEval) {
		this.actividadParaEval = actividadParaEval;
	}

	public String getIdRecogidaAct() {
		return idRecogidaAct;
	}

	public void setIdRecogidaAct(String idRecogidaAct) {
		this.idRecogidaAct = idRecogidaAct;
	}


	

	public List<Usuario> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(List<Usuario> alumnos) {
		this.alumnos = alumnos;
	}
	public Long getUserEvaluado() {
		return userEvaluado;
	}

	public void setUserEvaluado(Long userEvaluado) {
		this.userEvaluado = userEvaluado;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Evaluacion getEvalEditable() {
		return evalEditable;
	}

	public void setEvalEditable(Evaluacion evalEditable) {
		this.evalEditable = evalEditable;
	}
	

	public void postLoadDelete() {
		postLoad();
		
	}
	
	public String okBorrar() {
		return "verEvaluaciones.xhtml";
	}
	public List<Actividad> getActividades() {
		return actividades;
	}
	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
	
	

	
	
	
	
	
	
	
	
	
	
}
