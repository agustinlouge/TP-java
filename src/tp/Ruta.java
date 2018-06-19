package tp;

public class Ruta {

	private IP direccion;
	private Dispositivo interfaz;
	

	public Ruta(IP ip, Dispositivo disp) {
		direccion = ip;
		interfaz = disp;
	}
	
	@Override
	public boolean equals(Object r) {
		if (r instanceof Ruta) {
			return direccion.equals(((Ruta) r).getDireccion());
		}
		return false;
	}
	
	public IP getDireccion() {
		return direccion;
	}
	public void setDireccion(IP direccion) {
		this.direccion = direccion;
	}
	public Dispositivo getInterfaz() {
		return interfaz;
	}
	public void setInterfaz(Dispositivo interfaz) {
		this.interfaz = interfaz;
	}
	
}
