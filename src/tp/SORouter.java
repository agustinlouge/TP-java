package tp;

public class SORouter extends SistemaOperativo {

	private TablaRuteo tabla;
	private Dispositivo intDefault;
	
	
	
	public SORouter(String nombre, String version, Dispositivo intDefault) {
		super(nombre, version);
		this.intDefault = intDefault;
		tabla = new TablaRuteo();
	}

	
	
	public boolean pertenece(IP ip) {
		return tabla.pertenece(ip);
	}

	public void tratarPaquete(Paquete p) {
		
		if (p instanceof PaqueteServicio) {			
			
			if (p instanceof Who) {
				String info = "SO: " + nombre + ". versión: " + version;
				PaqueteServicio paq = new SendMessage(p.getIpDest(), p.getIpOrig(), p.getTtl(), info, tabla);
				p.finalizar();
				enviarPaquete(paq);
			} else if (p instanceof ICMPRequest) {
			
				PaqueteServicio paq = new ICMPResponse(p.getIpDest(), p.getIpOrig(), p.getTtl());
				p.finalizar();
				enviarPaquete(paq);
				
			} else if (p instanceof ICMPResponse) {
					//ver timestamp
					System.out.println("Recibido ICMP desde " + p.getIpOrig().toString());
					p.finalizar();
			} else {
				enviarPaquete(p);
			}
			
			
		}else {
		
			
		
			
			if (p.getTtl() > 0) {
				
				p.setTtl(p.getTtl() - 1);
				Paquete paq = ((PaqueteRuteo)p).getSubpaquete();
				
				if (tabla.pertenece(paq.getIpDest())) {
					//CASO 1
					
					enviarPaquete(paq);
					
					
					
					
				}else {
					if (intDefault == null) {
						//CASO 3
						String info = "No es posible enviar a destino.";
						PaqueteServicio paq2 = new SendMessage(p.getIpDest(), p.getIpOrig(), p.getTtl(), info, null);
						enviarPaquete(paq2);
					}else {
						//CASO 2	
						PaqueteRuteo paq2 = new PaqueteRuteo(paq.getIpDest(), intDefault.getIP(), paq.getTtl(), paq);
						intDefault.recibir(paq2);
					}
				}
				
			}
		}
		
	}

	

	public void agregarRuta(Ruta r) throws TablaLlenaException {
		tabla.agregarRuta(r);
	}


	public void enviarPaquete(Paquete p) {
		try {
			
//			System.out.println(tabla.getInterfaz(p.getIpDest()).getIP());
			tabla.getInterfaz(p.getIpDest()).recibir(p);
		} catch (CampoInexistenteException e) {
			System.out.println("El destino " + p.getIpDest() + " es inaccesible.");
		}
		
	}



	public void mostrarTabla() {
		tabla.mostrarTabla();
	}




}
