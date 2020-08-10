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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	JPanel panelInf;
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
		 atras.setPreferredSize(new Dimension(120, 40));
		 aceptar = new JButton("Aceptar");
		 aceptar.setPreferredSize(new Dimension(120, 40));
		 tablaCortas = new JTable();
		 tablaRapidas = new JTable();
		 tablaCortas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(tablaRapidas.getSelectedRow()!=-1) {
					tablaRapidas.clearSelection();
				}
			}
			 
		 });
		 tablaRapidas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					if(tablaCortas.getSelectedRow()!=-1) {
						tablaCortas.clearSelection();
					}
				}
				 
			 });
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
			rutas.get(i).crearListaTramos(g);
			//rutas.get(i).distanciaRuta();
			//rutas.get(i).duracionRuta();
			//rutas.get(i).calcularMenorPesoMax();
			informacion[i][0] = rutas.get(i).getListaPlantasRuta().get(0).getNombre();
			informacion[i][1] = rutas.get(i).getDistanciaTotal() + "";
			informacion[i][2] = rutas.get(i).getDuracionTotal() + "";
 			informacion[i][3] = rutas.get(i).getMenorPesoMax() + "";
		}
		return informacion;
	}

	private void armarPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panelInf = new JPanel();
		panelInf.setLayout(new FlowLayout());
		panel.add(splitVert, BorderLayout.CENTER);
		panel.add(panelInf, BorderLayout.SOUTH);
		panelInf.add(atras);
		panelInf.add(aceptar);
		
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