package com.interfaces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controladores.InsumoController;

public class VentanaAgregarInsumo extends JDialog {

	/**
	 * @param jFrame
	 * @param b
	 */
	public VentanaAgregarInsumo(JFrame jFrame, boolean modal) {
		super(jFrame, modal);
	}
	
}
