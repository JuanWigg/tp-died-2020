package com.interfaces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
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
import javax.swing.text.MaskFormatter;

import com.controladores.CamionController;
import com.logica.Camion;


public class VentanaModificarCamion extends JDialog{
	Vector datosViejos;

	
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
	public VentanaModificarCamion(Vector datos, JTable tabla, JFrame padre, boolean modal) {
		super(padre, modal);
		this.datosViejos = datos;
		inicializarComponentes();
		armarPanel();
		this.setContentPane(panel);
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
	
	public void inicializarComponentes() {
		//LABELS
				
				labelPatente = new JLabel("Patente:");
				labelKmReco = new JLabel("Kilometros recorridos:");
				labelCostoKm = new JLabel("Costo por kilometro:");
				labelCostoHora = new JLabel("Costo por hora:");
				labelFechaCompra = new JLabel("Fecha de compra:");
				labelModelo = new JLabel("Modelo:");
				labelMarca = new JLabel("Marca:");
				
				//CAMPOS
				fieldPatente = new JTextField(datosViejos.get(0).toString());
				fieldPatente.setPreferredSize(new Dimension(100, 20));
				
				fieldKmReco = new JTextField(datosViejos.get(1).toString());
				fieldKmReco.setPreferredSize(new Dimension(100, 20));
				
				fieldCostoKm = new JTextField(datosViejos.get(2).toString());
				fieldCostoKm.setPreferredSize(new Dimension(100, 20));
				
				fieldCostoHora = new JTextField(datosViejos.get(3).toString());
				fieldCostoHora.setPreferredSize(new Dimension(100, 20));
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				fieldFechaCompra = new JFormattedTextField(formatter);
				fieldFechaCompra.setText(datosViejos.get(4).toString());
				fieldFechaCompra.setPreferredSize(new Dimension(100, 20));
				fieldFechaCompra.setToolTipText("Recuerde ingresar la fecha en formato aaaa-mm-dd");
				fieldModelo = new JTextField(datosViejos.get(5).toString());
				fieldModelo.setPreferredSize(new Dimension(100, 20));
				
				fieldMarca = new JTextField(datosViejos.get(6).toString());
				fieldMarca.setPreferredSize(new Dimension(100, 20));
				
				botonAceptar = new JButton("Aceptar");
				botonAceptar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						ArrayList<String> datosNuevosLista = new ArrayList<String>();
						datosNuevosLista.add(fieldPatente.getText().trim());
						datosNuevosLista.add(fieldKmReco.getText().trim());
						datosNuevosLista.add(fieldCostoHora.getText().trim());
						datosNuevosLista.add(fieldCostoKm.getText().trim());
						datosNuevosLista.add(fieldFechaCompra.getText().trim());
						datosNuevosLista.add(fieldModelo.getText().trim());
						datosNuevosLista.add(fieldMarca.getText().trim());
						(new CamionController()).modificarCamion(datosNuevosLista, datosViejos);
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
	
	public void armarPanel() {
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
