package tp;

public class IngresoIPInvalidaException extends SistemaRedException {

	public IngresoIPInvalidaException() {
		super("La IP ingresada no es v�lida.");
	}

	public IngresoIPInvalidaException(String string) {
		super(string);
	}
}
