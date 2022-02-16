package es.uma.informatica.sii.tarea2.controlador;

import java.io.Serializable;
import java.util.List;

import es.uma.informatica.sii.tarea2.modelo.Actividad;
import es.uma.informatica.sii.tarea2.modelo.Proyecto;
import es.uma.informatica.sii.tarea2.modelo.Usuario;
import es.uma.informatica.sii.tarea2.servicio.ProyectoService;


import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("proyectoController")
@ViewScoped
public class ProyectoController implements Serializable {
	public List<Actividad> getActividades() {
		return actividades;
	}


	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}


	public void setOrganizacion(Usuario organizacion) {
		this.organizacion = organizacion;
	}
	private static final long serialVersionUID = 1L;
	
	private List<Proyecto> proyectos;
	private List<Actividad> actividades;
	private Usuario organizacion;
	private String id;
    private Proyecto proyecto;
    private String descripcion;
  
    
     
    @Inject
    private ProyectoService service;


	@PostConstruct
    public void init() {
		proyectos = service.createProyecto(20);
    	proyecto= new Proyecto();
       
    }
     
    
	public ProyectoService getService() {
		return service;
	}
	
    public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public List<Proyecto> getProyectos() {
		return proyectos;
	}


	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}



	public Proyecto getProyecto() {
		return proyecto;
	}


	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
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

	public String doCrearProyecto() {
		return "proyectos.xhtml";
	}

	public String doActualizarProyecto() {
    	return "proyectos.xhtml";
    }
	public String doEliminarProyecto() {
    	return "proyectos.xhtml";
    }
	 public void postLoad() {
	    
	    	
	    	boolean encontrado = false;
	    	int i = 0;
	    	Proyecto u = null;
	    	while(i<proyectos.size() && !encontrado) {
	    		if((proyectos.get(i).getIdProyecto()+"").equals(id)) {
	    			u=proyectos.get(i);
	    			encontrado=true;
	    		}
	    		i++;
	    	}
	    	if(encontrado) {
	    		proyecto=u;
	    	} else {
	    		proyecto=null;
	    	}
	    	
	    	
	    }

}