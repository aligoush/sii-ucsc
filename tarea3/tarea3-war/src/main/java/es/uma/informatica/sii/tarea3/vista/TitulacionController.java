package es.uma.informatica.sii.tarea3.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.tarea3.autentificacion.ControlAutorizacion;
import es.uma.informatica.sii.tarea3.entidades.Evaluacion;
import es.uma.informatica.sii.tarea3.entidades.Matriculacion;
import es.uma.informatica.sii.tarea3.entidades.Notificacion;
import es.uma.informatica.sii.tarea3.entidades.Titulacion;
import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.negocio.ContraseniaInvalidaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInactivaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInexistenteException;
import es.uma.informatica.sii.tarea3.negocio.CuentaRepetidaException;
import es.uma.informatica.sii.tarea3.negocio.NotificacionException;
import es.uma.informatica.sii.tarea3.negocio.NotificacionesEJB;
import es.uma.informatica.sii.tarea3.negocio.UsuariosEJB;
import es.uma.informatica.sii.tarea3.negocio.titulaciones.TitulacionEJB;
@Named("titulacionCtrl")
@ViewScoped
public class TitulacionController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	Integer ident;
	Titulacion ver;
	Titulacion nuevo;
	
	@EJB
	TitulacionEJB service;
	@EJB
	UsuariosEJB usuarioService;
	@Inject
	ControlAutorizacion sesion;
	
	@EJB
	NotificacionesEJB serviceNotificaciones;
	
	List<Titulacion> titulaciones;
	/**
	 * Metodo auxiliar para conseguir el Id 
	 * @return El id transformado en Long
	 * @throws NumberFormatException En el caso de que sea id = null
	 */
	private Long gettingId() throws NumberFormatException{
		if(ident!=null)
			return new Long(ident);
		if(id==null) {
			throw new NumberFormatException("El ID es nulo");
		}
		return Long.parseLong(id);
		
	}

	
	
	public void postLoadVerTodos() {
		titulaciones = service.findAllBySessionId(sesion.getUsuario());
	}
	
	/**
	 * Carga la Titulacion en la variable 'ver'
	 */
	public void postLoadVer() {
		Long ident= gettingId();
		ver = service.getById(ident,sesion.getUsuario());
	}
	/**
	 * Carga la Titulación en la variable 'ver'
	 */
	public void postLoadEdit() {
		postLoadVer();
	}
	/**
	 * Carga la Titulación en la variable 'ver'
	 */
	public void postLoadDelete() {
		postLoadVer();
	}
	/**
	 * Carga una nueva Titulacion en la variable 'nueva'
	 */
	public void postLoadCrear() {
		nuevo= new Titulacion();
	}
	/**
	 * Carga las Titulaciones en la variable Titulaciones
	 */
	public void postLoadAnyadir() {
		
		titulaciones= service.findAll(sesion.getUsuario());
	}
	
	
	//Mensajes de los forms
	public String okCrear() {
		service.crear(nuevo,sesion.getUsuario());
		return "verTitulaciones.xhtml";
	}
	
	public String okDelete() {
		service.deleteById(gettingId(),sesion.getUsuario());
		return "verTitulaciones.xhtml";
	}
	
	public String okEdit() {
		service.editar(ver,sesion.getUsuario());
		return "verTitulaciones.xhtml";
	}
	
	public String okAnyadir() throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException, CuentaRepetidaException {
		service.anyadir(sesion.getUsuario(), gettingId());
		Notificacion not = new Notificacion();
		not.setUsuario(sesion.getUsuario());
		not.setDescripcion("Te has matriculado en:"+service.getById(gettingId(), sesion.getUsuario()).getNombreTitulacion());
		not.setTitulo("Añadida Matricula");
		try {
			serviceNotificaciones.insertar(not);
		} catch (NotificacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sesion.setUsuario(usuarioService.findById(sesion.getUsuario(), sesion.getUsuario().getId()));
			return "verTitulaciones.xhtml";

	}
	
	public String okDesenlazar() {
		service.deleteById(gettingId(), sesion.getUsuario());
		Notificacion not = new Notificacion();
		not.setUsuario(sesion.getUsuario());
		not.setDescripcion("Te has desmatriculado en:"+service.getById(gettingId(), sesion.getUsuario()).getNombreTitulacion());
		not.setTitulo("Quitada Matricula");
		try {
			serviceNotificaciones.insertar(not);
		} catch (NotificacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "verTitulaciones.xhtml";
	}


	
	
	
	
	//Getters y setters :D

	public Titulacion getVer() {
		return ver;
	}

	public void setVer(Titulacion ver) {
		this.ver = ver;
	}

	public List<Titulacion> getTitulaciones() {
		return titulaciones;
	}

	public void setTitulaciones(List<Titulacion> titulaciones) {
		this.titulaciones = titulaciones;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}


	public Titulacion getNuevo() {
		return nuevo;
	}


	public void setNuevo(Titulacion nuevo) {
		this.nuevo = nuevo;
	}

	public Integer getIdent() {
		return ident;
	}


	public void setIdent(Integer ident) {
		this.ident = ident;
	}
	
	
	
	
	
	
	
	

}
