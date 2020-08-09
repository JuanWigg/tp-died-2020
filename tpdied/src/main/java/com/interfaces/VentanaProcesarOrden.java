package com.interfaces;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class VentanaProcesarOrden extends JDialog{
	JLabel mensajeError;
	JPanel panel;
	
	
	public VentanaProcesarOrden(JFrame padre) {
		super(padre);
		inicializarComponentes();
		armarPanel();
		this.setContentPane(panel);
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private void inicializarComponentes() {
		
		JOptionPane.showMessageDialog((JDialog) SwingUtilities.getWindowAncestor(panel), "No se ha podido procesar la orden, esta ha sido cancelada");
	}
	
	
	private void armarPanel() {
		
	}
	
}
