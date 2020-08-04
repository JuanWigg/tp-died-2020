/**
 * 
 */
package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * @author Pichi
 *
 */
public class PanelGestionCamiones extends JPanel {
	
	JLabel labelPatente;
	JLabel labelKmReco;
	JLabel labelCostoKm;
	JLabel labelCostoHora;
	JLabel labelFechaCompra;
	JLabel labelModelo;
	JLabel labelMarca;
	JTextField fieldPatente;
	JTextField fieldKmReco;
	JTextField fieldCostoKm;
	JTextField fieldCostoHora;
	JFormattedTextField fieldFechaCompra;
	JTextField fieldModelo;
	JTextField fieldMarca;
	JTable tablaResultados;
	JButton botonAtras;
	JButton botonBuscar;
	
	public PanelGestionCamiones() {
		super();
		inicializarComponentes();
		armarPanel();
	}
	
	public void inicializarComponentes() {
		//LABELS
		labelPatente = new JLabel("Patente:");
		labelKmReco = new JLabel("Kilometros recorridos:");
		labelCostoKm = new JLabel("Costo por kilometro:");
		labelCostoHora = new JLabel("Costo por hora:");
		labelFechaCompra = new JLabel("Fecha de compra:");
		labelModelo = new JLabel("Modelo:");
		labelMarca = new JLabel("Marca:");
		
		//CAMPOS
		fieldPatente = new JTextField();
		fieldPatente.setPreferredSize(new Dimension(100, 20));
		
		fieldKmReco = new JTextField();
		fieldKmReco.setPreferredSize(new Dimension(100, 20));
		
		fieldCostoKm = new JTextField();
		fieldCostoKm.setPreferredSize(new Dimension(100, 20));
		
		fieldCostoHora = new JTextField();
		fieldCostoHora.setPreferredSize(new Dimension(100, 20));
		
		fieldFechaCompra = new JFormattedTextField();
		fieldFechaCompra.setPreferredSize(new Dimension(100, 20));
		
		fieldModelo = new JTextField();
		fieldModelo.setPreferredSize(new Dimension(100, 20));
		
		fieldMarca = new JTextField();
		fieldMarca.setPreferredSize(new Dimension(100, 20));
		
		//TABLA
		tablaResultados = new JTable();
		
		//BOTONES
		botonAtras = new JButton("Atrás");
		botonBuscar = new JButton("Buscar");
		botonBuscar.setPreferredSize(new Dimension(100, 20));
	}
	
	public void armarPanel() {
		this.setLayout(new BorderLayout());
		
		
		JPanel panelBusqueda = new JPanel();
		JPanel panelTabla = new JPanel();
		JPanel panelAbajo = new JPanel();
		
		panelBusqueda.setLayout(new GridBagLayout());
		panelAbajo.setLayout(new GridBagLayout());
		
		this.add(panelBusqueda, BorderLayout.NORTH);
		this.add(panelTabla);
		this.add(panelAbajo, BorderLayout.SOUTH);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelBusqueda.add(labelPatente, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelBusqueda.add(fieldPatente, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelBusqueda.add(labelKmReco, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		panelBusqueda.add(fieldKmReco, gbc);
		gbc.gridx = 4;
		gbc.gridy = 0;
		panelBusqueda.add(labelCostoHora, gbc);
		gbc.gridx = 5;
		gbc.gridy = 0;
		panelBusqueda.add(fieldCostoHora, gbc);
		gbc.gridx = 6;
		gbc.gridy = 0;
		panelBusqueda.add(labelCostoKm, gbc);
		gbc.gridx = 7;
		gbc.gridy = 0;
		panelBusqueda.add(fieldCostoKm, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelBusqueda.add(labelFechaCompra, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelBusqueda.add(fieldFechaCompra, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		panelBusqueda.add(labelModelo, gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		panelBusqueda.add(fieldModelo, gbc);
		gbc.gridx = 4;
		gbc.gridy = 1;
		panelBusqueda.add(labelMarca, gbc);
		gbc.gridx = 5;
		gbc.gridy = 1;
		panelBusqueda.add(fieldMarca, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 6;
		gbc.gridy = 1;
		panelBusqueda.add(botonBuscar, gbc);
		
	}
}
