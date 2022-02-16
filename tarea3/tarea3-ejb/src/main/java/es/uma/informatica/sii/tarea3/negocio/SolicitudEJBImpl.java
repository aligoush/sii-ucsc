package es.uma.informatica.sii.tarea3.negocio;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.informatica.sii.tarea3.entidades.Evaluacion;
import es.uma.informatica.sii.tarea3.entidades.Solicitud;
import es.uma.informatica.sii.tarea3.entidades.Usuario;
/**
 * Session Bean implementation class SolicitudEJBImpl
 */

@Stateless
public class SolicitudEJBImpl implements SolicitudEJB{
	@PersistenceContext(unitName = "tarea3-EntidadesPU")
	private EntityManager em;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> findSolicitudes(Usuario user){
		Usuario usuario = em.find(Usuario.class, user.getId());
		return recorrerListaYDevolver(usuario.getSolicitudes());
	}
	public <E> List<E> recorrerListaYDevolver(List<E> lista){
		List<E> out = new ArrayList<E>();
		lista.forEach(out::add);
		return out;
	}
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> findSolicitudesProfesor(Usuario user){
		/*Query q = em.createQuery("SELECT s FROM Solicitud s, Actividad a, Usuario u WHERE "
				+ "s.actividad = u.actividades_coordinadas and a.coordinadores = u.actividades_coordinadas "
				+ "and a.coordinadores = :coordinador" );
		q.setParameter("coordinador", user);*/
		user = em.find(Usuario.class, user.getId());
		List<Solicitud> solicitudes = new ArrayList<Solicitud>();
		user.getActividades_coordinadas().forEach(x -> x.getSolicitudes().forEach(solicitudes::add));
		return solicitudes;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> findSolicitudesAdministrador(){
		List<Solicitud> solicitud = new ArrayList<Solicitud>();
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE tipoUsuario = :tipo");
		q.setParameter("tipo", "PROFESOR");
		List<Usuario> profesores = (List<Usuario>) q.getResultList();
		for (Usuario usuario : profesores){
			solicitud.addAll(findSolicitudesProfesor(usuario));
		}
		return solicitud;
	}


	@Override
	public void editarSolicitud(int idSolicitud, String estado) {
		Solicitud solic = em.find(Solicitud.class, idSolicitud);
		solic.setEstadoSolicitud(estado);
		em.merge(solic);
	}

}
