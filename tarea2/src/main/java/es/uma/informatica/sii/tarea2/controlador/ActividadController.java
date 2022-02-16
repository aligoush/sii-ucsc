package es.uma.informatica.sii.tarea2.controlador;
import java.io.Serializable;
import java.util.List;

import es.uma.informatica.sii.tarea2.modelo.Actividad;
import es.uma.informatica.sii.tarea2.modelo.Proyecto;
import es.uma.informatica.sii.tarea2.modelo.Usuario;
import es.uma.informatica.sii.tarea2.modelo.Valoracion;
import es.uma.informatica.sii.tarea2.servicio.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("abean")
@ApplicationScoped
public class ActividadController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Actividad> actividades;
    private Actividad actividad;
    private List<Usuario> usuarios;
    private Valoracion valoracion;
    private List<Valoracion> valoraciones;
	private String proyectoId;
    private List<Proyecto> proyectos;
    private String id;
    private Usuario usuario;
    @Inject
    private ActividadService service;
    @Inject
    private ProyectoService proyecto_service;
    
    @Inject
   private UsuarioService service_user;
    
    @PostConstruct
    public void init() {
	 actividades = service.createActividades(10);
	 usuarios = service_user.createUsuarios(10);
	 //nombres_users = get  -> conseguir los nombres de usuarios para valoraciones y conseguir id de la actividad
	 valoraciones = service.createValoraciones(5, usuarios,actividades);
	 valoracion= new Valoracion();
	 actividad = new Actividad();
	 proyectos= proyecto_service.createProyecto(10);
 }
    
   
 
    public List<Actividad> getActividades() {
        return actividades;
    }

    
    public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	public List<Valoracion> getValoraciones(){
    	boolean encontrado = false;
    	int i = 0;
    	Valoracion val = null;
    	while(i<valoraciones.size() && !encontrado) {
    		if((valoraciones.get(i).getActividad()+"").equals(id)) {
    			val=valoraciones.get(i);
    			encontrado=true;
    		} i++;
    	}
    	if(encontrado) {
    		valoracion=val;
    	}
    	
		return valoraciones;
	}
	//Los parametros de esta funcion es la var: usuario y la actividad :D
	public String doSolicitarActividad() {
		return "actividades.xhtml";
	}
	//Los parametros de esta funcion es la var: usuario y la actividad :D
	public String doTutorizarActividad() {
		return "actividades.xhtml";
	}

	public ActividadService getService() { 
		return service;
	}
	
    public void setService(ActividadService service) {
        this.service = service;
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
	
//	public List<Actividad> getNombresActiv() {
//		return ;
//	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
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
	


	public String getProyectoId() {
		return proyectoId;
	}



	public void setProyectoId(String proyectoId) {
		this.proyectoId = proyectoId;
	}



	public List<Proyecto> getProyectos() {
		return proyectos;
	}



	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}



	public String doModificarActividad() {
    	return "actividades.xhtml";
    }
    public String doCrearActividad() {
    	actividad= new Actividad();
    	return "actividades.xhtml";
    }
    public String doEliminarActividad() {
    	return "actividades.xhtml";
    }
    
    public String doCrearValoracion() {
    	valoracion= new Valoracion();
    	return "actividad.xhtml?id="+id;
    }
    
    public String doAnyadirTutor() {
    	return "actividades.xhtml";
    }
    
    public String doInscribirUsuario() {
    	return "usuariosActividad.xhtml";
    }
    
   
    public String doDesinscribirUsuario() {
    	return "usuariosActividad.xhtml";
    }
    
    public void postLoad() {
    	boolean encontrado = false;
    	int i = 0;
    	Actividad act = null;
    	while(i<actividades.size() && !encontrado) {
    		if((actividades.get(i).getIdActividad()+"").equals(id)) {
    			act=actividades.get(i);
    			encontrado=true;
    		} i++;
    	}
    	if(encontrado) {
    		actividad=act;
    	} else {
    		actividad=null;
    	}
    	
    }
     
    
}