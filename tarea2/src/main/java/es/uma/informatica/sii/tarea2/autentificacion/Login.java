/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.tarea2.autentificacion;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.tarea2.modelo.Usuario;
import es.uma.informatica.sii.tarea2.servicio.UsuarioService;

@Named(value = "login")
@RequestScoped
public class Login {

	private String email;
	private String contrasena;
	private List<Usuario> usuarios;

	@Inject
	private ControlAutorizacion ctrl;
	
	@Inject
	private UsuarioService servicio;
	
	@PostConstruct
    public void init() {
        usuarios = servicio.createUsuarios(50);
    }
	
	/**
	 * Creates a new instance of Login
	 */
	public Login() {
		
	}

	public String getEmail() {
		return email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public ControlAutorizacion getCtrl() {
		return ctrl;
	}

	public void setCtrl(ControlAutorizacion ctrl) {
		this.ctrl = ctrl;
	}

	public UsuarioService getServicio() {
		return servicio;
	}

	public void setServicio(UsuarioService servicio) {
		this.servicio = servicio;
	}

	private void error(String s) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, s, s));
	}

	public String autenticar() {
		// Implementar este método
		String paginaDevolver=null;
		Usuario u = new Usuario();
		boolean esta = false;
		boolean contrasenasIguales = false;
		int i = 0;
		while (!esta && i < usuarios.size()) {
			if (usuarios.get(i).getEmail().equals(email)) {
				if (usuarios.get(i).getContrasena().equals(contrasena)) {
					contrasenasIguales = true;
					u=usuarios.get(i);
				}
				esta = true;
			}
			i++;
		}
		
		if (esta) {
			if (contrasenasIguales) {
				ctrl.setUsuario(u);
				paginaDevolver="inicio.xhtml";
			} else {
				error("ERROR: Contraseña incorrecta");
			}

		} else {
			error("ERROR: El usuario no existe");
		}

		return paginaDevolver;
	}
}
