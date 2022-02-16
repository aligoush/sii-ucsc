package es.uma.informatica.sii.tarea3.negocio.titulaciones.exceptions;

public enum LoggerMsg {
	VACIO("No se ha encontrado la id:"),CREAR("Se ha creado una titulacion"),
	EDITAR("Se ha editado una titulacion"),BORRAR("Se ha borrado la Titulacion con id:"),
	BUSCARID("Se ha buscado una Titulacion con la id:"),BUSCAR("Se ha buscado en la base de datos las Titulaciones"), DESENLAZAR("Se ha desenlazado la Titulacion con id:");
	private String msg;
	
	private LoggerMsg(String msg) {
		this.msg=msg;
	}

	@Override
	public String toString() {
		return this.msg;
	}
	
	

}
