package com.buzzbike.bjss.qrandnfcdemo;

import android.nfc.NdefRecord;
import java.io.UnsupportedEncodingException;

public class Reader {

  public static String readText(NdefRecord record) throws UnsupportedEncodingException {
    byte[] payload = record.getPayload();
    String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
    int languageCodeLength = payload[0] & 0063;
    return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1,
        textEncoding);
  }
}
