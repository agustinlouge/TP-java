package tp;

public class SOTerminal extends SistemaOperativo {
	
	public SOTerminal(String nombre, String version) {
		super(nombre, version);
		
		
	}
	
	public void tratarPaquete(Paquete p, Dispositivo interfaz) { 
		
		if (p instanceof Who) {
			String info = "SO: " + nombre + ". versión: " + version;
			PaqueteServicio paq = new SendMessage(p.getIpDest(), p.getIpOrig(), p.getTtl(), info, null);	//posible clase
			p.finalizar();
			enviarPaquete(paq, interfaz);
			
	
			} else {
				if (p instanceof ICMPRequest) {
					PaqueteServicio paq = new ICMPResponse(p.getIpDest(), p.getIpOrig(), p.getTtl());	
					p.finalizar();
					enviarPaquete(paq, interfaz);
				} else {
					if (p instanceof ICMPResponse) {
						//ver timestamp
						p.finalizar();
						System.out.println("Recibido ICMP desde " + p.getIpOrig().toString());
					}else {
						if (p instanceof SendMessage) {
							
							p.finalizar();
							System.out.println(((SendMessage)p).getInfo());
						}
					}
				}	
		}
	}
	
	public void enviarPaquete(Paquete p, Dispositivo interfaz) {
		if (interfaz.getIP().esMismaRed(p.getIpOrig())) {
			interfaz.recibir(p);
		}else {
			PaqueteRuteo paq = new PaqueteRuteo(p.getIpDest(), p.getIpOrig(), 255, p);
			interfaz.recibir(paq);
		}
	}
	
}
