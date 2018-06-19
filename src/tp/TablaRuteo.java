package tp;

import java.util.ArrayList;

public class TablaRuteo {

	private ArrayList<Ruta> rutas;

	
	public TablaRuteo() {
		rutas = new ArrayList<>();
	}
	
	
	
	//Se utiliza para agregar una ruta a la tabla
	public void agregarRuta(Ruta ruta) throws TablaLlenaException { 
		
		
		if (pertenece(ruta.getDireccion())) {
			try {
				if (!(getInterfaz(ruta.getDireccion()) instanceof Terminal)) {					
					ruta.getInterfaz().conectar(getInterfaz(ruta.getDireccion()));
				}else {
					Hub h = new Hub(15);
					h.conectar(getInterfaz(ruta.getDireccion()));
					h.conectar(ruta.getInterfaz());
					eliminarRuta(ruta);
					ruta.setInterfaz(h);
					agregarRuta(ruta);
				}
				rutas.add(ruta);
			} catch (SistemaRedException e) {}
		}else {
			rutas.add(ruta);
		
		}
	}
	
	public void eliminarRuta(Ruta r) throws RutaInexistenteException {
	
		//VER: HAY RUTAS QUE NO SE PUEDEN ELIMINAR
		
		if(!rutas.remove(r)) {
			throw new RutaInexistenteException("La ruta a eliminar no se encontraba en la tabla.");
		}
	}
	
	public boolean pertenece(IP ip) {
		for (Ruta r : rutas) {
			if (ip.esMismaRed(r.getDireccion())) {
				return true;
			}
		}
		return false;
	}
	/*
	public IP getDireccion(Dispositivo interfaz) throws CampoInexistenteException {
		for(Ruta r : rutas) {
			if (r.getInterfaz().getIP().equals(interfaz.getIP())) {
				return r.getDireccion();
			}
		}
		throw new CampoInexistenteException("No se pudo encontrar la interfaz en la tabla.");
	}
	*/
	public Dispositivo getInterfaz(IP ip) throws CampoInexistenteException {
		for(Ruta r : rutas) {
			if (r.getDireccion().esMismaRed(ip)) {
				return r.getInterfaz();
			}
		}
		throw new CampoInexistenteException("No se pudo encontrar la red en la tabla.");
	}
	
	
	public void mostrarTabla() {
		for (Ruta r: rutas) {
			System.out.println(r.getDireccion());
		}
	}
}
