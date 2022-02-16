package es.uma.informatica.sii.tarea2.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.tarea2.modelo.Evaluacion;
import es.uma.informatica.sii.tarea2.modelo.Titulacion;
import es.uma.informatica.sii.tarea2.servicio.TitulacionService;
@Named("titulacionCtrl")
@SessionScoped
public class TitulacionController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	Integer ident;
	Titulacion ver;
	Titulacion nuevo;
	
	@Inject
	TitulacionService service;
	
	List<Titulacion> titulaciones;
	/**
	 * Metodo auxiliar, cuidao que devuelve un NumberFormatException 
	 * @return El id transformao
	 * @throws NumberFormatException Por si la lias en el id poder manejar el error
	 */
	private Integer gettingId() throws NumberFormatException{
		if(id==null) {
			throw new NumberFormatException("Esto jamas deberia salir, si es asi el codigo ha fallado por todos lados :D");
		}
		return Integer.parseInt(id);
		
	}
	
	
	//Post... construct
	@PostConstruct
	private void init() {
		titulaciones = service.findAll();
	}
	
	//Lo que carga despues de tratar el id
	public void postLoadVer() {
		Integer ident= gettingId();
		for(Titulacion tit : service.findAll()) {
			if(tit.getIdTitulacion().equals(ident)) {
				ver=tit;
				return;
			}
		}
	}
	public void postLoadEdit() {
		postLoadVer();
	}
	
	public void postLoadDelete() {
		postLoadVer();
	}
	
	public void postLoadCrear() {
		nuevo= new Titulacion();
	}
	
	public void postLoadAnyadir() {
		return;
	}
	
	
	//Mensajes de los forms
	public String okCrear() {
		return "verTitulaciones.xhtml";
	}
	
	public String okDelete() {
		return okCrear();
	}
	
	public String okEdit() {
		return okCrear();
	}
	
	public String okAnyadir() {
		return okCrear();
	}
	
	public String okDesenlazar() {
		return okCrear();
	}


	
	
	
	
	//Getters y setters :D

	public Titulacion getVer() {
		return ver;
	}

	public void setVer(Titulacion ver) {
		this.ver = ver;
	}

	public List<Titulacion> getTitulaciones() {
		return titulaciones;
	}

	public void setTitulaciones(List<Titulacion> titulaciones) {
		this.titulaciones = titulaciones;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}


	public Titulacion getNuevo() {
		return nuevo;
	}


	public void setNuevo(Titulacion nuevo) {
		this.nuevo = nuevo;
	}

	public Integer getIdent() {
		return ident;
	}


	public void setIdent(Integer ident) {
		this.ident = ident;
	}
	
	
	
	
	
	
	

}
