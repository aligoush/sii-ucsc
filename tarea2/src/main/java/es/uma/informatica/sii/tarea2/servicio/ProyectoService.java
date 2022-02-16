package es.uma.informatica.sii.tarea2.servicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import es.uma.informatica.sii.tarea2.modelo.Proyecto;
import es.uma.informatica.sii.tarea2.modelo.Actividad;
import es.uma.informatica.sii.tarea2.modelo.Usuario;

@Named
@ApplicationScoped
public class ProyectoService {
     
 
    public List<Proyecto> createProyecto(int size) {
        List<Proyecto> list = new ArrayList<Proyecto>();
        
        Proyecto u;
        for(int i = 0 ; i < size ; i++) {
        	u = new Proyecto();
        	u.setIdProyecto(i);
        	u.setNombre("Proyecto"+i);
        	u.setImagen("usuario.jpg");
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
     
   
     
    private int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }
     
    private boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true: false;
    }
     
}
    