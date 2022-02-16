package es.uma.informatica.sii.tarea3.negocio;

import es.uma.informatica.sii.tarea3.entidades.Actividad;
import es.uma.informatica.sii.tarea3.entidades.Notificacion;
import es.uma.informatica.sii.tarea3.entidades.Proyecto;
import es.uma.informatica.sii.tarea3.entidades.Solicitud;
import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.entidades.Valoracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ActividadesEJBImpl implements ActividadesEJB {

	@PersistenceContext(unitName = "tarea3-EntidadesPU")
	private EntityManager em;

	@Override
	public Actividad findById(Long id) {
		Query query = em.createQuery("select a FROM Actividad a WHERE a.idActividad=:fid and a.visible=TRUE");
		query.setParameter("fid", id);
		List<Actividad> actividades = query.getResultList();
		Actividad a;
		try {
			a=actividades.get(0);
			for(Usuario u : a.getCoordinadores());
			for(Solicitud s : a.getSolicitudes());
			for(Valoracion v: a.getValoraciones());
		} catch (IndexOutOfBoundsException e) {
			a = null;
		}
		return a;
	}

//	public List<Actividad> findByPartic(Actividad a) {
//		Query query = em.createQuery("SELECT a FROM Actividad a where = : fid");
//		query.setParameter("fid", a.);
//		List<Actividad> actividades = query.getResultList();
//		return actividades;
//	}

	@Override
	public List<Actividad> findByOrganizacion(Usuario u) {
		Query query = em.createQuery("SELECT a FROM Actividad a where a.proyecto.organizacion =:forganizacion and a.visible=TRUE");
		query.setParameter("forganizacion", u);
		List<Actividad> actividades = query.getResultList();
		return actividades;
	}

	@Override
	public List<Actividad> findAll() {
		Query query = em.createQuery("SELECT a FROM Actividad a WHERE a.visible=TRUE");
		List<Actividad> actividades = query.getResultList();
		return actividades;
	}


	@Override
	public List<Actividad> findValidadas() {
		Query query = em.createQuery("SELECT a FROM Actividad a where a.validacion =:fval and a.visible=TRUE");
		query.setParameter("fval", true);
		List<Actividad> actividades = query.getResultList();
		return actividades;
	}

	@Override
	public void insertarTutorizacion(Usuario u, Actividad a) {
		u.getActividades_coordinadas().add(a);
		em.merge(u);
	}

	@Override
	public void crearSolicitud(Usuario u, Actividad a) {
		Solicitud solicitud = new Solicitud();
		solicitud.setActividad(a);
		solicitud.setEstadoSolicitud("Sin Seleccionar");
		solicitud.setFecha(new Date());
		solicitud.setUsuario(u);
		em.persist(solicitud);
	}

	@Override
	public void insertarActividad(Actividad a) {
		a.setVisible(true);
		em.persist(a);
	}

	public void coordinar(Usuario u) {
		em.merge(u);
	}

	@Override
	public void inscribirUsuario(Usuario u, Actividad a) {
		Actividad sol = em.find(Actividad.class, a.getIdActividad());
		boolean haySolicitud = false;
		// System.out.println("solicitud "+haySolicitud);
		List<Solicitud> lista = a.getSolicitudes();// creo las solicitudes de la actividad
		// for(Solicitud s: sol.getSolicitudes());
		for (Solicitud s : sol.getSolicitudes()) {
			// System.out.println("solicitud "+s.getIdSolicitud()+"con estado
			// "+s.getEstadoSolicitud());
			if (s.getUsuario().equals(u)) {
				haySolicitud = true;
				s.setEstadoSolicitud("Aceptada");
				em.persist(s);
			}
		}
		// System.out.println("hay solicitud "+haySolicitud);
		if (haySolicitud == false) {
			// System.out.println("creo la solicitud ");
			Solicitud solicitud = new Solicitud();
			solicitud.setActividad(a);
			solicitud.setEstadoSolicitud("Aceptada");
			solicitud.setFecha(new Date());
			solicitud.setUsuario(u);
			// System.out.println("peristiendo");
			em.persist(solicitud);
			// System.out.println("persisto");
		}
	}

	private boolean tieneSolicitudAceptadaUsuarioActividad(Usuario u, Actividad a) {
		Query query = em.createQuery(
				"select s FROM Solicitud s WHERE s.actividad=:a and s.usuario=:u and s.estadoSolicitud=:estado");
		query.setParameter("a", a);
		query.setParameter("u", u);
		query.setParameter("estado", "Aceptada");
		List<Solicitud> resultado = query.getResultList();
		System.out.println(resultado);
		if (resultado.isEmpty()) {
			System.out.println("falso");
			return false;
			
		}
		System.out.println("true");
		return true;
	}

	@Override
	public List<Usuario> getByActividad(Actividad a){
		Query query = em.createQuery("select u from Actividad a  inner join Solicitud s on s.actividad = a.idActividad"  
						+ " inner join Usuario u on u.id= s.usuario where a.idActividad =:idActividad"
						+  " and s.estadoSolicitud = 'Aceptada'");
		query.setParameter("idActividad", a.getIdActividad());
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}
	
	
  @Override
	public List<Usuario> getByNoActividad(Actividad a) throws TipoUsuarioInvalidoException {
		List<Usuario> usuarios = new ArrayList<>();
		List<Usuario> usuariosTodos = findUsuarioByTipoUsuario("NORMAL");
		List<Usuario> prof = findUsuarioByTipoUsuario("PROFESOR");
		usuariosTodos.addAll(prof);

		for (Usuario u : usuariosTodos) {
			if (!tieneSolicitudAceptadaUsuarioActividad(u, a)) {
				usuarios.add(u);
			}
		}
		return usuarios;
	}

	@Override
	public List<Usuario> findUsuarioByTipoUsuario(String tipo) throws TipoUsuarioInvalidoException {
		if (!tipo.equals("ADMINISTRADOR") && !tipo.equals("PROFESOR") && !tipo.equals("NORMAL")
				&& !tipo.equals("ORGANIZACION")) {
			throw new TipoUsuarioInvalidoException();
		}
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.tipoUsuario=:ftipo");
		query.setParameter("ftipo", tipo);
		return query.getResultList();

	}

	@Override
	public List<Usuario> getProfes(Actividad a) throws TipoUsuarioInvalidoException {// devuelvo profes q NO COORDINAN
																						// ESA ACTIVIDAD
		Actividad actividad = em.find(Actividad.class, a.getIdActividad());

		List<Usuario> usuarios = new ArrayList<>();
		List<Usuario> profesores = findUsuarioByTipoUsuario("PROFESOR");// LISTA DE PROFES
		List<Usuario> coordinadores = actividad.getCoordinadores(); // usuarios que coordinan la actividad

		// recorrro a los profes, y si no coordinan los añado a la lista usuarios
		for (Usuario p : profesores) {
			if (!coordinadores.contains(p)) {
				usuarios.add(p);
			}

//			for(Usuario c: coordinadores) {
//				if(!(c.getId().equals(p.getId()))) {// si IdCoordinador!=IDusuario    GUARDO
//					usuarios.add(p);
//
//				}
//			}
		}
		return usuarios;

	}

	@Override
	public void insertarTutor(Usuario u, Actividad a) { /* añado tutor a una actividad*/
		Actividad sol = em.find(Actividad.class, a.getIdActividad());	
		Usuario user = em.find(Usuario.class, u.getId());		

		List<Usuario> usu= sol.getCoordinadores(); //meto  el usuario en la actividad
		usu.add(user);//la guardo

		sol.setCoordinadores(usu);//el usuario en la actividad
		
		List<Actividad>activ=user.getActividades_coordinadas();
		activ.add(sol);
		user.setActividades_coordinadas(activ);
		
		em.merge(user);
		em.merge(sol);
	}

	@Override
	public void desinscribirUsuario(Usuario u, Actividad a) {
		for (Solicitud s : a.getSolicitudes()) {
			if (s.getActividad().equals(a) && s.getUsuario().equals(u)) {
				s.setEstadoSolicitud("Denegada");
				em.merge(s);
			}
		}
	}

	@Override
	public void modificarActividad(Actividad a) {
		em.merge(a);
	}

	@Override
	public void eliminarActividad(Actividad a) {
		a = em.find(Actividad.class, a.getIdActividad());
		a.setVisible(false);
		//em.remove(em.merge(a));
		em.merge(a);
	}
}
