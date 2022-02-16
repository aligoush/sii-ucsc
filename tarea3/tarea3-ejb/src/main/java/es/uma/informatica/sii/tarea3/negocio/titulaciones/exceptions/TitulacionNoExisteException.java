package es.uma.informatica.sii.tarea3.negocio.titulaciones.exceptions;

public class TitulacionNoExisteException extends TitulacionException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TitulacionNoExisteException() {
		super();
	}

	public TitulacionNoExisteException(String msg,Long id) {
		super(msg+" "+id);
	}
	
}
