package tp;

import java.util.ArrayList;

public class SistemaRed {

	private static ArrayList<IP> dispositivos;
	private static SistemaRed instancia = null;
	
	
	private SistemaRed() {
		dispositivos = new ArrayList<>();
	}
	
	public static SistemaRed getInstancia() {
		if (instancia == null) {
			instancia = new SistemaRed();
		}
		return instancia;
	}
	
	public static boolean direccionExistente(IP ip) {
		return dispositivos.contains(ip);
	}
	
	public static void agregarIP(IP ip) {
		dispositivos.add(ip);
	}
	
	
}
