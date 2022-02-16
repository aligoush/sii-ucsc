package es.uma.informatica.sii.tarea3.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.uma.informatica.sii.tarea3.autentificacion.ControlAutorizacion;
import es.uma.informatica.sii.tarea3.entidades.Notificacion;
import es.uma.informatica.sii.tarea3.entidades.Proyecto;
import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.negocio.NotificacionException;
import es.uma.informatica.sii.tarea3.negocio.NotificacionesEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.Notification;


@Named("notificacionController")
@ViewScoped
public class NotificacionController implements Serializable {
	private static final long serialVersionUID = 1L;
    private Usuario usuario; //necesito a usuario para relacionar los mensajes con personas
    private Notificacion notifica;
    
    private String idNotificacion ;
    
   


    
  @Inject
  private ControlAutorizacion control;
     
  @EJB
    private NotificacionesEJB service; 
    
 
    @PostConstruct
    public void init() {
    	
    	usuario= new Usuario();
 
    }
    public String getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(String idNotificacion) {
		this.idNotificacion = idNotificacion;
	}
	public Notificacion getNotifica(){			
		return notifica ; 
	}
	
	public void setNotifica(Notificacion notifica) {
		this.notifica = notifica;
	}
	
 
	public List<Notificacion> getNotificaciones(){			
		return service.findByUsuario(control.getUsuario()); //usuario logueado al momento

	
	}
	
	 public Usuario getUsuario() { //quiero su id
			return usuario;
	}

    public void setUsuario(Usuario usuario) { //quiero su id para relacionarlo
			this.usuario = usuario;
	}
	

	
    public void doEliminarNotificacion()  { //la pagina me envia String, entonces dentro cambiar a Long
    	if(idNotificacion!=null) {
			try {
				service.eliminar(service.findByidNotificacion(Long.parseLong(idNotificacion)));
		    	System.out.println("ENTRO EN ELIMINAR(CONTROLLER)"+idNotificacion);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotificacionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	}else {
    		System.out.println("NO HE ELIMINADO(CONTROLLER)");
    	}
    	
    	//return"notificaciones.xhtml";
    }
	
   
  
    
  /*  public void postLoad() {
    //	usuario=service.findById(Long.parseLong(id));
    	System.out.println("ENTRO POSTLOAD");
    	
    try {
		//notifica=service.findByidNotificacion(Long.parseLong(idNotificacion));
    	notificaciones=service.findByUsuario(control.getUsuario()); //usuario logueado al momento
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }*/
    
    
    

}