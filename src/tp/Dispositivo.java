package tp;

public abstract class Dispositivo {
	
	protected IP ip;
	
	public abstract void recibir(Paquete p);
	/* El dispositivo recibe un paquete de llegada*/
	

	public abstract void conectar(Dispositivo d) throws ConexionException;
	/*Conecta un dispositivo con otro, salta excepcion en el caso de que se intente conectar 
	 * un dispositivo a sí mismo o no tenga mas puertos dispoibles*/
	
	
	
	public abstract void enviar(Paquete p); 
	/*Envia un paquete a otro dispositivo de la red. El paquete debe estar inicializado
	 * con sus respectivos componentes.*/
	
	
	public IP getIP() {
		return ip;
	}
	
	public void setIP(int po, int so, int to, int co) throws IngresoIPInvalidaException {
		//setea la ip del dispositivo verificando que no se ingrese una ip invalida
		if(po > 0 && po <= 255 && so >= 0 && so <= 255 && to >= 0 && to <= 255 && co < 255 && co > 0) {
			IP ip = new IP(po, so, to , co);
			if (SistemaRed.direccionExistente(ip)) {
				throw new IngresoIPInvalidaException("La direccion " + ip + " ya se encontraba asignada a otro equipo en el sistema.");
			}else {
				this.ip = ip;
				SistemaRed.agregarIP(ip);
			}
		}else {
			throw new IngresoIPInvalidaException();
		}
		
	}

}
