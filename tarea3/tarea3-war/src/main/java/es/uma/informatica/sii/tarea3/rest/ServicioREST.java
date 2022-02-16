package es.uma.informatica.sii.tarea3.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;


import es.uma.informatica.sii.tarea3.negocio.UsuariosEJB;

@Path("/imagenes")
public class ServicioREST {
	@EJB
	private UsuariosEJB usuarioService;
	
	@Context
	private UriInfo uriInfo;
	
	
	@Path("/usuario/{id}")
	@GET
	@Produces()
	public Response getImagenPerfil(@PathParam("id") Long id) {
		if(id==null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		byte[] respuesta = usuarioService.findImagenPerfilById(id);
		if (respuesta==null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		return Response.ok(respuesta, "image/jpeg").build();
		
	}
	
	
}
