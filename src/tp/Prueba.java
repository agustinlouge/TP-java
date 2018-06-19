package tp;


public class Prueba {

	public static void main(String[] args) {
		
		SistemaRed sist = SistemaRed.getInstancia();
		
		Terminal t1 = new Terminal("Computadora");
		Terminal t2 = new Terminal("Tablet");
		Terminal t3 = new Terminal("Computadora");
		Terminal t4 = new Terminal("Computadora");
		Terminal t5 = new Terminal("Computadora");
		Terminal t6 = new Terminal("Computadora");
		Terminal t7 = new Terminal("Computadora");
		Terminal t8 = new Terminal("Computadora");
	
		
		SOTerminal s1 = new SOTerminal("Windows", "XP");
		SOTerminal s2 = new SOTerminal("Windows", "XP");
		SOTerminal s3 = new SOTerminal("Windows", "XP");
		SOTerminal s4 = new SOTerminal("Windows", "XP");
		SOTerminal s5 = new SOTerminal("Windows", "XP");
		SOTerminal s6 = new SOTerminal("Windows", "XP");
		SOTerminal s7 = new SOTerminal("Windows", "XP");
		SOTerminal s8 = new SOTerminal("Windows", "XP");
		

		t1.instalar(s1);
		t2.instalar(s2);
		t3.instalar(s3);
		t4.instalar(s4);
		t5.instalar(s5);
		t6.instalar(s6);
		t7.instalar(s7);
		t8.instalar(s8);	
		
		try {
			t1.setDefaultGateway(192, 168, 1, 5);
			t2.setDefaultGateway(192, 168, 1, 5);
			t3.setDefaultGateway(192, 168, 1, 5);
			t4.setDefaultGateway(192, 168, 1, 5);
			t5.setDefaultGateway(192, 168, 2, 5);
			t6.setDefaultGateway(192, 168, 2, 5);
			t7.setDefaultGateway(192, 168, 2, 5);
			t8.setDefaultGateway(192, 168, 2, 5);
		}catch(IngresoIPInvalidaException e){
			System.out.println(e.getMessage());
		}
			
		try {
			t1.setIP(192, 168, 1, 1);
			t2.setIP(192, 168, 1, 2);
			t3.setIP(192, 168, 1, 3);
			t4.setIP(192, 168, 1, 4);
			t5.setIP(192, 168, 2, 1);
			t6.setIP(192, 168, 2, 2);
			t7.setIP(192, 168, 2, 3);
			t8.setIP(192, 168, 2, 4);
		} catch (IngresoIPInvalidaException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		Router r1 = new Router(10);
		Router r2 = new Router(5);
		
		SORouter sr1 = new SORouter("SistRouter", "1", null);
		SORouter sr2 = new SORouter("SistRouter", "1", null);
		
		
		r1.instalar(sr1);
		r2.instalar(sr2);
		
		Hub h1 = new Hub(15);
		Hub h2 = new Hub(12);

		try {
			r1.setIP(192, 168, 1, 5);
			r2.setIP(192, 168, 2, 5);
			h1.setIP(192, 168, 1, 6);
			h2.setIP(192, 168, 2, 6);
		}catch(IngresoIPInvalidaException e) {
			System.out.println(e.getMessage());
		}
			
		
		try {
			t1.conectar(h1);
			t2.conectar(h1);
			t3.conectar(h1);
			t4.conectar(h1);
			t5.conectar(h2);
			t6.conectar(h2);
			t7.conectar(h2);
			t8.conectar(h2);
			
			h1.conectar(r1);
			h2.conectar(r2);
			
			
			
			r1.conectar(r2);

			
		}catch(ConexionException e) {
			System.out.println(e.getMessage());
		}
		
		
		Paquete p = new SendMessage(t1.getIP(), t7.getIP(), 255, "Hola", null);
		Paquete p2 = new Who(t2.getIP(), t6.getIP(), 255);
		Paquete p3 = new ICMPRequest(t3.getIP(), t1.getIP(), 255);
		Paquete p4 = new ICMPResponse(t8.getIP(), t3.getIP(), 255);
		
		
		
		
		t1.enviar(p);
		t2.enviar(p2);
		t3.enviar(p3);
		t8.enviar(p4);
		
		System.out.println("fin");
	}

}
