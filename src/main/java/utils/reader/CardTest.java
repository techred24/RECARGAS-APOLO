
package utils.reader;

import utils.reader.util.ACR122Util;
//import utils.reader.util.PrintTicket;

import javax.smartcardio.CardException;

import java.util.HashMap;
import java.util.Map;


public class CardTest {
    public static void main(String []args) throws CardException {
        ACR122UReaderHelper reader = ACR122UReaderHelper.getInstance();
        ACR122Util readerUtil = ACR122Util.getInstance();

        //byte []authKeyData = new byte[]{(byte)0x01,(byte)0x02,(byte)0x03,(byte)0x04,(byte)0x05,(byte)0x06};
        //byte []data = new byte[]{(byte)0x09,(byte)0x09,(byte)0x09,(byte)0x09,(byte)0x09,(byte)0x09,(byte)0x09,(byte)0x09,(byte)0x09,   (byte)0x09,(byte)0x09,(byte)0x09,(byte)0x09,(byte)0x09,(byte)0x09,(byte)0x09};
        //String keyString = "C9855A4DA3E0";
        String keyA = "C9855A4DA3E0";
        String keyB = "ACD0BB44CDF5";

        String newKeyA = "FFFFFFFFFFF2";
        String accessBits = "08778F69";
        String newKeyB = "FFFFFFFFFFFF";
        String nuevosDatosParaIntroducir = keyA + accessBits + keyB;

        String keyString = "C9855A4DA3E0";
        //byte[] bytes = keyString.getBytes();
        String myInfoToWrite = "2295935631 RAFA ";
        byte[] byteToWriteInCard = myInfoToWrite.getBytes();
        int len = keyString.length();
        byte[] authKeyData = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            authKeyData[i / 2] = (byte) ((Character.digit(keyString.charAt(i), 16) << 4)
                    + Character.digit(keyString.charAt(i+1), 16));
        }
        int logitudData = nuevosDatosParaIntroducir.length();
        byte[] data = new byte[logitudData / 2];
        for (int i = 0; i < logitudData; i += 2) {
            data[i / 2] = (byte) ((Character.digit(nuevosDatosParaIntroducir.charAt(i), 16) << 4)
                                + Character.digit(nuevosDatosParaIntroducir.charAt(i+1), 16));
        }
        reader.connectReader();
        reader.connectCard(null);
        reader.getUID(); // Returns UID of the card which is placed on the readert.
        //reader.readCardUsingDefaultKey(1); // Returns 16 bytes of array for success, Returns 2 bytes of array(63,00) for failure
        //byte[] response = reader.readCardBlock(authKeyData, readerUtil.getAuthCmdForkeyA(), 12); // Returns 16 bytes of array for success, Returns 2 bytes of array(63,00) for failure
        //byte[] responseOfWriting = reader.writeDataIntoCard(authKeyData, readerUtil.getAuthCmdForkeyA(), 12, data); // Returns 2 bytes of array(90,00) for success, Returns 2 bytes of array(63,00) for failure

        byte[] responseOfWriting = reader.writeDataIntoCard(authKeyData, readerUtil.getAuthCmdForkeyB(), 15, data);
        //byte[] responseReading = reader.readCardBlock();

        //String stringResponse = new String(response);
        //System.out.println(stringResponse);


        Map<String, String> ticketData = new HashMap<>();
        ticketData.put("puntoexp", "AQUI");
        ticketData.put("vendedor", "Isis");

        ticketData.put("idtarjeta", "01");

        ticketData.put("tipotarjeta", "Estudiante" );
        ticketData.put("telefono", "2299554488");
        ticketData.put("operacion", "Recarga");
        ticketData.put("saldoanterior", "10");
        ticketData.put("saldopagado", "20");
        ticketData.put("saldocortesia", "10");
        ticketData.put("recargatotal", "30");
            //new PrintTicket(ticketData);
    }
}