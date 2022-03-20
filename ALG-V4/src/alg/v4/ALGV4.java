/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package alg.v4;

import java.util.Scanner;

/**
 *
 * @author Angel
 */
public class ALGV4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int columnas, renglones;
        Scanner teclado = new Scanner(System.in);
        
        //Registro de Renglones y Columnas
        System.out.println("Numero de Incognitas");
        columnas = teclado.nextInt();
        
        System.out.println("Numero de Renglones/Ecuaciones");
        renglones = teclado.nextInt();
        
        matriz Matriz = new matriz(renglones,columnas);
        Matriz.regMtz();
        
        for(int i =0;i<renglones;i++)
        {
            Matriz.gauss(i);
        }
        
        Matriz.jordan();
        
        Matriz.printMatriz();
        
        Matriz.resul();
    }
    
}
