package es.uma.informatica.sii.tarea3.negocio;

import java.util.List;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.CreateException;
import java.util.Locale;
import java.util.logging.Logger;

import javax.ejb.*;
import javax.management.Notification;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import es.uma.informatica.sii.tarea3.negocio.*;

import es.uma.informatica.sii.tarea3.entidades.Notificacion;
import es.uma.informatica.sii.tarea3.entidades.Usuario;

@Stateless
public class NotificacionesEJBImpl implements NotificacionesEJB {
	
    private static final Logger LOGGER = Logger.getLogger(NotificacionesEJBImpl.class.getCanonicalName());

	
	@PersistenceContext(unitName ="tarea3-EntidadesPU")//se conecta al archivo. Se conecta a la BD
	private EntityManager em; 
	
	public List<Notificacion>findAll()throws NotificacionException{
		Query query = em.createQuery("SELECT notif FROM Notificacion notif");
		List<Notificacion> notific = query.getResultList();
		return notific;
		
	}
	public List<Notificacion>findByUsuario(Usuario u){
		Query query = em.createQuery("SELECT notif FROM Notificacion notif WHERE notif.usuario=:fusuario");
		query.setParameter("fusuario",u);

		List<Notificacion> notific = query.getResultList();
		
		return notific;
		
	}

	public Notificacion findByidNotificacion(Long idNotificacion) throws NotificacionException{
	
		return em.find(Notificacion.class, idNotificacion);
	}

	
	public void insertar(Notificacion notif)throws NotificacionException {
		em.persist(notif);
//		if(notif.getUsuario().getNotificacionesCorreo()) {
//			EnviarCorreo ec = new EnviarCorreo();
//			ec.enviarNotificacion(notif);
//		}
	}
	public void eliminar(Notificacion notif)throws NotificacionException {
		System.out.println("EJB ELIMINAR:"+notif);
		em.remove(em.merge(notif));
	}


	

}