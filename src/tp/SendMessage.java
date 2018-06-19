package tp;

public class SendMessage extends PaqueteServicio{

	private String info;
	private TablaRuteo tabla;
	
	public SendMessage(IP ipOrig, IP ipDest, int ttl, String info, TablaRuteo tabla) {
		super(ipOrig, ipDest, ttl);
		this.info = info;
		this.tabla = tabla;
	}
	
	public String getInfo() {
		return info;
	}
	
	
}
