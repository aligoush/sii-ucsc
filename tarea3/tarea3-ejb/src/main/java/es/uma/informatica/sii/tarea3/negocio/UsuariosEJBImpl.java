package es.uma.informatica.sii.tarea3.negocio;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.UriBuilder;

import es.uma.informatica.sii.tarea3.entidades.Actividad;
import es.uma.informatica.sii.tarea3.entidades.Notificacion;
import es.uma.informatica.sii.tarea3.entidades.Solicitud;
import es.uma.informatica.sii.tarea3.entidades.Usuario;

@Stateless
public class UsuariosEJBImpl implements UsuariosEJB{
	
	@PersistenceContext(unitName = "tarea3-EntidadesPU")
    private EntityManager em;
	
	private static final int TAM_CADENA_VALIDACION = 20;
    private static final Logger LOGGER = Logger.getLogger(UsuariosEJBImpl.class.getCanonicalName());
	
	
	@Override
    public void registrarUsuario(Usuario u, UriBuilder uriBuilder) throws CuentaRepetidaException  {
       // Usuario user = findByEmail(u.getEmail());
        
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email=:femail");
		query.setParameter("femail", u.getEmail());
		List<Usuario> usuarios = query.getResultList();
		Usuario user;
		try {
			user=usuarios.get(0);
		} catch (IndexOutOfBoundsException e) {
			user=null;
		}
        
        
        if (user != null) {
            // El usuario ya existe
            throw new CuentaRepetidaException();
        }
        u.setCadenaValidacion(generarCadenaAleatoria());
        em.persist(u);

        URI uriValidacion = uriBuilder.build(u.getId(), u.getCadenaValidacion());

        LOGGER.info(uriValidacion.toString());
        
        EnviarCorreo ec = new EnviarCorreo();
        ec.enviarValidacion(u, uriValidacion.toString());
    }

    private String generarCadenaAleatoria() {
        Random rnd = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < TAM_CADENA_VALIDACION; i++) {
            int v = rnd.nextInt(62);
            if (v < 26) {
                sb.append((char) ('a' + v));
            } else if (v < 52) {
                sb.append((char) ('A' + v - 26));
            } else {
                sb.append((char) ('0' + v - 52));
            }
        }

        return sb.toString();
    }

    @Override
    public void validarCuenta(Long id, String validacion) throws CuentaInexistenteException, ValidacionIncorrectaException  {
        Usuario u = em.find(Usuario.class, id);
        if (u == null) {
            throw new CuentaInexistenteException();
        }

        if (u.getCadenaValidacion() == null) {
            // la cuenta ya está activa
            return;
        }

        if (!u.getCadenaValidacion().equals(validacion)) {
            throw new ValidacionIncorrectaException();
        }
        // else
        // Eliminamos la cadena de validación, indicando que ya está activa la cuenta
        u.setCadenaValidacion(null);
    }
	
	
	
	@Override
	public void comprobarUsuario(Usuario u) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException {
		Usuario user = em.find(Usuario.class, u.getId());
    	if(user==null) {
    		throw new CuentaInexistenteException();
    	}
    	if(!user.getContrasena().equals(u.getContrasena())) {
    		throw new ContraseniaInvalidaException();
    	}
    	
    	if(!(u.getCadenaValidacion()==null || u.getCadenaValidacion().equals(""))) {
    		throw new CuentaInactivaException();
    	}
    	
	}
		
	@Override
	public List<Usuario> findAll(Usuario usuario_login) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException {
		comprobarUsuario(usuario_login);
		Query query = em.createQuery("SELECT u FROM Usuario u");
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}
	
	@Override
	public Usuario findById(Usuario usuario_login, Long id) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException {
		comprobarUsuario(usuario_login);
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.id=:fid");
		query.setParameter("fid", id);
		List<Usuario> usuarios = query.getResultList();
		Usuario u;
		try {
			u=usuarios.get(0);
			for(Actividad a : u.getActividades_coordinadas());
			for(Solicitud s : u.getSolicitudes());
			for(Notificacion n : u.getNotificaciones());
			
			
		} catch (IndexOutOfBoundsException e) {
			u=null;
		}
		return u;
	}
	
	@Override
	public Usuario findByEmail(String email) {
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email=:femail");
		query.setParameter("femail", email);
		List<Usuario> usuarios = query.getResultList();
		Usuario u;
		try {
			u=usuarios.get(0);
			for(Actividad a : u.getActividades_coordinadas());
			for(Solicitud s : u.getSolicitudes());
			
		} catch (IndexOutOfBoundsException e) {
			u=null;
		}
		return u;
	}
	
	@Override 
	public List<Usuario> findByTipoUsuario(String tipo) throws TipoUsuarioInvalidoException{
		if(!tipo.equals("ADMINISTRADOR")&&!tipo.equals("PROFESOR")&&!tipo.equals("NORMAL")&&!tipo.equals("ORGANIZACION")) {
			throw new TipoUsuarioInvalidoException();
		}
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.tipoUsuario=:ftipo");
		query.setParameter("ftipo", tipo);
		return query.getResultList();
		
	}
	
	@Override
	public List<Usuario> findOrganizaciones(Usuario usuario_login) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException{
		comprobarUsuario(usuario_login);
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.tipoUsuario=:ftipo");
		query.setParameter("ftipo", "ORGANIZACION");
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}
	
	@Override
	public byte[] findImagenPerfilById(Long id) {
		Usuario u = em.find(Usuario.class, id);
		return u.getImagen();
	}
	
	@Override
	public void insertarUsuario(Usuario u) throws CuentaRepetidaException{
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email=:femail");
		query.setParameter("femail", u.getEmail());
		List<Usuario> usuarios = query.getResultList();
		Usuario user;
		try {
			user=usuarios.get(0);
		} catch (IndexOutOfBoundsException e) {
			user=null;
		}
        
        if (user != null) {
            // El usuario ya existe
            throw new CuentaRepetidaException();
        }
		
		em.persist(u);
	}
	
	@Override
	public void modificarUsuario(Usuario usuario_login, Usuario u) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException, CuentaRepetidaException{
		comprobarUsuario(usuario_login);
		Usuario antiguo = em.find(Usuario.class, u.getId());
		if (!antiguo.getEmail().equals(u.getEmail())) {
			Usuario comprobar_email = findByEmail(u.getEmail());
			if (comprobar_email!=null) {
				throw new CuentaRepetidaException();
			}
		}
		em.merge(u);
	}

	
	@Override
	public void editarPerfil(Usuario u) throws CuentaRepetidaException {
		Usuario antiguo = em.find(Usuario.class, u.getId());
		if (!antiguo.getEmail().equals(u.getEmail())) {
			Usuario comprobar_email = findByEmail(u.getEmail());
			if (comprobar_email!=null) {
				throw new CuentaRepetidaException();
			}
		}
		
		em.merge(u);
	}
	
	@Override
	public void eliminarUsuario(Usuario usuario_login, Usuario u) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException, AutorizacionException{
		comprobarUsuario(usuario_login);
		if(!usuario_login.getTipoUsuario().equals("ADMINISTRADOR")) {
			throw new AutorizacionException();
		}
		em.remove(em.merge(u));
	}
	
	@Override
	public List<Actividad> findActividadesCoordinadasByIdUsuario(Usuario usuario_login,Long idUsuario) throws CuentaInexistenteException, ContraseniaInvalidaException, CuentaInactivaException{
		List<Actividad> lista =  new ArrayList<Actividad>();
		Usuario u = this.findById(usuario_login, idUsuario);
		if(u!=null) {
			lista = u.getActividades_coordinadas();
		}
		return lista;
	}

}