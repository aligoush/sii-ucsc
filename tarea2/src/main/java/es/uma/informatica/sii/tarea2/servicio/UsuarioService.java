package es.uma.informatica.sii.tarea2.servicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import es.uma.informatica.sii.tarea2.modelo.Actividad;
import es.uma.informatica.sii.tarea2.modelo.Matriculacion;
import es.uma.informatica.sii.tarea2.modelo.Titulacion;
import es.uma.informatica.sii.tarea2.modelo.Usuario;

@Named
@ApplicationScoped
public class UsuarioService {
     
    private final static String[] colors;
     
    private final static String[] brands;
     
    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";
        
         
        brands = new String[10];
        brands[0] = "BMW";
        brands[1] = "Mercedes";
        brands[2] = "Volvo";
        brands[3] = "Audi";
        brands[4] = "Renault";
        brands[5] = "Fiat";
        brands[6] = "Volkswagen";
        brands[7] = "Honda";
        brands[8] = "Jaguar";
        brands[9] = "Ford";
    }
     
    public List<Usuario> createUsuarios(int size) {
        List<Usuario> list = new ArrayList<Usuario>();
        Usuario u;
        for(int i = 0 ; i < size/4 ; i++) {
        	u = new Usuario();
        	u.setId((long) i);
        	u.setDni(i+""+i+""+i+""+i+""+i);
        	u.setNombre("Antonio"+i);
        	u.setApellido1("García"+i);
        	u.setApellido2("Jiménez"+i);
        	u.setEmail("administrador"+i+"@uma.es");
        	u.setDireccion("Calle Falsa Número "+i);
        	u.setTelefono("952"+i);
        	u.setTipoUsuario("ADMINISTRADOR");
        	u.setImagen("usuario.jpg");
        	u.setDisponibilidad("M");
        	u.setDescripcion(""
        			+ "Esto es un descipcion. Aquí va una descripción del usuario. Aquí va una descripción del usuario. "
        			+ "Aquí va una descripción del usuario. Aquí va una descripción del usuario. "
        			+ "Aquí va una descripción del usuario. Aquí va una descripción del usuario. Aquí va una descripción"
        			+ " del usuario. Aquí va una descripción del usuario. Aquí va una descripción del usuario. Aquí va "
        			+ "una descripción del usuario. Aquí va una descripción del usuario. Aquí va una descripción del u"
        			+ "suario."
        			+ "");
        	u.setContrasena("asdf");
        	u.setNotificacionesCorreo(true);
          list.add(u);
        }
        for(int i = 0 ; i < size/4 ; i++) {
        	u = new Usuario();
        	u.setId((long) (size/4+i));
        	u.setDni(size/3+i+""+i+""+i+""+i+""+i);
        	u.setNombre("Juan"+i);
        	u.setApellido1("Gómez"+i);
        	u.setApellido2("Bueno"+i);
        	u.setEmail("normal"+i+"@uma.es");
        	u.setDireccion("Calle Falsa Número "+i+size/3);
        	u.setTelefono("952"+i+size/3);
        	u.setTipoUsuario("NORMAL");
        	u.setImagen("usuario.jpg");
        	u.setDisponibilidad("T");
        	u.setContrasena("asdf");
            list.add(u);
        }
        for(int i = 0 ; i < size/4 ; i++) {
        	u = new Usuario();
        	u.setId((long) (2*size/4+i));
        	u.setDni(size/4*2+i+""+i+""+i+""+i+""+i);
        	u.setNombre("Pedro"+i);
        	u.setApellido1("González"+i);
        	u.setApellido2("Medina"+i);
        	u.setEmail("profesor"+i+"@uma.es");
        	u.setDireccion("Calle Falsa Número "+i+size/4*2);
        	u.setTelefono("952"+i+size/4*2);
        	u.setTipoUsuario("PROFESOR");
        	u.setImagen("usuario.jpg");
        	u.setDisponibilidad("MT");
        	u.setContrasena("asdf");
        	u.setDescripcion("Esto sale luego en el cuadradito de logros academicos. "
        			+ "Esto sale luego en el cuadradito de logros academicos. "
        			+ "Esto sale luego en el cuadradito de logros academicos. Esto sale luego "
        			+ "en el cuadradito de logros academicos. Esto sale luego en el cuadradito "
        			+ "de logros academicos. Esto sale luego en el cuadradito de logros academi"
        			+ "cos. Esto sale luego en el cuadradito de logros academicos. Esto sale lu"
        			+ "ego en el cuadradito de logros academicos. Esto sale luego en el cuadrad"
        			+ "ito de logros academicos. Esto sale luego en el cuadradito de logros aca"
        			+ "demicos. Esto sale luego en el cuadradito de logros academicos. Esto sal"
        			+ "e luego en el cuadradito de logros academicos. Esto sale luego en el cua"
        			+ "dradito de logros academicos. Esto sale luego en el cuadradito de logros "
        			+ "academicos. Esto sale luego en el cuadradito de logros academicos. Esto "
        			+ "sale luego en el cuadradito de logros academicos. Esto sale luego en el "
        			+ "cuadradito de logros academicos. Esto sale luego en el cuadradito de log"
        			+ "ros academicos. Esto sale luego en el cuadradito de logros academicos. Es"
        			+ "to sale luego en el cuadradito de logros academicos. Esto sale luego en el"
        			+ " cuadradito de logros academicos. Esto sale luego en el cuadradito de log"
        			+ "ros academicos. Esto sale luego en el cuadradito de logros academicos. Es"
        			+ "to sale luego en el cuadradito de logros academicos. Esto sale luego en e"
        			+ "ogros academicos. Esto sale luego en el cuadradito de logros academicos."
        			+ " Esto sale luego en el cuadradito de logros academicos. Esto sale luego "
        			+ "n el cuadradito de logros academicos. Esto sale luego en el cuadradito de");
        	
//        	Matriculacion m1 = new Matriculacion();
//        	Titulacion t= new Titulacion();
//        	t.setNombreTitulacion("Ingeniería Informática");
//        	m1.setTitulacion(t);
//        	u.getMatriculas().add(m1);
        	
        	Actividad a = new Actividad();
        	a.setNombre("Actividad de prueba"+i);
        	a.setIdActividad(i);
        	Actividad a2 = new Actividad();
        	a2.setNombre("Actividad de prueba"+i);
        	a2.setIdActividad(i+400);
        	ArrayList<Actividad> actividades=new ArrayList<Actividad>();
        	actividades.add(a);
        	actividades.add(a2);
        	u.setActividades_coordinadas(actividades);
        	
            list.add(u);
        }
        for(int i = 0 ; i < size/4 ; i++) {
        	u = new Usuario();
        	u.setId((long) (3*size/4+i));
        	u.setDni(size/4*3+i+""+i+""+i+""+i+""+i);
        	u.setNombre("María"+i);
        	u.setApellido1("López"+i);
        	u.setApellido2("Márquez"+i);
        	u.setNombreOrganizacion("Greenpeace"+i);
        	u.setEmail("organizacion"+i+"@uma.es");
        	u.setDireccion("Calle Falsa Número "+i+size/4*3);
        	u.setTelefono("952"+i+size/4*3);
        	u.setTipoUsuario("ORGANIZACION");
        	u.setImagen("usuario.jpg");
        	u.setContrasena("asdf");
        	u.setDescripcion(""
        			+ "Esto es un descipcion. Aquí va una descripción del usuario. Aquí va una descripción del usuario. "
        			+ "Aquí va una descripción del usuario. Aquí va una descripción del usuario. "
        			+ "Aquí va una descripción del usuario. Aquí va una descripción del usuario. Aquí va una descripción"
        			+ " del usuario. Aquí va una descripción del usuario. Aquí va una descripción del usuario. Aquí va "
        			+ "una descripción del usuario. Aquí va una descripción del usuario. Aquí va una descripción del u"
        			+ "suario."
        			+ "");
            list.add(u);
        }
        return list;
    }
     
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }
     
    private String getRandomColor() {
        return colors[(int) (Math.random() * 10)];
    }
     
    private String getRandomBrand() {
        return brands[(int) (Math.random() * 10)];
    }
     
    private int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }
     
    private boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true: false;
    }
     
    public List<String> getColors() {
        return Arrays.asList(colors);
    }
     
    public List<String> getBrands() {
        return Arrays.asList(brands);
    }
    
}