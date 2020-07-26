package com.logica;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Grafo {
	private List<Tramo> tramos;
	private List<Planta> plantas;

	public Grafo(){
		this.tramos = new ArrayList<Tramo>();
		this.plantas = new ArrayList<Planta>();
	}
	
	public void agregarPlanta(Planta p) {
		this.plantas.add(p);
	}
	
	public void agregarTramo(Tramo t) {
		this.tramos.add(t);
	}
	
	public void conectar(Planta p1,Planta p2,Integer dist,LocalTime tEstimado,Integer pesoM) {
		Tramo t1 = new Tramo(p1,p2,dist,tEstimado,pesoM);
		this.tramos.add(t1);
	}
	
}
