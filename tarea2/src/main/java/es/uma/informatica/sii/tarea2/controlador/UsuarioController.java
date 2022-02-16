package es.uma.informatica.sii.tarea2.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.uma.informatica.sii.tarea2.modelo.Usuario;
import es.uma.informatica.sii.tarea2.servicio.UsuarioService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Usuario> usuarios;
    private Usuario usuario;
    private String id;
    private String nuevaContrasena;
    private String validacionContrasena;
     
    @Inject
    private UsuarioService service;
 
    @PostConstruct
    public void init() {
        usuarios = service.createUsuarios(50);
        usuario= new Usuario();
        validacionContrasena="";
    }
     
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
 
    public void setService(UsuarioService service) {
        this.service = service;
    }
    
    
    public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioService getService() {
		return service;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
	
	public List<Usuario> getOrganizaciones() {
		List<Usuario> organizaciones = new ArrayList<Usuario>();
		for(int i=0; i<usuarios.size(); i++){
			if(usuarios.get(i).getTipoUsuario().equals("ORGANIZACION")) {
				organizaciones.add(usuarios.get(i));
			}
		}
		return organizaciones;
	}

	
	
	public String doCrearUsuario() {
		return "usuarios.xhtml";
    }
    
    public String doEditarUsuario() {
    	return "usuarios.xhtml";
    }
    
    public String doEliminarUsuario() {
    	return"usuarios.xhtml";
    }
    
    
    
    public void postLoad() {
    	// usuario = usuarioRepository.findByDni(dni);
    	
    	boolean encontrado = false;
    	int i = 0;
    	Usuario u = null;
    	while(i<usuarios.size() && !encontrado) {
    		if((usuarios.get(i).getId()+"").equals(id)) {
    			u=usuarios.get(i);
    			encontrado=true;
    		}
    		i++;
    	}
    	if(encontrado) {
    		usuario=u;
    	} else {
    		usuario=null;
    	}
    	
    	
    }
}