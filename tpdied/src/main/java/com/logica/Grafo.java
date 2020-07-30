package com.logica;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/*Falta:
 *Ver si hace falta la funcion hayCamino,se usara para algo?
 **/
public class Grafo {
	private List<Tramo> tramos;
	private List<Planta> plantas;

	public Grafo(){
		this.tramos = new ArrayList<Tramo>();
		this.plantas = new ArrayList<Planta>();
	}
	
	public Planta getPlanta(Planta p) {
		return this.plantas.get(plantas.indexOf(p));
	}
	
	public Planta getPlanta(String np) {
		for(int i=0;i<plantas.size();i++) {
			if(plantas.get(i).getNombre().equals(np))
				return plantas.get(i);
		}
			return null;
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
	public void conectar(Planta p1,Planta p2,Tramo t) {
		this.tramos.add(t);
	}
	
	public List<Planta> getAdyacentes(Planta p){ 
		List<Planta> ady = new ArrayList<Planta>();
		for( Tramo t : this.tramos){
			if( t.getPlantaOrigen().equals(p)){
				ady.add(t.getPlantaDestino());
			}
		}
		return ady;
	}
	
	public List<Planta> getAdyacentes(String np){ 
		List<Planta> ady = new ArrayList<Planta>();
		for( Tramo t : this.tramos){
			if( t.getPlantaOrigen().getNombre().equals(np)){
				ady.add(t.getPlantaDestino());
			}
		}
		return ady;
	}
	
	public boolean esAdyacente(Planta p1,Planta p2){
    	List<Planta> ady = this.getAdyacentes(p1);
        if(ady.contains(p2))
        	return true;
        else
        	return false;
    }
	
	public Boolean hayCamino(Planta p1,Planta p2) {
    	List<Planta> adyacentes = getAdyacentes(p1);
    	for(Planta pA : adyacentes) {
    		if(pA.equals(p2)) {
    			return true;
    		} else {
    			return hayCamino(pA, p2);
    		}
    	}
    	return false;
    }
	
	
	
	
	
}
