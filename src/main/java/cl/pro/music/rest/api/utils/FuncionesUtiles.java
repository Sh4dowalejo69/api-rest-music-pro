package cl.pro.music.rest.api.utils;

import java.util.Random;

public class FuncionesUtiles {
	
	
	public String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numchars);
    }

	
	public String descripcionFormaPago(Integer codPago) {
		String retorno = "";
		switch(codPago) {
			case 1:
				retorno = "CREDITO";
				break;
			case 2:
				retorno ="CONTADO";
				break;
			case 3:
				retorno ="DEBITO";
				break;
			default:
				retorno ="DESCONOCIDO";
				break;
			
		}
		return retorno;
	}
	
	public String descripcionMoneda(Integer codMoneda) {
		String retorno = "";
		switch(codMoneda) {
			case 1:
				retorno = "DOLARES (USD)";
				break;
			case 2:
				retorno ="PESOS (CLP)";
				break;
			default:
				retorno ="DESCONOCIDO";
				break;
			
		}
		return retorno;
	}
		
	
}
