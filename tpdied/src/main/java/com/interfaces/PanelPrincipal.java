/**
 * 
 */
package com.interfaces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Pichi
 *
 */
public class PanelPrincipal extends JPanel{
	JButton opcionesCamiones;
	JButton opcionesPlantas;
	JButton opcionesInsumos;
	JButton opcionesStockInsumo;
	JButton opcionesPedidos;
	
	public PanelPrincipal() {
		super();
		inicializarComponentes();
		armarPanel();
		
		
	}
	
	public void inicializarComponentes() {
		opcionesCamiones = new JButton("Gestionar Camiones");
		opcionesPlantas = new JButton("Gestionar Plantas");
		opcionesInsumos = new JButton("Gestionar Insumos");
		opcionesStockInsumo = new JButton("Gestionar Stocks de Plantas");
		opcionesPedidos = new JButton("Gestionar Pedidos");
		
		opcionesCamiones.setPreferredSize(new Dimension(200,75));
		opcionesPlantas.setPreferredSize(new Dimension(200,75));
		opcionesInsumos.setPreferredSize(new Dimension(200,75));
		opcionesStockInsumo.setPreferredSize(new Dimension(200,75));
		opcionesPedidos.setPreferredSize(new Dimension(200,75));
		
	}
	
	public void armarPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 10, 10, 2);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(opcionesCamiones, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(opcionesPlantas, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(opcionesInsumos, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(opcionesStockInsumo, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(opcionesPedidos, gbc);
	}
}
