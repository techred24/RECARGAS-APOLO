package utils;

import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class PrintTicket {
    public PrinterMatrix printer;
    public Extenso e;
    public boolean tuvoExitoImpresion = false;

    public PrintTicket(Map<String, String> ticketData) {
        try {
            this.printer = new PrinterMatrix();
            this.e = new Extenso();

            e.setNumber(20.30);
            // ALTO - ANCHO
            this.printer.setOutSize(27, 70);

            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");

            printer.printTextWrap(0, 1, 1, 50, "                  APOLO PAY");
            printer.printTextWrap(1, 1, 0, 50, "Sistema de Prepago de Pasaje de Autobus");
            printer.printTextWrap(2, 1, 1, 50, "---------------------------------------------");
            printer.printTextWrap(3, 1, 1, 50, "FECHA EXP: " + dtf2.format(LocalDateTime.now()));
            printer.printTextWrap(4, 1, 1, 50, "PUNTO EXP: " + ticketData.get("puntoexp").toUpperCase());
            printer.printTextWrap(5, 1, 1, 50, "VENDEDOR: " + ticketData.get("vendedor").toUpperCase());

            printer.printTextWrap(7, 1, 1, 50, "ID TARJETA: " + ticketData.get("idtarjeta"));
            printer.printTextWrap(8, 1, 1, 50, "TIPO TARJETA: " + ticketData.get("tipotarjeta"));
            printer.printTextWrap(9, 1, 1, 50, "TEL. MOVIL: " + ticketData.get("telefono"));

            printer.printTextWrap(11, 1, 1, 50, "OPERACION: " + ticketData.get("operacion"));
            printer.printTextWrap(12, 1, 1, 50, "SALDO ANTERIOR: $" + ticketData.get("saldoanterior"));
            printer.printTextWrap(13, 1, 1, 50, "SALDO PAGADO: $" + ticketData.get("saldopagado"));
            printer.printTextWrap(14, 1, 1, 50, "SALDO CORTESIA: $" + ticketData.get("saldocortesia"));

            printer.printTextWrap(16, 1, 1, 50, "RECARGA TOTAL: $" + ticketData.get("recargatotal"));
            printer.printTextWrap(17, 1, 1, 50, "---------------------------------------------");
            printer.printTextWrap(18, 1, 1, 50, "PARA CUALQUIER ACLARACION ACUDIR AL MODULO DE");
            printer.printTextWrap(19, 1, 1, 50, "      EXPEDICION CON ESTE COMPROBANTE");
            // FILA -COLUMNA
            //printer.printTextLinCol( 1, 3, "OK" );
            //printer.toFile("C:\\Users\\frafa\\Desktop\\Proyectos\\apolorecargas\\impresion.txt");
            printer.toFile(System.getProperty("user.home") + "\\Documents\\ticket.txt");
            //C:\Users\frafa\Desktop\Proyectos\apolorecargas\impresion.txt
            FileInputStream inputStream = null;
            try {
                //inputStream = new FileInputStream("C:\\Users\\frafa\\Desktop\\Proyectos\\apolorecargas\\impresion.txt");
                inputStream = new FileInputStream(System.getProperty("user.home") + "\\Documents\\ticket.txt");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                //JOptionPane.showMessageDialog(null, "Error al guardar");
            }
            if (inputStream == null) {
                return;
            }

            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc document = new SimpleDoc(inputStream, docFormat, null);
            PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
            PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

            if (defaultPrintService != null) {
                DocPrintJob printJob = defaultPrintService.createPrintJob();
                try {
                    printJob.print(document, attributeSet);
                    tuvoExitoImpresion = true;
                    System.out.println("IMPRIMIENDO-----------------------------------------");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.err.println("No existen impresoras instaladas");
            }
            inputStream.close();
        } catch (Exception e) {
            //System.out.println(e);
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error al imprimir ticket");
        }
    }

}