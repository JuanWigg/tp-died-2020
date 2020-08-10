package com.logica;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.interfaces.PanelAgregarPedido;
import com.interfaces.PanelGestionCamiones;
import com.interfaces.PanelGestionPlantas;
import com.interfaces.PanelPrincipal;

public class App {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame ventana = new JFrame("Mi app");
		
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventana.pack();
		ventana.setSize(1024, 768);
		ventana.setContentPane(new PanelPrincipal());
		ventana.setVisible(true);
		
		
	}

}
