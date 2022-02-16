package es.uma.informatica.sii.tarea2.controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import es.uma.informatica.sii.tarea2.modelo.Usuario;

@Named("registroController")
@ViewScoped
public class RegistroController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private String repeticionContrasena;
	
	@PostConstruct
	public void init() {
		usuario=new Usuario();
	}
	
	public RegistroController() {
		
	}
	
	
	public String doRegistrarUsuario() {
		return "login.xhtml";
	}
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRepeticionContrasena() {
		return repeticionContrasena;
	}

	public void setRepeticionContrasena(String repeticionContrasena) {
		this.repeticionContrasena = repeticionContrasena;
	}
	
	
	
	

}
