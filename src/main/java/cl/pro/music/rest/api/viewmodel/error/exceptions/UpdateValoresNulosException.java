package cl.pro.music.rest.api.viewmodel.error.exceptions;

import cl.pro.music.rest.api.viewmodel.ErrorGenerico;

/**
 * Excepcion para retornar cuando se intenta actualizar datos de la tabla con
 * valores nulos
 *
 * @author MAguilar
 */
public class UpdateValoresNulosException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ErrorGenerico errorGenerico;

    public UpdateValoresNulosException(String message) {

        errorGenerico = new ErrorGenerico(message);
    }

    public ErrorGenerico getErrorGenerico() {

        return errorGenerico;
    }

}
