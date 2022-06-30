package cl.pro.music.rest.api.viewmodel.error.exceptions;

import cl.pro.music.rest.api.viewmodel.ErrorGenerico;

/**
 * Excepcion para retornar status 404 y mensaje con descripción
 *
 * @author MAguilar
 *
 */
public class ItemNoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ErrorGenerico errorGenerico;

    public ItemNoEncontradoException(String message) {
        errorGenerico = new ErrorGenerico(message);
    }

    public ItemNoEncontradoException() {
    }

    public ErrorGenerico getErrorGenerico() {
        return errorGenerico;
    }
}
