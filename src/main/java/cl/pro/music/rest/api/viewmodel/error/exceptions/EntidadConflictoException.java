package cl.pro.music.rest.api.viewmodel.error.exceptions;

import cl.pro.music.rest.api.viewmodel.ErrorGenerico;

/**
 * Excepcion para retornar cuando los valores de una consulta no se encuentre
 * con la sintaxis correcta
 *
 * @author MAguilar
 */
public class EntidadConflictoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final ErrorGenerico errorGenerico;

    public EntidadConflictoException(String message) {

        errorGenerico = new ErrorGenerico(message);
    }

    public ErrorGenerico getErrorGenerico() {

        return errorGenerico;
    }

}
