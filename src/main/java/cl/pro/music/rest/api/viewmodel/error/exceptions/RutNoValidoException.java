package cl.pro.music.rest.api.viewmodel.error.exceptions;


import cl.pro.music.rest.api.viewmodel.ErrorGenerico;
import cl.pro.music.rest.api.viewmodel.error.ErrorCodes;

/**
 * Excepcion para retornar status 400, code y message
 *
 */
public class RutNoValidoException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private ErrorGenerico errorGenerico;

  public RutNoValidoException(ErrorCodes code, String message) {

    errorGenerico = new ErrorGenerico(code, message);
  }

  public RutNoValidoException(String message) {

    errorGenerico = new ErrorGenerico(message);
  }

  public ErrorGenerico getErrorGenerico() {

    return errorGenerico;
  }

}
