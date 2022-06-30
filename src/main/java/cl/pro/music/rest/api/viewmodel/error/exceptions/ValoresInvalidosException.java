package cl.pro.music.rest.api.viewmodel.error.exceptions;

import cl.pro.music.rest.api.viewmodel.ErrorGenerico;

/**
 * Excepcion para retornar cuando los valores de los parametros no esten dentro
 * de los parametros establecidos
 *
 * @author MAguilar
 */
public class ValoresInvalidosException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ErrorGenerico errorGenerico;

    public ValoresInvalidosException(String message) {

        errorGenerico = new ErrorGenerico(message);
    }

    public ErrorGenerico getErrorGenerico() {

        return errorGenerico;
    }

}
