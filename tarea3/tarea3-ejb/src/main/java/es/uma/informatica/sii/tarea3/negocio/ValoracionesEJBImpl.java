package es.uma.informatica.sii.tarea3.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.informatica.sii.tarea3.entidades.Actividad;
import es.uma.informatica.sii.tarea3.entidades.Valoracion;

@Stateless
public class ValoracionesEJBImpl implements ValoracionesEJB {
 
	@PersistenceContext(unitName = "tarea3-EntidadesPU")
    private EntityManager em;

	@Override
	public List<Valoracion> findAll(){
		Query query = em.createQuery("SELECT v FROM Valoracion v");
		List<Valoracion> valoraciones = (List<Valoracion>)query.getResultList();
		return valoraciones;
	}

	@Override
	public void insertarValoracion(Valoracion v) {
		em.persist(v);
	}


}
