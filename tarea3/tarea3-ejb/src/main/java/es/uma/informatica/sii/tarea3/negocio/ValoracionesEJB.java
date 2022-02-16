package es.uma.informatica.sii.tarea3.negocio;

import java.util.List;

import es.uma.informatica.sii.tarea3.entidades.Actividad;
import es.uma.informatica.sii.tarea3.entidades.Valoracion;


public interface ValoracionesEJB {
	/**
	 * devuelve una lista de todas las valoraciones existentes
	 * @return
	 */
	public List<Valoracion> findAll();
	
	/**
	 * inserta una valoracion en la base de datos
	 * @param v
	 */
	public void insertarValoracion(Valoracion v);
	
}
