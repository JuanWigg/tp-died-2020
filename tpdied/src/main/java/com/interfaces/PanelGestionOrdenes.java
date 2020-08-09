package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelGestionOrdenes extends JPanel{
	//BOTONES
	JButton botonVerCreadas;
	JButton botonVerProcesadas;
	JButton botonNuevaOrden;
	JButton botonAtras;
	
	
	public PanelGestionOrdenes() {
		super();
		inicializarComponentes();
		armarPanel();
	}
	
	public void inicializarComponentes() {
		botonVerCreadas=new JButton("Ver ordenes en estado \"Creada\" ");
		botonVerProcesadas=new JButton("Ver ordenes procesadas");
		botonNuevaOrden=new JButton("Crear nueva orden");
		botonAtras=new JButton("Atrás");
		
		botonVerCreadas.setPreferredSize(new Dimension(300, 70));
		botonVerCreadas.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Window ventana = SwingUtilities.windowForComponent(((JButton) e.getSource()).getParent());
				((JFrame) ventana).setContentPane(new PanelOrdenesCreadas());
				ventana.revalidate();
				ventana.repaint();
				
			}
		});
		botonVerProcesadas.setPreferredSize(new Dimension(300, 70));
		botonVerProcesadas.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Window ventana = SwingUtilities.windowForComponent(((JButton) e.getSource()).getParent());
				((JFrame) ventana).setContentPane(new PanelOrdenesCreadas());
				ventana.revalidate();
				ventana.repaint();
			}
		});
		botonNuevaOrden.setPreferredSize(new Dimension(300, 70));
		botonNuevaOrden.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Window ventana = SwingUtilities.windowForComponent(((JButton) e.getSource()).getParent());
				((JFrame) ventana).setContentPane(new PanelAgregarPedido());
				ventana.revalidate();
				ventana.repaint();
			}
		});
		
		botonAtras.setPreferredSize(new Dimension(90, 30));
		
	}
	
	
	public void armarPanel() {
		this.setLayout(new BorderLayout());
		
		JPanel panelPrincipal = new JPanel();
		JPanel panelInferior = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		panelInferior.setLayout(new GridBagLayout());
		this.add(panelPrincipal, BorderLayout.CENTER);
		this.add(panelInferior, BorderLayout.SOUTH);
		
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagConstraints gbcInf = new GridBagConstraints();
		
		gbc.fill=GridBagConstraints.NONE;
		gbc.insets = new Insets(10,2,2,10);
		gbcInf.insets = new Insets(10,10,10,10);
		gbcInf.weightx=1.0;
		gbcInf.weighty=1.0;
		gbcInf.anchor=GridBagConstraints.WEST;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelPrincipal.add(botonVerCreadas,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelPrincipal.add(botonVerProcesadas,gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelPrincipal.add(botonNuevaOrden,gbc);
		gbcInf.gridx = 0;
		gbcInf.gridy = 1;
		panelInferior.add(botonAtras,gbcInf);
		
		
		
	}
	
}
