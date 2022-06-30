package cl.pro.music.rest.api.viewmodel.error.exceptions;


import cl.pro.music.rest.api.viewmodel.ErrorGenerico;
import cl.pro.music.rest.api.viewmodel.error.ErrorCodes;


public class JpaException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ErrorGenerico errorGenerico;

    public JpaException(ErrorCodes code, String message) {

        errorGenerico = new ErrorGenerico(code, message);
    }

    public JpaException(String message) {
        errorGenerico = new ErrorGenerico(message);
    }

    public ErrorGenerico getErrorGenerico() {

        return errorGenerico;
    }

}
