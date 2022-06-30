package cl.pro.music.rest.api.viewmodel.error.exceptions;

import cl.pro.music.rest.api.viewmodel.ErrorGenerico;

public class CasoBordeException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ErrorGenerico errorGenerico;

    public CasoBordeException(String message) {
        errorGenerico = new ErrorGenerico(message);
    }

    public ErrorGenerico getErrorGenerico() {
        return errorGenerico;
    }
}
