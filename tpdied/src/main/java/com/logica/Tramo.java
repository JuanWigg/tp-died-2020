package com.logica;

 
public class Tramo {
	private Integer idTramo;
	private Planta plantaOrigen;
	private Planta plantaDestino;
	private Integer distancia;
	private Double duracionEstimada;
	private Integer pesoMaximoPermitido; 
	private String unidadPesoMax;
	public Tramo() {
		idTramo=null;
		plantaOrigen= new Planta();
		plantaDestino= new Planta();
		distancia=0;
		duracionEstimada=Double.MIN_VALUE;
		pesoMaximoPermitido=0;
	}

	public Tramo(Integer idT,Planta plantaOrigen, Planta plantaDestino, Integer distancia, Double duracionEstimada,
			Integer pesoMaximoPermitido,String unidad) {
		super();
		this.idTramo=idT;
		this.plantaOrigen = plantaOrigen;
		this.plantaDestino = plantaDestino;
		this.distancia = distancia;
		this.duracionEstimada = duracionEstimada;
		this.pesoMaximoPermitido = pesoMaximoPermitido;
		this.unidadPesoMax=unidad;
	}
	
	
	public Integer getIdTramo() {
		return idTramo;
	}

	public void setIdTramo(Integer id) {
		this.idTramo = id;
	}
	
	public Planta getPlantaOrigen() {
		return plantaOrigen;
	}

	public void setPlantaOrigen(Planta plantaOrigen) {
		this.plantaOrigen = plantaOrigen;
	}

	public Planta getPlantaDestino() {
		return plantaDestino;
	}

	public void setPlantaDestino(Planta plantaDestino) {
		this.plantaDestino = plantaDestino;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public Double getDuracionEstimada() {
		return duracionEstimada;
	}

	public void setDuracionEstimada(Double duracionEstimada) {
		this.duracionEstimada = duracionEstimada;
	}

	public Integer getPesoMaximoPermitido() {
		return pesoMaximoPermitido;
	}

	public void setPesoMaximoPermitido(Integer pesoMaximoPermitido) {
		this.pesoMaximoPermitido = pesoMaximoPermitido;
	}
	
	public String getUnidad() {
		return unidadPesoMax;
	}

	public void setUnidad(String unidad) {
		this.unidadPesoMax = unidad;
	}
	
	
	@Override
	public String toString() {
		return "Tramo: "+idTramo+" ( "+this.plantaOrigen.getNombre()+" --> "+this.plantaDestino.getNombre()+" )";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Tramo) && ((Tramo)obj).getIdTramo().equals(this.idTramo); 
	}
	
	
	
}
