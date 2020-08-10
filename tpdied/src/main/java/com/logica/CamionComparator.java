package com.logica;

import java.util.Comparator;

public class CamionComparator implements Comparator<Camion>{ 

    // Overriding compare()method of Comparator
                // for descending order of cgpa 
    public int compare(Camion c1, Camion c2) { 
        if (c1.getKilometrosRecorridos() < c2.getKilometrosRecorridos()) 
            return 1; 
        else if (c1.getKilometrosRecorridos() > c2.getKilometrosRecorridos()) 
            return -1; 
                        return 0; 
        } 
}