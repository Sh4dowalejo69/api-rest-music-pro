package cl.pro.music.rest.api.viewmodel.error;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import cl.pro.music.rest.api.viewmodel.ErrorGenerico;
import cl.pro.music.rest.api.viewmodel.Errores;
import cl.pro.music.rest.api.viewmodel.error.exceptions.CasoBordeException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.ConflictoException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.ConflictoLdapException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.ConsultaErrorServicioException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.EdadAdicionalNoPermitidaException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.EntidadConflictoException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.ErrorEnServicioExternoException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.InsertaRegistroExcepction;
import cl.pro.music.rest.api.viewmodel.error.exceptions.ItemNoEncontradoException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.JpaException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.MontoVentaInferior;
import cl.pro.music.rest.api.viewmodel.error.exceptions.NoEncontradoException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.PeticionMalFormadaException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.RutNoValidoException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.UpdateValoresNulosException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.ValoresInvalidosException;
import cl.pro.music.rest.api.viewmodel.error.exceptions.YaExisteException;

/**
 * Esta clase personaliza el manejo de excepciones de spring MVC para que
 * retorne un formato especifico code y message sin revelar stacktrace por http.
 *
 */
@RestControllerAdvice
public class ManejoDeErrores {

        public static final String MENSAJE_NULO = "Mensaje nulo";

        private static final String VALORES_NO_VALIDOS_EN_CAMPO = "Valores no validos en campo ";

        private static final String MENSAJE_Y_ERROR = "%s: {}";

        private static final Logger logger = LoggerFactory.getLogger(ManejoDeErrores.class);

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorGenerico> manejarErrorNoControlado(Exception ex) {

                var errorGenerico = new ErrorGenerico(ErrorCodes.ERROR_NO_CONTROLADO, ex.getMessage());
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.ERROR_NO_CONTROLADO, ex);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(errorGenerico);
        }

        @ExceptionHandler(CasoBordeException.class)
        public ResponseEntity<ErrorGenerico> manejarCasoBordeDeNegocio(CasoBordeException ex) {

                logger.error(MENSAJE_Y_ERROR, ErrorCodes.CASO_BORDE, ex);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(ex.getErrorGenerico());
        }

        @ExceptionHandler(PeticionMalFormadaException.class)
        public ResponseEntity<ErrorGenerico> manejarPeticionMalFormada(PeticionMalFormadaException ex) {

                logger.error(MENSAJE_Y_ERROR, ErrorCodes.PETICION_MAL_FORMADA, ex);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ex.getErrorGenerico());
        }

        @ExceptionHandler(ItemNoEncontradoException.class)
        public ResponseEntity<ErrorGenerico> manejarItemNoEncontrado(ItemNoEncontradoException ex) {

                var headers = new HttpHeaders();
                headers.add("code", ErrorCodes.NADA_QUE_MOSTRAR.toString());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers).build();
        }

        @ExceptionHandler(ValoresInvalidosException.class)
        public ResponseEntity<ErrorGenerico> manejarValoresInvalidos(ValoresInvalidosException ex) {

                var errorMessage = String.format("%s : %s", VALORES_NO_VALIDOS_EN_CAMPO, ex.getMessage()
                                .substring(ex.getMessage()
                                                .lastIndexOf(
                                                                "field")
                                                + 1));
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.VALORES_INVALIDOS, ex);
                ex.getErrorGenerico()
                                .setCode(ErrorCodes.VALORES_INVALIDOS);
                ex.getErrorGenerico()
                                .setMessage(errorMessage);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ex.getErrorGenerico());
        }

        @ExceptionHandler(JpaException.class)
        public ResponseEntity<ErrorGenerico> manejarProblemaJpa(JpaException ex) {

                ex.getErrorGenerico()
                                .setCode(ErrorCodes.ERROR_EN_JPA);
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.ERROR_EN_JPA, ex);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(ex.getErrorGenerico());
        }

        @ExceptionHandler(YaExisteException.class)
        public ResponseEntity<ErrorGenerico> manejarRecursoDuplicado(YaExisteException ex) {

                ex.getErrorGenerico()
                                .setCode(ErrorCodes.YA_EXISTE);
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.YA_EXISTE, ex);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(ex.getErrorGenerico());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Errores> valoresNulos(MethodArgumentNotValidException ex) {

                var errores = new Errores();
                errores.setCode(ErrorCodes.VALORES_INVALIDOS);
                ArrayList<String> err = new ArrayList<>();
                for (FieldError error : ex.getBindingResult()
                                .getFieldErrors()) {
                        err.add(error.getField() + ":" + error.getDefaultMessage());
                }
                errores.setErrorList(err);
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.VALORES_INVALIDOS, ex);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                                .body(errores);
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ErrorGenerico> manejarJsonMalEstructurado(HttpMessageNotReadableException ex) {

                logger.error(MENSAJE_Y_ERROR, ErrorCodes.PETICION_MAL_FORMADA, ex);
                var error = new ErrorGenerico();
                error.setCode(ErrorCodes.PETICION_MAL_FORMADA);
                String errorMessage = ex.getMessage() == null ? MENSAJE_NULO : ex.getMessage();
                var message = String.format("%s: %s", VALORES_NO_VALIDOS_EN_CAMPO,
                                errorMessage.substring(errorMessage.lastIndexOf(
                                                "JSON parse error:") + 18,
                                                errorMessage
                                                                .lastIndexOf(
                                                                                "; nested")));
                error.setMessage(message);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(error);
        }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ErrorGenerico> manejarTipoEquivocado(MethodArgumentTypeMismatchException ex) {

                logger.error(MENSAJE_Y_ERROR, ErrorCodes.TIPO_INCORRECTO, ex);
                String errorMessage = ex.getMessage() == null ? MENSAJE_NULO : ex.getMessage();
                var campoEncontrado = errorMessage
                                .substring(errorMessage.lastIndexOf("of type 'java.lang.") + 19,
                                                errorMessage.lastIndexOf("to required"));
                var campoEsperado = errorMessage
                                .substring(errorMessage
                                                .lastIndexOf("required type 'java.lang.") + 25,
                                                errorMessage.lastIndexOf("';"));
                var error = new ErrorGenerico();
                var message = "Se esperaba parametro de tipo: ".concat(campoEsperado)
                                .concat(". Se encontró parametro de "
                                                + "tipo: ")
                                .concat(campoEncontrado);
                error.setCode(ErrorCodes.TIPO_INCORRECTO);
                error.setMessage(message);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                                .body(error);
        }

        @ExceptionHandler(RutNoValidoException.class)
        public ResponseEntity<ErrorGenerico> manejarProblemaRut(RutNoValidoException ex) {

                ex.getErrorGenerico()
                                .setCode(ErrorCodes.RUT_NO_VALIDO);
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.RUT_NO_VALIDO, ex);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                                .body(ex.getErrorGenerico());
        }

        @ExceptionHandler(ErrorEnServicioExternoException.class)
        public ResponseEntity<ErrorGenerico> manejarProblemasConServicioExterno(ErrorEnServicioExternoException ex) {

                ex.getErrorGenerico()
                                .setCode(ErrorCodes.ERROR_EN_SERVICIO_EXTERNO);
                logger.error(MENSAJE_Y_ERROR,ErrorCodes.ERROR_EN_SERVICIO_EXTERNO, ex);
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                                .body(ex.getErrorGenerico());
        }

        @ExceptionHandler(UpdateValoresNulosException.class)
        public ResponseEntity<ErrorGenerico> updateValoresNulos(UpdateValoresNulosException ex) {

                var error = new ErrorGenerico();
                error.setCode(ErrorCodes.VALORES_INVALIDOS);
                error.setMessage(ex.getErrorGenerico().getMessage());

                logger.error(" ", error.toString());
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                                .body(error);
        }

        @ExceptionHandler(InsertaRegistroExcepction.class)
        public ResponseEntity<ErrorGenerico> insertarTicketExcepction(InsertaRegistroExcepction ex) {

                var error = new ErrorGenerico();
                error.setCode(ErrorCodes.ERROR_INSERTAR_REGISTRO);
                error.setMessage(ex.getErrorGenerico().getMessage());

                logger.error(error.toString());
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(error);
        }

        @ExceptionHandler(EntidadConflictoException.class)
        public ResponseEntity<ErrorGenerico> manejarConflictoEntidad(EntidadConflictoException ex) {

                ex.getErrorGenerico()
                                .setCode(ErrorCodes.ENTIDAD_CONFLICTO);
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.ENTIDAD_CONFLICTO, ex);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(ex.getErrorGenerico());
        }

        @ExceptionHandler(EdadAdicionalNoPermitidaException.class)
        public ResponseEntity<ErrorGenerico> manejarConflictoEntidad(EdadAdicionalNoPermitidaException ex) {

                ex.getErrorGenerico()
                                .setCode(ErrorCodes.EDAD_ADICIONAL_NO_PERMITIDA);
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.EDAD_ADICIONAL_NO_PERMITIDA, ex);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(ex.getErrorGenerico());
        }
        
        
        @ExceptionHandler(MontoVentaInferior.class)
        public ResponseEntity<ErrorGenerico> manejarMontoVentaInferior(MontoVentaInferior ex) {

                ex.getErrorGenerico()
                                .setCode(ErrorCodes.MONTO_VENTA_INFERIOR_AL_PAGADO);
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.MONTO_VENTA_INFERIOR_AL_PAGADO, ex);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(ex.getErrorGenerico());
        }

        @ExceptionHandler(NoEncontradoException.class)
        public ResponseEntity<ErrorGenerico> manejarItemNoEncontrado(NoEncontradoException ex) {

                ex.getErrorGenerico()
                                .setCode(ErrorCodes.NO_ENCONTRADO);
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.NO_ENCONTRADO, ex);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(ex.getErrorGenerico());
        }

        @ExceptionHandler(ConflictoLdapException.class)
        public ResponseEntity<ErrorGenerico> manejarConflictoEntidad(ConflictoLdapException ex) {

                ex.getErrorGenerico()
                                .setCode(ErrorCodes.CONFLICTO_LDAP);
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.CONFLICTO_LDAP, ex);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(ex.getErrorGenerico());
        }
        
        @ExceptionHandler(ConflictoException.class)
        public ResponseEntity<ErrorGenerico> manejarConflicto(ConflictoException ex) {

                ex.getErrorGenerico()
                                .setCode(ErrorCodes.CONFLICTO);
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.CONFLICTO_LDAP, ex);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(ex.getErrorGenerico());
        }

        @ExceptionHandler(ConsultaErrorServicioException.class)
        public ResponseEntity<ErrorGenerico> manejarConflictoSoap(ConsultaErrorServicioException ex) {

                ex.getErrorGenerico()
                                .setCode(ErrorCodes.CONFLICTO_SERVICIO_SOAP);
                logger.error(MENSAJE_Y_ERROR, ErrorCodes.CONFLICTO_SERVICIO_SOAP, ex);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(ex.getErrorGenerico());
        }

}
