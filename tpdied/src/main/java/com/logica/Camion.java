package com.logica;

import java.time.LocalDate;
import java.util.function.Predicate;

public class Camion {
	private String patente;
	private Integer kilometrosRecorridos;
	private Double costoPorKilometro;
	private Double costoPorHora;
	private LocalDate fechaCompra;
	private Modelo modelo;

	
	
	public Camion(String patente, int kmReco, double costoXKm, double costoXHora, LocalDate fechaCompra, Modelo modelo) {
		this.patente = patente;
		kilometrosRecorridos = kmReco;
		costoPorKilometro = costoXKm;
		costoPorHora = costoXHora;
		this.fechaCompra = fechaCompra;
		this.modelo = modelo;
	}
	
	public Camion(String patente, int kmReco, double costoXKm, double costoXHora, Modelo modelo) {
		this.patente = patente;
		kilometrosRecorridos = kmReco;
		costoPorKilometro = costoXKm;
		costoPorHora = costoXHora;
		this.fechaCompra = LocalDate.now();
		this.modelo = modelo;
		
	}
	
	public Camion(String patente) {
		this.patente = patente;
	}
	public Camion(String patente, double costoXKm, double costoXHora, Modelo modelo) {
		this.patente = patente;
		kilometrosRecorridos = 0;
		costoPorKilometro = costoXKm;
		costoPorHora = costoXHora;
		this.fechaCompra = LocalDate.now();
		this.modelo = modelo;
	}
	
	public Camion(String patente, double costoXKm, double costoXHora, String modelo, String marca) {
		this.patente = patente;
		kilometrosRecorridos = 0;
		costoPorKilometro = costoXKm;
		costoPorHora = costoXHora;
		this.fechaCompra = LocalDate.now();
		this.modelo = new Modelo(modelo, marca);
	}
	
	public Camion() {
		// TODO Auto-generated constructor stub
	}

	public Double getCostoPorKilometro() {
		return this.costoPorKilometro;
	}
	
	public Double getCostoPorHora() {
		return this.costoPorHora;
	}
	
	public void sumarKilometros(int km) {
		this.kilometrosRecorridos += km;
	}

	public Integer getKilometrosRecorridos() {
		return kilometrosRecorridos;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setKilometrosRecorridos(int kilometrosRecorridos) {
		this.kilometrosRecorridos = kilometrosRecorridos;
	}

	public void setCostoPorKilometro(double costoPorKilometro) {
		this.costoPorKilometro = costoPorKilometro;
	}

	public void setCostoPorHora(double costoPorHora) {
		this.costoPorHora = costoPorHora;
	}
	
	
	
}
