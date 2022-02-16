/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.tarea3.negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import es.uma.informatica.sii.tarea3.entidades.Proyecto;
import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.entidades.Actividad;

@Stateless
public class ProyectoEJBImpl implements ProyectoEJB {

	private static final Logger LOGGER = Logger.getLogger(ProyectoEJBImpl.class.getCanonicalName());

	@PersistenceContext(unitName = "tarea3-EntidadesPU")
	private EntityManager em;
	


	@Override
	public void modificarProyecto(Proyecto p,Usuario u)  {
		//System.out.println("modificando proyecto selecionado"+p.getNombre()+" con id: " +p.getIdProyecto());
		if(u.getTipoUsuario().equals("ORGANIZACION")||u.getTipoUsuario().equals("ADMINISTRADOR")) {
				em.merge(p);
		}else {
			System.out.println("no tienes permisos para realizar esta accion");
		}
	}

	@Override
	public void insertarProyecto(Proyecto p,Usuario u)  {
		//System.out.println("nombre: "+p.getNombre()+"descripcion: "+p.getDescripcion()+"organixacion: "+p.getOrganizacion());
		if(u.getTipoUsuario().equals("ORGANIZACION")||u.getTipoUsuario().equals("ADMINISTRADOR")) {
		em.persist(p);
		}else {
			System.out.println("no tienes permisos para realizar esta accion");
		}
	}

	@Override
	public void eliminarProyecto(Proyecto p, Usuario u) {
		//System.out.println("borrando proyecto selecionado"+p.getNombre()+" con id: " +p.getIdProyecto());
		if(u.getTipoUsuario().equals("ADMINISTRADOR")||u.getTipoUsuario().equals("ORGANIZACION")) {
			//if(p.getActividades().isEmpty()) {
		//em.remove(em.merge(p));
		//}else {
			p.setVisible(false);
			em.merge(p);
		//}
			
			
		}else {
			System.out.println("no tienes permisos para realizar esta accion");
		}
	}

	@Override
	public List<Proyecto> findAll(Usuario u) {
		List<Proyecto> resultado;
		//System.out.println("he entrado en la vision de los usuarios");
		if (u.getTipoUsuario().equals("ORGANIZACION")) {
			//System.out.println("miro los proyectos de la organizacion");
			resultado = em.createQuery("SELECT p FROM Proyecto p WHERE p.organizacion =:usuario and p.visible=TRUE", Proyecto.class).setParameter("usuario", u).getResultList();
		} else {
			System.out.println("busco todos los proyectos");
			resultado= em.createNamedQuery(Proyecto.FIND_ALL, Proyecto.class).getResultList();
		}
		return resultado;
	}

	@Override
	public Proyecto findById(Long id) {
		//System.out.println("buscando por id"+id);
		Proyecto respuesta = em.find(Proyecto.class, id);
		if (respuesta != null) {
			//System.out.println("encontrado el proyecto "+respuesta.getNombre()+" con id: "+respuesta.getIdProyecto());
			for(Actividad a : respuesta.getActividades());
			return respuesta;
			
			}
		return null;
	}
}
