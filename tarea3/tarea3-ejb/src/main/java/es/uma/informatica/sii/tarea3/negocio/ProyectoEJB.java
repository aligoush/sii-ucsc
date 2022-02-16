
package es.uma.informatica.sii.tarea3.negocio;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.core.UriBuilder;


import es.uma.informatica.sii.tarea3.entidades.Proyecto;
import es.uma.informatica.sii.tarea3.entidades.Usuario;


public interface ProyectoEJB {
	/**
	 * inserta el proyecto en la base de datos 
	 * @param p proyecto a insertar
	 * @param u usuario que accede a ese metodo
	 * @throws ProyectoException
	 */
	 public void insertarProyecto(Proyecto p,Usuario u);
	 /**
	  * muestra la lista de todos los proyectos del sistema, en caso de que el usuario sea una organizacion 
	  * solo muestra los pruoyectos que pertenecen a dicha organizacion  
	  * @param u usuario que realiza la accion 
	  * @return
	  * @throws ProyectoException
	  */
	 public List<Proyecto> findAll(Usuario u);
	 /*
	  * busca un proyecto mediante su id , se usa para el boton ver 
	  */
	 public Proyecto findById(Long id) ;
	 /**
	  * modifica el proyecto 
	  * @param p proyecto
	  * @param u usuario que realiza la accion 
	  * @throws ProyectoException
	  */
	 public void modificarProyecto(Proyecto p,Usuario u );
	 /**
	  * elimina el proyecto del sistema 
	  * @param c proyecto a eliminar 
	  * @param u usuario que realiza la accion 
	  * @throws ProyectoException
	  */
	 public void eliminarProyecto(Proyecto c, Usuario u) ;
}