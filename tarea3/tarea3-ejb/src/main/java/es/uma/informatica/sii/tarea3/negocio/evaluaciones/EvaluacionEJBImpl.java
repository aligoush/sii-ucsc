package es.uma.informatica.sii.tarea3.negocio.evaluaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.sii.tarea3.entidades.Evaluacion;
import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.negocio.ContraseniaInvalidaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInactivaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInexistenteException;
import es.uma.informatica.sii.tarea3.negocio.UsuariosEJB;
import es.uma.informatica.sii.tarea3.negocio.evaluaciones.exceptions.EvaluacionNoExisteException;
import es.uma.informatica.sii.tarea3.negocio.evaluaciones.exceptions.LoggerMsg;

@Stateless
public class EvaluacionEJBImpl implements EvaluacionEJB {
	
	private final static Logger logger = Logger.getLogger(EvaluacionEJBImpl.class.getCanonicalName());
	
	@PersistenceContext(unitName = "tarea3-EntidadesPU")
	private EntityManager em;
	
	@EJB
	private UsuariosEJB usuarioService;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Evaluacion> findAll(Usuario user,Boolean verEvaluadas) {
		if(user==null)
			return null;
		logger.info(LoggerMsg.BUSCAR.toString()+".EvaluadasPorUser?="+verEvaluadas.toString());
		try {
			user = usuarioService.findById(user,user.getId());
		} catch (CuentaInexistenteException | ContraseniaInvalidaException | CuentaInactivaException e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, LoggerMsg.VACIO.toString()+user.getId());
		}
		if(user.getTipoUsuario().equalsIgnoreCase("administrador"))
			return (List<Evaluacion>) em.createQuery("select e from Evaluacion e where e.visible=TRUE").getResultList();
		if(user.getTipoUsuario().equalsIgnoreCase("profesor"))
			return verEvaluadas? recorrerListaYDevolver(user.getEvaluadas()):recorrerListaYDevolver(user.getEvaluaciones());
		else
			return recorrerListaYDevolver(user.getEvaluaciones());
	}
	
	@Override
	public Evaluacion findById(Long id,Usuario user) {
		if(user==null)
			return null;
		try {
			Evaluacion eval;
			if((eval= em.find(Evaluacion.class, id))==null)
				throw new EvaluacionNoExisteException();
			logger.info(LoggerMsg.BUSCARID.toString()+id);
			return eval;
		}catch(EvaluacionNoExisteException e) {
			logger.log(Level.SEVERE, LoggerMsg.VACIO.toString()+id);
		}
		return null;
		
	}
	
	@Override
	public void crear(Evaluacion evaluacion,Usuario user) {
		if(user==null||user.getTipoUsuario().equalsIgnoreCase("normal"))
			return;
		// TODO Auto-generated method stub
		logger.info(LoggerMsg.CREAR.toString());
		em.persist(evaluacion);

	}

	
	@Override
	public void editar(Evaluacion evaluacion,Usuario user) {
		if(user==null)
			return;
		// TODO Auto-generated method stub
		logger.info(LoggerMsg.EDITAR.toString());
		em.merge(evaluacion);

	}

	
	@Override
	public void deleteById(Long id,Usuario user) {
		if(user==null)
			return;
		// TODO Auto-generated method stub
		if(user.getTipoUsuario().equalsIgnoreCase("normal"))
			return;
		try {
			Evaluacion eval;
			if((eval= em.find(Evaluacion.class, id))==null)
				throw new EvaluacionNoExisteException();
			logger.info(LoggerMsg.BORRAR.toString()+id);
			eval.setVisible(false);
			em.merge(eval);
		}catch(EvaluacionNoExisteException e) {
			logger.log(Level.SEVERE, LoggerMsg.VACIO.toString()+id);
		}
	}
	
	public <E extends Evaluacion> List<E> recorrerListaYDevolver(List<E> lista){
		List<E> out = new ArrayList<E>();
		lista.forEach(x -> {if(x.getVisible())out.add(x);});
		return out;
	}

}
