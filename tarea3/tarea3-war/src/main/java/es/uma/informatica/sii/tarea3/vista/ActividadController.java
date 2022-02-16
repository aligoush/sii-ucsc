package es.uma.informatica.sii.tarea3.vista;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.faces.context.FacesContext;

import es.uma.informatica.sii.tarea3.autentificacion.ControlAutorizacion;
import es.uma.informatica.sii.tarea3.entidades.Actividad;
import es.uma.informatica.sii.tarea3.entidades.Notificacion;
import es.uma.informatica.sii.tarea3.entidades.Proyecto;
import es.uma.informatica.sii.tarea3.entidades.Solicitud;
import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.entidades.Valoracion;
import es.uma.informatica.sii.tarea3.negocio.ValoracionesEJB;
import es.uma.informatica.sii.tarea3.negocio.ActividadesEJB;
import es.uma.informatica.sii.tarea3.negocio.ContraseniaInvalidaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInactivaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInexistenteException;
import es.uma.informatica.sii.tarea3.negocio.NotificacionException;
import es.uma.informatica.sii.tarea3.negocio.NotificacionesEJB;
import es.uma.informatica.sii.tarea3.negocio.ProyectoEJB;
import es.uma.informatica.sii.tarea3.negocio.UsuariosEJB;
import es.uma.informatica.sii.tarea3.negocio.TipoUsuarioInvalidoException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@ViewScoped
@Named("abean")
public class ActividadController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Actividad> actividades;
    private Actividad actividad;
    private List<Usuario> usuarios;
    private Valoracion valoracion;
    private List<Valoracion> valoraciones;
	private Long proyectoId;
    private List<Proyecto> proyectos;
    private String idActividad;
    private Usuario usuario;
    
    @Inject
    private ActividadesEJB act_service;
    @Inject
    private ValoracionesEJB val_service;
    @Inject
    private ControlAutorizacion login_service;

    @EJB
	private UsuariosEJB serviceUsuarios;
    @EJB
    private ProyectoEJB serviceProyectos;
    @EJB
    private NotificacionesEJB serviceNotificaciones;
    
    
    
    @PostConstruct
    public void init() {
		valoraciones = val_service.findAll();
		valoracion= new Valoracion();
		actividades= act_service.findAll();
		actividad = new Actividad();
		actividad.setValidacion(false);
    }
    
	 /* si organizacion -> devuelve actividades de la organizacion
	  * si profesor o normal -> devuelve actividades en las que participa el profesor o usuario normal
	  * 
	  */
    public List<Actividad> getActividades() {
        if(login_service.getUsuario().getTipoUsuario().equals("ORGANIZACION")) {
        	actividades = act_service.findByOrganizacion(login_service.getUsuario());
        } else if (login_service.getUsuario().getTipoUsuario().equals("PROFESOR")||login_service.getUsuario().getTipoUsuario().equals("NORMAL")) {
        	List<Solicitud> solicitudes = login_service.getUsuario().getSolicitudes();
        	actividades = new ArrayList<Actividad>();
        		for (Solicitud s : solicitudes) {
        			if(s.getEstadoSolicitud().equals("Aceptada")) {
        				actividades.add(s.getActividad());
        			} 	
        		} 
        			
        } else if (login_service.getUsuario().getTipoUsuario().equals("ADMINISTRADOR")) {
        	actividades = act_service.findAll();
        }
    	return actividades;
    }
    
	public List<Actividad> getActividadesParaPartic() throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException {
		Usuario u = serviceUsuarios.findById(login_service.getUsuario(), login_service.getUsuario().getId());
		List<Actividad> actividades_validadas = act_service.findValidadas();
		List<Actividad> actividades_partic = new ArrayList<Actividad>();
		for (Actividad a : actividades_validadas) {
			boolean encontrado = false;
			for(Solicitud s: u.getSolicitudes()) {
				if(s.getActividad().equals(a)) {
					encontrado = true;
				}
			}
	if(!encontrado&&(!u.getActividades_coordinadas().contains(a))) {
				
				actividades_partic.add(a);
			} 
		}
		return actividades_partic;
	}
	public List<Actividad> getActividadesParaTutor() throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException {
		return this.getActividadesParaPartic();
	}
	
	public List<Actividad> getActividadesTutor (){
		return login_service.getUsuario().getActividades_coordinadas();
	}
    
    public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	public String getIdActividad() {
		return idActividad;
	}
	
	public void setIdActividad(String idActividad) {
		this.idActividad = idActividad;
	}

	public List<Valoracion> getValoraciones(){
		
		int i = 0;
    	List<Valoracion> vals = new ArrayList<Valoracion>();
    	while(i<valoraciones.size() ) {
    		if((valoraciones.get(i).getActividad()+" ").equals(idActividad)) {
    			vals.add(valoraciones.get(i));
    		} i++;
    	}
		return vals;
	}
	
    public List<Usuario> getUsuarios() {
		return usuarios;
	}
    
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Valoracion getValoracion() {
		return valoracion;
	}

	public void setValoracion(Valoracion valoracion) {
		this.valoracion = valoracion;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
		//Aqui meter
	}

	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}
	

	
//	public String obtenerValidacion() {
//		String validacion;
//		boolean val = actividad.getValidacion();
//		if (val == true) {
//			validacion = "Aceptada";
//		} else {
//			validacion = "Rechazada";
//		}
//		return validacion;
//	}

	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> getProfes() throws TipoUsuarioInvalidoException { 
		return act_service.getProfes(actividad);
	}

	public Long getProyectoId() {
		return proyectoId;
	}

	public List<Usuario> getByActividad() {
		return act_service.getByActividad(actividad);
	}
	public List<Usuario> getByNoActividad() throws TipoUsuarioInvalidoException{
		return act_service.getByNoActividad(actividad);
	}

	public void setProyectoId(Long proyectoId) {
		this.proyectoId = proyectoId;
	}



	public List<Proyecto> getProyectos() {
		return proyectos;
	}



	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	//Los parametros de esta funcion es la var: usuario y la actividad :D
	public String doSolicitarActividad() {
		act_service.crearSolicitud(login_service.getUsuario(),actividad);
		Notificacion n = new Notificacion();
		n.setTitulo("Actividad solicitada");
		n.setDescripcion("Se ha creado una nueva solicitud para la actividad "+actividad.getNombre());
		n.setUsuario(login_service.getUsuario());
		try {
			serviceNotificaciones.insertar(n);
		} catch (NotificacionException e) {
			e.printStackTrace();
		}
		return "actividades.xhtml";
	}
	//Los parametros de esta funcion es la var: usuario y la actividad :D
	public String doTutorizarActividad() {
		act_service.insertarTutorizacion(login_service.getUsuario(), actividad);
		
		Notificacion n = new Notificacion();
		n.setTitulo("Nueva tutorizacion");
		n.setDescripcion("Ahora estás tutorizando la actividad "+actividad.getNombre());
		n.setUsuario(login_service.getUsuario());
		try {
			serviceNotificaciones.insertar(n);
		} catch (NotificacionException e) {
			e.printStackTrace();
		}
		
		return "actividadesTutor.xhtml";
	}

	public String doModificarActividad() {
		actividad.setProyecto(serviceProyectos.findById(proyectoId));
		act_service.modificarActividad(actividad);
    	return "actividades.xhtml";
    }
    public String doCrearActividad() {
    	actividad.setVisible(true);
    	actividad.setProyecto(serviceProyectos.findById(proyectoId));
    	act_service.insertarActividad(actividad);
    	return "actividades.xhtml";
    }
    
    public String doEliminarActividad() {
    	actividad.setVisible(false);
    	act_service.eliminarActividad(actividad);
    	return "actividades.xhtml";
    }
    
    public String doCrearValoracion() {
    		valoracion.setUsuario(login_service.getUsuario());
    		valoracion.setActividad(actividad);
    		actividad.getValoraciones().add(valoracion);
    		act_service.modificarActividad(actividad);
    	return "actividades.xhtml";
    }

    public String doAnyadirTutor() {
    	act_service.insertarTutor(usuario,actividad); //la persona q hace click aparece como tutor
    	return "actividades.xhtml";
    }
    
    public String doInscribirUsuario() {
    
    	try {
	    	Usuario user = serviceUsuarios.findById(login_service.getUsuario(),usuario.getId());
	    	act_service.inscribirUsuario(user,actividad);
	    	
	    	Notificacion n = new Notificacion();
			n.setTitulo("Actividad aceptada");
			n.setDescripcion("Ahora participas en la actividad "+actividad.getNombre());
			n.setUsuario(user);
			try {
				serviceNotificaciones.insertar(n);
			} catch (NotificacionException e) {
				e.printStackTrace();
			}
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	
    	return null;
    }
    
   
    public String doDesinscribirUsuario() {
    	//System.out.println("Desinscribir a "+usuario);
    	act_service.desinscribirUsuario(usuario, actividad);
    	return null;
    }
    
    public void postLoad() {
		 try {
			 actividad = act_service.findById(Long.parseLong(idActividad));
		 }catch (Exception e) {
			 
		 }

    }
     
    
}
