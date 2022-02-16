package es.uma.informatica.sii.tarea2.servicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.ejb.Remove;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import es.uma.informatica.sii.tarea2.modelo.Evaluacion;
import es.uma.informatica.sii.tarea2.modelo.Notificacion;
import es.uma.informatica.sii.tarea2.modelo.Usuario;

@Named
@ApplicationScoped
public class NotificacionService {
	private List<Notificacion> notificaciones =null;
	private List<Usuario> id = null; //relacionar usuario con mensaje NI PUTA IDEA

     
 
     
    public List<Notificacion> VerNotificacion(int size) {
        List<Notificacion> list = new ArrayList<Notificacion>();
        Notificacion u;
        
        for(int i = 0 ; i < size ; i++) {
        	u = new Notificacion();
        	u.setId((long) i); //u.setId((long)i);
        	u.setIdNotificacion((long) i);
        	u.setTitulo("titulo"+i);
        
        	u.setDescripcion(""
        			+ "Esto una notificacion: aqui va un mensaje relacionado con notas o trabajo "
        			+ "Notificacion blablablablablabla "
        			+ "Notificacion blablablablablabla "
        			+ "Esto una notificacion: aqui va un mensaje relacionado con notas o trabajo "
        			+ "Notificacion blablablablablabla "
        			+ "Notificacion blablablablablabla ");
        	
          list.add(u);
        }

        return list;
    }
     
    
    public List<Usuario> getId() { //necesito la id de la persona para relacionarla con el correo 
		return id;
	}
    
    public void setId (List<Usuario> id) { //como coño relaciono persona y correo? 
    	 this.id=id;
    }
	
    
    private void removeNotificacion(List<Notificacion> notificaciones, int i) { //ya tengo la id del mensaje, porq  tengo q seleccionarlo para borrarlo
    	notificaciones.remove(i); //aqui quito el elemento de la lista
        
	}


	private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }
     

    
}