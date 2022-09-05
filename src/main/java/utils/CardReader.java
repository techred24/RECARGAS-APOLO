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
        //final int sector = bloque / 4;
        //bloque%4
        //System.out.println(((Map<Object, Object>) configuracionTarjeta.get("config")).get("sectores"));
        ArrayList<Object> sectores = (ArrayList<Object>) ((Map<Object, Object>) configuracionTarjeta.get("config")).get("sectores");
        //System.out.println(sectores.size());
        //System.out.println(sectores.get(3));
        Map<Object, Object> sectorMap = (Map<Object, Object>) sectores.get(3);
        System.out.println(sectorMap.get("keyA"));
        // System.out.println(sectores.get(3).getClass().getName());
        String keyString = "C9855A4DA3E0";
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
    public static String write (short bloque, String newData) throws CardException {
        String keyString = "ACD0BB44CDF5";
        int len = keyString.length();
        byte[] authKeyData = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            authKeyData[i / 2] = (byte) ((Character.digit(keyString.charAt(i), 16) << 4)
                    + Character.digit(keyString.charAt(i+1), 16));
        }

        int logitudData = newData.length();
        byte[] data = new byte[logitudData / 2];
        for (int i = 0; i < logitudData; i += 2) {
            data[i / 2] = (byte) ((Character.digit(newData.charAt(i), 16) << 4)
                    + Character.digit(newData.charAt(i+1), 16));
        }

        reader.connectReader();
        reader.connectCard(null);
        byte[] response = reader.writeDataIntoCard(authKeyData, readerUtil.getAuthCmdForkeyB(), 12, data);
        String stringResponse = new String(response);
        return stringResponse;
        // Returns 2 bytes of array(90,00) for success, Returns 2 bytes of array(63,00) for failure
    }

}
