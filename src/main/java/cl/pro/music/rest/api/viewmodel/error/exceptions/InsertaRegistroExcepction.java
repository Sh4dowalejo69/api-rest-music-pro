package cl.pro.music.rest.api.viewmodel.error.exceptions;

import cl.pro.music.rest.api.viewmodel.ErrorGenerico;

/**
 * 
 * Excepcion para retornar cuando el insert genera un error
 * 
 * @author alext
 *
 */

public class InsertaRegistroExcepction extends RuntimeException{
	
	  private static final long serialVersionUID = 1L;

	    private ErrorGenerico errorGenerico;

	    public InsertaRegistroExcepction(String message) {

	        errorGenerico = new ErrorGenerico(message);
	    }

	    public ErrorGenerico getErrorGenerico() {

	        return errorGenerico;
	    }

}
