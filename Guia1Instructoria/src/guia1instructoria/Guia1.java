
package guia1instructoria;

import java.util.Scanner;

/**
 *
 * @author Diana Rivas
 */

public class Guia1 {
    
    Scanner sc = new Scanner(System.in);
    
    public void Cuadrado(){
        
        int ladoA, ladoB,area;
        
        System.out.println("Encontremos el area de una figura de 4 lados");
        System.out.println("Ingrese lado A");
        ladoA=sc.nextInt();
        System.out.println("Ingrese lado B");
        ladoB=sc.nextInt();
        area=ladoA*ladoB;
        System.out.println("Su area es: "+area);
        
        
    }
    
    public void Circunferencia(){
        
        int opcion;
        double Radio, Diametro, circunferencia;
        
        System.out.println("Para encontrar la circunferencia puede ingresar: ");
        System.out.println(" 1-Radio \n 2-Diametro ");
        opcion=sc.nextInt();
        
        if(opcion==1){
            System.out.println("Ingrese su radio");
            Radio=sc.nextInt();
            circunferencia=(Radio*3.14)*2;
            System.out.println("Su circunferencia es: "+circunferencia);
        }
        else if(opcion==2){
            System.out.println("Ingrese su Diametro");
            Diametro=sc.nextInt();
            circunferencia=Diametro*3.14;
            System.out.println("Su circunferencia es: "+circunferencia);
        }
    }
    
    public void Cubo(){
        
       int diagonal;
       double volumen,parte1;
        System.out.println("Para encontrar el volumen del cubo ingrese su Diagonal");
        diagonal=sc.nextInt();
        parte1=(diagonal)/1.7320;
        volumen=parte1*parte1*parte1;
        System.out.println("Su volumen es: "+volumen);
    }
    
    public void Promedio(){
        
        int vector[]= new int[10];
        double valor=0;
        double resultado;
        System.out.println("Ingrese sus 10 datos :");
        for (int i = 0; i < 10; i++) {
            vector[i]=sc.nextInt();
            valor=valor+vector[i];
        }
        resultado=valor/10;
        System.out.println("Su promedio es: "+resultado);
    }
}
