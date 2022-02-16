package es.uma.informatica.sii.tarea3.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.tarea3.autentificacion.ControlAutorizacion;
import es.uma.informatica.sii.tarea3.entidades.Actividad;
import es.uma.informatica.sii.tarea3.entidades.Evaluacion;
import es.uma.informatica.sii.tarea3.entidades.Notificacion;
import es.uma.informatica.sii.tarea3.entidades.Solicitud;
import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.negocio.ActividadesEJB;
import es.uma.informatica.sii.tarea3.negocio.ContraseniaInvalidaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInactivaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInexistenteException;
import es.uma.informatica.sii.tarea3.negocio.NotificacionException;
import es.uma.informatica.sii.tarea3.negocio.NotificacionesEJB;
import es.uma.informatica.sii.tarea3.negocio.UsuariosEJB;
import es.uma.informatica.sii.tarea3.negocio.evaluaciones.EvaluacionEJB;

@Named("dtEvaluacionView")
@ViewScoped
public class EvaluacionController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Variables

	private List<Evaluacion> evaluaciones;

	private String id;

	private Boolean verEvaluadas;
	private Actividad actividadParaEval;

	private Long idRecogidaAct;

	private List<Usuario> alumnos;

	private Long userEvaluado;

	private Evaluacion evalEditable;

	private List<Actividad> actividades;

	private String nota;
	@EJB
	private EvaluacionEJB service;

	@Inject
	private ControlAutorizacion sesion;
	@EJB
	private NotificacionesEJB serviceNotificaciones;

	@EJB
	private ActividadesEJB serviceActividades;
	@EJB
	private UsuariosEJB serviceUsuarios;

	// TODO: Habría que inyectar aqui el servicio de actividades
	private Evaluacion verEval;

	// PostConstruct
	@PostConstruct
	public void init() {
		try {
			actividades= serviceUsuarios.findActividadesCoordinadasByIdUsuario(sesion.getUsuario(),sesion.getUsuario().getId());
		} catch (CuentaInexistenteException | ContraseniaInvalidaException | CuentaInactivaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			actividades=new ArrayList<Actividad>();
		}
//		try {
//			
//			//actividades = serviceUsuarios.findById(sesion.getUsuario(), sesion.getUsuario().getId()).getActividades_coordinadas();
//		} catch (CuentaInexistenteException | ContraseniaInvalidaException | CuentaInactivaException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			actividades= new ArrayList<Actividad>();
//		}
		
	}
	

	// Postload
	public void postLoadVerTodos() {
		if(verEvaluadas==null)
			evaluaciones = service.findAll(sesion.getUsuario(), false);
		else
			evaluaciones = service.findAll(sesion.getUsuario(), verEvaluadas);
	}
	
	public void postLoad() {
		Long l = Long.parseLong(getId());
		verEval=service.findById(l, sesion.getUsuario());
	}

	public void postLoadEdit() {
		Long ident = null;
		try {
			ident = Long.parseLong(id);
		} catch (Exception e) {
			return;
		}
		evalEditable = service.findById(ident, sesion.getUsuario());

	}

	public void postLoadEliminar() {
		postLoad();
	}

	public void postLoadEvaluar() {
		if (idRecogidaAct == null)
			return;
		
		this.alumnos=serviceActividades.getByActividad(serviceActividades.findById((idRecogidaAct)));
	}
	public void postLoadDelete() {
		postLoad();

	}

	// Final Form
	public String doCrearEval() {
		Evaluacion eval = new Evaluacion();
		eval.setActividad(serviceActividades.findById((idRecogidaAct)));
		try {
			eval.setProfesor_evaluador(serviceUsuarios.findById(sesion.getUsuario(), sesion.getUsuario().getId()));
		} catch (CuentaInexistenteException | ContraseniaInvalidaException | CuentaInactivaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "verEvaluaciones.xhtml";
		}
		eval.setNota(Integer.parseInt(getNota()));
		try {
			eval.setAlumno_evaluado(serviceUsuarios.findById(sesion.getUsuario(),(userEvaluado)));
		} catch (CuentaInexistenteException | ContraseniaInvalidaException | CuentaInactivaException e) {
			e.printStackTrace();
			return "verEvaluaciones.xhtml";
		}
		service.crear(eval, sesion.getUsuario());
		Notificacion not = new Notificacion();
		not.setDescripcion("Se te ha evaluado en la actividad:"+eval.getActividad().getNombre());
		not.setTitulo("Evaluación");
		not.setUsuario(eval.getAlumno_evaluado());
		try {
			serviceNotificaciones.insertar(not);
		} catch (NotificacionException e) {
			e.printStackTrace();
		}
		return "verEvaluaciones.xhtml";
	}
	public String doVerEvaluadas() {
		System.out.println(verEvaluadas);
		if(verEvaluadas==null)
			return "verEvaluaciones.xhtml?faces-redirect=true&eval="+"true";
		else
			return "verEvaluaciones.xhtml?faces-redirect=true&eval="+((Boolean)!verEvaluadas).toString();
	}

	public String doActualizarEval() {
		evalEditable=service.findById(Long.parseLong(id), sesion.getUsuario());
		evalEditable.setNota(Integer.parseInt(nota));
		service.editar(evalEditable, sesion.getUsuario());
		return "verEvaluaciones.xhtml";
	}

	public String doSetAsig() {
		System.out.println("Existo por favor, dime que vengo aunque no tendria sentido :(");
		return "evaluar.xhtml?faces-redirect=true&id=" + idRecogidaAct;
	}
	


	public String okBorrar() {
		service.deleteById(Long.parseLong(getId()), sesion.getUsuario());
		return "verEvaluaciones.xhtml";
	}

	// Getters Setters
	public List<Evaluacion> getEvaluaciones() {
		return evaluaciones;
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

	public Long getIdRecogidaAct() {
		return idRecogidaAct;
	}

	public void setIdRecogidaAct(Long idRecogidaAct) {
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

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		System.out.println("Se ha puesto la nota:"+nota);
		this.nota = nota;
	}

	public Evaluacion getEvalEditable() {
		return evalEditable;
	}

	public void setEvalEditable(Evaluacion evalEditable) {
		this.evalEditable = evalEditable;
	}

	

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}


	public Boolean getVerEvaluadas() {
		return verEvaluadas;
	}


	public void setVerEvaluadas(Boolean verEvaluadas) {
		this.verEvaluadas = verEvaluadas;
	}
	

}
