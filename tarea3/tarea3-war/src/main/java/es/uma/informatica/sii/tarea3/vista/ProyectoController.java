package es.uma.informatica.sii.tarea3.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import es.uma.informatica.sii.tarea3.autentificacion.ControlAutorizacion;
import es.uma.informatica.sii.tarea3.entidades.Actividad;
import es.uma.informatica.sii.tarea3.entidades.Notificacion;
import es.uma.informatica.sii.tarea3.entidades.Proyecto;
import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.negocio.NotificacionesEJB;
import es.uma.informatica.sii.tarea3.negocio.ProyectoEJB;
import es.uma.informatica.sii.tarea3.negocio.ProyectoException;
import es.uma.informatica.sii.tarea3.negocio.UsuariosEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named("proyectoController")
@ViewScoped
public class ProyectoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Proyecto> proyectos;
	private List<Actividad> actividades;
	private Long idOrganizacionProyecto;
	private Usuario organizacion;
	private Long id;
	private Proyecto proyecto;
	private String descripcion;

	@Inject
	private ControlAutorizacion control;

	@EJB
	private ProyectoEJB proyectoEJB;
	
	@EJB
	private UsuariosEJB serviceUsuarios;
	
	@EJB
	private NotificacionesEJB serviceNotificaciones;


	@PostConstruct
	public void init() {
		proyectos = proyectoEJB.findAll(control.getUsuario());
		proyecto = new Proyecto();
	}

	public List<Usuario> getUsuario(){
		List<Usuario> res = new ArrayList<Usuario>();
		res.add(control.getUsuario());
		return res;
	}

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	
	public void setActividad(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	
	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	
	public Long getIdOrganizacionProyecto() {
		return idOrganizacionProyecto;
	}


	public void setIdOrganizacionProyecto(Long idOrganizacionProyecto) {
		this.idOrganizacionProyecto = idOrganizacionProyecto;
	}


	public Proyecto getProyecto() {
		return proyecto;
	}

	
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	
	public List<Actividad> getActividades() {
		return actividades;
	}

	
	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	
	public Usuario getOrganizacion() {
		return organizacion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public String doCrearProyecto() throws ProyectoException {
		try {
			proyecto.setVisible(true);
			proyecto.setOrganizacion(serviceUsuarios.findById(control.getUsuario(), idOrganizacionProyecto));
			proyectoEJB.insertarProyecto(proyecto, control.getUsuario());
			
			Notificacion n = new Notificacion();
			n.setTitulo("Proyecto Creado");
			n.setDescripcion("Se ha creado el nuevo proyecto "+proyecto.getNombre()+" de tu organización "+proyecto.getOrganizacion().getNombreOrganizacion()+" en el sistema");
			n.setUsuario(proyecto.getOrganizacion());
			serviceNotificaciones.insertar(n);
		} catch (Exception e) {
			System.out.println("doCrearProyecto: " + e);
		}
		return "proyectos.xhtml";
	}

	
	public String doActualizarProyecto() {
		try {
			proyectoEJB.modificarProyecto(proyecto, control.getUsuario());
		} catch (Exception e) {
			System.out.println("doActualizarProyecto: " + e);
		}
		return "proyectos.xhtml";
	}

	
	public String doEliminarProyecto() {
		try {
			System.out.println("pulso el bonton para borrar "+proyecto.getNombre());
			proyecto.setVisible(false);
			System.out.println("el visionado del proyecto "+proyecto.getNombre()+" es "+proyecto.getVisible());
			proyectoEJB.eliminarProyecto(proyecto, control.getUsuario());
			Notificacion n = new Notificacion();
			n.setTitulo("Proyecto Eliminado");
			n.setDescripcion("Se ha eliminado el proyecto "+proyecto.getNombre()+" de tu organización "+proyecto.getOrganizacion().getNombreOrganizacion()+" en el sistema");
			n.setUsuario(proyecto.getOrganizacion());
			serviceNotificaciones.insertar(n);
		} catch (Exception e) {
			System.out.println("doEliminarProyecto: " + e);
		}
		return "proyectos.xhtml";
	}

	
	public void postLoad() {
		System.out.println("voy a buscar por id" + id);
				proyecto = proyectoEJB.findById(id);
	}
}