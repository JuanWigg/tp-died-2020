/**
 * 
 */
package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.controladores.StockInsumoController;
import com.logica.StockInsumo;
import com.logica.StockInsumoDAOImplSQL;


/**
 * @author Pichi
 *
 */
public class VentanaAgregarStock extends JDialog {
	JPanel panel;
	JLabel labelPlanta;
	JLabel labelInsumos;
	JLabel labelTituloTabla;
	JLabel labelCantidad;
	JLabel labelPuntoPedido;
	JTextField fieldCantidad;
	JTextField fieldPuntoPedido;
	JComboBox<String> comboInsumos;
	JComboBox<String> comboPlantas;
	JTable tablaStocks;
	JButton botonAceptar;
	JButton botonCancelar;
	ArrayList<StockInsumo> listaStock;
	ArrayList<String> columnasTabla;
	ModeloTablaStocks model;
	 
	public VentanaAgregarStock(String[] plantas, String[][] insumos,  JFrame frame, boolean modal) {
		super(frame, modal);
		listaStock = (ArrayList<StockInsumo>) (new StockInsumoDAOImplSQL()).readAll();
		inicializarComponentes(plantas, insumos);
		armarPanel();
		this.setContentPane(panel);
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private void armarPanel() {
		// TODO Auto-generated method stub
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel panelCamposSuperior = new JPanel();
		panelCamposSuperior.setLayout(new GridBagLayout());
		JPanel panelCamposInferior = new JPanel();
		panelCamposInferior.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		panel.add(panelCamposSuperior, BorderLayout.NORTH);
		panel.add(new JScrollPane(tablaStocks), BorderLayout.CENTER);
		panel.add(panelCamposInferior, BorderLayout.SOUTH);

		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCamposSuperior.add(labelPlanta, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelCamposSuperior.add(comboPlantas, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelCamposSuperior.add(labelInsumos, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelCamposSuperior.add(comboInsumos, gbc);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCamposInferior.add(labelCantidad, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelCamposInferior.add(fieldCantidad, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelCamposInferior.add(labelPuntoPedido, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelCamposInferior.add(fieldPuntoPedido, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panelCamposInferior.add(botonCancelar, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panelCamposInferior.add(botonAceptar, gbc);
		
		
		
	}

	private void inicializarComponentes(String[] plantas, String[][] insumos) {
		// TODO Auto-generated method stub
		//LABELS
		labelPlanta = new JLabel("Planta:");
		labelInsumos = new JLabel("Insumo:");
		labelTituloTabla = new JLabel("Stocks en la planta:");
		labelCantidad = new JLabel("Cantidad a agregar:");
		labelPuntoPedido = new JLabel("Punto de pedido:");
		
		//FIELDS
		fieldCantidad = new JTextField();
		fieldCantidad.setPreferredSize(new Dimension(100, 30));
		fieldPuntoPedido = new JTextField();
		fieldPuntoPedido.setPreferredSize(new Dimension(100,30));
		fieldPuntoPedido.setToolTipText("Si no desea modificar el punto pedido, puede dejar este campo vacio");
		
		//COMBO
		String[] descripcionesInsumo = new String[insumos.length];
		for(int i=0; i<insumos.length; i++) 
			descripcionesInsumo[i] = insumos[i][0];
		comboInsumos = new JComboBox<String>(descripcionesInsumo);
		comboPlantas = new JComboBox<String>(plantas);
		comboInsumos.setPreferredSize(new Dimension(100,30));
		comboPlantas.setPreferredSize(new Dimension(100,30));
		
		comboInsumos.addItemListener(new ItemListener() {
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
		comboPlantas.addItemListener(new ItemListener() {
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
		
		//TABLA
		tablaStocks = new JTable();
		construirTabla(setearColumnas(), obtenerMatrizDatos(listaStock));
		
		//botones
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setPreferredSize(new Dimension (100, 30));
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> datos = new ArrayList<String>();
				if(comboPlantas.getSelectedIndex() == 0 || comboInsumos.getSelectedIndex()==0 || fieldCantidad.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog((JDialog) SwingUtilities.getWindowAncestor(panel), "No puede dejar campos vacios");
				}
				else {
					datos.add((String) comboPlantas.getSelectedItem());
					datos.add((String) insumos[comboInsumos.getSelectedIndex()][1]);
					datos.add((String) fieldCantidad.getText().trim());
					if(fieldPuntoPedido.getText().trim().length()==0)
						datos.add(0 + "");
					else
						datos.add(fieldPuntoPedido.getText().trim());
					
					(new StockInsumoController()).agregarStock(datos);
					JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(panel);
					frame.dispose();
				}
			}
			
		});
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setPreferredSize(new Dimension (100, 30));
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(panel);
				frame.dispose();
			}
			
		});
	}
	
	private void filtrarTabla() {
		// TODO Auto-generated method stub
		ArrayList<Predicate<StockInsumo>> predicados = new ArrayList<Predicate<StockInsumo>>();
		if(!((String) comboPlantas.getSelectedItem()).equals("<Ninguna>"))
			predicados.add(st -> st.getPlanta().getNombre().equals((String) comboPlantas.getSelectedItem()));
		if(!((String) comboInsumos.getSelectedItem()).equals("<Ninguno>"))
			predicados.add(st -> st.getInsumo().getDescripcion().equals((String) comboInsumos.getSelectedItem()));
		
		if(predicados.size()!=0) {
			ArrayList<StockInsumo> result = (ArrayList<StockInsumo>) listaStock.stream()
		          .filter(predicados.stream().reduce(x->true, Predicate::and))
		          .collect(Collectors.toList());
			construirTabla(setearColumnas(), obtenerMatrizDatos(result));
		}
		else {
			construirTabla(setearColumnas(), obtenerMatrizDatos(listaStock));
		}
	}
	
	
	
	private void construirTabla(String[] columnas, Object[][] data) {
		model = new ModeloTablaStocks(data, columnas);
		tablaStocks.setModel(model);
		
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.PLANTA).setCellRenderer(new GestionCeldasStocks("texto"));
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.INSUMO).setCellRenderer(new GestionCeldasStocks("texto"));
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.STOCK_PLANTA).setCellRenderer(new GestionCeldasStocks("numero"));
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.PUNTO_PEDIDO).setCellRenderer(new GestionCeldasStocks("numero"));
		
		tablaStocks.getTableHeader().setReorderingAllowed(false);
		tablaStocks.setRowHeight(25);
		tablaStocks.setGridColor(Color.BLACK);
		
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.PLANTA).setPreferredWidth(10);
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.INSUMO).setPreferredWidth(32);
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.STOCK_PLANTA).setPreferredWidth(10);
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.PUNTO_PEDIDO).setPreferredWidth(10);
		
		
		
	}
	
	
	
	private Object[][] obtenerMatrizDatos(ArrayList<StockInsumo> listaStock){
		String informacion[][] = new String[listaStock.size()][columnasTabla.size()];
		
		for(int i=0; i<informacion.length ; i++) {
			informacion[i][UtilTablaStocks.PLANTA] = listaStock.get(i).getPlanta().getNombre() + "";
			informacion[i][UtilTablaStocks.INSUMO] = listaStock.get(i).getInsumo().getDescripcion() + "";
			informacion[i][UtilTablaStocks.STOCK_PLANTA] = listaStock.get(i).getCantidad() + "";
			informacion[i][UtilTablaStocks.PUNTO_PEDIDO] = listaStock.get(i).getPuntoDePedido() + "";

		}
		
		
		return informacion;
	}
	
	private String[] setearColumnas() {
		
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Planta");
		columnasTabla.add("Insumo");
		columnasTabla.add("Stock en planta");
		columnasTabla.add("Punto de pedido");
		String[] columnas = new String[columnasTabla.size()];
		
		
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		
		}
	
		return columnas;
		
		}
	}	
