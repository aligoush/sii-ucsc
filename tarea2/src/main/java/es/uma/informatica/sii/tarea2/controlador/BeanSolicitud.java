package es.uma.informatica.sii.tarea2.controlador;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import es.uma.informatica.sii.tarea2.modelo.*;
import es.uma.informatica.sii.tarea2.autentificacion.ControlAutorizacion;
/**
 * Session Bean implementation class BeanSolicitud
 * @author Raquel
 */
@Named(value = "beanSolicitud")
@Stateless
@LocalBean
public class BeanSolicitud {
    private ControlAutorizacion ca;
    private List<Solicitud> solicitudes;
    public BeanSolicitud() {
        solicitudes = solicitud();
    }
    public List<Solicitud> getSolicitudes(){
        return solicitudes;
    }
    private List<Actividad> actividad(){
        List<Actividad> us = new ArrayList<>();
        String [] actividades = new String[]{"Taller Vim&Tmux", "Taller Android", "Telegram Bot", "Configurar Hierro", "vSphere Taller", "Jenkins Tutorial", "Git Tutorial", "NodeJS", "PL/SQL", "Repaso BD"};
        Actividad aux;
        for(int i = 0; i < actividades.length; i++){
            aux = new Actividad();
            aux.setIdActividad(i+1);
            aux.setNombre(actividades[i]);
            us.add(aux);
        }
        return us;
    }
    private List<Solicitud> solicitud(){
        List<Solicitud> us = new ArrayList<>();
        Solicitud aux;
        String [] estado = new String[]{"Aceptada", "Sin Seleccionar", "Aceptada", "Sin Seleccionar", "Denegada", "Aceptada", "Sin Seleccionar", "Aceptada", "Sin Seleccionar", "Denegada"};
        List<Usuario> listaUser = usuarios();
        List<Actividad> actividad = actividad();
        for(int i = 0; i < estado.length; i++){
            aux = new Solicitud();
            aux.setIdSolicitud(i+1);
            aux.setFecha(new Date(2020, 3, 24));
            aux.setEstadoSolicitud(estado[i]);
            aux.setUsuario(listaUser.get(i));
            aux.setActividad(actividad.get(i));
            us.add(aux);
        }
        return us;
    }
    private List<Usuario> usuarios(){
        List<Usuario> us = new ArrayList<>();
        String [] nombres = new String[]{"Paco", "Pepe", "Pepa", "Juan", "Manolete", "Antonio", "Maria", "Laura", "Lucas", "Enrique"};
        String [] apellidos1 = new String[]{"Perez", "Montes", "Josefa", "Garcia", "Bellido", "Gonzalez", "Garcia", "Lopez", "Larrosa", "Zamora"};
        String [] apellidos2 = new String[]{"Rodriguez", "Martinez", "Gomez", "Alvarez", "Martin", "Diaz", "Alonso", "Ruiz", "Hernandez", "Gutierrez"};
        String [] tipoUsuario = new String[]{"NORMAL","NORMAL","NORMAL","NORMAL","NORMAL","NORMAL","NORMAL","NORMAL","NORMAL","NORMAL"};
        Usuario u;
        for(int i = 0; i < nombres.length; i++){
            u = new Usuario();
            u.setId((long) i+1);
            u.setNombre(nombres[i]);
            u.setApellido1(apellidos1[i]);
            u.setApellido2(apellidos2[i]);
            u.setTipoUsuario(tipoUsuario[i]);
            us.add(u);
        }
        return us;
    }
    public String setSolicitud(int sol, int i){
        for(Solicitud s: solicitudes){
            if(s.getIdSolicitud()==sol){
                if(i==1) s.setEstadoSolicitud("Aceptada");
                else s.setEstadoSolicitud("Denegada");
            }
        }
        return "solicitud.xhtml";
    }
}
