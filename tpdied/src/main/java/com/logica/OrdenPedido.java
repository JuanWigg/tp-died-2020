package com.logica;

import java.time.LocalDate;

/**
 * @author josesei
 *
 */

import java.util.List;

public class OrdenPedido {
	
	private int nroOrden;
	private LocalDate fechaSolicitud;
	private LocalDate fechaEntrega;
	private EstadoOrden estado;
	private DetalleEnvio detalleEnvio;
	private List<DetalleItem> detalleItems;
	private Planta plantaDestino;

	public DetalleEnvio getDetalleEnvio() {
		return detalleEnvio;
	}

	public void setDetalleEnvio(DetalleEnvio detalleEnvio) {
		this.detalleEnvio = detalleEnvio;
	}

	public int getNroOrden() {
		return nroOrden;
	}
	
	public void setNroOrden(int nroOrden) {
		this.nroOrden = nroOrden;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public EstadoOrden getEstado() {
		return estado;
	}

	public void setEstado(EstadoOrden estado) {
		this.estado = estado;
	}

	public OrdenPedido() {
		// TODO Auto-generated constructor stub
	}
	

	public OrdenPedido(
			int nroOrden, LocalDate fechaSolicitud, LocalDate fechaEntrega, 
			EstadoOrden estado, DetalleEnvio detalleEnvio, List<DetalleItem> detalleItems,
			Planta plantaDestino
	){
		this.nroOrden = nroOrden;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaEntrega = fechaEntrega;
		this.estado = estado;
		this.detalleEnvio = detalleEnvio;
		this.detalleItems = detalleItems;
		this.plantaDestino = plantaDestino;
	}

	public OrdenPedido(int id) {
		// TODO Auto-generated constructor stub
		this.nroOrden = id;
	}

	public OrdenPedido(int nroOrden, LocalDate fechaSolicitud, LocalDate fechaEntrega, EstadoOrden estado, List<DetalleItem> detalleItems, Planta plantaDestino) {
		// TODO Auto-generated constructor stub
		this.nroOrden = nroOrden;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaEntrega = fechaEntrega;
		this.estado = estado;
		this.plantaDestino = plantaDestino;
		this.detalleItems = detalleItems;
	}


	public List<DetalleItem> getDetalleItems() {
		return detalleItems;
	}

	public void setDetalleItems(List<DetalleItem> detalleItems) {
		this.detalleItems = detalleItems;
	}
	public void setPlantaDestino(Planta plantaDestino) {
		this.plantaDestino = plantaDestino;
	}

	public Planta getPlantaDestino() {
		return plantaDestino;
	}

	

}
