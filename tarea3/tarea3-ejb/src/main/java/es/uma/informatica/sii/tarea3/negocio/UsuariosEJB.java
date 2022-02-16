package es.uma.informatica.sii.tarea3.negocio;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.core.UriBuilder;

import es.uma.informatica.sii.tarea3.entidades.Actividad;
import es.uma.informatica.sii.tarea3.entidades.Usuario;

@Local
public interface UsuariosEJB {
	
	/**
	 * Comprueba que el usuario esté registrado en el sistema, la contraseña sea correcta y que su cuenta esté validada correctamente
	 * @param u
	 * @throws CuentaInexistenteException
	 * @throws ContraseniaInvalidaException
	 * @throws CuentaInactivaException
	 */
	public void comprobarUsuario(Usuario u) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException;
	
	
	
	
	/**
	 * Devuelve una lista con todos los usuarios del sistema
	 * @param usuario_login
	 * @return
	 * @throws CuentaInexistenteException
	 * @throws ContraseniaInvalidaException
	 * @throws CuentaInactivaException
	 */
	public List<Usuario> findAll(Usuario usuario_login) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException;
	
	
	
	
	/**
	 * Devuelve el usuario concreto registrado en la aplicación que posee el identificador que se le pase por parámetro
	 * @param usuario_login
	 * @param id
	 * @return
	 * @throws CuentaInexistenteException
	 * @throws ContraseniaInvalidaException
	 * @throws CuentaInactivaException
	 */
	public Usuario findById(Usuario usuario_login, Long id) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException;
	
	
	
	/**
	 * Devuelve el usuario registrado en la aplicación que tiene como email el que se le pase por parámetro
	 * @param email
	 * @return
	 */
	public Usuario findByEmail(String email);
	
	
	
	/**
	 * Devuelve una lista de todos los usuarios de tipo organización que hay en el sistema
	 * @param usuario_login
	 * @return
	 * @throws CuentaInexistenteException
	 * @throws ContraseniaInvalidaException
	 * @throws CuentaInactivaException
	 */
	public List<Usuario> findOrganizaciones(Usuario usuario_login) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException;
	
	
	
	
	/**
	 * Inserta en la base de datos el usuario pasado por parámetro
	 * @param u
	 * @throws CuentaRepetidaException 
	 */
	public void insertarUsuario(Usuario u) throws CuentaRepetidaException;
	
	
	
	/**
	 * Actualiza los datos del usuario que se le pasa por parámetro en la base de datos
	 * @param usuario_login
	 * @param u
	 * @throws CuentaInexistenteException
	 * @throws ContraseniaInvalidaException
	 * @throws CuentaInactivaException
	 * @throws CuentaRepetidaException
	 */
	public void modificarUsuario(Usuario usuario_login, Usuario u) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException, CuentaRepetidaException;
	
	
	
	/**
	 * Elimina de la base de datos el usuario que se le pasa por parámetro
	 * @param usuario_login
	 * @param u
	 * @throws CuentaInexistenteException
	 * @throws ContraseniaInvalidaException
	 * @throws CuentaInactivaException
	 * @throws AutorizacionException
	 */
	public void eliminarUsuario(Usuario usuario_login, Usuario u) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException, AutorizacionException;
	
	
	
	/**
	 * Valida la cuenta del usuario usando el id de usuario y el código de validación
	 * @param id
	 * @param validacion
	 * @throws CuentaInexistenteException
	 * @throws ValidacionIncorrectaException
	 */
	public void validarCuenta(Long id, String validacion) throws CuentaInexistenteException, ValidacionIncorrectaException;
	
	
	
	/**
	 * Registra un nuevo usuario en la aplicación, generándole y asignándole una cadena de validación
	 * @param u
	 * @param uriBuilder
	 * @throws CuentaRepetidaException
	 */
	public void registrarUsuario(Usuario u, UriBuilder uriBuilder) throws CuentaRepetidaException;



	/**
	 * Permite modificar el usuario que se le pasa por parámetro
	 * @param u
	 * @throws CuentaRepetidaException
	 */
	public void editarPerfil(Usuario u) throws CuentaRepetidaException;



	/**
	 * Devuelve una lista de las actividades del usuario con idUsuario (identificador que se le pasa por parámetro)
	 * @param usuario_login
	 * @param idUsuario
	 * @return
	 * @throws CuentaInexistenteException
	 * @throws ContraseniaInvalidaException
	 * @throws CuentaInactivaException
	 */
	public List<Actividad> findActividadesCoordinadasByIdUsuario(Usuario usuario_login, Long idUsuario) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException;



	/**
	 * Devuelve una lista de usuarios según el tipo de usuario que se le pase por parámetro
	 * @param tipo
	 * @return
	 * @throws TipoUsuarioInvalidoException
	 */
	public List<Usuario> findByTipoUsuario(String tipo) throws TipoUsuarioInvalidoException;



	/**
	 * Devuelve la imagen del perfil del usuario cuya id es la que se le pasa por parámetro
	 * @param id
	 * @return
	 */
	public byte[] findImagenPerfilById(Long id);

}

