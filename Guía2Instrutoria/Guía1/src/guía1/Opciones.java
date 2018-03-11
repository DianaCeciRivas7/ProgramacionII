package guía1;

import javax.swing.JOptionPane;

public class Opciones {

    private final String[] vectorAuxiliarOpciones = {"0", "1", "2", "3"};
    private String[] caracteres = new String[10];
    private String entrada, burbuja, salida;
    private int contabilizador;
    private char caracterIngresado, caracterAuxiliar1, caracterAuxiliar2;

    public String getVectorAuxiliarOpcionesPosicion(int posicion) {
        return this.vectorAuxiliarOpciones[posicion];
    }

    public void IngresarNotas() {
        /*
                    El equals sirve para comparar los String, es como decir == solamente que si pones == al
                    comparar un texto no se puede.
         */
        contabilizador = 1;
        //Reinicio el contabilizador para mostrar de nuevo la numeracion reiniciada si en dado caso vuelve a elegir esta opcion
        for (int posicion = 0; posicion < 10; posicion++) {
            do {
                do {
                    entrada = JOptionPane.showInputDialog("Ingrese caracter " + contabilizador + "\n(A - Z)");
                } while (entrada.length() != 1 || entrada.isEmpty() || entrada.equals(" "));
                /*
                            Length me devuelve el valor entero del tamanio del vector, en este caso de
                            la variable entrada ya que es un String(texto) y todo texto es un vector conformado
                            de caracterer.
                 */
                caracterIngresado = Character.toUpperCase(entrada.charAt(0));
                //toUpperCase como la palabra lo dice, convierte a mayusculas, asi no tenemos el problema de las minusculas.
                /*
                            charAt me toma el caracter del String en la posicion que le diga.
                            En este caso como se que anteriormente valide que solo seria un caracter el que
                            conformaria el String se que ese caracter que me interesa esta en la posicion 0 de mi texto
                 */
            } while (caracterIngresado < 'A' || caracterIngresado > 'Z');
            caracteres[posicion] = "" + caracterIngresado;
            /*
                        como debo convertir la variable de tipo char a String para asignarsela al vector
                        de tipo String lo que se puede es pone una cadena vacia y concatenerla con
                        la variable char para convertir todo eso en String y ya que es una cadena vacia da igual
                        porque no le quita ni le suma nada al caracter.
                        
             */
            contabilizador++;
            /*
                        Hasta este punto aumento contabilizador porque se que el caracter que me ingreso ya esta en mi vector
                        y procedere a pedir un nuevo caracter.
                        Por cierto, es de contabilizador++ es como decir contabilizador = contabilizador +1
             */
        }
    }

    public void Ordenar() {
        //por burbujita.
        for (int posicion1 = 0; posicion1 < 10; posicion1++) {
            for (int posicion2 = posicion1 + 1; posicion2 < 10; posicion2++) {
                caracterAuxiliar1 = caracteres[posicion1].charAt(0);
                caracterAuxiliar2 = caracteres[posicion2].charAt(0);
                /*
                                Hice eso debido a que debo compararlos si uno en Codigo ASCII es mayor que el otro.
                                 Eso con el con fin de ordenar alfbeticamente.
                 */
                if (caracterAuxiliar1 > caracterAuxiliar2) {
                    burbuja = caracteres[posicion2];
                    caracteres[posicion2] = caracteres[posicion1];
                    caracteres[posicion1] = burbuja;

                }
            }
        }
    }

    public String Mostrar() {
        salida = "";
        //Hago eso para limpiar la salida cada vez que se elija esta opcion y no mostrar lo que estuvo la vez anterior.
        for (int posicion = 0; posicion < 10; posicion++) {
            salida = salida + caracteres[posicion] + "  ";
        }

        return salida;
    }
}
