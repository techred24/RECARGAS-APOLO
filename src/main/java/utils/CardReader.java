package utils;

import utils.reader.ACR122UReaderHelper;
import utils.reader.util.ACR122Util;

import javax.smartcardio.CardException;
import java.util.ArrayList;
import java.util.Map;

public class CardReader {
    private static ACR122UReaderHelper reader = ACR122UReaderHelper.getInstance();
    private static ACR122Util readerUtil = ACR122Util.getInstance();

    public static String read (short bloque, Map<Object, Object> configuracionTarjeta) throws CardException {
        final int sector = bloque / 4;
        //System.out.println(bloque%4 + " El bloque en el sector del 0 al 3");
        //System.out.println(bloque/4 + " El sector en el que se encuentra el bloque");
        ArrayList<Object> sectores = (ArrayList<Object>) ((Map<Object, Object>) configuracionTarjeta.get("config")).get("sectores");
        //System.out.println(sectores.size());
        //System.out.println(sectores.get(3));
        Map<Object, Object> sectorMap = (Map<Object, Object>) sectores.get(sector);
        //System.out.println(sectorMap.get("keyA"));
        //System.out.println(sectores.get(3).getClass().getName());
        String keyString = (String) sectorMap.get("keyA");
        //String keyString = "C9855A4DA3E0";
        int len = keyString.length();
        byte[] authKeyData = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            authKeyData[i / 2] = (byte) ((Character.digit(keyString.charAt(i), 16) << 4)
                    + Character.digit(keyString.charAt(i+1), 16));
        }
        reader.connectReader();
        reader.connectCard(null);
        byte[] response = reader.readCardBlock(authKeyData, readerUtil.getAuthCmdForkeyA(), (int) bloque);
        String stringResponse = new String(response);
        return stringResponse;
        // Returns 2 bytes of array(63,00) for failure
    }
    public static String write (short bloque, String newData, Map<Object, Object> configuracionTarjeta) throws CardException {
        if (newData.length() != 16) {
            return "String must be 16 in length";
        }
        final int sector = bloque / 4;
        ArrayList<Object> sectores = (ArrayList<Object>) ((Map<Object, Object>) configuracionTarjeta.get("config")).get("sectores");
        Map<Object, Object> sectorMap = (Map<Object, Object>) sectores.get(sector);
        String keyString = (String) sectorMap.get("keyB");
        int len = keyString.length();
        byte[] authKeyData = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            authKeyData[i / 2] = (byte) ((Character.digit(keyString.charAt(i), 16) << 4)
                    + Character.digit(keyString.charAt(i+1), 16));
        }
        byte[] data = newData.getBytes();
        System.out.println(data.length);
        System.out.println("La longitud del arreglo de los datos a escribir");
        reader.connectReader();
        reader.connectCard(null);
        byte[] response = reader.writeDataIntoCard(authKeyData, readerUtil.getAuthCmdForkeyB(), bloque, data);
        String stringResponse = new String(response);
        return stringResponse;
        // Returns 2 bytes of array(90,00) for success, Returns 2 bytes of array(63,00) for failure
    }

}
