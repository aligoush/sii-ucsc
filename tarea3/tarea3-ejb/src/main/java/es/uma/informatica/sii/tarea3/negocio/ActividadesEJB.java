package es.uma.informatica.sii.tarea3.negocio;
import es.uma.informatica.sii.tarea3.entidades.Actividad;
import es.uma.informatica.sii.tarea3.entidades.Usuario;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ActividadesEJB {
	/**
	 * Devuelve una lista de todas las actividades existentes
	 * @return
	 */
	public List<Actividad> findAll();

	/**
	 * devuelve una actividad que se busca por id
	 * @param id
	 * @return
	 */
	public Actividad findById(Long id);
	
	/**
	 * al crear una nueva actividad la inserta en la base de dat0s
	 * @param a
	 */
	public void insertarActividad(Actividad a);
	
	
	/**
	 * un método creado para comprobar funcionamiento de coordinadores, crea a los tutores
	 * @param u
	 */
	public void coordinar(Usuario u);
	
	
	/**
	 * elimina una actividad que le entra como parámetro de base de datos
	 * @param a
	 */
	public void eliminarActividad(Actividad a);
	
	/**
	 * modifica una actividad en la base de datos que le entra como parámetro 
	 * @param a
	 */
	public void modificarActividad(Actividad a);
	
	/**
	 * devuelve todas las actividades validadas por el administrador-profesor y que son 
	 * visibles para usuarios tipo normal y profesor
	 * @return
	 */
	public List<Actividad> findValidadas();
	
	/**
	 * devuelve una lista de actividades del usuario tipo organizacion que fueron creados por 
	 * la organizacion misma
	 * @param u
	 * @return
	 */
	public List<Actividad> findByOrganizacion(Usuario u);
	
	/**
	 * Devuelve una lista de usuarios según el tipo de usuario que se le pase por parámetro
	 * @param tipo
	 * @return
	 * @throws TipoUsuarioInvalidoException
	 */
	public List<Usuario> findUsuarioByTipoUsuario(String tipo) throws TipoUsuarioInvalidoException;
	
	/**
	 * añade a un tutor,que entra como parámetro a una actividad, método permitido solo para administrador
	 * @param u
	 * @param a
	 */
	public void insertarTutorizacion(Usuario u, Actividad a);

	/**
	 * envía una solicitud para participar en una actividad 
	 * @param u
	 * @param a
	 */
	public void crearSolicitud(Usuario u, Actividad a);

	/**añade a un tutor,que entra como parámetro a una actividad, método permitido solo para administrador
	 * 
	 * 
	 * @param u
	 * @param a
	 */
	public void insertarTutor(Usuario u, Actividad a);
	
	/**
	 * inscribe a un usuario u (profesor o alumno)  en la actividad a
	 * @param u
	 * @param a
	 */
	public void inscribirUsuario(Usuario u, Actividad a);
	
	/**
	 * desinscribe al usuario u (profesor o alumno)  de la actividad a
	 * @param u
	 * @param a
	 */
	public void desinscribirUsuario(Usuario u, Actividad a);
	
	/**
	 * devuelve una lista de usuarios que están inscritos en una actividad
	 * @param a
	 * @return
	 */
	public List<Usuario> getByActividad(Actividad a);

	/**
	 * devuelve una lista de usuarios que no están en inscritos en la actividad, ni tutorizan la actividad
	 * @param a
	 * @return
	 * * @throws TipoUsuarioInvalidoException 
	 */
	public List<Usuario> getByNoActividad(Actividad a) throws TipoUsuarioInvalidoException;
	
	/**
	 * devuelve una lista de profesores que no coordinan la actividad que se le pasa por parámetro
	 * @param a
	 * @return
	 * @throws TipoUsuarioInvalidoException 
	 */
	public List<Usuario> getProfes(Actividad a) throws TipoUsuarioInvalidoException ;

	
}
