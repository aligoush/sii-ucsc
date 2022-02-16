package es.uma.informatica.sii.tarea3.negocio.titulaciones;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.tarea3.entidades.Titulacion;
import es.uma.informatica.sii.tarea3.entidades.Usuario;

@Local
public interface TitulacionEJB {
	/**
	 * Metodo que devuelve todas las titulaciones de la base de datos, verifica que el usuario dado por parametro tiene los permisos dados
	 * @param user Usuario de la sesión
	 * @return La lista con todas las titulaciones
	 */
	public List<Titulacion> findAll(Usuario user);
	/**
	 * Obtiene una titulacion dada su id,verifica que el usuario dado por parametro tiene los permisos dados
	 * @param id Id de la Titulación
	 * @param user Usuario de la sesión 
	 * @return La titulación con la id dada
	 */
	public Titulacion getById(Long id,Usuario user);
	/**
	 * Crea una titulacion en la base de datos, verifica que el usuario dado por parametro tiene los permisos dados
	 * @param titulacion la titulacion a persistir
	 * @param user El usuario de la sesión
	 */
	public void crear(Titulacion titulacion,Usuario user);
	
	/**
	 * Persiste los datos cambiados a una titulacion, verifica que el usuario dado por parametro tiene los permisos dados
	 * @param titulacion editada
	 * @param user El usuario de la sesión
	 */
	public void editar(Titulacion titulacion,Usuario user);
	/**
	 * Borra o desenlaza una aplicación de la base de datos o de un usuario respectivamente
	 * Si es admin el usuario borrará la titulación en otro caso tan solo la desenlaza del usuario dado
	 * @param id La id de la Titulación
	 * @param user El usuario de la sesión
	 */
	public void deleteById(Long id,Usuario user);
	/**
	 * Obtiene las titulaciones asociadas a un usuario,si es admin las coge todas
	 * @param usuario El usuario de la sesion
	 */
	public List<Titulacion> findAllBySessionId(Usuario usuario);
	/**
	 * Matricula a un usuario dado a la titulación con la id dada
	 * @param user El usuario
	 * @param id La id de la Titulación
	 */
	public void anyadir(Usuario user,Long id);

}
