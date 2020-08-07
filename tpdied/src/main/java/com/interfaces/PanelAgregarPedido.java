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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.controladores.CamionController;
import com.logica.Camion;
import com.logica.CamionDAOImplSQL;
import com.logica.DetalleItem;
import com.logica.Insumo;
import com.logica.PlantaDAOImplSQL;

/**
 * @author Pichi
 *
 */
public class PanelAgregarPedido extends JPanel{
	JButton botonAceptar;
	JButton botonCancelar;
	JButton botonAgregarItem;
	JLabel labelDestino;
	JLabel labelInsumos;
	ArrayList<DetalleItem> insumosAgregados;
	JComboBox<String> comboPlantas;
	JFormattedTextField fieldFecha;
	JLabel labelFecha;
	JTable tablaItems;
	
	ArrayList<String> columnasTabla;
	ModeloTablaItemsPedido model;
	
	public PanelAgregarPedido() {
		insumosAgregados = new ArrayList<DetalleItem>();
		inicializarComponentes();
		armarPanel();
	}

	private void armarPanel() {
		this.setLayout(new BorderLayout());
		JPanel panelCamposPedido = new JPanel();
		JPanel panelBotonesInferiores = new JPanel();
		
		panelCamposPedido.setLayout(new GridBagLayout());
		panelBotonesInferiores.setLayout(new FlowLayout());
		this.add(panelCamposPedido, BorderLayout.NORTH);
		this.add(panelBotonesInferiores, BorderLayout.SOUTH);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCamposPedido.add(labelDestino, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelCamposPedido.add(comboPlantas, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelCamposPedido.add(labelFecha, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelCamposPedido.add(fieldFecha, gbc);
		
		this.add(new JScrollPane(tablaItems), BorderLayout.CENTER);
		
		panelBotonesInferiores.add(botonCancelar);
		panelBotonesInferiores.add(botonAceptar);
		
		this.add(botonAgregarItem, BorderLayout.EAST);
		
	}

	private void inicializarComponentes() {
		//LABELS
		labelDestino = new JLabel("Planta de destino:");
		labelInsumos = new JLabel("Items del pedido:");
		labelFecha = new JLabel("Fecha de entrega máx.:");
		
		
		//COMBO
		String[] plantas;
		plantas = (new PlantaDAOImplSQL()).buscarNombresPlanta();
		if(plantas == null) {
			plantas = new String[1];
			plantas[0] = "<Ninguna>";
		}
		comboPlantas = new JComboBox<String>(plantas);
		comboPlantas.setPreferredSize(new Dimension(100, 25));
		
		//FIELDS
		fieldFecha = new JFormattedTextField();
		fieldFecha.setPreferredSize(new Dimension(100, 25));
		
		//BOTONES
		botonAceptar = new JButton("Registrar pedido");
		botonAceptar.setPreferredSize(new Dimension(200, 40));
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setPreferredSize(new Dimension(200, 40));
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelGestionOrdenes());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		botonAgregarItem = new JButton("Agregar item");
		botonAgregarItem.setPreferredSize(new Dimension(100, 40));
		botonAgregarItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaAgregarItem dialogo = new VentanaAgregarItem((JPanel) ((JButton) e.getSource()).getParent(), new JFrame(), true);
				// CAMBIAR insumosAgregados = (ArrayList<Camion>) (new CamionDAOImplSQL()).buscarCamiones();
				construirTabla(setearColumnas(), obtenerMatrizDatos());
			}
			
		});
		//TABLE
		tablaItems =  new JTable();
		tablaItems.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int fila = tablaItems.rowAtPoint(e.getPoint());
				int columna = tablaItems.columnAtPoint(e.getPoint());
				
				if(columna==UtilTablaItemsPedido.ELIMINAR) {
					String[] botonesDialogo = {"Aceptar", "Cancelar"};
					int dialogo = JOptionPane.showOptionDialog(null,
							"¿Seguro que desea eliminar el item seleccionado?", 
							"Confirmacion",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.QUESTION_MESSAGE, null,
							botonesDialogo, botonesDialogo[0]
							);
					if(dialogo==0) {
						model.removeRow(fila);
						insumosAgregados.remove(fila);
						model.fireTableDataChanged();
					}
				}
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		construirTabla(setearColumnas(), obtenerMatrizDatos());
		
	}

	private void construirTabla(String[] columnas, Object[][] data) {
		// TODO Auto-generated method stub
		model = new ModeloTablaItemsPedido(data, columnas);
		tablaItems.setModel(model);
		
		//ASIGNO TIPO DE DATOS A CADA COLUMNA
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.NOMBRE_INSUMO).setCellRenderer(new GestionCeldasItemsPedido("texto"));
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.CANTIDAD).setCellRenderer(new GestionCeldasItemsPedido("numero"));
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.PRECIO_UNIDAD).setCellRenderer(new GestionCeldasItemsPedido("numero"));
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.PRECIO_TOTAL).setCellRenderer(new GestionCeldasItemsPedido("numero"));
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.ELIMINAR).setCellRenderer(new GestionCeldasItemsPedido("boton"));
		
				
		tablaItems.getTableHeader().setReorderingAllowed(false);
		tablaItems.setRowHeight(25);
		tablaItems.setGridColor(Color.BLACK);
		
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.NOMBRE_INSUMO).setPreferredWidth(32);
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.CANTIDAD).setPreferredWidth(10);
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.PRECIO_UNIDAD).setPreferredWidth(20);
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.PRECIO_TOTAL).setPreferredWidth(20);
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.ELIMINAR).setPreferredWidth(10);
	}

	private String[] setearColumnas() {
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Insumo");
		columnasTabla.add("Cantidad");
		columnasTabla.add("Precio por unidad");
		columnasTabla.add("Costo total");
		columnasTabla.add("  ");
		String[] columnas = new String[columnasTabla.size()];
		
		
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		
		}
	
		return columnas;

	}

	private Object[][] obtenerMatrizDatos() {
		String informacion[][] = new String[insumosAgregados.size()][columnasTabla.size()];
		
		for(int i=0; i<informacion.length ; i++) {
			informacion[i][UtilTablaItemsPedido.NOMBRE_INSUMO] = insumosAgregados.get(i).getInsumo().getDescripcion() + "";
			informacion[i][UtilTablaItemsPedido.CANTIDAD] = insumosAgregados.get(i).getCantidad() + "";
			informacion[i][UtilTablaItemsPedido.PRECIO_UNIDAD] = insumosAgregados.get(i).getInsumo().getCostoPorUnidad() + "";
			informacion[i][UtilTablaItemsPedido.PRECIO_TOTAL] = (insumosAgregados.get(i).getCantidad() * insumosAgregados.get(i).getInsumo().getCostoPorUnidad())+ "";
			informacion[i][UtilTablaItemsPedido.ELIMINAR] = "ELIMINAR";
		}
		
		
		return informacion;
	}
	
	public void agregarItem(DetalleItem i) {
		insumosAgregados.add(i);
		construirTabla(setearColumnas(), obtenerMatrizDatos());
	}
	
	
}
