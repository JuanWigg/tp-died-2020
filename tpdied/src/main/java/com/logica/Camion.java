package com.logica;

import java.time.LocalDate;

public class Camion {
	private String patente;
	private int kilometrosRecorridos;
	private double costoPorKilometro;
	private double costoPorHora;
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
	
	public double getCostoPorKilometro() {
		return this.costoPorKilometro;
	}
	
	public double getCostoPorHora() {
		return this.costoPorHora;
	}
	
	public void sumarKilometros(int km) {
		this.kilometrosRecorridos += km;
	}

	public int getKilometrosRecorridos() {
		return kilometrosRecorridos;
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
