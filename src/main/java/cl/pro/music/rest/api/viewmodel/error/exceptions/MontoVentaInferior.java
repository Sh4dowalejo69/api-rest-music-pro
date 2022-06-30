package cl.pro.music.rest.api.viewmodel.error.exceptions;

import cl.pro.music.rest.api.viewmodel.ErrorGenerico;

public class MontoVentaInferior extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

    private ErrorGenerico errorGenerico;

    public MontoVentaInferior(String message) {

        errorGenerico = new ErrorGenerico(message);
    }

    public ErrorGenerico getErrorGenerico() {

        return errorGenerico;
    }
}
