package es.uma.informatica.sii.tarea2.servicio;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import es.uma.informatica.sii.tarea2.modelo.Actividad;
import es.uma.informatica.sii.tarea2.modelo.Usuario;
import es.uma.informatica.sii.tarea2.modelo.Valoracion;

@Named
@ApplicationScoped
public class ActividadService implements Serializable {
	private static final long serialVersionUID = 1L;

	  /* Fecha restricción
	   *  LocalDate startDate = LocalDate.now(); //start date
	    long start = startDate.toEpochDay();
	    System.out.println(start);

	    LocalDate endDate = LocalDate.of(2021,1,1); //end date
	    long endData = endDate.toEpochDay();
	    System.out.println(start);

	    long randomEpochDay = ThreadLocalRandom.current().longs(start, endData).findAny().getAsLong();
	    System.out.println(LocalDate.ofEpochDay(randomEpochDay)); // random date between the range
	    
	    long end = ThreadLocalRandom.current().longs(randomEpochDay, endData).findAny().getAsLong();
	    System.out.println(LocalDate.ofEpochDay(end)); // random date between the range
	    
	    */
	    
	    public List<Actividad> createActividades(int size) {
	        List<Actividad> list = new ArrayList<Actividad>();
	        String [] actividades = new String[]{"Limpieza de Montañas", "Nanofotos ","Poesía Cuántica","Alquimia Sonora","Construir el Mundo", "Telegram Bot", "Configurar Hierro", "Instalar tarjeta RAID", "Mentiras y Verduras", "Somos arquitectos de nuestra vida"};
	        Actividad act;
	        for(int i = 0 ; i < size; i++) {
	        	boolean validac;
	        	if(i%2==0) {
	        		validac = true;
	        	} else {
	        		validac = false;
	        	}
	        	
	        	int k = i+1;
	        	act = new Actividad();
	        	act.setIdActividad( k);
	        	act.setNombre(actividades[i]+" "+k);
	        	act.setNumPersonasRequeridas(i);
	        	act.setNumHorasRequeridas(i);
	        	act.setImagen("actividad.jpg");
	        	act.setValidacion(validac);
	        	act.setDescripcion(""
	        			+ "Aquí va una descripción de la actividad. Comentario de la actividad."
	        			+ "Aquí va una descripción de la actividad. Comentario de la actividad."
	        			+ "Aquí va una descripción de la actividad. Comentario de la actividad."
	        			+ "");
	        	act.setTipo("voluntariado");
	        	act.setFechaInicio(new Date(2020,2,2));
	        	act.setFechaFin(new Date(2020,2,2));
	        	act.setUbicacion("Calle Zambrano Número "+i);
	          list.add(act);
	        }
	        return list;
	    }
	    
	    public List<Valoracion> createValoraciones(int size, List<Usuario> usuarios, List<Actividad>actividades) {
	    	 	List<Valoracion> list = new ArrayList<Valoracion>();
		        String [] valoraciones = new String[]{"Muy buena actividad.", "Creo que deberían mejorar un poco", "Me encantó, recomiendo a todos","El profesor era muy malo","No me gustó"};
		        Valoracion val;
		        for(int i = 0 ; i < size; i++) {
		        	val = new Valoracion();
		        	val.setIdValoracion((long) i);
		        	val.setValoracionNum(4);
		        	val.setComentario(valoraciones[i]);
					val.setActividad(actividades.get(i));
		        	val.setUsuario(usuarios.get(i));
		        	
		        list.add(val);
		        }	
		        return list;
	    }
	    
	   
	    
	    
	    
	}