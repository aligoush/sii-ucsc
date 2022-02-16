package es.uma.informatica.sii.tarea3.negocio;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import es.uma.informatica.sii.tarea3.entidades.*;
import java.util.List;

@Local
public interface SolicitudEJB {
	
	/** 	
	 * 	Este metodo recoge todas las solicitudes de un usuario(alumno)
 	 * 	@args Usuario user coge como parametro un usuario que tiene que
	 * 	ser un alumno para mostrar sus solicitudes
 	 * 	@return List<Solicitud> solicitudes llama a la db para seleccionar
 	 * 	las solicitudes que ha pedido un alumno
	 */
	public List<Solicitud> findSolicitudes(Usuario user);
	
	/**	
	 * 	Este metodo recoge las solicitudes de una actividad de un usuario(profesor)
	 * 	@args Usuario user coge como parametro un usuario que tiene que
 	 * 	ser un profesor para mostrar sus solicitudes
	 * 	@return List<Solicitud> solicitudes llama a la db para seleccionar
	 *	las solicitudes que ha pedido un alumno	sobre la Actividad que coordina
	 * 	dicho profesor
	 */
	public List<Solicitud> findSolicitudesProfesor(Usuario user);
	
	/**	
	 *	Este metodo recoge todas las solicitudes de todas las actividades de un usuario(administrador)
	 * 	@return List<Solicitud> solicitudes llama a la db para seleccionar
	 *	las solicitudes que ha pedido un alumno	sobre la Actividad que coordina
	 * 	un profesor llamando al metodo creado para profesor
	 */
	public List<Solicitud> findSolicitudesAdministrador();
	
	/**	
	 *	Este metodo edita el estado de una solicitud
	 *      @args int idSolicitud de la solicitud que es necesario modificar
	 *      @args String estado a modificar
	 */
	public void editarSolicitud(int idSolicitud, String estado);
}
