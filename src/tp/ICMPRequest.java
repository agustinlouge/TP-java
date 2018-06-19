package tp;

public class ICMPRequest extends PaqueteServicio{

	public ICMPRequest(IP ipOrig, IP ipDest, int ttl) {
		super(ipOrig, ipDest, ttl);
	}

}
