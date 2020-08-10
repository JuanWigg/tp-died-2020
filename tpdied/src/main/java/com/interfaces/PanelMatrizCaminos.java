package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.logica.Grafo;
import com.logica.Planta;

public class PanelMatrizCaminos extends JPanel{
	JLabel labelCorta;
	JLabel labelRapida;
	JPanel panel;
	JPanel panelInf;
	JTable tablaCortas;
	JTable tablaRapidas;
	ModeloTablaCaminos modelCortas;
	ModeloTablaCaminos modelRapidas;
	JButton botonAtras;
	ArrayList<String> columnasTabla;
	JSplitPane splitVert;
	JSplitPane split;
	JSplitPane split2;
	
	List<Planta> plantas;
	
	public PanelMatrizCaminos() {
		super();
		inicializarComponentes();
		armarPanel();
	}


	private void inicializarComponentes() {
		botonAtras = new JButton("Atras");
		botonAtras.setPreferredSize(new Dimension(90, 30));
		botonAtras.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelAlgoritmos());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		labelCorta= new JLabel("Caminos segun distancia");
		labelRapida= new JLabel("Caminos segun duracion");
		 tablaCortas = new JTable();
		 tablaRapidas = new JTable();
		
		 split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(tablaRapidas),new JScrollPane(tablaCortas));
		 split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,labelCorta,labelRapida);
		 split.setResizeWeight(0.5d);
		 split2.setResizeWeight(0.5d);
		 splitVert = new JSplitPane(JSplitPane.VERTICAL_SPLIT,split2,split);
		 
		 construirTabla(setearColumnas(),obtenerMatrizDatos(0),obtenerMatrizDatos(1));
		
	}
	
	private void armarPanel() {
		panel = new JPanel();
		panelInf = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(splitVert, BorderLayout.CENTER);
		panel.add(panelInf,BorderLayout.SOUTH);
		panelInf.setLayout(new FlowLayout());
		panelInf.add(botonAtras);
		this.add(panel);
		
	}
	
	private Object[][] obtenerMatrizDatos(int tipo) {
		Grafo g = new Grafo();
		double[][] caminos;
		Double[][] result=new Double[g.getPlantas().size()][g.getPlantas().size()];
		if(tipo==0){
			caminos=g.caminosMinimosDistancia();
		for(int i=0;i<g.getPlantas().size();i++){
          for(int j=0;j<g.getPlantas().size();j++){
                	result[i][j]=Double.valueOf(caminos[i][j]);
                }
            }
		}
		
		else if(tipo==1){
			caminos=g.caminosMinimosTiempo();
           for(int i=0;i<g.getPlantas().size();i++){
        	   for(int j=0;j<g.getPlantas().size();j++){
        	  	result[i][j]=Double.valueOf(caminos[i][j]);
                }
            }
		}
		
		return result;
		
	}
	
	
	
	public void construirTabla(String[] columnas, Object[][] dataCorta,Object[][] dataRapida) {
		 modelCortas = new ModeloTablaCaminos(dataCorta, columnas);
		 modelRapidas = new ModeloTablaCaminos(dataRapida, columnas);
		 tablaCortas.setModel(modelCortas);
		 tablaRapidas.setModel(modelRapidas);
		 
		
		//ASIGNO TIPO DE DATOS A CADA COLUMNA
		 for(int i=0;i<dataCorta.length;i++) {
			 tablaCortas.getColumnModel().getColumn(i).setCellRenderer(new GestionCeldasOrdenes("numero"));
			 tablaRapidas.getColumnModel().getColumn(i).setCellRenderer(new GestionCeldasOrdenes("numero"));
		 }
		
		 tablaCortas.getTableHeader().setReorderingAllowed(false);
		 tablaCortas.setRowHeight(25);
		 tablaCortas.setGridColor(Color.BLACK);
		 
		 tablaRapidas.getTableHeader().setReorderingAllowed(false);
		 tablaRapidas.setRowHeight(25);
		 tablaRapidas.setGridColor(Color.BLACK);
		
		// TAMAÑO DE CADA COLUMNA
		 
		 for(int i=0;i<dataCorta.length;i++) {
			 tablaCortas.getColumnModel().getColumn(i).setPreferredWidth(10);
			 tablaRapidas.getColumnModel().getColumn(i).setPreferredWidth(15);
		 }
		 

	}
	
	private String[] setearColumnas() {
		columnasTabla = new ArrayList<String>();
		Grafo g = new Grafo();
		for (Planta p  : g.getPlantas() ) {
			columnasTabla.add(p.getNombre());
		}
		
		String[] columnas = new String[columnasTabla.size()];
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		}
		return columnas;
		
	}
	
}
