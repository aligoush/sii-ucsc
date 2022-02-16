package es.uma.informatica.sii.tarea3.vista;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriBuilder;

import org.primefaces.model.file.UploadedFile;

import es.uma.informatica.sii.tarea3.entidades.Notificacion;
import es.uma.informatica.sii.tarea3.entidades.Usuario;
import es.uma.informatica.sii.tarea3.negocio.CuentaRepetidaException;
import es.uma.informatica.sii.tarea3.negocio.NotificacionException;
import es.uma.informatica.sii.tarea3.negocio.NotificacionesEJB;
import es.uma.informatica.sii.tarea3.negocio.Tarea3Exception;
import es.uma.informatica.sii.tarea3.negocio.UsuariosEJB;

@Named("registroController")
@RequestScoped
public class RegistroController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String PARAM_VALIDACION="codigoValidacion";
	private static final String PARAM_ID = "id";
	
	@EJB
	UsuariosEJB usuarioService;
	
	@EJB
	NotificacionesEJB notificacionService;
	
	private Long id;
	private String codigoValidacion;
	private boolean validacionOK;
	private boolean registroOK;
	
	private String mensajeValidacion;
	private Usuario usuario;
	private String repeticionContrasena;
	
	private UploadedFile imagenSubida;
	
	@PostConstruct
	public void init() {
		usuario=new Usuario();
	}
	
	public RegistroController() {
		
	}
	
	
	public String doRegistrarUsuario() {
		try {
            if (!usuario.getContrasena().equals(repeticionContrasena)) {
                FacesMessage fm = new FacesMessage("Las contraseñas deben coincidir");
                FacesContext.getCurrentInstance().addMessage("registro:repeticionContrasena", fm);
                return null;
            }
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
            		.getExternalContext()
            		.getRequest();
            
            String thisUri = request.getRequestURL().toString();
            
            int ultimaBarra = thisUri.lastIndexOf('/');
            if (ultimaBarra < 0) {
            	FacesMessage fm = new FacesMessage("Error interno de URL");
                FacesContext.getCurrentInstance().addMessage(null, fm);
                return null;
            }
            
            UriBuilder uriBuilder = UriBuilder.fromUri(thisUri.substring(0, ultimaBarra))
            		.path("validarCuenta.xhtml")
            		.queryParam(PARAM_ID, "{id}")
            		.queryParam(PARAM_VALIDACION, "{validacion}");
            usuario.setTipoUsuario("NORMAL");
            usuarioService.registrarUsuario(usuario, uriBuilder);
            registroOK = true;
            return "exitoRegistro.xhtml";
            
        } catch (CuentaRepetidaException e) {
            FacesMessage fm = new FacesMessage("Existe un usuario con la misma cuenta");
            FacesContext.getCurrentInstance().addMessage("registro:user", fm);
            
        } catch (Tarea3Exception e) {
        }
        return null;
		//return "login.xhtml";
	}
	
	public String validarCuenta() {
		try {
			System.out.println(id);
			System.out.println(codigoValidacion);
            if (id != null && codigoValidacion != null) {
                usuarioService.validarCuenta(id, codigoValidacion);
            }
            mensajeValidacion = "La validación ha sido correcta, ahora puede acceder con este usuario.";
            validacionOK = true;
           
            
        } catch (Tarea3Exception e) {
            mensajeValidacion = "Ha habido un error con la validación, compruebe que la URL es correcta.";
            validacionOK = false;
        }
        return null;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoValidacion() {
		return codigoValidacion;
	}

	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}

	public boolean isValidacionOK() {
		return validacionOK;
	}

	public void setValidacionOK(boolean validacionOK) {
		this.validacionOK = validacionOK;
	}

	public boolean isRegistroOK() {
		return registroOK;
	}

	public void setRegistroOK(boolean registroOK) {
		this.registroOK = registroOK;
	}

	public String getMensajeValidacion() {
		return mensajeValidacion;
	}

	public void setMensajeValidacion(String mensajeValidacion) {
		this.mensajeValidacion = mensajeValidacion;
	}
	
	
	public UploadedFile getImagenSubida() {
		return imagenSubida;
	}
	

	public void setImagenSubida(UploadedFile imagenSubida) {
		byte[] b = imagenSubida.getContent();
		usuario.setImagen(b);
		this.imagenSubida = imagenSubida;
	}
	
	
	

}
