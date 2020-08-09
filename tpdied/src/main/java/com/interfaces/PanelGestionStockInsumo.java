/**
 * 
 */
package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.controladores.InsumoController;
import com.controladores.PlantaController;
import com.controladores.StockInsumoController;
import com.logica.Camion;
import com.logica.CamionDAOImplSQL;
import com.logica.Insumo;
import com.logica.Planta;
import com.logica.StockInsumo;

/**
 * @author Pichi
 *
 */
public class PanelGestionStockInsumo extends JPanel{
	ArrayList<String> columnasTabla;
	JLabel labelPlanta;
	JLabel labelInsumo;
	JComboBox<String> comboPlanta;
	JComboBox<String> comboInsumo;
	JTable tablaStocks;
	ModeloTablaStocks model;
	JButton botonAtras;
	JButton botonAgregarStock;
	String[] plantas;
	String[][] insumos;
	ArrayList<StockInsumo> listaStocks;
	HashMap<Integer,Integer> mapaInsumos;
	
	public PanelGestionStockInsumo() {
			super();
			listaStocks = (ArrayList<StockInsumo>) (new StockInsumoController()).consultarStocksInsuficientes();
			mapaInsumos = (new StockInsumoController()).buscarStockTotalInsumos();
			inicializarComponentes(); 
			armarPanel();
		
		
			
		}
	
	
	public void inicializarComponentes() {
		//LABELS
		labelPlanta = new JLabel("Plantas:");
		labelInsumo = new JLabel("Insumos:");
		
		

		plantas = (new PlantaController()).buscarNombresPlanta();
		if(plantas == null) {
			plantas = new String[1];
			plantas[0] = "<Ninguna>";
		}
		comboPlanta = new JComboBox<String>(plantas);
		comboPlanta.setPreferredSize(new Dimension(100,20));
		comboPlanta.addItemListener(new ItemListener() {
            // Listening if a new items of the combo box has been selected.

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				JComboBox comboBox = (JComboBox) e.getSource();

                // The item affected by the event.
                Object item = e.getItem();


                if (e.getStateChange() == ItemEvent.SELECTED) {
                    filtrarTabla();
                }
			}

			
        });
		
		insumos = (new InsumoController()).consultarDescripcionesInsumos();
		if(insumos[0].length == 0) {
			insumos = new String[1][2];
			insumos[0][0] = "<Ninguno>";
		}
		String[] descripcionesInsumo = new String[insumos.length];
		for(int i=0; i<insumos.length; i++) 
			descripcionesInsumo[i] = insumos[i][0];
		
	
		comboInsumo = new JComboBox<String>(descripcionesInsumo);
		comboInsumo.setPreferredSize(new Dimension(100,20));
		comboInsumo.addItemListener(new ItemListener() {
            // Listening if a new items of the combo box has been selected.

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				JComboBox comboBox = (JComboBox) e.getSource();

                // The item affected by the event.
                Object item = e.getItem();


                if (e.getStateChange() == ItemEvent.SELECTED) {
                    filtrarTabla();
                }
			}
        });
		
		//TABLE 
		tablaStocks = new JTable();
		construirTabla(setearColumnas(), obtenerMatrizDatos(listaStocks));
		
		//BUTTONS
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window ventana = SwingUtilities.windowForComponent(((JButton) e.getSource()).getParent());
				((JFrame) ventana).setContentPane(new PanelPrincipal());
				ventana.revalidate();
				ventana.repaint();
			}
		});
		botonAgregarStock = new JButton("Agregar Stock");
		
		
		
		botonAgregarStock.setPreferredSize(new Dimension(100, 30));
		botonAgregarStock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaAgregarStock dialogo = new VentanaAgregarStock( plantas, insumos, (new JFrame()), true);
				listaStocks = (ArrayList<StockInsumo>) (new StockInsumoController()).consultarStocksInsuficientes();
				mapaInsumos = (new StockInsumoController()).buscarStockTotalInsumos();
				construirTabla(setearColumnas(), obtenerMatrizDatos(listaStocks));
			}
			
		});
		botonAtras.setPreferredSize(new Dimension(100, 30));
		
		
		
	}
	
	private void filtrarTabla() {
		// TODO Auto-generated method stub
		ArrayList<Predicate<StockInsumo>> predicados = new ArrayList<Predicate<StockInsumo>>();
		if(!((String) comboPlanta.getSelectedItem()).equals("<Ninguna>"))
			predicados.add(st -> st.getPlanta().getNombre().equals((String) comboPlanta.getSelectedItem()));
		if(!((String) comboInsumo.getSelectedItem()).equals("<Ninguno>"))
			predicados.add(st -> st.getInsumo().getDescripcion().equals((String) comboInsumo.getSelectedItem()));
		
		if(predicados.size()!=0) {
			ArrayList<StockInsumo> result = (ArrayList<StockInsumo>) listaStocks.stream()
		          .filter(predicados.stream().reduce(x->true, Predicate::and))
		          .collect(Collectors.toList());
			construirTabla(setearColumnas(), obtenerMatrizDatos(result));
		}
		else {
			construirTabla(setearColumnas(), obtenerMatrizDatos(listaStocks));
		}
	}
	
	
	public void armarPanel() {
		this.setLayout(new BorderLayout());
		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setLayout(new GridBagLayout());
		JPanel panelInf = new JPanel();
		panelInf.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.add(panelBusqueda, BorderLayout.NORTH);
		this.add(panelInf, BorderLayout.SOUTH);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelBusqueda.add(labelPlanta, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelBusqueda.add(comboPlanta, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelBusqueda.add(labelInsumo, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		panelBusqueda.add(comboInsumo, gbc);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;

		
		panelInf.add(botonAtras);
		panelInf.add(botonAgregarStock);
		this.add(new JScrollPane(tablaStocks), BorderLayout.CENTER);
		
		
	}
	
	private void construirTabla(String[] columnas, Object[][] data) {
		model = new ModeloTablaStocks(data, columnas);
		tablaStocks.setModel(model);
		
		// ASIGNO TIPO DE DATO A CADA COLUMNA 
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.PLANTA).setCellRenderer(new GestionCeldasStocks("texto"));
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.INSUMO).setCellRenderer(new GestionCeldasStocks("texto"));
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.STOCK_PLANTA).setCellRenderer(new GestionCeldasStocks("numero"));
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.PUNTO_PEDIDO).setCellRenderer(new GestionCeldasStocks("numero"));
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.STOCK_TOTAL).setCellRenderer(new GestionCeldasStocks("numero"));
		
		tablaStocks.getTableHeader().setReorderingAllowed(false);
		tablaStocks.setRowHeight(25);
		tablaStocks.setGridColor(Color.BLACK);
		
		// TAMAÑO DE CADA COLUMNA
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.PLANTA).setPreferredWidth(10);
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.INSUMO).setPreferredWidth(20);
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.STOCK_PLANTA).setPreferredWidth(10);
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.PUNTO_PEDIDO).setPreferredWidth(10);
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.STOCK_TOTAL).setPreferredWidth(10);
	}
	
	private Object[][] obtenerMatrizDatos(ArrayList<StockInsumo> listaStocks){
		String informacion[][] = new String[listaStocks.size()][columnasTabla.size()];
		
		for(int i=0; i<informacion.length ; i++) {
			informacion[i][UtilTablaStocks.PLANTA] = listaStocks.get(i).getPlanta().getNombre() + "";
			informacion[i][UtilTablaStocks.INSUMO] = listaStocks.get(i).getInsumo().getDescripcion() + "";
			informacion[i][UtilTablaStocks.STOCK_PLANTA] = listaStocks.get(i).getCantidad() + ""; 
			informacion[i][UtilTablaStocks.PUNTO_PEDIDO] = listaStocks.get(i).getPuntoDePedido() + "";
			informacion[i][UtilTablaStocks.STOCK_TOTAL] = 
					(mapaInsumos.get(listaStocks.get(i).getInsumo().getId()) == null? 
							0:
								mapaInsumos.get(listaStocks.get(i).getInsumo().getId())) + "";
			
		}
		
		
		return informacion;
	}
	
	private String[] setearColumnas() {
		
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Planta");
		columnasTabla.add("Insumo");
		columnasTabla.add("Stock en la planta");
		columnasTabla.add("Punto de pedido");
		columnasTabla.add("Stock total");
		String[] columnas = new String[columnasTabla.size()];
		
		
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		
		}
	
		return columnas;
		
	}
	
}
