package tp;


public class Router extends Dispositivo {

	private Dispositivo[] interfaces; // Lista de dispositivos conectados
	private int conectados; //Cantidad actual de dispositivos conectados al Router 
	private SORouter sistema; //Debe ser instalado luego de instanciado el Router con instalar(SORouter); 
	private final int CANT_INTERFACES; 

	
	//La cantidad de interfaces del router se define a nivel constructor pasando como parámetro.
	public Router(int cantInterfaces) {
		CANT_INTERFACES = cantInterfaces;
		interfaces = new Dispositivo[CANT_INTERFACES];
		conectados = 0;
	}
	
	
	public void instalar(SORouter sist) {
		//Instala el sistema operativo del router.
		sistema = sist;
		
	}
	
	public void mostrarTabla() {
		//Muestra la tabla de ruteo.
		sistema.mostrarTabla();
	}

	
	@Override
	public void recibir(Paquete p) {
		
		//Recibe un paquete y verifica que este no haya sido finalizado y que pertenezca al sistema la ip de destino.
		if (!p.fueRecibido() && sistema.pertenece(p.getIpDest())) {
			sistema.tratarPaquete(p);
		}
		
	}

	
	@Override
	public void conectar(Dispositivo d) throws ConexionException {
		
		/*Conecta el router a otro dispositivo verificando que no se conecte 
		 * a sí mismo y que tenga puertos disponibles
		 */
		if (d.getIP()== ip) {
			
			throw new ConexionASiMismoException();
			
		}else {
			
			if (!sistema.pertenece(d.getIP())){
				
				if(conectados == CANT_INTERFACES) {
					throw new RouterLlenoException("El router está lleno, no se puede conectar");
				}else {
					
					conectados++;
					interfaces[conectados] = d;
					
					Ruta r = new Ruta(d.getIP(), d);
					sistema.agregarRuta(r); //Se crea una ruta (direccion - interfaz) y se agrega a la tabla de ruteo.
					
					d.conectar(this); //Se hace la conexión desde el otro dispositivo hacia el Router.
					
				}
				
			}
			
		}
		
	}

	
	public void enviar(Paquete p) {
		/*Solo se pueden generar paquetes de tipo ICMPRequest.
		 * Los paquetes a enviar deben ser inicializados y pasados por parametro.
		 */
		
		if (p instanceof ICMPRequest) {
			sistema.enviarPaquete(p);
		}
		
	}
	
	

	
	
	
}
