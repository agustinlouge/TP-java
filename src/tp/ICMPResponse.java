package tp;

public class ICMPResponse extends PaqueteServicio {

	public ICMPResponse(IP ipOrig, IP ipDest, int ttl) {
		super(ipOrig, ipDest, ttl);
	}

}
