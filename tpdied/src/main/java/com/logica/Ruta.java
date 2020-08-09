package com.logica;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Ruta {
	private Integer id;
	private List<Tramo> listaTramos;
	private List<Planta> listaPlantasRuta;
	private Double duracionTotal;
	private Integer distanciaTotal;
	private Integer menorPesoMax;
	
	public Ruta(Integer id,List<Planta> plantas) {
		super();
		this.id = id;
		this.listaTramos= new ArrayList<Tramo>();
		this.listaPlantasRuta = plantas;
		this.distanciaTotal=0;
		this.duracionTotal=0d;
		this.menorPesoMax=Integer.MAX_VALUE;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Tramo> getListaTramos() {
		return listaTramos;
	}

	public void setListaTramos(List<Tramo> listaTramos) {
		this.listaTramos = listaTramos;
	}

	public Double getDuracionTotal() {
		return duracionTotal;
	}

	public Integer getDistanciaTotal() {
		return distanciaTotal;
	}

	public Integer getMenorPesoMax() {
		return menorPesoMax;
	}
	
	public void calcularMenorPesoMax() {
		Integer menor=listaTramos.get(0).getPesoMaximoPermitido();
		for (Tramo t : listaTramos) {
			if(t.getPesoMaximoPermitido()<menor)
				menor=t.getPesoMaximoPermitido();
		}
		this.menorPesoMax=menor;
	}
	
	public Ruta(Integer id, List<Tramo> listaTramos, Double duracionTotal, Integer distanciaTotal,
			Integer menorPesoMax) {
		super();
		this.id = id;
		this.listaTramos = listaTramos;
		this.duracionTotal = duracionTotal;
		this.distanciaTotal = distanciaTotal;
		this.menorPesoMax = menorPesoMax;
	}
	public void crearListaTramos(Grafo G) {
		for(int i=0;i<this.listaPlantasRuta.size()-1;i++) {
			listaTramos.add(G.getTramo(listaPlantasRuta.get(i),listaPlantasRuta.get(i+1)));
		}
	}
	public void distanciaRuta() {
		for (Tramo t : listaTramos) {
			this.distanciaTotal+=t.getDistancia();
		}
		
		
	}
	public void duracionRuta() {
		for (Tramo t : listaTramos) {
			this.duracionTotal+=t.getDuracionEstimada();
		}
	}
	
	
	@Override
	public String toString() {
		
		for (Tramo t : listaTramos) {
			System.out.println(t.toString());
		}
		return "Ruta: "+id;
	}
	
	
	
	
}
