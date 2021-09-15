package controller;
import java.util.concurrent.Semaphore;

public class Vendasingressos extends  Thread{
		private int usuarios;
		private int ingressos;
		private Semaphore semaphore;
		
	public Vendasingressos(int usuarios, int ingressos, Semaphore semaphore) {
		this.usuarios= usuarios;
		this.ingressos =ingressos;
		this.semaphore = semaphore;
	}
	
	public void run() {

		usuarios = login(usuarios);
		usuarios = processocompra(usuarios);
		try {
			semaphore.acquire();
			validarcomprar (usuarios,ingressos);
		} catch (Exception error) {
	          error.printStackTrace();
	    } 
	    finally {
	        semaphore.release();
	    }
	}

	

	private int login(int usuarios) {

		int tempo = (int)((Math.random() * 2000) + 50);
		System.out.println(tempo +" "+ usuarios);
		int aux = 0;
		try {
			sleep(tempo);
			if(tempo>3000) {
				System.err.println(usuarios + " ==>Timeout");
			}else {
				aux = usuarios;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return aux;
	}
	
	private int processocompra(int usuarios) {
		int tempo = (int)((Math.random() * 3000) + 1000);
		int aux=0;
		System.err.println(tempo + usuarios);
		try {
			sleep(tempo);
			if(tempo>5500) {
				System.err.println(usuarios + "Timeout");
			}else {
				aux = usuarios;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return aux;
	}
	
	private void validarcomprar(int usuarios, int ingressos) {
	int total = 100;
		if(total!=0) {
			total = ingressos - total;
			System.err.println("Usuario " + usuarios + " Comprou " + ingressos);
			System.out.println("Quantidade vendida " + "Quantidade Disponivel "+ total);
			
		}
		
		
	}
}
