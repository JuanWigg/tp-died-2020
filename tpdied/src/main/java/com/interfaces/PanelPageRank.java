package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.logica.Grafo;
import com.logica.Planta;
import com.logica.PlantaDAOImplSQL;

public class PanelPageRank extends JPanel {
	JTable tablaRank;
	JButton botonAtras;
	ArrayList<String> columnasTabla;
	List<Planta> plantas;
	Map<String,Double> pageRanks;
	ModeloTablaPageRank model;
	public PanelPageRank() {
		super();
		plantas = (new PlantaDAOImplSQL().consultarPlantas());
		inicializarComponentes();
		armarPanel();
	}
	
	public void inicializarComponentes() {
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelAlgoritmos());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		tablaRank  = new JTable();
		tablaRank.setMinimumSize(new Dimension(1000,300));
		construirTabla(setearColumnas(), obtenerMatrizDatos());
	}
	
	public void armarPanel() {
		this.setLayout(new BorderLayout());
		JPanel panelPrincipal = new JPanel();
		JPanel panelInferior = new JPanel();
		JPanel panelSuperior = new JPanel();
		
		JLabel titulo = new JLabel("PLANTAS SEGUN SU PAGERANK");
		JScrollPane scPaneRank = new JScrollPane(tablaRank);
		scPaneRank.setMinimumSize(new Dimension(1000,300));
		panelPrincipal.setLayout(new GridBagLayout());
		panelInferior.setLayout(new GridBagLayout());
		panelSuperior.setLayout(new GridBagLayout());
		this.add(panelPrincipal, BorderLayout.CENTER);
		this.add(panelInferior, BorderLayout.SOUTH);
		this.add(panelSuperior, BorderLayout.NORTH);
		
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagConstraints gbcInf = new GridBagConstraints();
		GridBagConstraints gbcSup = new GridBagConstraints();
		
	
		panelSuperior.add(titulo,gbc);
		
		gbcInf.insets = new Insets(10,10,10,10);
		gbcInf.weightx=1.0;
		gbcInf.weighty=1.0;
		gbcInf.anchor=GridBagConstraints.WEST;
		
		gbc.fill=GridBagConstraints.BOTH;
		gbc.insets = new Insets(10,2,2,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelPrincipal.add(scPaneRank,gbc);
		panelInferior.add(botonAtras,gbcInf);
	}
	
	
	private Object[][] obtenerMatrizDatos(){
		String informacion[][] = new String[plantas.size()][columnasTabla.size()];
		Grafo g = new Grafo();
		g.pageRank();
		pageRanks = g.pr;
		for(int i=0; i<informacion.length ; i++) {
			informacion[i][0] = plantas.get(i).getNombre() + "";
			informacion[i][1] = pageRanks.get(plantas.get(i).getNombre()) + "";
		}
		return informacion;
	}
	public void construirTabla(String[] columnas, Object[][] data) {
		 model = new ModeloTablaPageRank(data, columnas);
		 tablaRank.setModel(model);
		
		//ASIGNO TIPO DE DATOS A CADA COLUMNA
		tablaRank.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldasCamiones("texto"));
		tablaRank.getColumnModel().getColumn(1).setCellRenderer(new GestionCeldasCamiones("numero"));

		
		tablaRank.getTableHeader().setReorderingAllowed(false);
		tablaRank.setRowHeight(25);
		tablaRank.setGridColor(Color.BLACK);
		
		// TAMAÑO DE CADA COLUMNA
		tablaRank.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablaRank.getColumnModel().getColumn(1).setPreferredWidth(15);
	}
	
	
	private String[] setearColumnas() {
		
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Planta");
		columnasTabla.add("PageRank");
		String[] columnas = new String[columnasTabla.size()];
		
		
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		}
	
		return columnas;
		
	}
	
}
