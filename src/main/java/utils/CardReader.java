package utils;
import com.mycompany.cardtest.ACR122UReaderHelper;
import com.mycompany.cardtest.util.ACR122Util;
//import javax.smartcardio.CardException;

import java.util.HashMap;
import java.util.Map;
public class CardReader {
    ACR122UReaderHelper reader = ACR122UReaderHelper.getInstance();
    ACR122Util readerUtil = ACR122Util.getInstance();
    reader.connectReader();
    reader.connectCard(null);
    public String read () {
        byte[] response = reader.readCardBlock(authKeyData, readerUtil.getAuthCmdForkeyA(), 12);
        String stringResponse = new String(response);
        return stringResponse;
    }
    public write () {

    }

}
