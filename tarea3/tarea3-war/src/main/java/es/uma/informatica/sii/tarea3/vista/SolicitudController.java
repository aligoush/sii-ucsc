package es.uma.informatica.sii.tarea3.vista;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import es.uma.informatica.sii.tarea3.entidades.*;
import es.uma.informatica.sii.tarea3.negocio.ActividadesEJB;
import es.uma.informatica.sii.tarea3.negocio.SolicitudEJB;
import es.uma.informatica.sii.tarea3.autentificacion.ControlAutorizacion;
/**
*
* @author Raquel
*/
@Named(value = "beanSolicitud")
@RequestScoped
public class SolicitudController {
	@Inject
	private ControlAutorizacion ca;
	@EJB
	private SolicitudEJB bd;
	@EJB
	private ActividadesEJB actividadService;
	
	

	public SolicitudController() {
	}

	public List<Solicitud> getMostrarSolicitudes(){
		List<Solicitud> aux;
		System.out.println("Hola!");
		StringBuilder builder = new StringBuilder();
		if (ca.getUsuario().getTipoUsuario().equals("NORMAL")) {
			bd.findSolicitudes(ca.getUsuario()).forEach(builder::append);
			System.out.println("\n\n\nNormal!"+builder.toString());
			return bd.findSolicitudes(ca.getUsuario());
		} else if (ca.getUsuario().getTipoUsuario().equals("PROFESOR")) {
			return bd.findSolicitudesProfesor(ca.getUsuario());
		} else if (ca.getUsuario().getTipoUsuario().equals("ADMINISTRADOR")) {
			return bd.findSolicitudesAdministrador();

		}
		return null;
	}

	public String setSolicitud(int sol, int i){
		if (i == 1) {
			bd.editarSolicitud(sol, "Aceptada");
			// aquí habría que poner el envio de una nueva notificación....
		} else {
			bd.editarSolicitud(sol, "Denegada");
		}
		return "solicitud.xhtml";
	}
	
	
}
