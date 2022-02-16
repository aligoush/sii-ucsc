
package es.uma.informatica.sii.tarea2.autentificacion;

import es.uma.informatica.sii.tarea2.modelo.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

@Named(value = "controlAutorizacion")
@SessionScoped
public class ControlAutorizacion implements Serializable {

    private Usuario usuario;
    
    private String nuevaContrasena;
    private String repeticionContrasena;
    
    public ControlAutorizacion() {
    
    }

    
    public String logout() {
        // Destruye la sesión (y con ello, el ámbito de este bean)
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuario = null;
        return "login.xhtml";
    }
    
    
    
    public String doEditarPerfil() {
    	return null;
    }
    
    
    
    
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

	public String getNuevaContrasena() {
		return nuevaContrasena;
	}

	public void setNuevaContrasena(String nuevaContrasena) {
		this.nuevaContrasena = nuevaContrasena;
	}

	public String getRepeticionContrasena() {
		return repeticionContrasena;
	}

	public void setRepeticionContrasena(String repeticionContrasena) {
		this.repeticionContrasena = repeticionContrasena;
	}

	   
}
