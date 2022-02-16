
package es.uma.informatica.sii.tarea3.autentificacion;

import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.negocio.ContraseniaInvalidaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInactivaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInexistenteException;
import es.uma.informatica.sii.tarea3.negocio.CuentaRepetidaException;
import es.uma.informatica.sii.tarea3.negocio.UsuariosEJB;

import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "controlAutorizacion")
@SessionScoped
public class ControlAutorizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuariosEJB usuarioService;

    private Usuario usuario;
    
    private String nuevaContrasena;
    private String repeticionContrasena;
    
    private UploadedFile imagenSubida;
    
    public ControlAutorizacion() {
    
    }

    
    public synchronized String logout() {
        // Destruye la sesión (y con ello, el ámbito de este bean)
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuario = null;
        return "login.xhtml";
    }
    
    
    
    public synchronized String doEditarPerfil() throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException {
    	if(!nuevaContrasena.equals("")) {
    		if (nuevaContrasena.equals(repeticionContrasena)) {
    			usuario.setContrasena(nuevaContrasena);
    		}
    	}
		try {
			usuarioService.editarPerfil(usuario);
		} catch (CuentaRepetidaException e) {
			e.printStackTrace();
			error("ERROR: Ese email ya está en uso");
		};
    	return "inicio.xhtml";
    }
    
    
    
    
    
    public synchronized void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public synchronized Usuario getUsuario() {
        return usuario;
    }

	public synchronized String getNuevaContrasena() {
		return nuevaContrasena;
	}

	public synchronized void setNuevaContrasena(String nuevaContrasena) {
		this.nuevaContrasena = nuevaContrasena;
	}

	public synchronized String getRepeticionContrasena() {
		return repeticionContrasena;
	}

	public synchronized void setRepeticionContrasena(String repeticionContrasena) {
		this.repeticionContrasena = repeticionContrasena;
	}
	
	public synchronized Integer getNumeroNotificaciones() throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException {
		return usuarioService.findById(usuario, usuario.getId()).getNotificaciones().size();
	}
	
	private synchronized void error(String s) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, s, s));
	}


	public synchronized UploadedFile getImagenSubida() {
		return imagenSubida;
	}


	public synchronized void setImagenSubida(UploadedFile imagenSubida) {
		usuario.setImagen(imagenSubida.getContent());
		this.imagenSubida = imagenSubida;
	}
	
	
	
	   
}
