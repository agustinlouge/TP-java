
package tp;

public abstract class Paquete {
	protected IP ipDest;
	protected IP ipOrig;
	protected int ttl;
	protected boolean sent;

	public Paquete(IP ipo, IP ipd, int ttl) {
		ipDest = ipd;
		ipOrig = ipo;
		this.ttl = ttl;
		sent = false;
	}

	
	public void finalizar() {
		//Confirma que el dispositivo de destino recibio el paquete.
		sent = true;
	}
	
	public boolean fueRecibido() {
		//Verifica si el paquete llego a destino o no.
		return sent;
	}
	
	
	public IP getIpDest() {
		return ipDest;
	}

	public void setIpDest(IP ipDest) {
		this.ipDest = ipDest;
	}

	public IP getIpOrig() {
		return ipOrig;
	}

	public void setIpOrig(IP ipOrig) {
		this.ipOrig = ipOrig;
	}

	public int getTtl() {
		return ttl;
	}

	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

	

}
