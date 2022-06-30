package cl.pro.music.rest.api.viewmodel.error.exceptions;

import cl.pro.music.rest.api.viewmodel.ErrorGenerico;


/**
 * Excepcion para retornar status 204 y mensaje con descripción
 *
 * @author MAguilar
 *
 */
public class NoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ErrorGenerico errorGenerico;

    public NoEncontradoException(String message) {
        errorGenerico = new ErrorGenerico(message);
    }

    public NoEncontradoException() {
    }

    public ErrorGenerico getErrorGenerico() {
        return errorGenerico;
    }
}
