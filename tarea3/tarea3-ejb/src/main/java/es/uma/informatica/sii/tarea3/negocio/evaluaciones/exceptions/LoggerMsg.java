package es.uma.informatica.sii.tarea3.negocio.evaluaciones.exceptions;

public enum LoggerMsg {
	VACIO("No se ha encontrado la id:"),CREAR("Se ha creado una evaluacion"),
	EDITAR("Se ha editado una evaluacion"),BORRAR("Se ha borrado la evaluacion con id:"),
	BUSCARID("Se ha buscado una evaluacion con la id:"),BUSCAR("Se ha buscado en la base de datos las evaluacion");
	private String msg;
	
	private LoggerMsg(String msg) {
		this.msg=msg;
	}

	@Override
	public String toString() {
		return this.msg;
	}
}
