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
	private Double menorPesoMax;
	
	public Ruta(Integer id,List<Planta> plantas) {
		super();
		this.id = id;
		this.listaTramos= new ArrayList<Tramo>();
		this.listaPlantasRuta = plantas;
		this.distanciaTotal=0;
		this.duracionTotal=0d;
		this.menorPesoMax=Double.MAX_VALUE;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public List<Planta> getListaPlantasRuta(){
		return listaPlantasRuta;
	}
	public List<Tramo> getListaTramos() {
		return listaTramos;
	}

	public void setListaPlantasRuta(List<Planta> listaPlantasRuta) {
		this.listaPlantasRuta = listaPlantasRuta;
	}

	public void setDuracionTotal(Double duracionTotal) {
		this.duracionTotal = duracionTotal;
	}

	public void setDistanciaTotal(Integer distanciaTotal) {
		this.distanciaTotal = distanciaTotal;
	}

	public void setMenorPesoMax(Double menorPesoMax) {
		this.menorPesoMax = menorPesoMax;
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

	public Double getMenorPesoMax() {
		return menorPesoMax;
	}
	
	public void calcularMenorPesoMax() {
		Double menor=(double) listaTramos.get(0).getPesoMaximoPermitido();
		for (Tramo t : listaTramos) {
			if(t.getPesoMaximoPermitido()<menor)
				menor=t.getPesoMaximoPermitido();
		}
		this.menorPesoMax=menor;
	}
	
	public Ruta(Integer id, List<Tramo> listaTramos, Double duracionTotal, Integer distanciaTotal,
			Double menorPesoMax) {
		super();
		this.id = id;
		this.listaTramos = listaTramos;
		this.duracionTotal = duracionTotal;
		this.distanciaTotal = distanciaTotal;
		this.menorPesoMax = menorPesoMax;
	}
	public Ruta() {
		// TODO Auto-generated constructor stub
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
	
	public void aumentarCapacidad(Double capacidad) {
		for (Tramo t : listaTramos) {
			t.setCapacidadUsada(t.getCapacidadUsada()+capacidad);
		}
	}
	
	public boolean hayTramoLleno() {
		return (this.listaTramos.stream().filter(t -> t.getCapacidadUsada() >= t.getPesoMaximoPermitido()).count()>0);
		
	}
	public Double capacidadMinRestante() {
		Double menorCap = listaTramos.get(0).getPesoMaximoPermitido()-listaTramos.get(0).getCapacidadUsada();
		for (Tramo t : listaTramos) {
			if((t.getPesoMaximoPermitido()-t.getCapacidadUsada())<menorCap)
				menorCap=t.getPesoMaximoPermitido()-t.getCapacidadUsada();
		}
		return menorCap;
	}
	
	@Override
	public String toString() {
		
		for (Tramo t : listaTramos) {
			System.out.println(t.toString());
		}
		return "Ruta: "+id;
	}
	
	
	
	
}
