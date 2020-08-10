package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.controladores.PlantaController;
import com.logica.Grafo;
import com.logica.Planta;
import com.logica.PlantaDAOImplSQL;

public class PanelFlujoMax extends JPanel{
	JLabel labelP1;
	JLabel labelP2;
	JLabel labelResultado;
	JLabel labelFlujo;
	JComboBox<String> comboP1;
	JComboBox<String> comboP2;
	JButton botonCalcular;
	JButton botonAtras;
	String[] plantas;
	Grafo g;
	
	public PanelFlujoMax() {
		super();
		g = new Grafo();
		inicializarComponentes();
		armarPanel();
	}

	public void inicializarComponentes() {
		labelP1 = new JLabel("Planta 1");
		labelP2 = new JLabel("Planta 2");
		labelResultado = new JLabel("Flujo Maximo:");
		labelFlujo = new JLabel();
		
		botonCalcular = new JButton("Calcular");
		botonAtras = new JButton("Atras");
		
		plantas = (new PlantaController()).buscarNombresPlanta();
		if(plantas == null) {
			plantas = new String[1];
			plantas[0] = "<Ninguna>";
		}
		comboP1 = new JComboBox<String>(plantas);
		comboP1.setPreferredSize(new Dimension(100,20));
		
		comboP2 = new JComboBox<String>(plantas);
		comboP2.setPreferredSize(new Dimension(100,20));
		botonCalcular.setPreferredSize(new Dimension(90,30));
		botonCalcular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboP1.getSelectedItem().toString() != "<Ninguna>" && comboP2.getSelectedItem().toString() != "<Ninguna>" ) {
				Optional<Planta> p1 = (new PlantaDAOImplSQL()).consultarPlanta(comboP1.getSelectedItem().toString());
				Optional<Planta> p2 = (new PlantaDAOImplSQL()).consultarPlanta(comboP2.getSelectedItem().toString());
				labelFlujo.setText((g.flujoMax(p1.get(),p2.get())).toString());}
				else {
					labelFlujo.setText("POR FAVOR, SELECCIONE DOS PLANTAS");
				}
			}
			
		});
		botonAtras.setPreferredSize(new Dimension(90, 30));
		botonAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Window ventana = SwingUtilities.windowForComponent(((JButton) e.getSource()).getParent());
				((JFrame) ventana).setContentPane(new PanelAlgoritmos());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
	}

	public void armarPanel() {
		this.setLayout(new BorderLayout());
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		this.add(panelPrincipal, BorderLayout.CENTER);
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill=GridBagConstraints.BOTH;
		gbc.insets = new Insets(10,2,2,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelPrincipal.add(labelP1,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelPrincipal.add(comboP1,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelPrincipal.add(labelP2,gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelPrincipal.add(comboP2,gbc);
		gbc.gridx=0;
		gbc.gridy=2;
		panelPrincipal.add(labelResultado,gbc);
		gbc.gridx=1;
		gbc.gridy=2;
		panelPrincipal.add(labelFlujo,gbc);
		gbc.gridx=0;
		gbc.gridy=3;
		panelPrincipal.add(botonAtras,gbc);
		gbc.gridx=1;
		gbc.gridy=3;
		panelPrincipal.add(botonCalcular,gbc);
		
	}
}
