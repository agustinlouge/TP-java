package tp;

import java.util.ArrayList;

public class Hub extends Dispositivo {

	private ArrayList<Dispositivo> puertos;
	private final int cantPuertos;
	
	
	
	//La cantidad de puertos del Hub se define a nivel constructor pasando como parámetro.
	public Hub(int cant) { 
		
		cantPuertos = cant;
		puertos = new ArrayList<>();
	}
	
	
	@Override
	public void recibir(Paquete p) {
		
		/*El hub lo unico que hace es recibir el paquete y reenviarlo a todos los 
		 * dispositivos que se encuentren conectados al mismo
		 */
		enviar(p);
	}


	@Override
	public void conectar(Dispositivo d) throws ConexionException {
		
		if(ip == d.getIP()) {
			throw new ConexionASiMismoException();
		}else {
			if (!(puertos.contains(d))) {
				// El dispositivo no se encontraba conectado anteriormente.
				if (puertos.size() == cantPuertos) {
					throw new HubLlenoException("El hub está lleno, no se puede conectar.");
				} else {
					puertos.add(d);
					d.conectar(this); //Se hace la conexión desde el otro dispositivo hacia el hub.
				}
			} 
		}
		
	}

	
	@Override
	public void enviar(Paquete p) {
		for (Dispositivo x : puertos) {
			x.recibir(p);
		}
		
		//Reenvía el paquete a todos los dispositivos conectados al Hub.

	}

}
