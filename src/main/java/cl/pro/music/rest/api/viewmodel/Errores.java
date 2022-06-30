package cl.pro.music.rest.api.viewmodel;

import java.util.ArrayList;

import cl.pro.music.rest.api.viewmodel.error.ErrorCodes;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Errores {

    private ErrorCodes code;

    private ArrayList<String> errorList;

    public Errores() {

        this.errorList = new ArrayList<>();
    }

}
