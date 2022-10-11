package utils;

import utils.reader.ACR122UReaderHelper;
import utils.reader.util.ACR122Util;

import javax.smartcardio.CardException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class CardReader {
    private static ACR122UReaderHelper reader = ACR122UReaderHelper.getInstance();
    private static ACR122Util readerUtil = ACR122Util.getInstance();
    public static String byteToHex(byte num) {
        /*String tmpHex;
        tmpHex = Integer.toHexString(((Byte)num).intValue() & 0xFF).toUpperCase();
        //For single character hex
        if (tmpHex.length() == 1)  tmpHex = "0" + tmpHex;
        //tmpStr += " " + tmpHex;
        System.out.println(tmpHex);*/
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }
    public static String read (short bloque, ArrayList<Object> sectoresArgumento) throws CardException {
        final int sector = bloque / 4;
        //System.out.println(bloque%4 + " El bloque en el sector del 0 al 3");
        //System.out.println(bloque/4 + " El sector en el que se encuentra el bloque");
        //ArrayList<Object> sectores = (ArrayList<Object>) ((Map<Object, Object>) configuracionTarjeta.get("config")).get("sectores");
        ArrayList<Object> sectores = sectoresArgumento;
        Map<Object, Object> sectorMap = (Map<Object, Object>) sectores.get(sector);
        String keyString = (String) sectorMap.get("keyA");
        int len = keyString.length();
        byte[] authKeyData = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            authKeyData[i / 2] = (byte) ((Character.digit(keyString.charAt(i), 16) << 4)
                    + Character.digit(keyString.charAt(i+1), 16));
        }
        reader.connectReader();
        reader.connectCard(null);
        byte[] response = reader.readCardBlock(authKeyData, readerUtil.getAuthCmdForkeyA(), (int) bloque);
        if (bloque == 0) {
            byte[] UID = reader.getUID();
            StringBuffer hexStringBuffer = new StringBuffer();
            for (short j = 0; j < UID.length; j++) {
                hexStringBuffer.append(byteToHex(UID[j]));
            }
            return hexStringBuffer.toString().substring(0,8);
        }
        String stringResponse = new String(response);
        /*System.out.println(new String(response, StandardCharsets.UTF_8));*/
        return stringResponse;
        // Returns 2 bytes of array(63,00) for failure
    }
    public static String write (short bloque, String newData, ArrayList<Object> sectoresArgumento) throws CardException {
        if (newData.length() != 16) {
            return "String must be 16 in length";
        }
        final int sector = bloque / 4;
        //ArrayList<Object> sectores = (ArrayList<Object>) ((Map<Object, Object>) configuracionTarjeta.get("config")).get("sectores");
        ArrayList<Object> sectores = sectoresArgumento;
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
    public static void reassignAllSectorKeys(ArrayList sectoresArgumento) {
        // 3 ... 63
        try {
            for (int bloque = 3; bloque <= 63; bloque += 4) {
                System.out.println(bloque + "  <--- EL INCREMENTO DE I PARA ACCEDER A LOS SECTORES");
                final int sector = bloque / 4;
                ArrayList<Object> sectores = sectoresArgumento;
                Map<Object, Object> sectorMap = (Map<Object, Object>) sectores.get(sector);
                //String keyAold = (String) sectorMap.get("keyAold");
                String keyBold = (String) sectorMap.get("keyBold");
                String keyA = (String) sectorMap.get("keyA");
                String keyB = (String) sectorMap.get("keyB");
                String accessBits = (String) sectorMap.get("accessBits");
                //System.out.println(keyA + " <<<<<<KEY A");
                //System.out.println(keyB + " <<<<<<KEY B");
                //System.out.println(accessBits + " <<<<<<accessBits");
                //System.out.println(sectorMap + "  <<<<<SECTOR");

                String keyBlock = keyA + accessBits + keyB;
                //System.out.println(keyBlock + " < keyblock");



                int len = keyBold.length();
                byte[] authKeyData = new byte[len / 2];
                for (int i = 0; i < len; i += 2) {
                    authKeyData[i / 2] = (byte) ((Character.digit(keyBold.charAt(i), 16) << 4)
                            + Character.digit(keyBold.charAt(i+1), 16));
                }

                int longKeys =keyBlock.length();
                byte[] data = new byte[longKeys / 2];
                for (int i = 0; i < longKeys; i += 2) {
                    data[i / 2] = (byte) ((Character.digit(keyBlock.charAt(i),16) << 4)
                            + Character.digit(keyBlock.charAt(i+1),16));
                }
                /*StringBuffer hexStringBuffer = new StringBuffer();
                for (short j = 0; j < data.length; j++) {
                    hexStringBuffer.append(byteToHex(data[j]));
                }*/
                //System.out.println(hexStringBuffer.toString().toUpperCase() + "  <<< LO QUE SE MANDA A ESCRIBIR");
                reader.connectReader();
                reader.connectCard(null);
                //byte[] response = reader.readCardBlock(authKeyData, readerUtil.getAuthCmdForkeyA(), bloque);
                byte[] response = reader.writeDataIntoCard(authKeyData, readerUtil.getAuthCmdForkeyB(), bloque, data);
                String arrayResponse = "";
                for (int i = 0; i < response.length; i++) {
                    arrayResponse += String.valueOf((int) response[i]);
                    arrayResponse += "-";
                }
                System.out.println(arrayResponse + " <<EXITO O FALLO. BLOQUE: " + bloque);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Returns 2 bytes of array(63,00) for failure
    }
}
