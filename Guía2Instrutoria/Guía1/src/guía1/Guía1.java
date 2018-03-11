package guía1;

import javax.swing.JOptionPane;

/*
 * @author Diana Rivas
 */
public class Guía1 {

    public static void main(String[] args) {
        String opcion;
        /*
        Esto lo hare porque dice que debe arrojar error con letras.
        Yo desde un inicio limitara que solo entre 0 y 3 permitiese.
        El vector que hice de auxiliar lo lleno con 0,1,2,3 las cuale son mis opciones de menu
         */
        Opciones opc = new Opciones();
        boolean flag1 = false, flag2, flag3 = false;

        flag2 = false;

        do {
            if (flag2 == false) {
                flag2 = true;
                flag1 = false;
                opcion = JOptionPane.showInputDialog(null, "Elija un opción\n\n1- Ingresar datos\n2- Ordenar datos\n3- Mostrar datos\n0- Salir");
                ControlarOpciones:
                for (int posicion = 0; posicion < 4; posicion++) {
                    if (opcion.equals(opc.getVectorAuxiliarOpcionesPosicion(posicion))) {
                        flag1 = true;
                        break ControlarOpciones;
                        /*
                            Aca lo que hice fue que si la variable opcion coincide con algun valor
                            de mi vector String pues significa que es un valor de mis opciones de menu
                            entonces no me interesa seguir el bucle por lo cual lo termino con esa linea de
                            break (nombre del bucle)
                            yo le puse de nombre ControlarOpciones
                         */
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
                flag1 = false;
                opcion = JOptionPane.showInputDialog(null, "Elija un opción\n\n1- Ingresar datos\n2- Ordenar datos\n3- Mostrar datos\n0- Salir");
                ControlarOpciones:
                for (int posicion = 0; posicion < 4; posicion++) {
                    if (opcion.equals(opc.getVectorAuxiliarOpcionesPosicion(posicion))) {
                        flag1 = true;
                        break ControlarOpciones;
                    }
                }
            }
        } while (flag1 != true);

        while (Integer.parseInt(opcion) >= 1 && Integer.parseInt(opcion) <= 3) {
            if (opcion.equals("1")) {
                flag3 = true;
                opc.IngresarNotas();
            } else if (opcion.equals("2")) {
                if (flag3 == true) {
                    opc.Ordenar();
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese primeramente los valores");
                }
            } else {
                if (flag3 == true) {
                    JOptionPane.showMessageDialog(null, opc.Mostrar());
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese primeramente los valores");
                    /*
                        Aca volvi a ocupar mi bandera3 porque no puedo mostrar algo que no haya ingresado el
                        usuario. Mostraria una cadena de null.
                     */
                }
            }

            flag2 = false;

            do {
                if (flag2 == false) {
                    flag2 = true;
                    flag1 = false;
                    opcion = JOptionPane.showInputDialog(null, "Elija un opción\n\n1- Ingresar datos\n2- Ordenar datos\n3- Mostrar datos\n0- Salir");
                    ControlarOpciones:
                    for (int posicion = 0; posicion < 4; posicion++) {
                        if (opcion.equals(opc.getVectorAuxiliarOpcionesPosicion(posicion))) {
                            flag1 = true;
                            break ControlarOpciones;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
                    flag1 = false;
                    opcion = JOptionPane.showInputDialog(null, "Elija un opción\n\n1- Ingresar datos\n2- Ordenar datos\n3- Mostrar datos\n0- Salir");
                    ControlarOpciones:
                    for (int posicion = 0; posicion < 4; posicion++) {
                        if (opcion.equals(opc.getVectorAuxiliarOpcionesPosicion(posicion))) {
                            flag1 = true;
                            break ControlarOpciones;
                        }
                    }
                }
            } while (flag1 != true);
        }

    }

}
