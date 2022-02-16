package es.uma.informatica.sii.tarea3.autentificacion;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.negocio.UsuariosEJB;

@Named(value = "login")
@RequestScoped
public class Login {

	private String email;
	private String contrasena;

	@Inject
	private ControlAutorizacion ctrl;
	
	@EJB
	private UsuariosEJB usuarioService;
	
	
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
	


	public ControlAutorizacion getCtrl() {
		return ctrl;
	}

	public void setCtrl(ControlAutorizacion ctrl) {
		this.ctrl = ctrl;
	}



	private void error(String s) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, s, s));
	}

	public String autenticar() {
		Usuario u = usuarioService.findByEmail(email);
		if(u==null) {
			error("ERROR: El usuario no existe");
			return null;
		} else {
			if(contrasena.equals(u.getContrasena())) {
				if(u.getCadenaValidacion()==null || u.getCadenaValidacion().equals("")) {
					ctrl.setUsuario(u);
					return "inicio.xhtml";
				} else {
					error("Cuenta no validada. Revisa tu correo y pulsa en el enlace de validación.");
					return null;
				}
				
			} else {
				error("ERROR: Contraseña incorrecta");
				return null;
			}
		}
	}
}
