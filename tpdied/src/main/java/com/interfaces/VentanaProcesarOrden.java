package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.controladores.StockInsumoController;
import com.logica.Grafo;
import com.logica.OrdenPedido;
import com.logica.Planta;
import com.logica.Ruta;


public class VentanaProcesarOrden extends JDialog{
	ArrayList<String> columnasTabla;
	ArrayList<Ruta> listaRutasCortas;
	ArrayList<Ruta> listaRutasRapidas;
	JLabel mensajeError;
	JLabel rutaCorta;
	JLabel rutaRapida;
	JButton atras;
	JButton aceptar;
	JPanel panel;
	JPanel panel2;
	JSplitPane splitVert;
	JSplitPane split;
	JSplitPane split2;
	JTable tablaCortas;
	JTable tablaRapidas;
	ModeloTablaOrdenes modelCortas;
	ModeloTablaOrdenes modelRapidas;
	OrdenPedido ordenPedido;
	
	public VentanaProcesarOrden(OrdenPedido ordenPedido, JFrame padre, boolean modal) {
		super();
		this.ordenPedido = ordenPedido;
		inicializarComponentes();
		armarPanel();
		
		this.setContentPane(panel);
		this.setSize(1024,720);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private void inicializarComponentes() {
		 columnasTabla = new  ArrayList<String>();
		 rutaCorta = new JLabel("Ruta mas Corta");
		 rutaCorta.setHorizontalAlignment(SwingConstants.CENTER);
		 rutaRapida = new JLabel("Ruta mas rapida");
		 rutaRapida.setHorizontalAlignment(SwingConstants.CENTER);
		 atras = new JButton("Atrás");
		 aceptar = new JButton("Aceptar");
		 tablaCortas = new JTable();
		 tablaRapidas = new JTable();
		 split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,tablaCortas,tablaRapidas);
		 split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,rutaCorta,rutaRapida);
		 split.setResizeWeight(0.5d);
		 split2.setResizeWeight(0.5d);
		 splitVert = new JSplitPane(JSplitPane.VERTICAL_SPLIT,split2,split);
		 construirTabla(setearColumnas(), obtenerMatrizDatos(0), obtenerMatrizDatos(1));
		
	}
	
	
	private Object[][] obtenerMatrizDatos(int tipo) {
		ArrayList<Planta> plantasConStock = (new StockInsumoController()).plantasConStockSuficiente(ordenPedido);
		ArrayList<Ruta> caminosPosibles = new ArrayList<Ruta>();
		ArrayList<Ruta> rutas = null;
		Grafo g = new Grafo();
		for(Planta p : plantasConStock) {
			caminosPosibles.addAll(g.caminos(p, ordenPedido.getPlantaDestino()));
		}
		
		if(tipo == 0) {
			listaRutasCortas = (ArrayList<Ruta>) (g.rutaMasCorta(caminosPosibles));
			rutas = listaRutasCortas;
		}
		if(tipo == 1) {
			listaRutasCortas = (ArrayList<Ruta>) (g.rutaMasCortaTiempo(caminosPosibles));
			rutas = listaRutasCortas;
		}
		
		String informacion[][] = new String[rutas.size()][columnasTabla.size()];
		for(int i=0; i<informacion.length ; i++) {
			informacion[i][0] = rutas.get(i).getListaPlantasRuta().get(0).getNombre();
			informacion[i][1] = rutas.get(i).getDistanciaTotal() + "";
			informacion[i][2] = rutas.get(i).getDuracionTotal() + "";
 			informacion[i][3] = rutas.get(i).getMenorPesoMax() + "";
		}
		return informacion;
	}

	private void armarPanel() {
		panel = new JPanel();
		panel2= new JPanel();
		panel.setLayout(new GridBagLayout());
		panel2.setLayout(new FlowLayout());
		this.add(panel,BorderLayout.CENTER);
		this.add(panel2,BorderLayout.SOUTH);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.NONE;
		gbc.insets = new Insets(2,0,2,0);
		panel.add(splitVert,gbc);
		gbc.gridx=0;
		gbc.gridy=0;
		panel2.add(atras);
		gbc.gridx=1;
		gbc.gridy=0;
		panel2.add(aceptar);
		
	}
	
	public void construirTabla(String[] columnas, Object[][] dataCorta, Object[][] dataRapida) {
		 modelCortas = new ModeloTablaOrdenes(dataCorta, columnas);
		 modelRapidas = new ModeloTablaOrdenes(dataRapida, columnas);
		 tablaCortas.setModel(modelCortas);
		 tablaRapidas.setModel(modelRapidas);
		
		//ASIGNO TIPO DE DATOS A CADA COLUMNA
		 tablaCortas.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldasOrdenes("texto"));
		 tablaCortas.getColumnModel().getColumn(1).setCellRenderer(new GestionCeldasOrdenes("numero"));
		 tablaCortas.getColumnModel().getColumn(2).setCellRenderer(new GestionCeldasOrdenes("numero"));
		 tablaCortas.getColumnModel().getColumn(3).setCellRenderer(new GestionCeldasOrdenes("numero"));
		 
		 tablaRapidas.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldasOrdenes("texto"));
		 tablaRapidas.getColumnModel().getColumn(1).setCellRenderer(new GestionCeldasOrdenes("numero"));
		 tablaRapidas.getColumnModel().getColumn(2).setCellRenderer(new GestionCeldasOrdenes("numero"));
		 tablaRapidas.getColumnModel().getColumn(3).setCellRenderer(new GestionCeldasOrdenes("numero"));
		 
		 tablaCortas.getTableHeader().setReorderingAllowed(false);
		 tablaCortas.setRowHeight(25);
		 tablaCortas.setGridColor(Color.BLACK);
		
		 
		 tablaRapidas.getTableHeader().setReorderingAllowed(false);
		 tablaRapidas.setRowHeight(25);
		 tablaRapidas.setGridColor(Color.BLACK);
		// TAMAÑO DE CADA COLUMNA
		 tablaCortas.getColumnModel().getColumn(0).setPreferredWidth(30);
		 tablaCortas.getColumnModel().getColumn(1).setPreferredWidth(15);
		 tablaCortas.getColumnModel().getColumn(2).setPreferredWidth(20);
		 tablaCortas.getColumnModel().getColumn(3).setPreferredWidth(20);
		 
		 
		 tablaRapidas.getColumnModel().getColumn(0).setPreferredWidth(30);
		 tablaRapidas.getColumnModel().getColumn(1).setPreferredWidth(15);
		 tablaRapidas.getColumnModel().getColumn(2).setPreferredWidth(20);
		 tablaRapidas.getColumnModel().getColumn(3).setPreferredWidth(20);
	}
	
	private String[] setearColumnas() {
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Planta de Origen");
		columnasTabla.add("Distancia");
		columnasTabla.add("Duración estimada");
		columnasTabla.add("Menor peso máximo");
		
		
		String[] columnas = new String[columnasTabla.size()];
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		}
		return columnas;
		
	}
	
}