package com.logica;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo m1 = new Modelo("XD2AS", "RENAULT");
		Camion c1 = new Camion("AAAA201", 200.00, 300.00, m1);

		CamionDAOImplSQL camionDAO = new CamionDAOImplSQL();
		camionDAO.bajaCamion(c1);
		
	}

}
