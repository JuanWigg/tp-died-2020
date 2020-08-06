package com.logica;

import java.time.LocalDate;
import java.util.List;

public class OrdenPedido {
	
	private String nroOrden;
	private LocalDate fechaSolicitud;
	private LocalDate fechaEntrega;
	private EstadoOrden estado;
	private DetalleEnvio detalleEnvio;
	private List<DetalleItem> detalleItems;

	public DetalleEnvio getDetalleEnvio() {
		return detalleEnvio;
	}

	public void setDetalleEnvio(DetalleEnvio detalleEnvio) {
		this.detalleEnvio = detalleEnvio;
	}

	public String getNroOrden() {
		return nroOrden;
	}

	public void setNroOrden(String nroOrden) {
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

	public List<DetalleItem> getDetalleItems() {
		return detalleItems;
	}

	public void setDetalleItems(List<DetalleItem> detalleItems) {
		this.detalleItems = detalleItems;
	}

}
