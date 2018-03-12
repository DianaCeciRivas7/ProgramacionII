package guia3instructoria;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class frmVenta extends javax.swing.JFrame {
 /*
    *Un cuadro de texto (jTextField) te permite escribir dentro de un cuadro
    *Una etiqueta (label) mostrar texto
    *Un cuadro combinado (comboBox) es una lista desplegable que te enseña varias opciones a elejgir.
      El comportamiento de esta es como un arreglo unidimensional (vector) ya que en cada posicion guarda una opcion a elegir.
    *Una casilla de activacion (checkBox) es una casilla donde puedes darle click y activar algo
    *Un boton (button) es un boton xD
    *Un area de texto (textArea) es como una pagina en blanco, parrafo, etc.
    *No seria malo aclarar que cualquier componente lo puedes "desactivar" para que no permita interactuar con el al usuario,
      esto para que por ejemplo sino a elegido la opcion camara principal no le permite ingresar los pixeles, etc.
    *los evt o eventos en un formulario
      Aca es lo mismo, tienes la capacidad de capturar un instante de lo que hace el usuario, eso se conoce como evento.
      Por ejemplo, el hecho de presionar una tecla es un evento, darle click a un boton tambien lo es, al tener esa capacidad
      puedes hacer muchas cosas, menos lograr que te ame.
      Un ejemplo de lo que se puede hacer es que si la tecla que presiona es una letra que la borre, lo cual seria solo permitir
      numeros. Otro ejemplo es que si da click en la casilla de activacion para camara principla, habilite el cuadro de texto de
      camara principal.
    *Private es un modificador de acceso el cual restringe mas la accesibilidad de estas variables.
      Aunque si haces un metodo con modificador de acceso public el cual te devuelva el valor de una variable privada
      y a este lo ejecutas en otra clase te permite ver ese valor, A eso se le llama getter, igual
      un setter es un metodo publico que modfica algo de una variable privada.
      Un setter es de tipo void y getter es del tipo de variable que quieras retornar, es decir int para un return de variable entera,etc.
    *El evt.consume() es un metodo que consume al evento, es decir mata al evento.
    *Focus, es algo importante, yo lo tomo como enfoque, concentracion, etc..
      focus se refiere a lo que usuario tiene enfocado, puede ser un cuadro de texto cuando lo esta digitando, una determinada
      seccion del formulario (eso es avanzado), etc.
      El requestFocus() pide que se pase el focus al elemento que tu quieras.
     */
    public frmVenta() {
        LlenarModelo();
        initComponents();

        /*
        Hago estas lineas de codigo luego del initComponents porque sino me equivoco da error debido a que no ha inicializado
        componentes.
         */
        this.setResizable(false);
        //Con eso hago que el usuario no pueda cambiar el tamanio de la ventana.
        this.setLocationRelativeTo(null);
        //Con eso modifico la posicion de mi ventana con respecto a un punto, en este caso null, lo cual la centra en el monitor.
        txtaPropiedades.setEditable(false);
        //Con eso hago que no pueda editar el area de texto, es decir que no pueda escribir sobre lo que tengo ni borrar :v
        txtCamaraPrincipal.setEnabled(false);
        txtCamaraSecundaria.setEnabled(false);
        //El metodo setEnabled modifica si un componente esta habilitado o no, true lo habilita y false lo deshabilita.
    }

    /*
    Ese metodo lo hago porque el proposito de la POO es el codigo repetido y poderlo ahorrar en un metodo.
    Como parametros le pasare la respectiva checkBox y el respecti JTextField.
     */
    public void ActivarTxt(JCheckBox ch, JTextField txt) {
        /*
        isSelected() devuelve true si la checkBox esta seleccionada y false si no.
         */
        if (ch.isSelected()) {
            txt.setEnabled(true);
        } else {
            txt.setEnabled(false);
            txt.setText("");
        }
    }

    public void LlenarModelo() {
        /*
        es un molde, plantilla, etc...
        Aca utilizo un modelo para el cmb para poder meterle las opciones.
        Hago un vector, lo lleno con las opciones y luego ocupo un for para recorrer ese vector y por cada posicion asignarle ese
        elemento a mi modelo.
        Luego en el constructor llamo este metodo antes de initComponents para tenerlo listo antes de que inicialice todo los
        componentes.
        en la parte del codigo protegido que hace java he modificado que el modelo sea este.
        */
        String contenidoModelo[] = {"NEXUS", "PIXIE", "LENOVO", "HUAWEI", "SONY", "APPLE", "SAMSUNG", "ALCATEL", "LG", "OTRA"};
        for (int posicion = 0; posicion < contenidoModelo.length; posicion++) {
            cmbMarcasModel.addElement(contenidoModelo[posicion]);
        }
    }

    public void ControlPunto(char caracter, String texto, java.awt.event.KeyEvent evt) {
        //Utilizo 3 veces este codigo por lo cual me conviene tenerlo en un metodo.
        CharSequence car = ".";
        int contabilizadorCar = 0;
        //Aca utilizo eso para ocupar el metodo contains() el cual me dice si la charSequence esta dentro del texto.
        BuclePunto:
        for (int posicion = 0; posicion < texto.length(); posicion++) {
            /*
            Un texto es un vector y por eso pienso recorrerlo con el charAt() y  si lo concateno con una cadena vacia
            se convierte en string y como es una cadena vacia no le añade ni le afecta en nada al char resultante.
            Entonces como ya tengo un string puedo ocupar el metodo contains() lo cual ve si ese string contiene algo,
            en este caso seria la CharSequence car.
            Si eso pasa retorna un true el cual lo guardo en mi variable flagContiene.
            Luego comparo si es verdad que aumente el contador.
            Luego si el contador es mayor que 1 significa que es el segundo "." ingresado, pero no puedo consumir el evento solo por
            asi ya que no me dejaria poner algo luego de poner un punto, entonces por eso luego comparo si es un ".",
            y si lo es entonces si puedo consumir el evento.
             */
            boolean flagContiene = ("" + texto.charAt(posicion)).contains(car);
            if (flagContiene == true) {
                contabilizadorCar++;
                if (contabilizadorCar >= 1) {
                    if (caracter == '.') {
                        evt.consume();
                        break BuclePunto;
                    }
                }
            }
        }
    }

    public void ValidacionesTxtCamaras(java.awt.event.KeyEvent evt, char caracter, String texto) {
        if (texto.length() >= 4) {
            evt.consume();
        } else if (texto.length() == 0 || texto.length() == 3) {
            if (caracter == '.') {
                evt.consume();
            }
        }

        ControlPunto(caracter, texto, evt);

        char[] caracteres = texto.toCharArray();
        for (int posicion = 0; posicion < caracteres.length; posicion++) {
            if (caracteres[posicion] == '.') {
                flagPunto = true;
                break;
            } else {
                flagPunto = false;
            }
        }

        if (flagPunto == true) {
            if ((caracter < '0' || caracter > '9') && caracter != '.') {
                evt.consume();
            }
        } else {
            if (texto.length() == 2) {
                if (caracter != '.') {
                    evt.consume();
                }
            } else if ((caracter < '0' || caracter > '9') && caracter != '.') {
                evt.consume();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblCpu = new javax.swing.JLabel();
        txtCpu = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtPantalla = new javax.swing.JTextField();
        lblRam = new javax.swing.JLabel();
        txtRam = new javax.swing.JTextField();
        lblGarantia = new javax.swing.JLabel();
        txtGarantia = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblMarca = new javax.swing.JLabel();
        cmbMarcas = new javax.swing.JComboBox<>();
        lblCamara = new javax.swing.JLabel();
        chbPrincipal = new javax.swing.JCheckBox();
        chbSecundaria = new javax.swing.JCheckBox();
        chbFlash = new javax.swing.JCheckBox();
        txtCamaraPrincipal = new javax.swing.JTextField();
        txtCamaraSecundaria = new javax.swing.JTextField();
        btnEjecutar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaPropiedades = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblTitulo.setText("Puntaje de moviles");

        lblCpu.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblCpu.setText("Cpu numero de nucleos:");

        txtCpu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCpuKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCpuKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Tamaño de pantalla:");

        txtPantalla.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPantallaFocusLost(evt);
            }
        });
        txtPantalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPantallaKeyTyped(evt);
            }
        });

        lblRam.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblRam.setText("Cantidad de RAM:");

        txtRam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRamFocusLost(evt);
            }
        });
        txtRam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRamKeyTyped(evt);
            }
        });

        lblGarantia.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblGarantia.setText("Tiempo de garantia:");

        txtGarantia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGarantiaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGarantiaKeyReleased(evt);
            }
        });

        lblPrecio.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblPrecio.setText("Precio:");

        txtPrecio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioFocusLost(evt);
            }
        });
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        lblMarca.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblMarca.setText("Marca:");

        cmbMarcas.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cmbMarcas.setModel(cmbMarcasModel);
        cmbMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMarcasActionPerformed(evt);
            }
        });

        lblCamara.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblCamara.setText("Camaras:");

        chbPrincipal.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        chbPrincipal.setText("Principal");
        chbPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbPrincipalActionPerformed(evt);
            }
        });

        chbSecundaria.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        chbSecundaria.setText("Secundaria");
        chbSecundaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbSecundariaActionPerformed(evt);
            }
        });

        chbFlash.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        chbFlash.setText("Flash");

        txtCamaraPrincipal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCamaraPrincipalFocusLost(evt);
            }
        });
        txtCamaraPrincipal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCamaraPrincipalKeyTyped(evt);
            }
        });

        txtCamaraSecundaria.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCamaraSecundariaFocusLost(evt);
            }
        });
        txtCamaraSecundaria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCamaraSecundariaKeyTyped(evt);
            }
        });

        btnEjecutar.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        txtaPropiedades.setColumns(20);
        txtaPropiedades.setRows(5);
        jScrollPane1.setViewportView(txtaPropiedades);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblCpu)
                                    .addComponent(jLabel1)
                                    .addComponent(lblRam)
                                    .addComponent(lblGarantia)
                                    .addComponent(lblPrecio)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblMarca)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCpu, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                        .addComponent(txtPantalla)
                                        .addComponent(txtRam)
                                        .addComponent(txtGarantia))
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCamara)
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCamaraPrincipal)
                                    .addComponent(chbPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCamaraSecundaria)
                                    .addComponent(chbSecundaria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(43, 43, 43)
                                .addComponent(chbFlash))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(btnEjecutar)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCpu)
                    .addComponent(txtCpu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRam)
                    .addComponent(txtRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGarantia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecio))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarca)
                    .addComponent(cmbMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCamara)
                    .addComponent(chbPrincipal)
                    .addComponent(chbSecundaria)
                    .addComponent(chbFlash))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCamaraPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCamaraSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEjecutar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCpuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpuKeyTyped
        /*
        Utilizo este metodo de keyTyped por el hecho que quiero capturar el evento de cuando el usuario digita.
        dice java.awt.event.KeyEvent evt; lo cual hace relacion a una libreria que te permite administrar eventos
         */
        char caracter = evt.getKeyChar();
        //Con eso guardo el caracter que digita el usuario en una variable.
        textoCpu = txtCpu.getText();
        //Con eso guardo el texto del cuadro de texto en una variable.
        if (textoCpu.length() >= 2) {
            //lenght() me devuelve el tamanio de un texto.
            evt.consume();
            //Al ocupar el evt.consume() aca hago que cualquier otro digito luego de digitar 2 caracteres no pueda existir.
        } else if (caracter < '0' || caracter > '9') {
            evt.consume();
            //Es obvio que si no esta dentro del 0 y 9 no quiero que exista ese evento.
        }
    }//GEN-LAST:event_txtCpuKeyTyped

    private void txtCpuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpuKeyReleased
        /*
        Ocupo KeyReleased porque cuando suelte la tecla significa que dejo de escribir y puedo proceder a ver si
        su dato esta entre 1 y 12
         */
        textoCpu = txtCpu.getText();
        if (!txtCpu.getText().isEmpty()) {
            /*
            isEmpty() evalua si esta vacio el texto xD,
            Aca le pregunto si el texto esta vacio, lo hago porque si esta vacio no quiero que me evalue si el vacio esta
            entre 1 y 12 
            Le pongo el ! al inicio ya que signfica un "no", entonces la condicion quedaria de la siguiente forma:
            Si no esta vacio el texto hace lo siguiente.
             */
            if (Integer.parseInt(textoCpu) < 1 || Integer.parseInt(textoCpu) > 12) {
                /*
                Aca evalua si esta entre 1 y 12
                Ocupo el setText() para modificar el texto y ponerlo vacio, en pocas palabras para limpiar el dato y
                que no lo deje pasar
                 */
                txtCpu.setText("");
            }
        }
    }//GEN-LAST:event_txtCpuKeyReleased

    private void txtPantallaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPantallaKeyTyped
        //Aca mas de lo mismo para validar tamaño de un texto como lo hice con el anterior de caso del Cpu.
        textoPantalla = txtPantalla.getText();
        char caracter = evt.getKeyChar();
        if (textoPantalla.length() >= 3) {
            evt.consume();
        } else if (textoPantalla.length() == 1) {
            if (caracter != '.') {
                evt.consume();
                //Aca valido que el segundo caracter sea punto.
            }
        } else if (caracter < '0' || caracter > '9') {
            //Mas de lo mismo para limitar caracteres posibles de ingresar que hice en el anterior de Cpu.
            /*
            La condicion queda:
            Si el caracter esta fuera del intervalo de 0 a 9 y si caracter es distinto del punto.
            Con eso me refiero a que si esta afuera del 0 a 9 puede que sea o no igual al punto, por eso
            pongo lo de caracter != '.' para validar que no sea punto.
            Si entra significa que es cualquier cosa menos lo que puedo permitir.
             */
            evt.consume();
        }
    }//GEN-LAST:event_txtPantallaKeyTyped

    private void txtPantallaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPantallaFocusLost
        /*
        Ocupo el focusLost debido a que si el usuario quita el focus de este componente significa que se quiere ir xD
         */
        if (!txtPantalla.getText().isEmpty()) {
            if (txtPantalla.getText().length() <= 2) {
                double num = Math.round(Double.parseDouble(txtPantalla.getText()) * 10) / 10.0;
                txtPantalla.setText("" + num);
            }
            textoPantalla = txtPantalla.getText();
            if (textoPantalla.length() == 3) {
                if (Double.parseDouble(textoPantalla) < 3.0 || Double.parseDouble(textoPantalla) > 6.7) {
                    txtPantalla.setText("");
                    JOptionPane.showMessageDialog(null, "Campo incorrecto");
                }
            }
        }

    }//GEN-LAST:event_txtPantallaFocusLost

    private void txtRamKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRamKeyTyped
        //Esto es copiar, pegar y modificar los valores de los limites del anterior caso para Pantalla.
        textoRam = txtRam.getText();
        char caracter = evt.getKeyChar();
        if (textoRam.length() >= 3) {
            evt.consume();
        } else if (textoRam.length() == 1) {
            if (caracter != '.') {
                evt.consume();
                //Aca valido que el segundo caracter sea punto.
            }
        } else if (caracter < '0' || caracter > '9') {
            //Mas de lo mismo para limitar caracteres posibles de ingresar que hice en el anterior de Cpu.
            /*
            La condicion queda:
            Si el caracter esta fuera del intervalo de 0 a 9 y si caracter es distinto del punto.
            Con eso me refiero a que si esta afuera del 0 a 9 puede que sea o no igual al punto, por eso
            pongo lo de caracter != '.' para validar que no sea punto.
            Si entra significa que es cualquier cosa menos lo que puedo permitir.
             */
            evt.consume();
        }
    }//GEN-LAST:event_txtRamKeyTyped

    private void txtRamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRamFocusLost
        //Mas de copiar y pegar 
        if (!txtRam.getText().isEmpty()) {
            if (txtRam.getText().length() <= 2) {
                double num = Math.round(Double.parseDouble(txtRam.getText()) * 10) / 10.0;
                txtRam.setText("" + num);
            }
            textoRam = txtRam.getText();
            if (textoRam.length() == 3) {
                if (Double.parseDouble(textoRam) < 0.5 || Double.parseDouble(textoRam) > 6.0) {
                    txtRam.setText("");
                    JOptionPane.showMessageDialog(null, "Campo incorrecto");
                }
            }
        }
    }//GEN-LAST:event_txtRamFocusLost

    private void txtGarantiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGarantiaKeyTyped
        // Copiar y pegar x1000
        char caracter = evt.getKeyChar();
        textoGarantia = txtGarantia.getText();
        if (textoGarantia.length() >= 2) {
            evt.consume();
        } else if (caracter < '0' || caracter > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtGarantiaKeyTyped

    private void txtGarantiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGarantiaKeyReleased
        //Copiar y pegar x12312312412
        textoGarantia = txtGarantia.getText();
        if (!txtGarantia.getText().isEmpty()) {
            if (Integer.parseInt(textoGarantia) < 1 || Integer.parseInt(textoGarantia) > 24) {
                txtGarantia.setText("");
            }
        }
    }//GEN-LAST:event_txtGarantiaKeyReleased

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        //Limitare que caracteres puede ingresar en precio
        char caracter = evt.getKeyChar();
        textoPrecio = txtPrecio.getText();
        if (textoPrecio.length() >= 6) {
            evt.consume();
        } else if (textoPrecio.length() == 0 || textoPrecio.length() == 5) {
            if (caracter == '.') {
                evt.consume();
            }
        }
        ControlPunto(caracter, textoPrecio, evt);
        /*
        Aca hago un caracter del tipo char y pongo el texto del cuadro de texto como un vector de tipo char.
        es lo que hace el metodo toCharArray()
        Luego ocupo una bandera en la cual si hay un punto en dicho arreglo pasa a ser true y termina el bucle (break)
        Si no se mantiene falsa
        Esto para el fin de poder limitar que si va por 4 digito el 5 debe ser un punto y el otro un numero, pero
        si ya hay un punto no debe hacer esa limitacion.
         */
        char[] caracteres = textoPrecio.toCharArray();
        for (int posicion = 0; posicion < caracteres.length; posicion++) {
            if (caracteres[posicion] == '.') {
                flagPunto = true;
                break;
            } else {
                flagPunto = false;
            }
        }

        if (flagPunto == true) {
            if ((caracter < '0' || caracter > '9') && caracter != '.') {
                evt.consume();
            }
        } else {
            if (textoPrecio.length() == 4) {
                if (caracter != '.') {
                    evt.consume();
                }
            } else if ((caracter < '0' || caracter > '9') && caracter != '.') {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtPrecioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioFocusLost
        //Ocupo este porque cuando el usuario quite el focus de aca significa ingreso el valor.
        if (!txtPrecio.getText().isEmpty()) {
            /*
        El metodo math.round() redondea decimales, lo utilizo para siempre tener 2 decimales en lugar de 5 para un precio.
             */
            if (txtPrecio.getText().length() == 6) {
                if (txtPrecio.getText().charAt(2) == '.' || txtPrecio.getText().charAt(1) == '.') {
                    double num = Math.round(Double.parseDouble(txtPrecio.getText()) * 100) / 100.0;
                    txtPrecio.setText("" + num);
                }
            } else {
                double num = Math.round(Double.parseDouble(txtPrecio.getText()) * 100) / 100.0;
                txtPrecio.setText("" + num);
            }
            //Misma validacion para un intervalo de numeros
            textoPrecio = txtPrecio.getText();
            if (!textoPrecio.isEmpty()) {
                if (Double.parseDouble(textoPrecio) < 0.01 || Double.parseDouble(textoPrecio) > 999.99) {
                    txtPrecio.setText("");
                    JOptionPane.showMessageDialog(null, "Campo incorrecto");
                }
            }
        }
    }//GEN-LAST:event_txtPrecioFocusLost

    private void chbPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbPrincipalActionPerformed
        ActivarTxt(chbPrincipal, txtCamaraPrincipal);
    }//GEN-LAST:event_chbPrincipalActionPerformed

    private void chbSecundariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbSecundariaActionPerformed
        ActivarTxt(chbSecundaria, txtCamaraSecundaria);
    }//GEN-LAST:event_chbSecundariaActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        if (flagComboBox != true || txtCpu.getText().isEmpty() || txtPantalla.getText().isEmpty() || txtRam.getText().isEmpty() || txtGarantia.getText().isEmpty() || txtPrecio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete los campos.");
        } else {
            //Inicializo de nuevo la salida para no acumular texto de le ejecucion pasada.
            salida = "El numero de cpu es:  ";
            salida += txtCpu.getText() + "\t\tEl tamanio de pantalla es:  " + txtPantalla.getText() + "\nLa ram es de:  " + txtRam.getText()
                    + "\t\tLa garantia es de:  " + txtGarantia.getText() + "\nSu precio es de:  $" + txtPrecio.getText() + "\t\t y la marca es:  ";
            int eleccionMarca = cmbMarcas.getSelectedIndex();
            switch (eleccionMarca) {
                case 0:
                    salida += "NEXUS\nPosee camara:  ";
                    break;
                case 1:
                    salida += "PIXIE\nPosee camara:  ";
                    break;
                case 2:
                    salida += "LENOVO\nPosee camara:  ";
                    break;
                case 3:
                    salida += "HUAWEI\nPosee camara:  ";
                    break;
                case 4:
                    salida += "SONY\nPosee camara:  ";
                    break;
                case 5:
                    salida += "APPLE\nPosee camara:  ";
                    break;
                case 6:
                    salida += "SAMSUNG\nPosee camara:  ";
                    break;
                case 7:
                    salida += "ALCATEL\nPosee camara:  ";
                    break;
                case 8:
                    salida += "LG\nPosee camara:  ";
                    break;
                default:
                    salida += "OTRA\nPosee camara:  ";
                    break;
            }
            if (chbPrincipal.isSelected()) {
                salida += "Principal de " + txtCamaraPrincipal.getText() + " mpx. ";
            }
            if (chbSecundaria.isSelected()) {
                salida += " Secundaria de " + txtCamaraSecundaria.getText() + " mpx. ";
            }
            if (chbFlash.isSelected()) {
                salida += "Posee flash.";
            }

            txtaPropiedades.setText(salida);
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void txtCamaraPrincipalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCamaraPrincipalKeyTyped
        // Validacion de tamanio y caracteres.
        char caracter = evt.getKeyChar();
        textoCamaraPrincipal = txtCamaraPrincipal.getText();
        ValidacionesTxtCamaras(evt, caracter, textoCamaraPrincipal);
        //Era el mismo codigo para ambos txt de las camaras por lo que lo meti en un metodo.
    }//GEN-LAST:event_txtCamaraPrincipalKeyTyped

    private void txtCamaraSecundariaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCamaraSecundariaKeyTyped
        char caracter = evt.getKeyChar();
        textoCamaraSecundaria = txtCamaraSecundaria.getText();
        ValidacionesTxtCamaras(evt, caracter, textoCamaraSecundaria);
    }//GEN-LAST:event_txtCamaraSecundariaKeyTyped

    private void cmbMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMarcasActionPerformed
        // Uso una bandera para validar que eligio una marca
        flagComboBox = true;
    }//GEN-LAST:event_cmbMarcasActionPerformed

    private void txtCamaraPrincipalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCamaraPrincipalFocusLost
        // Esto es para que si solo pone un numero y el punto se lo lleve a 2.0 por ejemplo
        double num = Math.round(Double.parseDouble(txtCamaraPrincipal.getText()) * 1000) / 1000.0;
        txtCamaraPrincipal.setText("" + num);
    }//GEN-LAST:event_txtCamaraPrincipalFocusLost

    private void txtCamaraSecundariaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCamaraSecundariaFocusLost
        double num = Math.round(Double.parseDouble(txtCamaraSecundaria.getText()) * 1000) / 1000.0;
        txtCamaraSecundaria.setText("" + num);
    }//GEN-LAST:event_txtCamaraSecundariaFocusLost

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JCheckBox chbFlash;
    private javax.swing.JCheckBox chbPrincipal;
    private javax.swing.JCheckBox chbSecundaria;
    private javax.swing.JComboBox<String> cmbMarcas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCamara;
    private javax.swing.JLabel lblCpu;
    private javax.swing.JLabel lblGarantia;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblRam;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCamaraPrincipal;
    private javax.swing.JTextField txtCamaraSecundaria;
    private javax.swing.JTextField txtCpu;
    private javax.swing.JTextField txtGarantia;
    private javax.swing.JTextField txtPantalla;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtRam;
    private javax.swing.JTextArea txtaPropiedades;
    // End of variables declaration//GEN-END:variables
    /*
    Colon pone la declaracion de variables al inicio pero segun vi Zamora lo hace aca.
     */
    private String textoCpu, textoPantalla, textoRam, textoGarantia, textoPrecio, textoCamaraPrincipal, textoCamaraSecundaria, salida;
    private boolean flagPunto, flagComboBox = false;
    private DefaultComboBoxModel cmbMarcasModel = new DefaultComboBoxModel();
}
