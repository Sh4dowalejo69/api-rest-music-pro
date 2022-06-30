package cl.pro.music.rest.api.viewmodel.error.exceptions;
import cl.pro.music.rest.api.viewmodel.ErrorGenerico;

public class ConsultaErrorServicioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorGenerico errorGenerico;

    public ConsultaErrorServicioException(String message) {
        errorGenerico = new ErrorGenerico(message);
    }

    public ErrorGenerico getErrorGenerico() {
        return errorGenerico;
    }
}
