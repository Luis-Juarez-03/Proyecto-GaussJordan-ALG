/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alg.v4;

import java.awt.font.NumericShaper;
import java.util.Scanner;

/**
 *
 * @author Angel
 */
public class matriz 
{
    int nRen;
    int nCol;
    int resulN;
    int resulD;
    int aNum[][];
    int aDen[][];
    int rEscalonar;
    int iEsc = 0;
    int colEsc[];
    int renEsc[];
    int aProvNum[];
    int[] mProvNumG;
    int[] mProvDenG;
    boolean macht=false;
    
    Scanner teclado = new Scanner(System.in);
    
    public matriz(){
        
    }
    public matriz(int renglones, int columnas)
    {
        nRen = renglones;
        nCol = columnas + 1;
        int arrayN[][] = new int[nRen][nCol];
        int arrayD[][] = new int[nRen][nCol];
        int arrayEscC[] = new int[nRen];
        int arrayEscR[] = new int[nRen];
        int arrayProvNum[] =  new int[nCol];
        int[] arraymProvNumG = new int[nCol];
        int arraymProvDenG[] = new int[nCol];
        aNum = arrayN;
        aDen = arrayD;
        colEsc = arrayEscC;
        renEsc = arrayEscR;
        aProvNum = arrayProvNum;
        mProvNumG = arraymProvNumG;
        mProvDenG = arraymProvDenG;
    }
    
    //Registro de Datos
    public void regMtz()
    {
        System.out.println("Ingrese el sitema de Ecuaciones como matriz aumentada(poniendo coeficientes separados por espacios, incluso los terminos independiente)");
        // i == RENGLONES
        for(int i = 0;i< nRen;i++)
        {
            // ii == COLUMNAS
            for(int ii = 0; ii < nCol;ii++)
            {
                aNum[i][ii]= teclado.nextInt();
                //Se le asigna un uno, como denominador a todos los numeros registrados
                aDen[i][ii] = 1;
            }
        }
    }
    
    public void setFraccion(int numerador, int denominador, int pRen, int pCol)
    {
        aNum[pRen][pCol] = numerador;
        aDen[pRen][pCol] = denominador;
        
        if(numerador < 0 && denominador < 0)
        {
        aNum[pRen][pCol] =Math.abs(resulN);
        aDen[pRen][pCol]=Math.abs(resulD);
        }
        
        //En caso de que el denominador sea negativo
        //Se le quita el signo con la funcion
        //Y se le asigna el simbolo negativo al numerador
        if(denominador < 0)
        {
            aNum[pRen][pCol] = -(resulN);
            aDen[pRen][pCol]= Math.abs(resulD);
        }
        
        //En caso de que el numerador == denominador
        //Se le asigna un 1/1, para simplificar la fraccion
        if(numerador == denominador && numerador > 0)
        {
            aNum[pRen][pCol] = 1;
            aDen[pRen][pCol] = 1;
        }
    }
    
    public void simplf(int pRen, int pCol)
    {
        int numerador_A_reducir = Math.abs(aNum[pRen][pCol]); //valor absoluto del numerador
        int denominador_A_reducir = Math.abs(aDen[pRen][pCol]); //valor absoluto del denominador
        int resultado_Modulo;
        while (denominador_A_reducir != 0) 
        {
            resultado_Modulo = numerador_A_reducir % denominador_A_reducir;
            numerador_A_reducir = denominador_A_reducir;
            denominador_A_reducir = resultado_Modulo;
        }
        
        int maximo_comun_divisor = numerador_A_reducir; //se calcula el mcd de la fracción
        
        resulN = aNum[pRen][pCol] / maximo_comun_divisor;
        resulD = aDen[pRen][pCol] / maximo_comun_divisor;
        
        setFraccion(resulN, resulD, pRen, pCol);
    }
    
    public void suma(int num1, int den1, int num2, int den2)
    {
        //El if evita que los denominadores se hagan mas grandes
        if(den1 == den2)
        {
            resulN= num1+num2;
            resulD= den1;
        } else {
        resulN = num1*den2+num2*den1;
        resulD = den1*den2;
        }
    }
    
    public void mult(int num1, int den1, int num2, int den2)
    {
        //Se almacena la multiplicacion en los resultado...
        resulN =num1*num2;
        resulD=den1*den2;
    }
    
    public int buscarNum(int renglon, int columna)
    {
        boolean ok = false;
        for(int i = renglon; i < nRen;i++)    
        {
            if(aNum[i][columna] != 0)
            {
                ok = true;
                macht = true;
                return i;
            }
        }
        if(ok != true)
        {
            macht = false;
            return -1;
        }
        return 0;
    }
    
    public void intercambiar(int renglon1, int renglon2)
    {
        //int aProvNum[] = new int[5];
        if(renglon1 ==  renglon2)
        {
            return;
        } else{
            for(int i = 0;i< nCol;i++)
            {
            aNum[renglon2][i] = aNum[renglon1][i];
            }
        
            for(int i = 0;i<nCol;i++)
            {
            aNum[renglon1][i] = aProvNum[i];
            }
        }
        
    }
    
    
    
    public void gauss(int renglon)
    {
        //Buscar Numero a Escalonar
        boolean esUno = false;
        boolean escalonar = false;
        int columna = -1;
        int auxNum, auxDen;
        //int[] mProvNumG = new int[5];
        //int[] mProvDenG = new int[5];
        
        for(int i = 0; i < nCol && escalonar == false; i++)
        {
            rEscalonar = buscarNum(renglon,i);
            
            if(rEscalonar != -1)
            {
                intercambiar(renglon, rEscalonar);
                escalonar = true;
                columna = i;
                colEsc[iEsc] = i;
                renEsc[iEsc++] = rEscalonar;
            }
        }
        if(columna == -1)
        {
            return;
        }
        
        if(aNum[renglon][columna] == 1 && aNum[renglon][columna] == 1)
            esUno=true;
        
        
        //Hacer 1
        //En caso de que esUno = false o macht =  true
        if(esUno == false)
        {
            //Guarda el numerador y denominador del numero a hacer uno
            auxNum=aNum[renglon][columna];
            auxDen=aDen[renglon][columna];
            
            //En caso de que el numerador sea negativo
            //Al multiplicar nos apegamos a la regla de conservar los signos en los numeradores
            if(aNum[renglon][columna]<0)
            {
                //-1/abs(array_Numeradores)*array_Numeradores[renglon][i]/denom[renglon[i]
                for(int i = 0; i < nCol; i++)
                {
                    mult(-(auxDen),Math.abs(auxNum),aNum[renglon][i],aDen[renglon][i]);
                    //setFraccion(resulNum, resulDen, renglon, i);
                    //setFraccion(aNum[renglon][i],aDen[renglon][i],resulN,resulD);
                    setFraccion(resulN,resulD,renglon,i);
                }
            } else {
                for(int i = 0; i < nCol; i++)
                {
                    mult(auxDen,auxNum,aNum[renglon][i],aDen[renglon][i]);
                    //setFraccion(resulNum, resulDen, renglon, i);
                    //setFraccion(aNum[renglon][i],aDen[renglon][i],resulN,resulD);
                    setFraccion(resulN,resulD,renglon,i);
                }
            }
        }
        
        //Hacer ceros
        //Hacer cero la columna debajo del uno
        for(int i = renglon+1;i<nRen;i++)
        {
            //Pregunta si es cero
            if(aNum[i][columna]!= 0)
            {
                //Multiplica 1(el numero que arriba hicimos uno) por el numero a hacer 0
                //Lo almacenamos en los auxiliares
                mult(-aNum[renglon][columna],aDen[renglon][columna],aNum[i][columna],aDen[i][columna]);
                auxNum = resulN;
                auxDen= resulD;
                
                //Hace la multiplicacion de los auxiliares por cada elemento del renglon(del numero a hacer 0)
                for(int ii = 0;ii<nCol;ii++)
                {
                    mProvNumG[ii] = auxNum*aNum[renglon][ii];
                    mProvDenG[ii] = auxDen*aDen[renglon][ii];
                }
                
                //Hace la suma de la multiplicacion y del renglon (del numero a hacer cero)
                for(int ii = 0; ii <nCol;ii++)
                {
                    //mProvN2[ii] = mProvNum[ii]+array_Numeradores[i][ii];
                    suma(mProvNumG[ii],mProvDenG[ii],aNum[i][ii],aDen[i][ii]);
                    //setFraccion(resulNum, resulDen, i, ii);
                    setFraccion(resulN,resulD,i,ii);
                }
            }  
        }
        
        //Recorre todos los elementos y los reduce
        //(se puede optimizar) xdd
        for(int i = 0; i<nRen;i++)
        {
            for(int ii = 0;ii<nCol;ii++)
            {
                simplf(i,ii);
            }
        }
    }
    
    public void jordan()
    {
        //int[] mProvDen = new int[5];
        //int[] mProvNum = new int[5];
        int auxNum, auxDen;
        int renglon, columna;    
        //Hacer ceros 
         
        for(int j = iEsc-1; j>= 0;j--)
        {
            columna = colEsc[j];
            renglon = renEsc[j];
            for(int i = renglon-1;i>=0;i--)
            {
                //Pregunta si es cero
                if(aNum[i][columna]!= 0)
                {
                    //Multiplica 1(el numero que arriba hicimos uno) por el numero a hacer 0
                    //Lo almacenamos en los auxiliares
                    mult(-aNum[renglon][columna],aDen[renglon][columna],aNum[i][columna],aDen[i][columna]);
                    auxNum = resulN;
                    auxDen= resulD;

                    //Hace la multiplicacion de los auxiliares por cada elemento del renglon(del numero a hacer 0)
                    for(int ii = 0;ii<nCol;ii++)
                    {
                        mProvNumG[ii] = auxNum*aNum[renglon][ii];
                        mProvDenG[ii] = auxDen*aDen[renglon][ii];
                    }

                    //Hace la suma de la multiplicacion y del renglon (del numero a hacer cero)
                    for(int ii = 0; ii <nCol;ii++)
                    {
                        suma(mProvNumG[ii],mProvDenG[ii],aNum[i][ii],aDen[i][ii]);
                        setFraccion(resulN, resulD, i, ii); 
                    }
                }  
            }
            
            for(int i = 0; i<nRen;i++)
            {
                for(int ii = 0;ii<nCol;ii++)
                {
                    simplf(i,ii);
                }
            }
        }
    }
    
    public void printMatriz()
    {
        for(int i = 0; i < nRen;i++)
        {
            System.out.print("[");
            for(int ii = 0; ii < nCol;ii++)
            {
                if(ii != nCol-1)
                    System.out.print(" "+aNum[i][ii]+"/"+aDen[i][ii]+" ");
                else
                    System.out.print("|"+" "+aNum[i][ii]+"/"+aDen[i][ii]+" ");
            }
            System.out.print("]");
            System.out.println("");
        }
    }
    
    //Evaluar
    
    
    
    public void resul()
    {
     System.out.println("Resultados:");
     
     //Variable de las letras del abc
     char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','v','w','x','y','z'};
     
     for(int i = 0; i<nRen;i++)
     {
         System.out.println(abc[i]+"="+aNum[i][nCol-1]+"/"+aDen[i][nCol-1]);
         //System.out.println(abc[i]+"="+mA[i][nVars]);
     }
     
    }
    
    
}
