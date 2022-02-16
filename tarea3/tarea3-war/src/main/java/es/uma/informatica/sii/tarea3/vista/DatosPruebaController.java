package es.uma.informatica.sii.tarea3.vista;

import java.io.Serializable;

import java.util.Random;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


import es.uma.informatica.sii.tarea3.entidades.Titulacion;
import es.uma.informatica.sii.tarea3.entidades.Valoracion;
import es.uma.informatica.sii.tarea3.entidades.Notificacion;
import es.uma.informatica.sii.tarea3.entidades.Actividad;
import es.uma.informatica.sii.tarea3.entidades.Evaluacion;
import es.uma.informatica.sii.tarea3.entidades.Proyecto;

import es.uma.informatica.sii.tarea3.entidades.Usuario;

import es.uma.informatica.sii.tarea3.negocio.ActividadesEJB;
import es.uma.informatica.sii.tarea3.negocio.ContraseniaInvalidaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInactivaException;
import es.uma.informatica.sii.tarea3.negocio.CuentaInexistenteException;
import es.uma.informatica.sii.tarea3.negocio.ProyectoEJB;
import es.uma.informatica.sii.tarea3.negocio.ProyectoException;
import es.uma.informatica.sii.tarea3.negocio.CuentaRepetidaException;
import es.uma.informatica.sii.tarea3.negocio.NotificacionException;
import es.uma.informatica.sii.tarea3.negocio.NotificacionesEJB;
import es.uma.informatica.sii.tarea3.negocio.UsuariosEJB;

import es.uma.informatica.sii.tarea3.negocio.titulaciones.TitulacionEJB;

import es.uma.informatica.sii.tarea3.negocio.evaluaciones.EvaluacionEJB;


@Named
@RequestScoped
public class DatosPruebaController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuariosEJB usuarioService;
	
	@EJB
	TitulacionEJB titulacionService;

	@EJB
	NotificacionesEJB notificacionService;
  
	@EJB
	ActividadesEJB actividadesService;
  
	@EJB
	ProyectoEJB proyectosService;
	
	@EJB
	EvaluacionEJB evaluacionService;

	public DatosPruebaController() {

	}
	
	public String crearDatosPrueba() throws CuentaRepetidaException, NotificacionException {
		crearUsuarios();
		crearTitulaciones();
		crearNotificaciones();
		crearProyectos();
		crearActividades();
		crearEvaluaciones();
		
		return "welcome.xhtml";	
	}
	
	
	public String crearUsuarios() throws CuentaRepetidaException {
		Usuario u = usuarioService.findByEmail("administrador0@uma.es");
		if (u == null) {
			u = new Usuario();
			u.setId(null);
			u.setDni("543210");
			u.setNombre("Antonio");
			u.setApellido1("García");
			u.setApellido2("Jiménez");
			u.setEmail("administrador0@uma.es");
			u.setDireccion("Calle Falsa Número");
			u.setTelefono("9520");
			u.setTipoUsuario("ADMINISTRADOR");
			//u.setImagen("usuario.jpg");
			u.setDisponibilidad("M");
			u.setDescripcion(""
					+ "Esto es un descipcion. Aquí va una descripcion del usuario. Aqui va una descripcion del usuario. "
					+ "Aqui va una descripcion del usuario. Aqui va una descripciónn del usuario. "
					+ "Aquí va una descripción del usuario. Aquí va una descripción del usuario. Aquí va una descripción"
					+ " del usuario. Aquí va una descripción del usuario. Aquí va una descripción del usuario. Aquí va "
					+ "una descripción del usuario. Aquí va una descripción del usuario. Aquí va una descripción del u"
					+ "suario." + "");
			u.setContrasena("asdf");
			u.setNotificacionesCorreo(true);
			usuarioService.insertarUsuario(u);
		}

		u = usuarioService.findByEmail("normal0@uma.es");
		if (u == null) {
			u = new Usuario();
			// u.setId((long) 0);
			u.setDni("543211");
			u.setNombre("Francisca");
			u.setApellido1("García");
			u.setApellido2("Jiménez");
			u.setEmail("normal0@uma.es");
			u.setDireccion("Calle Falsa Número");
			u.setTelefono("9521");
			u.setTipoUsuario("NORMAL");
			//u.setImagen("usuario.jpg");
			u.setDisponibilidad("T");
			u.setDescripcion(""
					+ "Esto es un descipcion. Aquí va una descripción del usuario. Aquí va una descripción del usuario. "
					+ "Aquí va una descripción del usuario. Aquí va una descripción del usuario. "
					+ "Aquí va una descripción del usuario. Aquí va una descripción del usuario. Aquí va una descripción"
					+ " del usuario. Aquí va una descripción del usuario. Aquí va una descripción del usuario. Aquí va "
					+ "una descripción del usuario. Aquí va una descripción del usuario. Aquí va una descripción del u"
					+ "suario." + "");
			u.setContrasena("asdf");
			u.setNotificacionesCorreo(true);
			usuarioService.insertarUsuario(u);
		}

		u = usuarioService.findByEmail("profesor0@uma.es");
		if (u == null) {
			u = new Usuario();
			// u.setId((long) (2));
			u.setDni("543212");
			u.setNombre("Pedro");
			u.setApellido1("González");
			u.setApellido2("Medina");
			u.setEmail("profesor0@uma.es");
			u.setDireccion("Calle Falsa Número 2");
			u.setTelefono("9522");
			u.setTipoUsuario("PROFESOR");
			//u.setImagen("usuario.jpg");
			u.setDisponibilidad("MT");
			u.setContrasena("asdf");
			u.setDescripcion("Esto sale luego en el cuadradito de logros academicos. "
					+ "Esto sale luego en el cuadradito de logros academicos. "
					+ "Esto sale luego en el cuadradito de logros academicos. Esto sale luego "
					+ "en el cuadradito de logros academicos. Esto sale luego en el cuadradito "
					+ "de logros academicos. Esto sale luego en el cuadradito de logros academi"
					+ "cos. Esto sale luego en el cuadradito de logros academicos. Esto sale lu"
					+ "ego en el cuadradito de logros academicos. Esto sale luego en el cuadrad");
        	usuarioService.insertarUsuario(u);
		}
		

		u = usuarioService.findByEmail("1profesor@uma.es");
		if (u == null) {
			u = new Usuario();
			u.setDni("590212");
			u.setNombre("Martin");
			u.setApellido1("González");
			u.setApellido2("Medina");
			u.setEmail("1profesor@uma.es");
			u.setDireccion("Calle Falsa Número 2");
			u.setTelefono("9522");
			u.setTipoUsuario("PROFESOR");
			//u.setImagen("usuario.jpg");
			u.setDisponibilidad("MT");
			u.setContrasena("asdf");
			u.setDescripcion("Esto sale luego en el cuadradito de logros academicos. "
					+ "Esto sale luego en el cuadradito de logros academicos. "
					+ "Esto sale luego en el cuadradito de logros academicos. Esto sale luego "
					+ "en el cuadradito de logros academicos. Esto sale luego en el cuadradito "
					+ "de logros academicos. Esto sale luego en el cuadradito de logros academi"
					+ "cos. Esto sale luego en el cuadradito de logros academicos. Esto sale lu"
					+ "ego en el cuadradito de logros academicos. Esto sale luego en el cuadrad");
     	usuarioService.insertarUsuario(u);
		}

		u = usuarioService.findByEmail("organizacion0@uma.es");
		if (u == null) {
			u = new Usuario();
			// u.setId((long) (3));
			u.setDni("543213");
			u.setDisponibilidad("M");
			u.setNombre("María");
			u.setApellido1("López");
			u.setApellido2("Márquez");
			u.setNombreOrganizacion("Greenpeace");
			u.setEmail("organizacion0@uma.es");
			u.setDireccion("Calle Falsa Número 3");
			u.setTelefono("9523");
			u.setTipoUsuario("ORGANIZACION");
			//u.setImagen("usuario.jpg");
			u.setContrasena("asdf");
			u.setDescripcion(""
					+ "Esto es un descipcion. Aquí va una descripción del usuario. Aquí va una descripción del usuario. "
					+ "Aquí va una descripción del usuario. Aquí va una descripción del usuario. "
					+ "Aquí va una descripción del usuario. Aquí va una descripción del usuario. Aquí va una descripción"
					+ " del usuario. Aquí va una descripción del usuario. Aquí va una descripción del usuario. Aquí va "
					+ "una descripción del usuario. Aquí va una descripción del usuario. Aquí va una descripción del u"
					+ "suario." + "");
			usuarioService.insertarUsuario(u);
		}

		return null;
	}

	public String crearNotificaciones() throws NotificacionException {
		System.out.println("CREO NOTIFICACION");
		Notificacion u;

		u = new Notificacion();
		u.setTitulo("titulo1");
		u.setIdNotificacion(null);
		u.setUsuario(usuarioService.findByEmail("administrador0@uma.es"));
		u.setDescripcion("" + "SOY ADMINISTRADOR "
				+ "Notificacion blablablablablabla " + "Notificacion blablablablablabla "
				+ "Esto una notificacion: aqui va un mensaje relacionado con notas o trabajo "
				+ "Notificacion blablablablablabla " + "Notificacion blablablablablabla ");
		
		notificacionService.insertar(u);
		return null;

	}

	public void crearProyectos()   {
		//System.out.println("le doy al boton");
		Proyecto p= new Proyecto();
		p.setIdProyecto(null);
		p.setNombre("proyecto 1");
		p.setVisible(true);
		p.setDescripcion(""
        			+ "Esto es un descipcion. Aquí va una descripción del usuario. Aquí va una descripción del usuario. "
        			+ "Aquí va una descripción del usuario. Aquí va una descripción del usuario. "
        			+ "Aquí va una descripción del usuario. Aquí va una descripción del usuario. Aquí va una descripción"
        			+ " del usuario. Aquí va una descripción del usuario. Aquí va una descripción del usuario. Aquí va "
        			+ "una descripción del usuario. Aquí va una descripción del usuario. Aquí va una descripción del u"
        			+ "suario."
        			+ "");
	p.setOrganizacion(usuarioService.findByEmail("organizacion0@uma.es"));
	p.setImagen("imagen");
	
		proyectosService.insertarProyecto(p, usuarioService.findByEmail("organizacion0@uma.es"));
	
	
	}



	
	public void crearActividades() {
		  String [] actividades = new String[]{"Limpieza de Montañas", "Nanofotos ","Poesía Cuántica","Alquimia Sonora","Construir el Mundo", "Telegram Bot", "Configurar Hierro", "Instalar tarjeta RAID", "Mentiras y Verduras", "Somos arquitectos de nuestra vida"};
		  Actividad act;
		  Proyecto proj = new Proyecto();
		  proj.setIdProyecto(null);
		  proj.setDescripcion("Nuevo proyecto");
		  proj.setImagen("imagen.jpg");
		  proj.setOrganizacion(usuarioService.findByEmail("organizacion0@uma.es"));
		  proj.setNombre("Our 1st project");
		  proj.setVisible(true);
		  proyectosService.insertarProyecto(proj,usuarioService.findByEmail("organizacion0@uma.es"));
		  for(int i = 0 ; i < 10; i++) {
			  boolean validac =true;
		      if(i==9) {
		    	  validac = false;
		      }	
		      int k = i+1;
			  act = new Actividad();
			  act.setIdActividad( null);
			  act.setNombre(actividades[i]);
			  act.setNumPersonasRequeridas(i);
			  act.setNumHorasRequeridas(i);
			  act.setImagen("actividad.jpg");
			  act.setDescripcion(""   
			        			+ "Aquí va una descripción de la actividad. Comentario de la actividad."
			        			+ "Aquí va una descripción de la actividad. Comentario de la actividad."
			        			+ "Aquí va una descripción de la actividad. Comentario de la actividad."
			        			+ "");
			  act.setTipo("voluntariado");
			//  act.getValoraciones().add(val);
			  act.setFechaInicio(new Date());
			  act.setFechaFin(new Date());
			  act.setUbicacion("Calle Zambrano Número "+i);
			  if(i%2==0) {
				  act.setProyecto(proj);
					 
			  }
			   //List<Usuario> tutores = new ArrayList<Usuario>();
			  //tutores.add(usuarioService.findByEmail("profesor0@uma.es"));
			  //act.setCoordinadores(tutores);
			 
			  act.setValidacion(true);
			  actividadesService.insertarActividad(act);
			  
			  if(i%2==0) {
				  actividadesService.inscribirUsuario(usuarioService.findByEmail("normal0@uma.es"), act);
			  }
			  
		  }
		  crearCoordinadores();
		  crearValoraciones();
		        
	}
	
	public String crearEvaluaciones() {
		Actividad zero = actividadesService.findAll().get(0);
		Usuario prof = usuarioService.findByEmail("profesor0@uma.es");
		Usuario alum = usuarioService.findByEmail("normal0@uma.es");
		actividadesService.inscribirUsuario(alum, zero);
		Evaluacion eval = new Evaluacion();
		eval.setActividad(zero);
		eval.setAlumno_evaluado(alum);
		eval.setProfesor_evaluador(prof);
		eval.setNota(9);
		evaluacionService.crear(eval, prof);
		return null;
	}

	
	public String crearTitulaciones() {
		String[] prenombre = new String[] {"Ciencia","Ingenieria","Master en","Doctorado en"};
		String[] nombre = new String[] {"Biologica","Informática","de la salud","estadistica","html"};
		Random rand = new Random();
		Titulacion ptr;
		for(int i=0;i<10;i++) {
			ptr = new Titulacion();
			ptr.setNombreTitulacion(prenombre[rand.nextInt(prenombre.length)]+" "+nombre[rand.nextInt(nombre.length)]);
			try {
				titulacionService.crear(ptr, usuarioService.findById(usuarioService.findByEmail("administrador0@uma.es"),1l));
			} catch (CuentaInexistenteException | ContraseniaInvalidaException | CuentaInactivaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}


	  public void crearCoordinadores() {
		  List<Usuario> tutores = new ArrayList<Usuario>();
		  tutores.add(usuarioService.findByEmail("profesor0@uma.es"));
		  List<Actividad> actividades = actividadesService.findAll();
		  for(int i=0;i<actividades.size();i++) {
			  if(i%2==0) {
				  Actividad act =  actividades.get(i);
				  act.setCoordinadores(new ArrayList(tutores));
			  }
			 
		  }
		  Usuario u = usuarioService.findByEmail("profesor0@uma.es");
		  u.setActividades_coordinadas(actividades);
		  actividadesService.coordinar(u);
		  
	  }
	  

	public void eliminarActividad() {
		  Actividad act;
		  act = new Actividad();
		  actividadesService.eliminarActividad(actividadesService.findById((long) 6));
	}
	public void crearValoraciones() {
		String [] valor = new String[] {"Muy buena actividad.", "Creo que deberían mejorar un poco", "Me encantó, recomiendo a todos","El profesor era muy malo","No me gustó"};;
		  Valoracion val;
		  List<Actividad> actividades = actividadesService.findAll();
		  
	        for(int i = 0 ; i < 5; i++) {
	        	List<Valoracion> list = new ArrayList<Valoracion>();
	        	val = new Valoracion();
	        	val.setIdValoracion(null);
	        	val.setValoracionNum(4);
	        	val.setComentario(valor[i]);
				val.setActividad(actividades.get(i));
	        	val.setUsuario(usuarioService.findByEmail("normal0@uma.es"));
	        	list.add(val);
		        actividades.get(i).setValoraciones(list);
				actividadesService.modificarActividad(actividades.get(i));
		
	        	
	        }
}
	



}


