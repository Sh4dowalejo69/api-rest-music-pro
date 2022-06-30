package cl.pro.music.rest.api.viewmodel.error.exceptions;


import cl.pro.music.rest.api.viewmodel.ErrorGenerico;
import cl.pro.music.rest.api.viewmodel.error.ErrorCodes;

/**
 * Excepcion para retornar status 400, code y message
 *
 * @author trufo
 */
public class PeticionMalFormadaException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ErrorGenerico errorGenerico;

    public PeticionMalFormadaException(ErrorCodes code, String message) {

        this.errorGenerico = new ErrorGenerico(code, message);
    }

    public PeticionMalFormadaException(String message) {
        this.errorGenerico = new ErrorGenerico(message);
    }

    public ErrorGenerico getErrorGenerico() {

        return errorGenerico;
    }

}