package com.logica;

import java.time.LocalTime;
/*TO-DO
 Cambiar tipo de dato de pesoMaximo
  */
public class Tramo {
	private Planta plantaOrigen;
	private Planta plantaDestino;
	private Integer distancia;
	private LocalTime duracionEstimada;
	private Integer pesoMaximoPermitido; 
	
	public Tramo() {
		plantaOrigen= new Planta();
		plantaDestino= new Planta();
		distancia=0;
		duracionEstimada=LocalTime.MIN;
		pesoMaximoPermitido=0;
	}

	public Tramo(Planta plantaOrigen, Planta plantaDestino, Integer distancia, LocalTime duracionEstimada,
			Integer pesoMaximoPermitido) {
		super();
		this.plantaOrigen = plantaOrigen;
		this.plantaDestino = plantaDestino;
		this.distancia = distancia;
		this.duracionEstimada = duracionEstimada;
		this.pesoMaximoPermitido = pesoMaximoPermitido;
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

	public LocalTime getDuracionEstimada() {
		return duracionEstimada;
	}

	public void setDuracionEstimada(LocalTime duracionEstimada) {
		this.duracionEstimada = duracionEstimada;
	}

	public Integer getPesoMaximoPermitido() {
		return pesoMaximoPermitido;
	}

	public void setPesoMaximoPermitido(Integer pesoMaximoPermitido) {
		this.pesoMaximoPermitido = pesoMaximoPermitido;
	}
	
	@Override
	public String toString() {
		return "( "+this.plantaOrigen.getNombre()+" --> "+this.plantaDestino.getNombre()+" )";
	}
	
	
	
	
	
}
