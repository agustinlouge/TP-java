package tp;

public class ConexionASiMismoException extends ConexionException {

	public ConexionASiMismoException() {
		super("No se puede conectar un dispositivo a sí mismo");
	}
}
