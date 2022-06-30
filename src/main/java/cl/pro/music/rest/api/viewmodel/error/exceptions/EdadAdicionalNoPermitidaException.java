package cl.pro.music.rest.api.viewmodel.error.exceptions;

import cl.pro.music.rest.api.viewmodel.ErrorGenerico;

/**
 * Excepcion para retornar status 404 y mensaje con descripción
 *
 * @author MAguilar
 *
 */
public class EdadAdicionalNoPermitidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ErrorGenerico errorGenerico;

    public EdadAdicionalNoPermitidaException(String message) {
        errorGenerico = new ErrorGenerico(message);
    }

    public EdadAdicionalNoPermitidaException() {
    }

    public ErrorGenerico getErrorGenerico() {
        return errorGenerico;
    }
}
