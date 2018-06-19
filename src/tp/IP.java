package tp;


public class IP {
	private int oct1;
	private int oct2;
	private int oct3;
	private int oct4;

	public IP() {
	}
	
	
	public IP(int po, int so, int to, int co) {
		oct1 = po;
		oct2 = so;
		oct3 = to;
		oct4 = co;
	}


	public void setDireccionRed(int po, int so, int to) throws IngresoIPInvalidaException {
		if(po > 0 && po <= 255 && so >= 0 && so <= 255 && to >= 0 && to <= 255) {
			oct1 = po;
			oct2 = so;
			oct3 = to;
			oct4 = 0;
		}else {
			throw new IngresoIPInvalidaException();
		}
	}
	
	

	@Override
	public String toString() {
		return oct1 + "." + oct2 + "." + oct3 + "." + oct4;
	}

	@Override
	public boolean equals(Object ip) {
		if(ip instanceof IP) {
			return ((IP)ip).getOct1() == oct1 && ((IP)ip).getOct2() == oct2 && ((IP)ip).getOct3() == oct3 && ((IP)ip).getOct4() == oct4;
		}
		return false;
	}
	
	
	
	public int getOct1() {
		return oct1;
	}

	public int getOct2() {
		return oct2;
	}

	public int getOct3() {
		return oct3;
	}

	public int getOct4() {
		return oct4;
	}


	public boolean esMismaRed(IP ip) {
		//Verifica si dos direcciones de red son iguales entre sí.
		return ip.getOct1() == oct1 && ip.getOct2() == oct2 && ip.getOct3() == oct3;
	}

	
	
}
