package es.uma.informatica.sii.tarea3.vista;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.uma.informatica.sii.tarea3.autentificacion.ControlAutorizacion;
import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.negocio.AutorizacionException;
import es.uma.informatica.sii.tarea3.negocio.ContraseniaInvalidaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInactivaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInexistenteException;
import es.uma.informatica.sii.tarea3.negocio.CuentaRepetidaException;
import es.uma.informatica.sii.tarea3.negocio.UsuariosEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@Named("usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {
	private static final long serialVersionUID = 1L;

	// private List<Usuario> usuarios;
	private Usuario usuario;
	private String id;
	private String nuevaContrasena;
	private String validacionContrasena;
	private UploadedFile imagenSubida;
	private UploadedFile imagenSubidaEditar;

	@EJB
	private UsuariosEJB service;

	@Inject
	private ControlAutorizacion sesion;

	@PostConstruct
	public void init() {
//        usuarios = service.findAll();
		usuario = new Usuario();
		validacionContrasena = "";
	}

	public List<Usuario> getUsuarios()throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException {
		return service.findAll(sesion.getUsuario());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
//	public void subirImagen(FileUploadEvent event) {
//		UploadedFile uf = event.getFile();
//		byte[] b = uf.getContent();
//		usuario.setNuevaImagen(b);
//	}
	
	

	public UploadedFile getImagenSubida() {
		return imagenSubida;
	}
	

	public void setImagenSubida(UploadedFile imagenSubida) {
		byte[] b = imagenSubida.getContent();
		usuario.setImagen(b);
		this.imagenSubida = imagenSubida;
	}
	
	

	public UploadedFile getImagenSubidaEditar() {
		return imagenSubidaEditar;
	}

	public void setImagenSubidaEditar(UploadedFile imagenSubidaEditar) {
		this.imagenSubidaEditar = imagenSubidaEditar;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNuevaContrasena() {
		return nuevaContrasena;
	}

	public void setNuevaContrasena(String nuevaContrasena) {
		this.nuevaContrasena = nuevaContrasena;
	}

	public void setValidacionContrasena(String validacionContrasena) {
		this.validacionContrasena = validacionContrasena;
	}

	public String getValidacionContrasena() {
		return validacionContrasena;
	}

	public List<Usuario> getOrganizaciones() throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException {
		return service.findOrganizaciones(sesion.getUsuario());
	}

	private void error(String s) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, s, s));
	}

	public String doCrearUsuario() {
		if (nuevaContrasena.equals(validacionContrasena)) {
			usuario.setContrasena(nuevaContrasena);
			try {
				service.insertarUsuario(usuario);
			} catch (CuentaRepetidaException e) {
				e.printStackTrace();
				error("ERROR: Ya existe un usuario con el mismo email");
				return null;
			}
			return "usuarios.xhtml";
		} else {
			error("Las contraseñas no coinciden");
			return null;
		}

	}

	public String doEditarUsuario() throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException {
		if (!nuevaContrasena.equals("") || !validacionContrasena.equals("")) {
			if (nuevaContrasena.equals(validacionContrasena)) {
				usuario.setContrasena(nuevaContrasena);
				try {
					service.modificarUsuario(sesion.getUsuario(),usuario);
				} catch (CuentaRepetidaException e) {
					e.printStackTrace();
					error("ERROR: Ya existe un usuario con el mismo email");
					return null;
				}
				return "usuarios.xhtml";
			} else {
				error("Error: las contraseñas no coinciden");
				return null;
			}
		} else {
			usuario.setContrasena(service.findById(sesion.getUsuario(),usuario.getId()).getContrasena());
			try {
				service.modificarUsuario(sesion.getUsuario(),usuario);
			} catch (CuentaRepetidaException e) {
				e.printStackTrace();
				error("ERROR: Ya existe un usuario con el mismo email");
				return null;
			}
			return "usuarios.xhtml";
		}

	}

	public String doEliminarUsuario() throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException, AutorizacionException {
		service.eliminarUsuario(sesion.getUsuario(),usuario);
		return "usuarios.xhtml";
	}

	public void postLoad() throws NumberFormatException, CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException {
		usuario = service.findById(sesion.getUsuario(), Long.parseLong(id));
	}
}