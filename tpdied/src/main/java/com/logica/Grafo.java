package com.logica;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Grafo {
	private List<Tramo> tramos;
	private List<Planta> plantas;
	public TreeMap<String,Double> pr;
	
	public Grafo(){
		this.tramos = new ArrayList<Tramo>();
		this.plantas = new ArrayList<Planta>();
		this.pr=new TreeMap<String, Double>();
	}
	
	public Grafo(ArrayList<Tramo> tramos, ArrayList<Planta> plantas){
		this.tramos = tramos;
		this.plantas = plantas;
		this.pr=new TreeMap<String, Double>();
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
	
	public List<Tramo> getTramos() {
		return tramos;
	}

	public void conectar(Planta p1,Planta p2,Integer dist,double tEstimado,Integer pesoM,Integer idT,String uPmax) {
		Tramo t1 = new Tramo(idT,p1,p2,dist,tEstimado,pesoM,uPmax);
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
	public Tramo getTramo(Planta p1,Planta p2) {
		for (Tramo t : this.getTramos()) {
			if(t.getPlantaOrigen() == p1 && t.getPlantaDestino() == p2)
				return t;
		}
		return null;
		
	}
	public List<List<Planta>> caminos(Planta p1,Planta p2){
		List<List<Planta>> salida = new ArrayList<List<Planta>>();
		List<Planta> marcadas = new ArrayList<Planta>();
		marcadas.add(p1);
		buscarCaminosAux(p1,p2,marcadas,salida);
		return salida;
    	
    }
	

	private void buscarCaminosAux(Planta p1,Planta p2,List<Planta> marcadas,
			List<List<Planta>> salida) {
			List<Planta> adyacentes = this.getAdyacentes(p1);
			List<Planta> copiaMarcadas = null;
			for(Planta p: adyacentes) {
				copiaMarcadas=marcadas.stream().collect(Collectors.toList());
				if(p.equals(p2)) {
					copiaMarcadas.add(p2);
					salida.add(new ArrayList<Planta>(copiaMarcadas));
				}
				else {
					if(!copiaMarcadas.contains(p)) {
						copiaMarcadas.add(p);
						this.buscarCaminosAux(p, p2, copiaMarcadas, salida);
					}
				}
			}
	}
	
	public List<Ruta> rutaMasCorta(List<Ruta> rutas) {
		Ruta rMasCorta;
		List<Ruta> masCortas = new ArrayList<Ruta>();
		rMasCorta = rutas.get(0);
		for (Ruta ruta : rutas) {
			if(ruta.getDistanciaTotal()<rMasCorta.getDistanciaTotal())
				rMasCorta=ruta;
		}
		masCortas.add(rMasCorta);
		for (Ruta ruta : rutas) {
			if(ruta.getDistanciaTotal().equals( rMasCorta.getDistanciaTotal()) && ruta.getId()!=rMasCorta.getId()) {
				masCortas.add(ruta);
			}
		}
		
		return masCortas;
	}
	public List<Ruta> rutaMasCortaTiempo(List<Ruta> rutas) {
		Ruta rMasCortaT;
		List<Ruta> masCortasT = new ArrayList<Ruta>();
		rMasCortaT = rutas.get(0);
		for (Ruta ruta : rutas) {
			if(ruta.getDistanciaTotal()<rMasCortaT.getDistanciaTotal())
				rMasCortaT=ruta;
		}
		masCortasT.add(rMasCortaT);
		for (Ruta ruta : rutas) {
			if(ruta.getDuracionTotal().equals( rMasCortaT.getDuracionTotal()) && ruta.getId()!=rMasCortaT.getId()) {
				masCortasT.add(ruta);
			}
		}
		
		return masCortasT;
	}
	
	public List<Planta> esAlcanzadaPor(Planta p) {
		List<Planta> plantas = new ArrayList<Planta>();
		for (Tramo t : tramos) {
			if(t.getPlantaDestino()==p && !plantas.contains(t.getPlantaOrigen()))
				plantas.add(t.getPlantaOrigen());
		}
		return plantas;
	}
	
	
	public Integer gradoEntrada(Planta P){
		Integer res =0;
		for(Tramo t : this.tramos){
			if(t.getPlantaDestino().equals(P)) ++res;
		}
		return res;
	}

	public Integer gradoSalida(Planta P){
		Integer res =0;
		for(Tramo t : this.tramos){
			if(t.getPlantaOrigen().equals(P)) ++res;
		}
		return res;
	}
	
	
	public void pageRank() {
		TreeMap<String,Double> pageAnterior = new TreeMap<String, Double>();
		List<Planta> ady = new ArrayList<Planta>();
		Double nuevoPr=0d;
		for (Planta planta : plantas) {
			pageAnterior.put(planta.getNombre(),1/(double)plantas.size());
			pr.put(planta.getNombre(),1/(double)plantas.size());
		}
		
	for(int i=0;i<plantas.size()-2;i++) {
		for (Planta planta : plantas) {
			ady=esAlcanzadaPor(planta);
			for (Planta pAdy : ady) {
				nuevoPr+=(pageAnterior.get(pAdy.getNombre())/gradoSalida(pAdy));
			}
			pr.replace(planta.getNombre(),nuevoPr.doubleValue());
			nuevoPr=0d;
		}
		pageAnterior.clear();
		pageAnterior.putAll(pr);
	}
		
		return;
	}
	
	
}