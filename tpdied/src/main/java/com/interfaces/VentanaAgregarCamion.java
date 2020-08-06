package com.interfaces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.controladores.CamionController;

public class VentanaAgregarCamion extends JDialog{
	JPanel panel;
	
	JLabel labelPatente;
	JLabel labelKmReco;
	JLabel labelCostoKm;
	JLabel labelCostoHora;
	JLabel labelFechaCompra;
	JLabel labelModelo;
	JLabel labelMarca;
	JTextField fieldPatente;
	JTextField fieldKmReco;
	JTextField fieldCostoKm;
	JTextField fieldCostoHora;
	JFormattedTextField fieldFechaCompra;
	JTextField fieldModelo;
	JTextField fieldMarca;
	
	JButton botonAceptar;
	JButton botonCancelar;
	public VentanaAgregarCamion(JFrame padre, boolean modal) {
		
		super(padre, modal);
		inicializarComponentes();
		armarPanel();
		this.setContentPane(panel);
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
	
	private void inicializarComponentes() {
		//LABELS
				
				labelPatente = new JLabel("Patente:");
				labelKmReco = new JLabel("Kilometros recorridos:");
				labelCostoKm = new JLabel("Costo por kilometro:");
				labelCostoHora = new JLabel("Costo por hora:");
				labelFechaCompra = new JLabel("Fecha de compra:");
				labelModelo = new JLabel("Modelo:");
				labelMarca = new JLabel("Marca:");
				
				//CAMPOS
				fieldPatente = new JTextField();
				fieldPatente.setPreferredSize(new Dimension(100, 20));
				
				fieldKmReco = new JTextField();
				fieldKmReco.setPreferredSize(new Dimension(100, 20));
				
				fieldCostoKm = new JTextField();
				fieldCostoKm.setPreferredSize(new Dimension(100, 20));
				
				fieldCostoHora = new JTextField();
				fieldCostoHora.setPreferredSize(new Dimension(100, 20));
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
				fieldFechaCompra = new JFormattedTextField(formatter);
				fieldFechaCompra.setText("");
				fieldFechaCompra.setPreferredSize(new Dimension(100, 20));
				fieldFechaCompra.setToolTipText("Recuerde ingresar la fecha en formato aaaa-mm-dd");
				
				fieldModelo = new JTextField();
				fieldModelo.setPreferredSize(new Dimension(100, 20));
				
				fieldMarca = new JTextField();
				fieldMarca.setPreferredSize(new Dimension(100, 20));
				
				botonAceptar = new JButton("Aceptar");
				botonAceptar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						ArrayList<String> datosLista = new ArrayList<String>();
						datosLista.add(fieldPatente.getText().trim());
						datosLista.add(fieldKmReco.getText().trim());
						datosLista.add(fieldCostoHora.getText().trim());
						datosLista.add(fieldCostoKm.getText().trim());
						datosLista.add(fieldFechaCompra.getText().trim());
						datosLista.add(fieldModelo.getText().trim());
						datosLista.add(fieldMarca.getText().trim());
						(new CamionController()).altaCamion(datosLista);
						JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(panel);
						frame.dispose();
						
					}
					
						
				});
				botonCancelar = new JButton("Cancelar");
				botonCancelar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(panel);
						frame.dispose();
					}
					
				});
				
	}
	
	private void armarPanel() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(labelPatente, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(fieldPatente, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(labelKmReco, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(fieldKmReco, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(labelCostoHora, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(fieldCostoHora, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(labelCostoKm, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(fieldCostoKm, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(labelFechaCompra, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(fieldFechaCompra, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(labelModelo, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(fieldModelo, gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(labelMarca, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		panel.add(fieldMarca, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		panel.add(botonCancelar, gbc);
		gbc.gridx = 1;
		gbc.gridy = 7;
		panel.add(botonAceptar, gbc);
	}
}
