package es.uma.informatica.sii.tarea2.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.uma.informatica.sii.tarea2.modelo.Notificacion;
import es.uma.informatica.sii.tarea2.modelo.Usuario;
import es.uma.informatica.sii.tarea2.servicio.NotificacionService;
import es.uma.informatica.sii.tarea2.servicio.UsuarioService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.Notification;


@Named("notificacionController")
@SessionScoped
public class NotificacionController implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Notificacion>notificaciones; //creo la lista con las notificaciones
    private Usuario usuario; //necesito a usuario para relacionar los mensajes con personas
    private Long idUsuario;

     
    @Inject
    private NotificacionService service; 
    
 
    @PostConstruct
    public void init() {
    	notificaciones=service.VerNotificacion(10); //llamo al service para q muestre los mensajes
        usuario= new Usuario();
 
    }
 
	public List<Notificacion> getNotificaciones(){			
		return notificaciones;
	}
	
	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}
	
	 public Usuario getUsuario() { //quiero su id
			return usuario;
	}

    public void setUsuario(Usuario usuario) { //quiero su id para relacionarlo
			this.usuario = usuario;
	}
	
	public NotificacionService getService() {
		return service;
	}
	
    
    public String doEliminarNotificacion(String idNotificacion) {
    	return"notificaciones.xhtml";
    }
    public String doActualizarNotificacion() {
    	return"notificaciones.xhtml";
    }
    
    public void postLoad(String idUsuario) {
    	
    }
    
    
    

}