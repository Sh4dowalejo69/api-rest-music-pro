package cl.pro.music.rest.api.utils;

public class Test {

	public static void main(String[] args) {
		
		FuncionesUtiles funciones = new FuncionesUtiles();
		
		String codigo = funciones.getRandomHexString(12);
		
		System.out.println(codigo);

	}

}
