package cl.pro.music.rest.api.viewmodel;

import cl.pro.music.rest.api.viewmodel.error.ErrorCodes;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ErrorGenerico {

    private ErrorCodes code;
    private String message;

    public ErrorGenerico(ErrorCodes code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ErrorGenerico(String message) {
        this.message = message;
    }

}
