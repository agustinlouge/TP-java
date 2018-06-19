package tp;

import java.util.ArrayList;

public class Terminal extends Dispositivo {
	
	private String dispositivo; //Computadora, SmartPhone, Tablet, etc.
	private SOTerminal sistema; //Debe ser instalado luego de instanciado el terminal con instalar(SOTerminal); 
	private Dispositivo interfaz; 
	private ArrayList<IP> direcciones; //Lista de direcciones del terminal
	private IP defaultGateway;
	
	
	
	public Terminal(String disp){
		dispositivo = disp;
		direcciones = new ArrayList<>();
		interfaz = null;
	}
	
	public void agregarDireccion(IP ip) throws IngresoIPInvalidaException {
		
		//Agrego una dirección IP a la lista de direcciones de la terminal.
		if (SistemaRed.direccionExistente(ip)) {
			
			/*si la direccion de ip que se quiere insertar ya existe en el sistema, es decir, un ip de otro 
			 * dispositivo, ya sea terminal, hub o router, salta una excepción.
			 */
			throw new IngresoIPInvalidaException("La direccion " + ip + "ya se encontraba asignada a otro equipo en el sistema.");
			
		}else {
			
			if(!direcciones.contains(ip)) {
				
				//si la dirección de ip no se encuentra en la lista de direcciones de la terminal
				direcciones.add(ip);   
				SistemaRed.agregarIP(ip);
				
			}
		}
		
	}
	
	
	public void setDefaultGateway(int po, int so, int to, int co) throws IngresoIPInvalidaException {
		
		//se define o setea la direccion ip de default de la terminal.
		if(po > 0 && po <= 255 && so >= 0 && so <= 255 && to >= 0 && to <= 255 && co < 255 && co > 0) {
			
			IP ip = new IP(po, so, to , co);
			this.defaultGateway = ip;
			
		}else {
			
			throw new IngresoIPInvalidaException();
			
		}
	}
	
	
	public void instalar(SOTerminal sist) {
		//Se instala el sistema operativo de la terminal
		sistema = sist;
	}
	
	public void recibir(Paquete p) {
		//Si el paquete que recibe no llego a destino y la direccion de destino esta en la lista de direcciones de la terminal, lo procesa.
		if (!p.fueRecibido() && direcciones.contains(p.getIpDest())){
			if (p instanceof PaqueteServicio) {

				sistema.tratarPaquete(p, interfaz);
				
			}
		}
	}
	
	
	public void setIP(int po, int so, int to, int co) throws IngresoIPInvalidaException {
		//Se setea la direccion de ip ingresada
		if(po > 0 && po <= 255 && so >= 0 && so <= 255 && to >= 0 && to <= 255 && co < 255 && co > 0) {
			IP ip = new IP(po, so, to , co);
			
			//Si la ip ingresada ya existe en las direcciones del sistema, entonces salta excepción.
			if (SistemaRed.direccionExistente(ip)) {
				throw new IngresoIPInvalidaException("La direccion " + ip + " ya se encontraba asignada a otro equipo en el sistema.");
			}else {
				//Si no hay errores, se setea la ip y se agrega al sistema. 
				this.ip = ip;
				SistemaRed.agregarIP(ip);
				direcciones.add(ip);
			}
		}else {
			//Si los octetos de la ip no cumplen con los requisitos pedidos, salta excepción.
			throw new IngresoIPInvalidaException();
		}
	}
	
	

	@Override
	public void conectar(Dispositivo d) throws ConexionException {
		
		//Conectar la terminal a otro dispositivo.
		//Se verifica que no se intente conectar la terminal a sí misma.
		
		if (ip == d.getIP()) {
			
			throw new ConexionASiMismoException();
			
		}else {
			
			//Si la interfaz no se encuentra conectada a nada, se la conecta al dispositivo.
			
			if (interfaz == null) {
				interfaz = d;
				d.conectar(this);  //Se conecta el dispositivo a la terminal.
			}
		}
	}
	
	
	public void enviar(Paquete p) {
		
		/*Se chequea si la ip destino del paquete comparte red con el terminal, para determinar
		 * si el paquete será puesto dentro de otro de ruteo.
		 */
			
		boolean existe = false;
		int i = 0;
		while(!existe && i < direcciones.size()) {
			if(p.getIpDest().esMismaRed(direcciones.get(i))) {
				existe = true;
			}else {
				i++;
			}
		}
		
		
		if (existe) {
			//El destino pertenece a la misma red
			interfaz.recibir(p);
		}else {
			//El destino no pertenece a la misma red
			Paquete paq = new PaqueteRuteo(ip, defaultGateway, 255, p);
			interfaz.recibir(paq);
		}
	}
			
	
}



