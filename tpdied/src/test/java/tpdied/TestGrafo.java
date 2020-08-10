package tpdied;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.logica.Grafo;
import com.logica.Planta;
import com.logica.Tramo;

public class TestGrafo {
	private Grafo g;
	private Grafo g2;
	private Planta p1;
	private Planta p2;
	private Planta p3;
	private Planta p4;
	private Planta p5;
	private Tramo t1;
	private Tramo t2;
	private Tramo t3;
	private Tramo t4;
	private Tramo t5;
	private Tramo t6;
	private Tramo t7;
	private Tramo t8;
	private Tramo t9;
	private Tramo t10;
	private Tramo t11;
	private Tramo t12;
	private Tramo t13;
	private Tramo t14;
	
	@Before
	public void init() {
		p1 = new Planta("p1");
		p2 = new Planta("p2");
		p3 = new Planta("p3");
		p4 = new Planta("p4");
		p5 = new Planta("p5");
		//tramo(IDTRAMO, PORIGEN PDESTINO, DISTANCIA, DURACION,PESOMAX,UNIDAD)
		t1 = new Tramo(1,p1,p2,100,10d,10d,"kg");
		t2 = new Tramo(2,p2,p3,100,10d,20d,"kg");
		t3 = new Tramo(3,p1,p3,200,10d,5d,"kg");
		t4 = new Tramo(4,p1,p4,30,10d,10d,"kg");	
		t5 = new Tramo(5,p4,p3,400,10d,10d,"kg");
	    t6 = new Tramo(6,p1,p5,100,10d,30d,"kg");
		t7 = new Tramo(7,p5,p3,100,10d,25d,"kg");
		ArrayList<Tramo> tramosg= new ArrayList<Tramo>();
		ArrayList<Planta> plantasg= new ArrayList<Planta>();
		plantasg.add(p1);
		plantasg.add(p2);
		plantasg.add(p3);
		plantasg.add(p4);
		plantasg.add(p5);
		tramosg.add(t1);
		tramosg.add(t2);
		tramosg.add(t3);
		tramosg.add(t4);
		tramosg.add(t5);
		tramosg.add(t6);
		tramosg.add(t7);
		g = new Grafo(tramosg,plantasg);
		t8 = new Tramo(1,p1,p2,100,10d,10d,"kg");
		t9 = new Tramo(2,p2,p4,100,10d,20d,"kg");
		t10 = new Tramo(3,p1,p3,100,10d,5d,"kg");
		t11 = new Tramo(4,p3,p1,100,10d,10d,"kg");
		t12 = new Tramo(5,p3,p2,100,10d,10d,"kg");
		t13 = new Tramo(6,p3,p4,100,10d,30d,"kg");
		t14 = new Tramo(7,p4,p3,100,10d,25d,"kg");
		ArrayList<Tramo> tramosg2= new ArrayList<Tramo>();
		ArrayList<Planta> plantasg2= new ArrayList<Planta>();
		plantasg2.add(p1);
		plantasg2.add(p2);
		plantasg2.add(p3);
		plantasg2.add(p4);
		tramosg2.add(t8);
		tramosg2.add(t9);
		tramosg2.add(t10);
		tramosg2.add(t11);
		tramosg2.add(t12);
		tramosg2.add(t13);
		tramosg2.add(t14);
		g2 = new Grafo(tramosg2,plantasg2);
	}
	
	@Test
	public void testFlujoMax() {
		assertTrue((g.flujoMax(p1,p3).equals(50.0)));
	}
	@Test
	public void testPageRank() {
		g2.pageRank();
		assertTrue(g2.pr.get(p1.getNombre()).equals(0.125));
		assertTrue(g2.pr.get(p2.getNombre()).equals(2d/12d));
		assertTrue(g2.pr.get(p3.getNombre()).equals(3d/8d));
		assertTrue(g2.pr.get(p4.getNombre()).equals(1d/3d));
	}
	
}
