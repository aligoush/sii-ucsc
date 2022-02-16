package es.uma.informatica.sii.tarea2.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Named;

import es.uma.informatica.sii.tarea2.modelo.Titulacion;

@Named
@ApplicationScoped
public class TitulacionService {
	private List<String> nombres = new ArrayList<String>();
	
	public TitulacionService() {
		init();
	}
	private void init() {
		nombres.add("Ingeniería informáctica");
		nombres.add("Ingenieria Aeroespacial");
		nombres.add("Administración de empresas");
		nombres.add("Biología");
		nombres.add("Matemáticas");
		nombres.add("Derecho civil");
		nombres.add("Ciencias ocultas");
	}
	
	
	public List<Titulacion> findAll(){
		List<Titulacion> salida = new ArrayList<Titulacion>();
		Titulacion aux;
		for(int i =0;i<nombres.size();i++) {
			aux= new Titulacion();
			aux.setIdTitulacion(i);
			aux.setNombreTitulacion(nombres.get(i));
			salida.add(aux);
		}
		return salida;
	}
	
}
