package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.controladores.InsumoController;
import com.logica.DetalleItem;
import com.logica.Insumo;

public class VentanaAgregarItem extends JDialog {
	JPanel panel;
	JLabel labelInsumo;
	JLabel labelCantidad;
	JTextField fieldCantidad;
	JSplitPane split;
	ArrayList<Insumo> listaInsumos;
	JList<String> lista;
	JTextArea areaTexto;
	JButton botonAceptar;
	JButton botonCancelar;
	JPanel panelPadre;
	
	
	public VentanaAgregarItem(JPanel panelPadre,  JFrame padre, boolean modal) {
		super(padre, modal);
		listaInsumos = (ArrayList<Insumo>) (new InsumoController()).consultarInsumos();
		this.panelPadre = panelPadre;
		inicializarComponentes();
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
		JPanel panelAbajo = new JPanel();
		panelAbajo.setLayout(new GridBagLayout());
		JPanel panelArriba = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		gbc.fill = GridBagConstraints.NONE;
		
		
		
		panelArriba.add(split);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelAbajo.add(labelCantidad, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelAbajo.add(fieldCantidad, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panelAbajo.add(botonCancelar, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panelAbajo.add(botonAceptar, gbc);
		
		panel.add(panelArriba, BorderLayout.NORTH);
		panel.add(panelAbajo, BorderLayout.SOUTH);
		
	}

	private void inicializarComponentes() {
		// TODO Auto-generated method stub
		//LABELS
		labelInsumo = new JLabel("Insumo:");
		labelCantidad = new JLabel("Cantidad:");
		
		//FIELD
		fieldCantidad = new JTextField();
		fieldCantidad.setPreferredSize(new Dimension(100, 20));
		
		//LISTA
		List<String> listaParaJList = listaInsumos.stream().map(i -> i.getDescripcion()).collect(Collectors.toList());
		lista = new JList<String>(
				listaParaJList.toArray(new String[listaParaJList.size()])
				);
		lista.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				Insumo in = listaInsumos.get(lista.getSelectedIndex());
				areaTexto.setText("Insumo: " + in.getDescripcion() + "\n"
						+ "Costo por unidad: " + in.getCostoPorUnidad() + "\n"
						+ "Unidad: " + in.getUnidad());
				
			}
			
		});
		lista.setPreferredSize(new Dimension(100, 200));
		
		
		//TEXT AREA 
		areaTexto = new JTextArea();
		areaTexto.setEditable(false);
		areaTexto.setPreferredSize(new Dimension(100, 200));
		//SPLIT
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(lista), areaTexto);
		split.setPreferredSize(new Dimension(500,300));
		
		
		//Botones
		botonAceptar = new JButton("Aceptar");
		botonCancelar = new JButton("Cancelar");
		botonAceptar.setPreferredSize(new Dimension(100, 40));
		botonCancelar.setPreferredSize(new Dimension(100, 40));
		
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DetalleItem item = new DetalleItem();
				if(lista.getSelectedIndex()==-1 || fieldCantidad.getText().trim().length()==0) {
					JOptionPane.showMessageDialog((JDialog) SwingUtilities.getWindowAncestor(panel), "No puede dejar campos vacios");
				}
				else {
					item.setInsumo(listaInsumos.get(lista.getSelectedIndex()));
					item.setCantidad(Double.parseDouble(fieldCantidad.getText().trim()));
					((PanelAgregarPedido) panelPadre).agregarItem(item);
					JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(panel);
					frame.dispose();
				}
			}
			
		});
		
		
		botonCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(panel);
				frame.dispose();
			}
			
		});
		
	}
}
