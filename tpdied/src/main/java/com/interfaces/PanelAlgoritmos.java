package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelAlgoritmos extends JPanel {
	JButton botonPageRank;
	JButton botonMatriz;
	JButton botonFlujo;
	JButton botonAtras;
	
	
	public PanelAlgoritmos() {
		super();
		inicializarComponentes();
		armarPanel();
	}
	
	public void inicializarComponentes() {
		botonPageRank=new JButton("Ver plantas segun PageRank ");
		botonMatriz=new JButton("Ver Rutas cortas segun distancia y tiempo");
		botonFlujo=new JButton("Ver Flujo Maximo entre plantas");
		botonAtras=new JButton("Atrás");
		
		botonPageRank.setPreferredSize(new Dimension(300, 70));
		botonPageRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window ventana = SwingUtilities.windowForComponent(((JButton) e.getSource()).getParent());
				((JFrame) ventana).setContentPane(new PanelPageRank ());
				ventana.revalidate();
				ventana.repaint();
				
			}
		});
		botonMatriz.setPreferredSize(new Dimension(300, 70));
		botonMatriz.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Window ventana = SwingUtilities.windowForComponent(((JButton) e.getSource()).getParent());
				((JFrame) ventana).setContentPane(new PanelMatrizCaminos());
				ventana.revalidate();
				ventana.repaint();
			}
		});
		botonFlujo.setPreferredSize(new Dimension(300, 70));
		botonFlujo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Window ventana = SwingUtilities.windowForComponent(((JButton) e.getSource()).getParent());
				((JFrame) ventana).setContentPane(new PanelFlujoMax());
				ventana.revalidate();
				ventana.repaint();
			}
		});
		
		botonAtras.setPreferredSize(new Dimension(90, 30));
		botonAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Window ventana = SwingUtilities.windowForComponent(((JButton) e.getSource()).getParent());
				((JFrame) ventana).setContentPane(new PanelPrincipal());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		
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
		panelPrincipal.add(botonPageRank,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelPrincipal.add(botonMatriz,gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelPrincipal.add(botonFlujo,gbc);
		gbcInf.gridx = 0;
		gbcInf.gridy = 1;
		panelInferior.add(botonAtras,gbcInf);
		
	}
	
	
	
	
	
}
