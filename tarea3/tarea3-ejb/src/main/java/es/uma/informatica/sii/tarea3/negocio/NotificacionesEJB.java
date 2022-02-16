package es.uma.informatica.sii.tarea3.negocio;

import java.util.List;

import javax.ejb.Local;
import es.uma.informatica.sii.tarea3.entidades.*;
//necesito entidades Notificaciones

@Local
public interface NotificacionesEJB {
	/**
	 * 
	 * @return
	 * Listo todas las notificaciones
	 * @throws NotificacionException
	 */
	public List<Notificacion>findAll()throws NotificacionException; //necesito todas las notific
	/**
	 * 
	 * @param u
	 * Listo las notificaciones dado un usuario
	 * @return
	 */
	public List<Notificacion>findByUsuario(Usuario u); //busca notif por usuario
	/**
	 * 
	 * @param id
	 * Doy una notificación dado un id
	 * @return
	 * @throws NotificacionException
	 */
	public Notificacion findByidNotificacion(Long id)throws NotificacionException;
	/**
	 * 
	 * @param notif
	 * crea la notificacion qee le pasas por parametro
	 * @throws NotificacionException
	 */
	public void insertar(Notificacion notif)throws NotificacionException;
	/**
	 * 
	 * @param notif
	 * elimina la notificación pasada por parametro
	 * @throws NotificacionException
	 */
	public void eliminar(Notificacion notif)throws NotificacionException;
	
	//el unico que puede enviar es el administrador


}