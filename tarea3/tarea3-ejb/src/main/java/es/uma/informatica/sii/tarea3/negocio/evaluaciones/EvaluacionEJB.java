package es.uma.informatica.sii.tarea3.negocio.evaluaciones;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.tarea3.entidades.Evaluacion;
import es.uma.informatica.sii.tarea3.entidades.Usuario;

@Local
public interface EvaluacionEJB {
	/**
	 * Busca las evaluaciones en la BBDD asociadas a un usuario, o todas si es el administrador
	 * @param user El usuario de la sesion
	 * @param verEvaluadas si se desean ver las evaluaciones evaluadas por el profesor
	 * @return La lista con las evaluaciones
	 */
	public List<Evaluacion> findAll(Usuario user,Boolean verEvaluadas);
	/**
	 * Busca una evaluacion con la id dada
	 * @param id La id que se buscará
	 * @param user El usuario de la sesión
	 * @return La evaluación con la id del param
	 */
	public Evaluacion findById(Long id,Usuario user);
	/**
	 * Persiste la evaluación pasada por parametro
	 * @param evaluacion La evaluación
	 * @param user El usuario de la sesión
	 */
	public void crear(Evaluacion evaluacion,Usuario user);
	/**
	 * Persiste los cambios de la evaluación pasada por parámetro
	 * @param evaluacion La evaluación
	 * @param user El usuario de la sesión
	 */
	public void editar(Evaluacion evaluacion,Usuario user);
	/**
	 * Borra una evaluación con la ID dada
	 * @param id La id de la evaluación
	 * @param user El usuario de la sesión
	 */
	public void deleteById(Long id,Usuario user);
	
	
}
