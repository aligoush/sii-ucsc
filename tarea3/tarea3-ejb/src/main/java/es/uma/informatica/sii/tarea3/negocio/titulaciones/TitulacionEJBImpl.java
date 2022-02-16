package es.uma.informatica.sii.tarea3.negocio.titulaciones;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.sii.tarea3.entidades.Matriculacion;
import es.uma.informatica.sii.tarea3.entidades.Titulacion;
import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.negocio.titulaciones.exceptions.LoggerMsg;
import es.uma.informatica.sii.tarea3.negocio.titulaciones.exceptions.TitulacionNoExisteException;
@Stateless
public  class TitulacionEJBImpl implements TitulacionEJB{
	
	
	private  final static Logger logger = Logger.getLogger(TitulacionEJB.class.getCanonicalName());
	
	@PersistenceContext(unitName = "tarea3-EntidadesPU")
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Titulacion> findAll(Usuario user) {
		logger.info(LoggerMsg.BUSCAR.toString());
		return (List<Titulacion>) em.createQuery("select t from Titulacion t where t.visible=TRUE").getResultList();
	}

	
	@Override
	public Titulacion getById(Long id,Usuario user) {
		
		try {
			@SuppressWarnings("unused")
			Titulacion tit;
			if((tit = em.find(Titulacion.class, id))==null)
				throw new TitulacionNoExisteException("La titulacion no existe, id: ",id);
			
			logger.info(LoggerMsg.BUSCARID.toString()+id);
			return em.find(Titulacion.class, id);
			
		}catch(TitulacionNoExisteException exception) {
			logger.log(Level.WARNING, LoggerMsg.VACIO.toString()+id);
		}
		return null;
	}
	
	@Override
	public void crear(Titulacion titulacion,Usuario user) {
		logger.info(LoggerMsg.CREAR.toString());
		if(user.getTipoUsuario().equalsIgnoreCase("administrador"))
			em.persist(titulacion);
		
	}
	
	@Override
	public void editar(Titulacion titulacion,Usuario user) {
		logger.info(LoggerMsg.EDITAR.toString());
		if(user.getTipoUsuario().equalsIgnoreCase("administrador"))
		em.merge(titulacion);
		
	}

	
	@Override
	public void deleteById(Long id,Usuario user) {
		try {
			Titulacion tit;
		if((tit = em.find(Titulacion.class, id))==null)
			throw new TitulacionNoExisteException("La titulacion no existe, id: ",id);
		user = em.find(Usuario.class, user.getId());
		if(user.getTipoUsuario().equalsIgnoreCase("administrador")) {
			logger.info(LoggerMsg.BORRAR.toString()+id);
			tit.setVisible(false);
			em.merge(tit);
			tit.getMatriculas().forEach(x -> x.setVisible(false));
			
		}
		else {
			for(Matriculacion matriculacion: tit.getMatriculas())
				if(matriculacion.getUsuario().equals(user)) {
					matriculacion.setVisible(false);
					em.merge(matriculacion);
					logger.info(LoggerMsg.DESENLAZAR.toString()+id);
				}
					
		}
		}catch(TitulacionNoExisteException exception) {
			logger.log(Level.WARNING,LoggerMsg.VACIO.toString()+id);
		}
	}

	
	@Override
	public List<Titulacion> findAllBySessionId(Usuario usuario) {
		usuario = em.find(Usuario.class, usuario.getId());
		if(usuario.getTipoUsuario().equalsIgnoreCase("ADMINISTRADOR"))
			return findAll(usuario);
		List<Matriculacion> matriculas = usuario.getMatriculas();
		List<Titulacion> titPorId = new ArrayList<Titulacion>();
		matriculas.forEach((x) -> {if(x.getVisible()) titPorId.add(x.getTitulacion());});
		return titPorId;
	}
	
	@Override
	public void anyadir(Usuario user, Long id) {
		user = em.find(Usuario.class, user.getId());
		Matriculacion matriculacion = new Matriculacion();
		matriculacion.setFechaFin(new Date());
		matriculacion.setUsuario(user);
		matriculacion.setTitulacion(getById(id, user));
		em.persist(matriculacion);
		
	}

}
