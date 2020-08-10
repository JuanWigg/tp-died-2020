package com.interfaces;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelFlujoMax extends JPanel{
	JLabel labelP1;
	JLabel labelP2;
	JLabel labelResultado;
	JLabel labelFlujo;
	JComboBox<String> comboP1;
	JComboBox<String> comboP2;
	JButton botonCalcular;
	JButton botonAtras;
	
	public PanelFlujoMax() {
		super();
		inicializarComponentes();
		armarPanel();
	}

	public void armarPanel() {
		labelP1 = new JLabel("Planta 1");
		labelP2 = new JLabel("Planta 2");
		labelResultado = new JLabel("Flujo Maximo:");
		labelFlujo = new JLabel();
		
		
	}

	public void inicializarComponentes() {
		this.setLayout(new BorderLayout());
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());

	}
}
