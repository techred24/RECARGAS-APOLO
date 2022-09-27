/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import services.Consulta;
import utils.CardReader;
import utils.PrintTicket;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author frafa
 */
public class Recargas extends javax.swing.JFrame {

    /**
     * Creates new form Recargas
     */
    private static Map<Object, Object> userInformation;
    private static  Map<Object, Object> dataConfiguracionTarjeta;
    private static Map<Object, Object> configuracionTarjeta;
    private ArrayList<Object> sectores;
    private String[] tiposTarjeta;
    private String[] clavesTipoTarjeta;
    private int indiceClaveTipoTarjeta;
    public Recargas(Map<Object,Object> userData) {
        this.setTitle("Apolo Recargas");
        userInformation = userData;
        //new Consulta((String) userInformation.get("token"));
        initComponents();
        final String query = "configuraciontarjetas/pc";
        buscarConfiguracionTarjetas();
    }
    private void buscarConfiguracionTarjetas() {
        System.out.println("INSIDE");
        try {
            //final String query = "config/tarjeta/lector";
            final String query = "configuraciontarjetas/pc";
            Map<Object, Object> configTarjetaResponse = Consulta.sendGet(query);
            /*if ((int) configTarjetaResponse.get("code") != 200) {
                JOptionPane.showMessageDialog(null, "Algo salio mal");
                this.dispose();
                return;
            }*/
            //System.out.println(configTarjetaResponse + "LA RESPUESTA");
            dataConfiguracionTarjeta = (Map<Object, Object>) configTarjetaResponse.get("data");
            configuracionTarjeta = (Map<Object, Object>) dataConfiguracionTarjeta.get("data");
            System.out.println(configuracionTarjeta + " <------ CONFIGURACION TARJETA");
            sectores = (ArrayList<Object>) configuracionTarjeta.get("sectores");
            //System.out.println(configuracionTarjeta.get("subsidios"));

            List subsidios = (List) configuracionTarjeta.get("subsidios");
            //System.out.println(subsidios + " <------- SUBSIDIOS");
            tiposTarjeta = new String[subsidios.size()];
            clavesTipoTarjeta = new String[subsidios.size()];
            //System.out.println(subsidios.size());
            //System.out.println("TAMANIO DE LA LISTA DE SUBSIDIOS");
            for (short i = 0; i < subsidios.size(); i++) {
                tiposTarjeta[i] =(String) ((Map<Object, Object>) subsidios.get(i)).get("nombre");
                clavesTipoTarjeta[i] = (String) ((Map<Object, Object>) subsidios.get(i)).get("clave");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void bloquearEscritura () {
        tipoTarjeta.setEnabled(false);
        agregarSaldoButton.setEnabled(false);
        guardarButton.setEnabled(false);
        cerrarTarjetaButton.setEnabled(false);
        nombre.setEnabled(false);
        apellidoPaterno.setEnabled(false);
        apellidoMaterno.setEnabled(false);
        celular.setEnabled(false);
        saldoDisponible.setEnabled(false);
        saldoAgregar.setEnabled(false);
        saldoCortesia.setEnabled(false);
        folio.setEnabled(false);
        id.setEnabled(false);
    }
    private void limpiarCampos () {
        nombre.setText("");
        apellidoPaterno.setText("");
        apellidoMaterno.setText("");
        celular.setText("");
        saldoDisponible.setText("");
        saldoAgregar.setText("");
        saldoCortesia.setText("");
        folio.setText("");
        id.setText("");
        tipoTarjeta.setModel(new DefaultComboBoxModel<>());
    }
    private void initComponents() {

        leerTarjetaButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tipoTarjeta = new javax.swing.JComboBox<>();
        agregarSaldoButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        cerrarTarjetaButton = new javax.swing.JButton();
        reporteButton = new javax.swing.JButton();
        nombre = new javax.swing.JTextField();
        apellidoPaterno = new javax.swing.JTextField();
        apellidoMaterno = new javax.swing.JTextField();
        celular = new javax.swing.JTextField();
        saldoDisponible = new javax.swing.JTextField();
        saldoAgregar = new javax.swing.JTextField();
        saldoCortesia = new javax.swing.JTextField();
        folio = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        bloquearEscritura();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leerTarjetaButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        leerTarjetaButton.setText("LEER TARJETA");
        leerTarjetaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerTarjetaButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("RECARGAS MATACOCUITE");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("NOMBRE:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("APELLIDO PATERNO:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("APELLIDO MATERNO:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("CELULAR:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("SALDO DISPONIBLE:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("SALDO A AGREGAR:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setText("DE LOS CUALES SON CORTESIA:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setText("FOLIO:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setText("ID:");

        tipoTarjeta.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        agregarSaldoButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        agregarSaldoButton.setText("AGREGAR SALDO");
        agregarSaldoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarSaldoButtonActionPerformed();
            }
        });
        guardarButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        guardarButton.setText("GUARDAR");
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { guardarButtonActionPerformed(evt); }
        });
        cerrarTarjetaButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cerrarTarjetaButton.setText("CERRAR TARJETA");
        cerrarTarjetaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { cerrarTarjetaButtonActionPerformed(); }
        });

        reporteButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        reporteButton.setText("REPORTE RECARGAS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(leerTarjetaButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(agregarSaldoButton)
                                .addGap(18, 18, 18)
                                .addComponent(guardarButton))
                            .addComponent(reporteButton)
                            .addComponent(tipoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombre)
                                    .addComponent(apellidoPaterno)
                                    .addComponent(apellidoMaterno)
                                    .addComponent(celular)
                                    .addComponent(saldoDisponible)
                                    .addComponent(saldoAgregar)
                                    .addComponent(saldoCortesia)
                                    .addComponent(folio)
                                    .addComponent(id)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(cerrarTarjetaButton)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(leerTarjetaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(apellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(apellidoMaterno))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(saldoDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saldoAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(saldoCortesia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(folio)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(tipoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarSaldoButton)
                    .addComponent(guardarButton)
                    .addComponent(cerrarTarjetaButton))
                .addGap(18, 18, 18)
                .addComponent(reporteButton)
                .addGap(72, 72, 72))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private  void habilitaDesabilitaBotonesAgregarSaldo () {
        agregarSaldoButton.setEnabled(!agregarSaldoButton.isEnabled());
        guardarButton.setEnabled(!guardarButton.isEnabled());
        cerrarTarjetaButton.setEnabled(!cerrarTarjetaButton.isEnabled());
    }
    private void activarTarjeta () {

    }
    private void  recargarTarjeta () {
        final String numericRegeX = "^[0-9]+([.][0-9]+)?$";
        final Pattern pattern = Pattern.compile(numericRegeX);
        final Matcher agregarSaldoMatcher = pattern.matcher(saldoAgregar.getText());
        final Matcher cortesiaSaldoMatcher = pattern.matcher(saldoCortesia.getText());
        if (saldoAgregar.getText().isBlank() || saldoAgregar.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "NO SE HA AGREGADO SALDO A LA TARJETA");
            return;
        }
        if (!agregarSaldoMatcher.matches()) {
            JOptionPane.showMessageDialog(null, "SALDO A AGREGAR NO VALIDO");
            return;
        }
        if (saldoCortesia.getText().isBlank()) {
            saldoCortesia.setText("0");
        }
        InetAddress localMachine = null;
        try {
            localMachine = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(userInformation.get("usuario") + " <------------ EL USUARIO");
        String idUsuario = (String) ((Map<Object, Object>) userInformation.get("usuario")).get("_id");
        //System.out.println(userInformation.get("usuario"));
        System.out.println(idUsuario + " LA INFORMACION DEL USUARIO");
        try {
            String idTarjeta1 = CardReader.read((short) 1, sectores);
            String idTarjeta2 = CardReader.read((short) 2, sectores);
            System.out.println(idTarjeta1 + "  <<<---- EL ID 1");
            System.out.println(idTarjeta2 + "  <<<---- EL ID 2");
            idTarjeta1 = idTarjeta1.substring(0,16);
            idTarjeta2 = idTarjeta2.substring(0,16);
            String idTarjeta = idTarjeta1 + idTarjeta2;
            idTarjeta = idTarjeta.replaceAll("\u0000.*","");
            System.out.println(idTarjeta + "  <<<---- EL ID DE LA TARJETA");
            System.out.println("Leyendo bloque 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        final Date date = new Date();
        Map body = new HashMap();
        float saldoActualizado = Float.parseFloat(saldoAgregar.getText()) + Float.parseFloat(saldoDisponible.getText()) + Float.parseFloat(saldoCortesia.getText());
        try {
            String res = CardReader.read((short) 1, sectores);
            System.out.println(res + " <<--- RESPONSE");
            if (res.length() > 0) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        body.put("saldo", saldoActualizado);
        body.put("saldocortesia", saldoCortesia.getText());
        body.put("fechaaltaLector", dateFormat.format(date));
        body.put("usuario", idUsuario);
        body.put("usuarioPc", System.getProperty("user.name"));
        body.put("tarjeta", ""); // La info viene en el Bloque 1 de la tarjeta pero varia con el programa actual
        body.put("idMaquina", localMachine.getHostName());
        String saldoActualizadoString = Float.toString(saldoActualizado);
        System.out.println(saldoActualizadoString.length() + " <-- LA LONGITUD DEL STRING SALDO");
        for (int i = saldoActualizadoString.length() - 1; i < 15; i++) {
            saldoActualizadoString += " ";
            //System.out.println(saldoActualizadoString.length() + " <---- LONGITUD EN LOOP");
        }
        //System.out.println(saldoActualizadoString.length() + " <------- La longitud del string a escribir");
        try {
            Map<Object,Object> respuesta = Consulta.sendPost("recargasaldo/", body);
            System.out.println(respuesta + " <-------- LA RESPUESTA DEL SERVIDOR");
            String response = CardReader.write((short) 20, saldoActualizadoString, sectores);
            String responseReader = CardReader.read((short) 20, sectores);
            System.out.println(responseReader + " <-- LA RESPUESTA DE LA LECTURA");
            //float saldoParaCortesia = Float.parseFloat(cortesiaField.getText());
            //float saldoPagadoOperacion = Float.parseFloat(saldoAgregar.getText()) - Float.parseFloat(saldoCortesia.getText());
            String saldoPagado = saldoAgregar.getText();
            float recargaTotal = Float.parseFloat(saldoCortesia.getText()) + Float.parseFloat(saldoAgregar.getText());
            String nombreUsuario = (String) ((Map<Object,Object>) userInformation.get("usuario")).get("nombre");
            //System.out.println(userInformation + "   <<<------ EL NOMBRE DE USUARIO");
            //Map<String, String> ticketData = new HashMap<>();
            Map<String, String> ticketData = new HashMap<>();
            ticketData.put("puntoexp", localMachine.getHostName());
            ticketData.put("vendedor", nombreUsuario);
            ticketData.put("idtarjeta", "---");
            ticketData.put("tipotarjeta",  tipoTarjeta.getSelectedItem().toString());
            ticketData.put("telefono", celular.getText());
            ticketData.put("operacion", "RECARGA");
            ticketData.put("saldoanterior", saldoDisponible.getText());
            ticketData.put("saldopagado", saldoPagado);
            ticketData.put("saldocortesia", saldoCortesia.getText());
            ticketData.put("recargatotal", String.valueOf(recargaTotal));
            new PrintTicket(ticketData);
            cerrarTarjetaButtonActionPerformed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void guardarButtonActionPerformed (java.awt.event.ActionEvent evt) {
        System.out.println(nombre.isEnabled() + " <- Text Field nombre habilitado, tarjeta nueva");
        if (nombre.isEnabled()) {
            activarTarjeta();
            return;
        }
        recargarTarjeta();
    }
    private void cerrarTarjetaButtonActionPerformed() {
        limpiarCampos();
        habilitaDesabilitaBotonesAgregarSaldo();
        leerTarjetaButton.setEnabled(true);
    }
    private void agregarSaldoButtonActionPerformed() {//GEN-FIRST:event_agregarSaldoButtonActionPerformed
        JTextField recargarSaldoTextField = new javax.swing.JTextField();
        JTextField saldoCortesiaTextField = new javax.swing.JTextField();
        String[] opciones = {"Recargar", "Cancelar"};
        JPanel basePanel = new JPanel();
        basePanel.setOpaque(true);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 2, 5, 5));

        centerPanel.setOpaque(true);


        JLabel recargarSaldoLabel = new JLabel("Ingresar saldo a recargar:");
        recargarSaldoLabel.setFont(new java.awt.Font("Segoe UI", 0, 24));

        JLabel saldoCortesiaLabel = new JLabel("Ingresar saldo de cortesia:");
        saldoCortesiaLabel.setFont(new java.awt.Font("Segoe UI", 0, 24));

        recargarSaldoTextField.setFont(new java.awt.Font("Segoe UI", 0, 24));
        saldoCortesiaTextField.setFont(new java.awt.Font("Segoe UI",0,24));

        centerPanel.add(recargarSaldoLabel);
        centerPanel.add(recargarSaldoTextField);
        centerPanel.add(saldoCortesiaLabel);
        centerPanel.add(saldoCortesiaTextField);
        basePanel.add(centerPanel);
        int seleccionada = JOptionPane.showConfirmDialog(
                null, basePanel, "Recargar saldo: "
                , JOptionPane.OK_CANCEL_OPTION
                , JOptionPane.PLAIN_MESSAGE);
        System.out.println(seleccionada + " Seleccionada"); // zero if for ok and 2 is for cancel in variable seleccionada
        if (seleccionada == JOptionPane.OK_OPTION) {
            final String numericRegEx = "^[0-9]+([.][0-9]+)?$";
            final Pattern pattern = Pattern.compile(numericRegEx);
            final Matcher recargarSaldoRegExMatcher = pattern.matcher(recargarSaldoTextField.getText());
            final Matcher saldoCortesiaRegExMatcher = pattern.matcher(saldoCortesiaTextField.getText());
            if (recargarSaldoRegExMatcher.matches()) {
                saldoAgregar.setText(recargarSaldoTextField.getText());
            }
            if (saldoCortesiaRegExMatcher.matches()) {
                saldoCortesia.setText(saldoCortesiaTextField.getText());
            }
        }
    }//GEN-LAST:event_agregarSaldoButtonActionPerformed
    private void leerTarjetaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerTarjetaButtonActionPerformed
        short[] bloquesParaAccesar = new short[]{12, 13, 14, 20, 10, 0, 16};
        String nombre_y_telefono = "";

        String soloNombre = "";
        String id;
        try {
            for (short i = 0; i < bloquesParaAccesar.length; i++) {
                short bloque = bloquesParaAccesar[i];
                String response = CardReader.read(bloque, sectores);
                //CardReader.write(bloque, "2299556644 Wendy", configuracionTarjeta);
                //System.out.println("Reading block 12"); 20220407T013812Z

                if (bloquesParaAccesar[i] != 0) {
                    response = response.substring(0, 16);
                }
                //[\u0068] una h minuscula
                if (bloquesParaAccesar[i] != 0) {
                    response = response.replaceAll("\u0000.*","");
                }
                if (i <= 2 ) {
                    nombre_y_telefono += response;
                }
                if (bloquesParaAccesar[i] == 20) {
                    this.saldoDisponible.setText(response);
                }
                if (bloquesParaAccesar[i] == 10) {
                    this.folio.setText(response);
                }
                if (bloquesParaAccesar[i] == 16) {
                    this.tipoTarjeta.setModel(new DefaultComboBoxModel<>(this.tiposTarjeta));
                    indiceClaveTipoTarjeta = Arrays.asList(clavesTipoTarjeta).indexOf(response);
                    this.tipoTarjeta.setSelectedIndex(indiceClaveTipoTarjeta);
                }
                if (bloquesParaAccesar[i] == 0) {

                }
            }
            final String[] informacionUsuario = nombre_y_telefono.split(" ");
            this.celular.setText(informacionUsuario[0]);
            for (short j = 1; j < informacionUsuario.length-2; j++) {
                soloNombre += " " + informacionUsuario[j];
                soloNombre = soloNombre.trim();
            }
            this.nombre.setText(soloNombre);
            this.apellidoPaterno.setText(informacionUsuario[informacionUsuario.length-2]);
            this.apellidoMaterno.setText(informacionUsuario[informacionUsuario.length-1]);

            habilitaDesabilitaBotonesAgregarSaldo();
            this.leerTarjetaButton.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_leerTarjetaButtonActionPerformed
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
            java.util.logging.Logger.getLogger(Recargas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recargas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recargas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recargas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Recargas().setVisible(true);
            }
        });
    }

    private javax.swing.JButton agregarSaldoButton;
    private javax.swing.JTextField apellidoMaterno;
    private javax.swing.JTextField apellidoPaterno;
    private javax.swing.JTextField celular;
    private javax.swing.JButton cerrarTarjetaButton;
    private javax.swing.JTextField folio;
    private javax.swing.JButton guardarButton;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton leerTarjetaButton;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton reporteButton;
    private javax.swing.JTextField saldoAgregar;
    private javax.swing.JTextField saldoCortesia;
    private javax.swing.JTextField saldoDisponible;
    private javax.swing.JComboBox<String> tipoTarjeta;

}
